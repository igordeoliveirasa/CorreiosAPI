CorreiosAPI
===========

Biblioteca que auxiliará os desenvolvedores a fazer qualquer tipo de interface com o sistema dos correios. No momento, existe o método de consulta de endereço pelo CEP. Outras funcionalidades serão bem vindas.

Exemplo de utilização
===========

ZIPCodeAddress address = CorreiosAPI.getAddressByZipCode("27259-000");<br/>
System.out.println(address);

Maiores informações
===========

A classe ZIPCodeAddress é um bean que contem os atributos de um endereço localizado pelo CEP.<br/>
Seguem seus atributos:</br>

<ul>
<li>street</li>
<li>neighborhood</li>
<li>city</li>
<li>stateAcronym</li>
<li>state</li>
<li>country</li>
<li>zipCode</li>
</ul>
