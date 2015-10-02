package com.text2net.core;

import java.io.File;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.jsoup.Jsoup;
import org.junit.Assert;
import org.junit.Test;

import com.text2net.core.api.Connection;
import com.text2net.core.gateUtil.GATEExtractor;

import gate.AnnotationSet;
import gate.Document;
import gate.Factory;

public class TextUpMarkerTest {

	@Test
	public void testProcess() throws Exception{

		//repetindo o teste do anootator... fodas
		String filePath = this.getClass().getClassLoader().getResource("com/text2net/gate/Nomes_Trecho.xapp").getPath().substring(1);
		File gappFile = new File(filePath);
		String docFilePath = this.getClass().getClassLoader().getResource("com/text2net/discursoSample/lorem.txt").getPath().substring(1);
		File file = new File(docFilePath);
		GATEExtractor.getInstance();
		Document doc = Factory.newDocument(file.toURL(), "UTF-8");
		
		Document annotatedDoc = new TextAnnotator2().processDoc(gappFile, doc);
		
		Assert.assertEquals(2186, annotatedDoc.getAnnotations().size());
		AnnotationSet todasAnnots = annotatedDoc.getAnnotations();
		List<gate.Annotation> inicioList = gate.Utils.inDocumentOrder(todasAnnots.get("Inicio"));
		List<gate.Annotation> entidadeList = gate.Utils.inDocumentOrder(todasAnnots.get("EntidadeIdentificada"));
		Assert.assertEquals(9, inicioList.size());
		Assert.assertEquals(9, entidadeList.size());

		
		//retepe o teste e fodas, cara... nao da pra mockar essa merda
		ConnectionProducer cp = new ConnectionProducer();
		List<Connection> connectionList = cp.process(annotatedDoc);
		
		
		Assert.assertEquals(10, connectionList.size());
		
		
		
		
		//agora testa o upmarker
		
		String textMarked = TextUpMarker.markUp(annotatedDoc, connectionList);
		int i = 0;
		Pattern p = Pattern.compile("separator");
		Matcher m = p.matcher(textMarked);
		while (m.find()) {
		    i++;
		}
		System.out.println("text:" + textMarked);
		Assert.assertEquals(inicioList.size(), i);
		
		
		i = 0;
		p = Pattern.compile("class=\"element\"");
		m = p.matcher(textMarked);
		while (m.find()) {
		    i++;
		}
		
		Assert.assertEquals(entidadeList.size(), i);
		
		
		
		
		Assert.assertTrue(textMarked.indexOf("<span class=\"element\">Angela")>0);
		Assert.assertTrue(textMarked.indexOf("<span class=\"element\">José")>0);
		Assert.assertTrue(textMarked.indexOf("<span class=\"element\">ANTONIO")>0);
		Assert.assertTrue(textMarked.indexOf("<span class=\"element\">ONOFRE")>0);
		Assert.assertTrue(textMarked.indexOf("<span class=\"element\">Mauro")>0);
		Assert.assertTrue(textMarked.indexOf("<span class=\"element\">Vera")>0);
		Assert.assertTrue(textMarked.indexOf("<span class=\"element\">Evandro")>0);
		Assert.assertTrue(textMarked.indexOf("<span class=\"element\">Paulo")>0);
		Assert.assertTrue(textMarked.indexOf("<span class=\"element\">Oscar")>0);
		Assert.assertTrue(textMarked.indexOf("class=\"separator\">Publ.:")>0);
				
		
		Assert.assertTrue(textMarked.indexOf("pellentesque Angela Merkel")<=0);
		Assert.assertTrue(textMarked.indexOf("donec José")<=0);
		Assert.assertTrue(textMarked.indexOf("turpis ANTONIO BULHÕES")<=0);
		Assert.assertTrue(textMarked.indexOf("vitae ONOFRE")<=0);
		Assert.assertTrue(textMarked.indexOf("orci, Mauro Benevides")<=0);
		Assert.assertTrue(textMarked.indexOf("sem Vera Lúcia Cabreira")<=0);
		Assert.assertTrue(textMarked.indexOf("consectetur Evandro Pavan")<=0);
		Assert.assertTrue(textMarked.indexOf("neque Paulo Maluf")<=0);
		Assert.assertTrue(textMarked.indexOf("fermentum Oscar Niemeye")<=0);
		
		//verifica se os  textops tem o mesmo tamanho em quantidade de palavras
		Assert.assertEquals(doc.getContent().toString().replace("\n", "").split("\\s+").length, html2text(textMarked).replace("\n", "").split("\\s+").length);
		
		
		
		
		Factory.deleteResource(annotatedDoc);

		
	}
	
	public static String html2text(String html) {
	    return Jsoup.parse(html).text();
	}
}
