package com.text2net;

import static org.junit.Assert.assertEquals;

import javax.ws.rs.client.Entity;
import javax.ws.rs.core.Application;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.glassfish.jersey.server.ResourceConfig;
import org.glassfish.jersey.test.JerseyTest;
import org.junit.Assert;
import org.junit.Test;

import com.text2net.core.api.ConnectionQuery;
import com.text2net.core.api.ConnectionQueryResult;


public class MyResourceTest extends JerseyTest {

    @Override
    protected Application configure() {
        return new ResourceConfig(Text2Net.class);
    }

    /**
     * Test to see that the message "Got it!" is sent in the response.
     */
    @Test
    public void testGetIt() {
        final String responseMsg = target().path("text2net/3/").request().get(String.class);

        System.out.println("responseMsg: " + responseMsg);
        
        assertEquals("Hello, Heroku!", responseMsg);
    }
    
    @Test
    public void testGetConnectionsLineBreak() {
    	
    	ConnectionQuery connectionQuery = new ConnectionQuery();
    	connectionQuery.setLineBreak("DISCURSO");
    	connectionQuery.setText("C�MARA DOS DEPUTADOS ORDEM DO DIA ENCAMINHAMENTO DE VOTA��O DISCURSO Sum�rio O SR. MARCUS PESTANA (Bloco/PSDB-MG. Sem revis�o do orador.) - Sr. Presidente, Sras. e Srs. Deputados, eu n�o queria, de forma ing�nua, contribuir para o processo protelat�rio de obstru��o liderado pelo PT. Mas � necess�rio colocar alguns pingos nos is. Caro amigo, Deputado Henrique Fontana, V.Exa. sabe que eu tenho isen��o para fazer isso. Esta � uma agenda priorit�ria h� mais de 1 d�cada. O sistema pol�tico brasileiro, o sistema partid�rio eleitoral se esgotou: 70% da popula��o, 2 anos depois, n�o sabe dizer o nome do Deputado em quem votou; as regras do financiamento precisam de aprimoramento; os partidos n�o s�o fortalecidos, pelo contr�rio, � um sistema an�rquico que divide os partidos e n�o alimenta a solidariedade partid�ria, porque voc� compete com um companheiro e n�o com um advers�rio, na l�gica absurda que n�o existe em parte nenhuma do mundo. Existem tr�s sistemas cl�ssicos: sistema distrital puro, misto e lista fechada. O Brasil tem uma engenharia que � uma verdadeira jabuticaba, s� existe aqui. Esgotou-se! Mal ou bem, viemos at� aqui com ele. Ulysses e Tancredo nos entregaram a democracia. Mal ou bem, chegamos aqui. N�o vamos cuspir no prato em que comemos, mas agora � hora de mudar. Instalaram-se dois procedimentos em 2011. Um se deu no Senado, foi sum�rio, durou 2 meses e acabou em nada, foi desfigurado, e o Senado esqueceu o assunto. N�s tivemos uma Comiss�o Especial que trabalhou 2 anos, cujo Relator era exatamente Henrique Fontana. N�o foi poss�vel construir o consenso, sequer votamos o relat�rio, e o processo foi arquivado no final de 2012. 87 Com as mobiliza��es de rua de junho de 2013 surgiu o tema novamente da reforma pol�tica. Ningu�m na rua pediu reforma pol�tica. Este � um tema instrumental um tanto �rido para a popula��o. � obriga��o nossa, institucional, modernizar. Mas n�o � um tema popular, vamos dizer claramente. � um meio, n�o � o fim. Mas surgiram, entre outras coisas - uma coisa captada do Podemos, dos indignados espanh�is - os cartazes Voc�s n�o nos representam. No fim daquele ciclo de manifesta��es de rua, a Presidente Dilma prop�s uma Constituinte exclusiva, e foi combatida por todos os lados. Como ter um poder constituinte com a democracia e as institui��es funcionando democraticamente? N�o cabe uma Constituinte numa democracia que est� fortalecida e institucionalizada. Ela passou a defender o plebiscito, que n�o � vi�vel. O referendo � vi�vel. Para um tema dessa complexidade, n�o � vi�vel. Estudo esse tema h� 15, 17 anos. A� o Presidente Henrique Eduardo Alves, numa s�bia decis�o, em reposta �s ruas, nomeou um grupo de trabalho que trabalhou 4 meses sem holofotes, sem vaidades. Eram 15 Deputados e um coordenador: Deputada Luiza Erundina, representando a bancada feminina; Deputado Esperidi�o Amin; Deputado Guilherme Campos, representando o PSD; Ministro Ricardo Berzoini, representando o PT; Deputado Daniel Almeida, representando o PCdoB; Deputado Marcelo Castro, o PMDB. Chegamos � formula��o de uma PEC. Coordenada pelo Deputado C�ndido Vaccarezza, do PT, teve a participa��o na vota��o do relat�rio do Ministro Berzoini. � uma tempestade em copo d'�gua. Quem n�o quer discutir o m�rito � quem n�o quer deixar instalar a Comiss�o Especial, n�o � s� financiamento. Eu acho que o PT tem um complexo de culpa freudiano, pois tem fixa��o no tema do financiamento, por causa do mensal�o e do petrol�o. (Palmas.) H� muito mais coisa em jogo: a emenda fala em cl�usula de desempenho progressiva de 3%, em 2018, de 4%, em 2022 e de 5%, atingindo o patamar alem�o, em 2026, em voto facultativo; acaba com as coliga��es proporcionais, permitindo federa��es nacionais de partido; promove a coincid�ncia de mandato; muda as regras de financiamento; acaba com a reelei��o; regionaliza o voto proporcional. Este sistema nosso � maluco. S�o Paulo teve mil candidatos a Deputado Federal, disputando 34 milh�es de votos. O eleitor tem qualidade? Ele consegue comparar os mil candidatos? Ele vai ter contato no m�ximo com 20 ou 30, atrav�s de um santinho, uma palestra, um com�cio ou, qui��, pela televis�o. O que estamos votando hoje � s� o in�cio da conversa. Quem n�o quer debater � que est� obstruindo. A PEC pode ser virada pelo avesso. 88 N�s vamos ter audi�ncias p�blicas, Relator, Presidente, e contradit�rio. � uma cascata essa conversa de que n�s estamos botando goela abaixo. O povo brasileiro exige a melhoria da democracia, com uma verdadeira reforma pol�tica. Muito obrigado, Sr. Presidente. Documento 26/27 010.1.55.");
    	
    	
    	final Response responseMsg = target().path("text2net/3/").request().post(Entity.entity(connectionQuery, MediaType.APPLICATION_JSON));

    	//System.out.println("SSSSSSSS" + responseMsg.readEntity(ConnectionQueryResult.class));
    	
    	ConnectionQueryResult result = responseMsg.readEntity(ConnectionQueryResult.class);
    	
    	Assert.assertEquals(78, result.getConnections().size());
    	
    }
    
    
    
    
}
