package com.text2net.core;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

import org.junit.Assert;
import org.junit.Test;

import com.text2net.core.api.AnnotatedText;
import com.text2net.core.api.Connection;
import com.text2net.core.api.ConnectionQuery;
import com.text2net.core.gateUtil.GATEExtractor;

import gate.AnnotationSet;
import gate.Document;
import gate.Factory;

public class TextAnnotator2Test {

	
	
	@Test 
	public void testProcessDoc() throws Exception{
		
		
		String filePath = this.getClass().getClassLoader().getResource("com/text2net/gate/Nomes_Trecho.xapp").getPath().substring(1);
		
		File gappFile = new File(filePath);
		
		String docFilePath = this.getClass().getClassLoader().getResource("com/text2net/discursoSample/2012.txt").getPath().substring(1);
		
		System.out.println(docFilePath);
		File file = new File(docFilePath);
		
		
		GATEExtractor.getInstance();
		Document doc = Factory.newDocument(file.toURL(), "UTF-8");
		
		Document annotatedDoc = new TextAnnotator2().processDoc(gappFile, doc);
		
		
		Assert.assertEquals(36874, annotatedDoc.getAnnotations().size());
		
		AnnotationSet todasAnnots = annotatedDoc.getAnnotations();
		List<gate.Annotation> inicioList = gate.Utils.inDocumentOrder(todasAnnots.get("Inicio"));
		List<gate.Annotation> entidadeList = gate.Utils.inDocumentOrder(todasAnnots.get("EntidadeIdentificada"));
		
		
		Assert.assertEquals(13, inicioList.size());
		Assert.assertEquals(125, entidadeList.size());

		Factory.deleteResource(annotatedDoc);
	}

	@Test
	public void testCOmSeparadorEListadeNomes() {
		try{
			
						
			//The path \C:\Sample\sample.txt must not have a leading \. It should be just C:\Sample\sample.txt http://stackoverflow.com/questions/9834776/java-nio-file-path-issue
			String docFilePath = this.getClass().getClassLoader().getResource("com/text2net/douSample/teste.txt").getPath().substring(1);
			
			byte[] encoded = Files.readAllBytes(Paths.get(docFilePath));
			
			
			
			
			
			ConnectionQuery query = new ConnectionQuery();
			query.setText(new String(encoded,"UTF-8"));
			query.setLineBreak("@SEPARA@");
			
			query.setNamesList("Henrique Fontana,Ulysses e Tancredo, Ricardo Berzoini, Daniel Almeida, Marcelo Castro");
			
			File gappFileConfigured = new ConfigurationSetup().configure(query.getLineBreak(), query.getNamesList(), "Nomes_Trecho.xapp");
			
			
			Document annotatedText = new TextAnnotator2().processString(gappFileConfigured, query.getText());
			
			assertEquals(annotatedText.getAnnotations().size(), 1743);
			
			System.out.println("annotatedText.getDoc().getAnnotations().size():" + annotatedText.getAnnotations().size());

			List<Connection> connections = new ConnectionProducer().process(annotatedText);
			
			
			String markedUpText = new TextUpMarker().markUp(annotatedText, connections);
			
			Factory.deleteResource(annotatedText);
			
			assertEquals(connections.size(), 4);
			
		}
		catch(Exception e){
			e.printStackTrace();
			fail(e.getMessage());
		}
	}
	@Test
	public void testSEMseparadorEListadeNomes() {
		try{
			
						
			//The path \C:\Sample\sample.txt must not have a leading \. It should be just C:\Sample\sample.txt http://stackoverflow.com/questions/9834776/java-nio-file-path-issue
			String docFilePath = this.getClass().getClassLoader().getResource("com/text2net/douSample/teste.txt").getPath().substring(1);
			
			byte[] encoded = Files.readAllBytes(Paths.get(docFilePath));
			
			
			
			
			
			ConnectionQuery query = new ConnectionQuery();
			query.setText(new String(encoded,"UTF-8"));
			
			
			query.setNamesList("Henrique Fontana,Ulysses e Tancredo, Ricardo Berzoini, Daniel Almeida, Marcelo Castro");
			
			File gappFileConfigured = new ConfigurationSetup().configure(query.getLineBreak(), query.getNamesList(), "Nomes_Trecho.xapp");
			
			
			Document annotatedText = new TextAnnotator2().processString(gappFileConfigured, query.getText());
			
			assertEquals(annotatedText.getAnnotations().size(), 1747);
			
			System.out.println("annotatedText.getDoc().getAnnotations().size():" + annotatedText.getAnnotations().size());

			List<Connection> connections = new ConnectionProducer().process(annotatedText);
			
			
			String markedUpText = new TextUpMarker().markUp(annotatedText, connections);
			
			assertEquals(connections.size(), 10);
			
		}
		catch(Exception e){
			e.printStackTrace();
			fail(e.getMessage());
		}
	}

	@Test
	public void testCOmSeparador() {
		try{
			
						
			//The path \C:\Sample\sample.txt must not have a leading \. It should be just C:\Sample\sample.txt http://stackoverflow.com/questions/9834776/java-nio-file-path-issue
			String docFilePath = this.getClass().getClassLoader().getResource("com/text2net/douSample/teste.txt").getPath().substring(1);
			
			byte[] encoded = Files.readAllBytes(Paths.get(docFilePath));
			
			
			
			ConnectionQuery query = new ConnectionQuery();
			query.setText(new String(encoded,"UTF-8"));
			query.setLineBreak("@SEPARA@");
			
			File gappFileConfigured = new ConfigurationSetup().configure(query.getLineBreak(), query.getNamesList(), "Nomes_Trecho.xapp");
			
			
			Document annotatedText = new TextAnnotator2().processString(gappFileConfigured, query.getText());
			
			assertEquals(annotatedText.getAnnotations().size(), 1768);
			
			System.out.println("annotatedText.getDoc().getAnnotations().size():" + annotatedText.getAnnotations().size());

			List<Connection> connections = new ConnectionProducer().process(annotatedText);
			
			
			String markedUpText = new TextUpMarker().markUp(annotatedText, connections);
			
			assertEquals(connections.size(), 28);
			
		}
		catch(Exception e){
			e.printStackTrace();
			fail(e.getMessage());
		}
	}
		@Test
		public void testSemSeparador() {
			try{
				
							
				//The path \C:\Sample\sample.txt must not have a leading \. It should be just C:\Sample\sample.txt http://stackoverflow.com/questions/9834776/java-nio-file-path-issue
				String docFilePath = this.getClass().getClassLoader().getResource("com/text2net/douSample/teste.txt").getPath().substring(1);
				
				byte[] encoded = Files.readAllBytes(Paths.get(docFilePath));
				
				
				
				ConnectionQuery query = new ConnectionQuery();
				query.setText(new String(encoded,"UTF-8"));
				
				File gappFileConfigured = new ConfigurationSetup().configure(query.getLineBreak(), query.getNamesList(), "Nomes_Trecho.xapp");
				
				
				Document annotatedText = new TextAnnotator2().processString(gappFileConfigured, query.getText());
				
				assertEquals(annotatedText.getAnnotations().size(), 1772);
				
				//System.out.println("annotatedText.getDoc().getAnnotations().size():" + annotatedText.getDoc().getAnnotations().size());

				List<Connection> connections = new ConnectionProducer().process(annotatedText);
				
				
				String markedUpText = new TextUpMarker().markUp(annotatedText, connections);
				
				assertEquals(connections.size(), 78);
				
			}
			catch(Exception e){
				e.printStackTrace();
				fail(e.getMessage());
			}
				
		
	}
	
	


	
	
	
}
