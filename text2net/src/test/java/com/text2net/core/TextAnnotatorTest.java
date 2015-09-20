package com.text2net.core;

import static org.junit.Assert.*;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

import org.junit.Test;
import org.omg.PortableInterceptor.SUCCESSFUL;

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
			
			assertEquals(annotatedText.getDoc().getAnnotations().size(), 56120);
			
			
			List<Connection> connections = new ConnectionProducer().process(annotatedText);
			
			assertEquals(connections.size(), 8846);
			
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
			
			assertEquals(annotatedText.getDoc().getAnnotations().size(), 56120);
			
			
			List<Connection> connections = new ConnectionProducer().process(annotatedText);
			
			assertEquals(connections.size(), 8846);
			
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
			String docFilePath = this.getClass().getClassLoader().getResource("com/text2net/douSample/Dou-02012013-1.min.txt").getPath().substring(1);
			
			byte[] encoded = Files.readAllBytes(Paths.get(docFilePath));
			
			File gappFile =  new File(gappFilePath);
			AnnotatedText annotatedText = new TextAnnotator().processString(gappFile, new String(encoded,"UTF-8"));
			
			assertEquals(annotatedText.getDoc().getAnnotations().size(), 56120);
			
			
			List<Connection> connections = new ConnectionProducer().process(annotatedText);
			
			
			String txtMarkedUp = annotatedText.getDoc().getContent().toString();
			
			
			assertEquals(connections.size(), 8846);
			
		}
		catch(Exception e){
			e.printStackTrace();
			fail(e.getMessage());
		}
	}
	
}
