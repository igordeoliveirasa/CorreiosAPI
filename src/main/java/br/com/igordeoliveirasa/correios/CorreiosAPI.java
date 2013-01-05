/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.igordeoliveirasa.correios;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 *
 * @author igor
 */
public class CorreiosAPI {
    private static String requestZipCodePage(String zipCode) throws Exception {
        System.setProperty("http.agent", "");
        
        String urlString = "http://www.buscacep.correios.com.br/servicos/dnec/consultaEnderecoAction.do?TipoCep=ALL&semelhante=N&cfm=1&Metodo=listaLogradouro&TipoConsulta=relaxation&StartRow=1&EndRow=10&relaxation=" + zipCode;
        System.out.println(urlString);
        URL url = new URL(urlString);
        
        
        HttpURLConnection connection = (HttpURLConnection)url.openConnection();
        StringBuilder sb = new StringBuilder();

        // find the cookies in the response header from the first request
        List<String> cookies = connection.getHeaderFields().get("Set-Cookie");
        if (cookies != null) {
            for (String cookie : cookies) {
                if (sb.length() > 0) {
                    sb.append("; ");
                }
                String value = cookie.split(";")[0];
                sb.append(value);
            }
        }
        String cookieHeader = sb.toString();

        BufferedReader br = new BufferedReader(new InputStreamReader(connection.getInputStream()));
        
        urlString = "http://www.buscacep.correios.com.br/servicos/dnec/detalheCEPAction.do?Metodo=detalhe&Posicao=1&TipoCep=2&CEP=" + zipCode;
        System.out.println(urlString);
        url = new URL(urlString);
        connection = (HttpURLConnection)url.openConnection();
        connection.setRequestProperty("Cookie", cookieHeader);
        connection.connect();
        br = new BufferedReader(new InputStreamReader(connection.getInputStream()));
        
        String content = "";
        String inputLine;
        while ((inputLine = br.readLine()) != null) 
            content += inputLine + "\n";
        
        return content;
    }
        
    private static ZIPCodeAddress parse(String content) {
        ZIPCodeAddress ret = new ZIPCodeAddress();
        
        Pattern pattern = Pattern.compile("value\\\">(.+)</td>");
        Matcher matcher = pattern.matcher(content);
        
        int i = 1;
        while (matcher.find()) {
            
            switch (i) {
                case 1:
                    String street = matcher.group(1); 
                    ret.setStreet(street);
                    break;
                case 2:
                    ret.setNeighborhood(matcher.group(1));
                    break;
                case 3:
                    String v = matcher.group(1);
                    String city = v.substring(0,v.indexOf("/"));
                    String stateAcronym = v.substring(v.indexOf("/")+1);
                    String state = StatesMap.states.get(stateAcronym.toUpperCase());
                    ret.setCity(city);
                    ret.setStateAcronym(stateAcronym);
                    ret.setState(state);
                    ret.setCountry("Brasil");
                    break;
                case 4:
                    ret.setZipCode(matcher.group(1));
                    break;
            }
            i++;
        }
        return ret;
    }
    
    
    public static ZIPCodeAddress getAddressByZipCode(String zipCode) throws Exception {
        String content = requestZipCodePage(zipCode);
        return parse(content);
    }
    
}
