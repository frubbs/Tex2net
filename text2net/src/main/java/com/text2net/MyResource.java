package com.text2net;

import java.io.File;
import java.net.URI;
import java.net.URISyntaxException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.text2net.core.ConnectionProducer;
import com.text2net.core.TextAnnotator;
import com.text2net.core.api.AnnotatedText;
import com.text2net.core.api.Connection;

/**
 * Root resource (exposed at "myresource" path)
 */
@Path("myresource")
public class MyResource {

    /**
     * Method handling HTTP GET requests. The returned object will be sent
     * to the client as "text/plain" media type.
     *
     * @return String that will be returned as a text/plain response.
     */
	
	
    //@GET
    //@Produces(MediaType.TEXT_PLAIN)
    public String getIt() {
       
    	String gappFilePath = "nao";//"C:\\Users\\rafa\\workspace\\Text2Net\\text2net\\src\\main\\resources\\com\\text2net\\gate\\InicioInicio_v5.xapp";
		String docFilePath = "nop";//"C:\\Users\\rafa\\workspace\\Text2Net\\text2net\\src\\main\\resources\\com\\text2net\\douSample\\Dou-02012013-1.txt";
		
		try{
		 gappFilePath = this.getClass().getClassLoader().getResource("com/text2net/gate/InicioInicio_v5.xapp").getPath();
		 docFilePath = this.getClass().getClassLoader().getResource("com/text2net/douSample/Dou-02012013-1.txt").getPath();
		}
		catch(Exception e){
			e.printStackTrace();
		}
		
		
		String r = runGate(gappFilePath, docFilePath);
    	return r;
    }

	private String runGate(String gappFilePath, String docFilePath) {
		String r = "Result: ";
		
		File gappFile =  new File(gappFilePath);
		File docFile =  new File(docFilePath);
		try {
		AnnotatedText annotatedText;
		
			annotatedText = new TextAnnotator().processFile(gappFile, docFile);
		
		
		//System.out.println("Finished Annotation. Starting connection detection");
		
		List<Connection> connections = new ConnectionProducer().process(annotatedText);
		
		for (int i = 0; i < 100; i++) {
			Connection connection = connections.get(i);
			
			r += "Conncetion: [" + connection.getElementA() + "][" + connection.getElementB() + "](" + connection.getDistance() + ")";
		}
		
		/*
		for (Connection connection : connections) {
			System.out.println("Conncetion: [" + connection.getElementA() + "][" + connection.getElementB() + "](" + connection.getDistance() + ")");
		}
		
		
		*/
		//System.out.println("Finished Connection");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return r;
	}
}
