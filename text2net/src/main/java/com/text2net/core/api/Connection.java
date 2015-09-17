package com.text2net.core.api;

public class Connection {

	String elementA;
	String elementB;
	
	long distance; //co-word distance;
	
	long textChunkID; //reference to a chunk where the connection was found

	
	public Connection (String elementA, String elementB, long distance, long textChunkID) {
		this.elementA = elementA;
		this.elementB = elementB;
		this.distance = distance;
		this.textChunkID = textChunkID;
	}


	public String getElementA() {
		return elementA;
	}


	public String getElementB() {
		return elementB;
	}


	public long getDistance() {
		return distance;
	}


	public long getTextChunkID() {
		return textChunkID;
	}
	
	

	
			
}
