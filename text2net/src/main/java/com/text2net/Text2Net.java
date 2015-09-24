package com.text2net;

import java.io.File;
import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.text2net.core.ConfigurationSetup;
import com.text2net.core.ConnectionProducer;
import com.text2net.core.TextAnnotator;
import com.text2net.core.TextUpMarker;
import com.text2net.core.api.AnnotatedText;
import com.text2net.core.api.Connection;
import com.text2net.core.api.ConnectionElement;
import com.text2net.core.api.ConnectionQuery;
import com.text2net.core.api.ConnectionQueryResult;

@Path("text2net")
public class Text2Net {
	
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Path("{id}")
	public Connection getConnection(@PathParam("id") int id) {
		ConnectionElement elementA = new ConnectionElement("Elemneto testea", 554, 564);
		ConnectionElement elementB = new ConnectionElement("Elemneto testeb", 554, 564);
		
		
		return new Connection(elementA, elementB, 256L, id);
	}
	/*
	
	@POST
	@Produces(MediaType.APPLICATION_JSON)
	@Path("{id}")
	public ConnectionQueryResult getConnections(@PathParam("id") int id, String text) {
		ConnectionQueryResult result = null;
		System.out.println("getCopnnections............");
		try {
			File gappFile =  new File(this.getClass().getClassLoader().getResource("com/text2net/gate/Nomes_Trecho.xapp").getPath());
			
			result = processRequest(text, gappFile);
		}
		catch(Exception e){
			e.printStackTrace();
		}
		
		return result;
	}*/
	
	@POST
	@Produces(MediaType.APPLICATION_JSON)
	@Path("{id}")
	public ConnectionQueryResult getConnectionsLineBreak(@PathParam("id") int id, ConnectionQuery connectionQuery) {
		ConnectionQueryResult result = null;
		try {
			File gappFile =  new File(this.getClass().getClassLoader().getResource("com/text2net/gate/Nomes_Trecho.xapp").getPath());
			File gappFileConfigured = new ConfigurationSetup().configure(connectionQuery.getLineBreak(), gappFile.getName());
			result = processRequest(connectionQuery.getText(), gappFileConfigured);
		}
		catch(Exception e){
			e.printStackTrace();
		}
		
		return result;
	}

	

	private ConnectionQueryResult processRequest(String text, File gappFile) throws Exception {
		ConnectionQueryResult result;
		
		System.out.println("Starting annotation with xapp: " + gappFile.getAbsolutePath());
		
		AnnotatedText annotatedText = new TextAnnotator().processString(gappFile, text);
		
		System.out.println("Finished Annotation. Starting connection detection");
		
		List<Connection> connections = new ConnectionProducer().process(annotatedText);
		
		
		String markedUpText = new TextUpMarker().markUp(annotatedText, connections);
		
		result = new ConnectionQueryResult(connections, markedUpText);
		return result;
	}
	    
	/*
	@GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<Connection> getIt() {
       
		try{
			File gappFile =  new File(this.getClass().getClassLoader().getResource("com/text2net/gate/InicioInicio_v5.xapp").getPath());
			File docFile =  new File(this.getClass().getClassLoader().getResource("com/text2net/douSample/Dou-02012013-1.min.txt").getPath());

			AnnotatedText annotatedText = new TextAnnotator().processFile(gappFile, docFile);
			
			System.out.println("Finished Annotation. Starting connection detection");
			
			List<Connection> connections = new ConnectionProducer().process(annotatedText);
			
			return connections;
		}
		catch(Exception e){
			e.printStackTrace();
		}
		
    	return null;
    }
    */

}
