package app.correiosapi;

import br.com.igordeoliveirasa.correios.CorreiosAPI;
import br.com.igordeoliveirasa.correios.ZIPCodeAddress;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args ) throws Exception
    {   
        ZIPCodeAddress address = CorreiosAPI.getAddressByZipCode("27259000");
        System.out.println(address);
    }
}
