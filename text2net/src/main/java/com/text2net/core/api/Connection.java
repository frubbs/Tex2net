package com.text2net.core.api;

public class Connection {

	ConnectionElement elementA;
	ConnectionElement elementB;
	
	long distance; //co-word distance;
	
	long textChunkID; //reference to a chunk where the connection was found

	public Connection() {
		
	}
	
	public Connection(ConnectionElement elementA, ConnectionElement elementB, long distance, long textChunkID) {
		super();
		this.elementA = elementA;
		this.elementB = elementB;
		this.distance = distance;
		this.textChunkID = textChunkID;
	}

	public ConnectionElement getElementA() {
		return elementA;
	}

	public void setElementA(ConnectionElement elementA) {
		this.elementA = elementA;
	}

	public ConnectionElement getElementB() {
		return elementB;
	}

	public void setElementB(ConnectionElement elementB) {
		this.elementB = elementB;
	}

	public long getDistance() {
		return distance;
	}

	public void setDistance(long distance) {
		this.distance = distance;
	}

	public long getTextChunkID() {
		return textChunkID;
	}

	public void setTextChunkID(long textChunkID) {
		this.textChunkID = textChunkID;
	}

	
				
}
