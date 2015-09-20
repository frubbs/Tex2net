package com.text2net.core.api;

public class ConnectionElement {
	String name;
	long initialOffset; //Position in the document
	long finalOffset; //Position in the document
	
	public ConnectionElement(String name, long initialOffset, long finalOffset) {
		super();
		this.name = name;
		this.initialOffset = initialOffset;
		this.finalOffset = finalOffset;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public long getInitialOffset() {
		return initialOffset;
	}

	public void setInitialOffset(long initialOffset) {
		this.initialOffset = initialOffset;
	}

	public long getFinalOffset() {
		return finalOffset;
	}

	public void setFinalOffset(long finalOffset) {
		this.finalOffset = finalOffset;
	}
	
	
}
