package com.text2net.core;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

import org.junit.Test;

import com.text2net.core.api.AnnotatedText;
import com.text2net.core.api.Connection;

import junit.framework.Assert;

public class TextAnnotatorTest {

	@Test
	public void testAnotaEGeraConexao() {
		try{
			String gappFilePath = this.getClass().getClassLoader().getResource("com/text2net/gate/InicioInicio_v5.xapp").getPath();
			String docFilePath = this.getClass().getClassLoader().getResource("com/text2net/douSample/Dou-02012013-1.min.txt").getPath();
			File gappFile =  new File(gappFilePath);
			File docFile =  new File(docFilePath);
			AnnotatedText annotatedText = new TextAnnotator().processFile(gappFile, docFile);
			
			assertEquals(annotatedText.getDoc().getAnnotations().size(), 56064);
			
			
			List<Connection> connections = new ConnectionProducer().process(annotatedText);
			
			assertEquals(connections.size(), 3735);
			
		}
		catch(Exception e){
			e.printStackTrace();
			fail(e.getMessage());
		}
	}

	@Test
	public void testAnotaEGeraConexaoString() {
		try{
			String gappFilePath = this.getClass().getClassLoader().getResource("com/text2net/gate/InicioInicio_v5.xapp").getPath();

			//The path \C:\Sample\sample.txt must not have a leading \. It should be just C:\Sample\sample.txt http://stackoverflow.com/questions/9834776/java-nio-file-path-issue
			String docFilePath = this.getClass().getClassLoader().getResource("com/text2net/douSample/Dou-02012013-1.min.txt").getPath().substring(1);
			
			byte[] encoded = Files.readAllBytes(Paths.get(docFilePath));
			
			File gappFile =  new File(gappFilePath);
			AnnotatedText annotatedText = new TextAnnotator().processString(gappFile, new String(encoded,"UTF-8"));
			
			assertEquals(annotatedText.getDoc().getAnnotations().size(), 56064);
			
			
			List<Connection> connections = new ConnectionProducer().process(annotatedText);
			
			assertEquals(connections.size(), 3735);
			
		}
		catch(Exception e){
			e.printStackTrace();
			fail(e.getMessage());
		}
	}

	
	@Test
	public void testAnotaEGeraConexaoStringEAnotaTexto() {
		try{
			String gappFilePath = this.getClass().getClassLoader().getResource("com/text2net/gate/InicioInicio_v5.xapp").getPath();

			//The path \C:\Sample\sample.txt must not have a leading \. It should be just C:\Sample\sample.txt http://stackoverflow.com/questions/9834776/java-nio-file-path-issue
			String docFilePath = this.getClass().getClassLoader().getResource("com/text2net/douSample/Dou-teste.txt").getPath().substring(1);
			
			byte[] encoded = Files.readAllBytes(Paths.get(docFilePath));
			
			File gappFile =  new File(gappFilePath);
			AnnotatedText annotatedText = new TextAnnotator().processString(gappFile, new String(encoded,"UTF-8"));
			
			assertEquals(annotatedText.getDoc().getAnnotations().size(), 4600);
			
			
			List<Connection> connections = new ConnectionProducer().process(annotatedText);
			
			
			String markedUpText = new TextUpMarker().markUp(annotatedText, connections);
			
			assertEquals(connections.size(), 80);
			
		}
		catch(Exception e){
			e.printStackTrace();
			fail(e.getMessage());
		}
	}

	
	@Test
	public void testAnotaEGeraConexaoStringEAnotaTexto_NomesTrecho_xapp() {
		try{
			String gappFilePath = this.getClass().getClassLoader().getResource("com/text2net/gate/Nomes_Trecho.xapp").getPath();

			//The path \C:\Sample\sample.txt must not have a leading \. It should be just C:\Sample\sample.txt http://stackoverflow.com/questions/9834776/java-nio-file-path-issue
			String docFilePath = this.getClass().getClassLoader().getResource("com/text2net/douSample/Dou-teste.txt").getPath().substring(1);
			
			byte[] encoded = Files.readAllBytes(Paths.get(docFilePath));
			
			File gappFile =  new File(gappFilePath);
			AnnotatedText annotatedText = new TextAnnotator().processString(gappFile, new String(encoded,"UTF-8"));
			
			assertEquals(annotatedText.getDoc().getAnnotations().size(), 4589);
			
			
			List<Connection> connections = new ConnectionProducer().process(annotatedText);
			
			
			String markedUpText = new TextUpMarker().markUp(annotatedText, connections);
			
			assertEquals(connections.size(), 80);
			
		}
		catch(Exception e){
			e.printStackTrace();
			fail(e.getMessage());
		}
	}

	@Test
	public void testAnotaEGeraConexaoStringEAnotaTexto_InicioDinamico_xapp() {
		try{
			String gappFilePath = this.getClass().getClassLoader().getResource("com/text2net/gate/Nomes_Trecho.xapp").getPath();

						
			//The path \C:\Sample\sample.txt must not have a leading \. It should be just C:\Sample\sample.txt http://stackoverflow.com/questions/9834776/java-nio-file-path-issue
			String docFilePath = this.getClass().getClassLoader().getResource("com/text2net/douSample/Dou-teste.txt").getPath().substring(1);
			
			byte[] encoded = Files.readAllBytes(Paths.get(docFilePath));
			
			File gappFile =  new File(gappFilePath);
			AnnotatedText annotatedText = new TextAnnotator().processString(gappFile, new String(encoded,"UTF-8"));
			
			assertEquals(annotatedText.getDoc().getAnnotations().size(), 4589);
			
			
			List<Connection> connections = new ConnectionProducer().process(annotatedText);
			
			
			String markedUpText = new TextUpMarker().markUp(annotatedText, connections);
			
			assertEquals(connections.size(), 80);
			
		}
		catch(Exception e){
			e.printStackTrace();
			fail(e.getMessage());
		}
	}
	
	


	
	
	
}
