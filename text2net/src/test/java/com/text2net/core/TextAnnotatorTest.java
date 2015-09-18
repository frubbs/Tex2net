package com.text2net.core;

import static org.junit.Assert.*;

import java.io.File;
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
			String docFilePath = this.getClass().getClassLoader().getResource("com/text2net/douSample/Dou-02012013-1.txt").getPath();
			File gappFile =  new File(gappFilePath);
			File docFile =  new File(docFilePath);
			AnnotatedText annotatedText = new TextAnnotator().processFile(gappFile, docFile);
			
			assertEquals(annotatedText.getDoc().getAnnotations().size(), 187749);
			
			
			List<Connection> connections = new ConnectionProducer().process(annotatedText);
			
			assertEquals(connections.size(), 512306);
			
		}
		catch(Exception e){
			e.printStackTrace();
			fail(e.getMessage());
		}
	}

}
