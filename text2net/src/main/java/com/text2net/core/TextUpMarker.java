package com.text2net.core;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import com.text2net.core.api.AnnotatedText;
import com.text2net.core.api.Connection;
import com.text2net.core.api.ConnectionElement;

import gate.Document;
import gate.DocumentContent;
import gate.Factory;
import gate.creole.ResourceInstantiationException;
import gate.util.InvalidOffsetException;


public class TextUpMarker {

	public String markUp(AnnotatedText annotatedText, List<Connection> connections) {
		
		ArrayList<ConnectionElement> elements = new ArrayList<ConnectionElement>();
		
		for (Connection connection : connections) {
			elements.add(connection.getElementA());
			elements.add(connection.getElementB());
		}

		Collections.sort( elements, new Comparator<ConnectionElement>() {

			public int compare(ConnectionElement e1, ConnectionElement e2) {
				 long initOffset1 = e1.getInitialOffset();
				 long initOffset2 = e2.getInitialOffset();
				  
			   return (int) (initOffset2 - initOffset1);

		    }} );
		Document doc = null;
	    try {
			doc = annotatedText.getDoc();
			long lastIniOffsetProcessed = doc.getContent().size();
		    for (ConnectionElement connectionElement : elements) {
		    	
		    	if(connectionElement.getInitialOffset() < lastIniOffsetProcessed) {
					DocumentContent dcbefore = doc.getContent().getContent(0L, connectionElement.getInitialOffset());
					DocumentContent dcword = doc.getContent().getContent(connectionElement.getInitialOffset(), connectionElement.getFinalOffset());
					DocumentContent dcafter = doc.getContent().getContent(connectionElement.getFinalOffset(), annotatedText.getDoc().getContent().size());
				/*	
					System.out.println("dcbefore: " + dcbefore.toString());
					System.out.println("dcword: " + dcword.toString());
					System.out.println("dcafter: " + dcafter.toString());
					System.out.println("ini: " + connectionElement.getInitialOffset());
					
					*/
					
					doc = Factory.newDocument(dcbefore.toString() + "<span class=\"element\">" + dcword.toString() + "</span>" + dcafter.toString());
					
					
					lastIniOffsetProcessed = connectionElement.getInitialOffset(); 
					//System.out.println("doc: " + doc.toString());
		    	}
					
		    	}		
			} catch (Exception e) {
				e.printStackTrace();
			//	continue;
			}
			
	    	
		
		
		// TODO Auto-generated method stub
		return doc.toString();
	}
}

//http://stackoverflow.com/questions/2839137/how-to-use-comparator-in-java-to-sort