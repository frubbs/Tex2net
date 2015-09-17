package com.text2net;

import java.io.File;
import java.util.List;

import com.text2net.core.ConnectionProducer;
import com.text2net.core.TextAnnotator;
import com.text2net.core.api.AnnotatedText;
import com.text2net.core.api.Connection;



public class mainTest {

	public  void main(String[] args) throws Exception {


	
		
		/*String gappFilePath = "C:\\Users\\rafa\\workspace\\analisador-dou\\GateDeveloperFiles\\Processador\\InicioInicio_v5.xapp";
		String docFilePath = "C:\\Users\\rafa\\workspace\\Text2NetCore\\src\\main\\resources\\TempDir2013\\Dou-02012013-1.txt";
		*/
		
		//String gappFilePath = "C:\\Users\\rafa\\workspace\\Text2Net\\text2net\\src\\main\\resources\\com\\text2net\\gate\\InicioInicio_v5.xapp";
		String docFilePath = "C:\\Users\\rafa\\workspace\\Text2Net\\text2net\\src\\main\\resources\\com\\text2net\\douSample\\Dou-02012013-1.txt";
		
		String gappFilePath = this.getClass().getClassLoader().getResource("com\\text2net\\gate\\InicioInicio_v5.xapp").getPath();
		
		System.out.println("gappFilePath: " + gappFilePath);
		/*
		
		File gappFile =  new File(gappFilePath);
		File docFile =  new File(docFilePath);
		
		AnnotatedText annotatedText = new TextAnnotator().processFile(gappFile, docFile);
		
		System.out.println("Finished Annotation. Starting connection detection");
		
		List<Connection> connections = new ConnectionProducer().process(annotatedText);
		
		for (Connection connection : connections) {
			System.out.println("Conncetion: [" + connection.getElementA() + "][" + connection.getElementB() + "](" + connection.getDistance() + ")");
		}
		*/
		
		System.out.println("Finished Connection");
		
		

	}

}
