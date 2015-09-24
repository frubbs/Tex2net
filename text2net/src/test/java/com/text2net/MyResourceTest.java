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
    	connectionQuery.setText("CÂMARA DOS DEPUTADOS ORDEM DO DIA ENCAMINHAMENTO DE VOTAÇÃO DISCURSO Sumário O SR. MARCUS PESTANA (Bloco/PSDB-MG. Sem revisão do orador.) - Sr. Presidente, Sras. e Srs. Deputados, eu não queria, de forma ingênua, contribuir para o processo protelatório de obstrução liderado pelo PT. Mas é necessário colocar alguns pingos nos is. Caro amigo, Deputado Henrique Fontana, V.Exa. sabe que eu tenho isenção para fazer isso. Esta é uma agenda prioritária há mais de 1 década. O sistema político brasileiro, o sistema partidário eleitoral se esgotou: 70% da população, 2 anos depois, não sabe dizer o nome do Deputado em quem votou; as regras do financiamento precisam de aprimoramento; os partidos não são fortalecidos, pelo contrário, é um sistema anárquico que divide os partidos e não alimenta a solidariedade partidária, porque você compete com um companheiro e não com um adversário, na lógica absurda que não existe em parte nenhuma do mundo. Existem três sistemas clássicos: sistema distrital puro, misto e lista fechada. O Brasil tem uma engenharia que é uma verdadeira jabuticaba, só existe aqui. Esgotou-se! Mal ou bem, viemos até aqui com ele. Ulysses e Tancredo nos entregaram a democracia. Mal ou bem, chegamos aqui. Não vamos cuspir no prato em que comemos, mas agora é hora de mudar. Instalaram-se dois procedimentos em 2011. Um se deu no Senado, foi sumário, durou 2 meses e acabou em nada, foi desfigurado, e o Senado esqueceu o assunto. Nós tivemos uma Comissão Especial que trabalhou 2 anos, cujo Relator era exatamente Henrique Fontana. Não foi possível construir o consenso, sequer votamos o relatório, e o processo foi arquivado no final de 2012. 87 Com as mobilizações de rua de junho de 2013 surgiu o tema novamente da reforma política. Ninguém na rua pediu reforma política. Este é um tema instrumental um tanto árido para a população. É obrigação nossa, institucional, modernizar. Mas não é um tema popular, vamos dizer claramente. É um meio, não é o fim. Mas surgiram, entre outras coisas - uma coisa captada do Podemos, dos indignados espanhóis - os cartazes Vocês não nos representam. No fim daquele ciclo de manifestações de rua, a Presidente Dilma propôs uma Constituinte exclusiva, e foi combatida por todos os lados. Como ter um poder constituinte com a democracia e as instituições funcionando democraticamente? Não cabe uma Constituinte numa democracia que está fortalecida e institucionalizada. Ela passou a defender o plebiscito, que não é viável. O referendo é viável. Para um tema dessa complexidade, não é viável. Estudo esse tema há 15, 17 anos. Aí o Presidente Henrique Eduardo Alves, numa sábia decisão, em reposta às ruas, nomeou um grupo de trabalho que trabalhou 4 meses sem holofotes, sem vaidades. Eram 15 Deputados e um coordenador: Deputada Luiza Erundina, representando a bancada feminina; Deputado Esperidião Amin; Deputado Guilherme Campos, representando o PSD; Ministro Ricardo Berzoini, representando o PT; Deputado Daniel Almeida, representando o PCdoB; Deputado Marcelo Castro, o PMDB. Chegamos à formulação de uma PEC. Coordenada pelo Deputado Cândido Vaccarezza, do PT, teve a participação na votação do relatório do Ministro Berzoini. É uma tempestade em copo d'água. Quem não quer discutir o mérito é quem não quer deixar instalar a Comissão Especial, não é só financiamento. Eu acho que o PT tem um complexo de culpa freudiano, pois tem fixação no tema do financiamento, por causa do mensalão e do petrolão. (Palmas.) Há muito mais coisa em jogo: a emenda fala em cláusula de desempenho progressiva de 3%, em 2018, de 4%, em 2022 e de 5%, atingindo o patamar alemão, em 2026, em voto facultativo; acaba com as coligações proporcionais, permitindo federações nacionais de partido; promove a coincidência de mandato; muda as regras de financiamento; acaba com a reeleição; regionaliza o voto proporcional. Este sistema nosso é maluco. São Paulo teve mil candidatos a Deputado Federal, disputando 34 milhões de votos. O eleitor tem qualidade? Ele consegue comparar os mil candidatos? Ele vai ter contato no máximo com 20 ou 30, através de um santinho, uma palestra, um comício ou, quiçá, pela televisão. O que estamos votando hoje é só o início da conversa. Quem não quer debater é que está obstruindo. A PEC pode ser virada pelo avesso. 88 Nós vamos ter audiências públicas, Relator, Presidente, e contraditório. É uma cascata essa conversa de que nós estamos botando goela abaixo. O povo brasileiro exige a melhoria da democracia, com uma verdadeira reforma política. Muito obrigado, Sr. Presidente. Documento 26/27 010.1.55.");
    	
    	
    	final Response responseMsg = target().path("text2net/3/").request().post(Entity.entity(connectionQuery, MediaType.APPLICATION_JSON));

    	//System.out.println("SSSSSSSS" + responseMsg.readEntity(ConnectionQueryResult.class));
    	
    	ConnectionQueryResult result = responseMsg.readEntity(ConnectionQueryResult.class);
    	
    	Assert.assertEquals(78, result.getConnections().size());
    	
    }
    
    
    
    
}
