package com.text2net.core.api;

import java.util.List;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class ConnectionQueryResult {
	private List<Connection> connections;
	
	private String markedUpText;
	
	public ConnectionQueryResult() {
		
	}
	
	
		public ConnectionQueryResult(List<Connection> connections, String markedUpText) {
		super();
		this.connections = connections;
		this.markedUpText = markedUpText;
	}

		public List<Connection> getConnections() {
			return connections;
		}

		public void setConnections(List<Connection> connections) {
			this.connections = connections;
		}

		public String getMarkedUpText() {
			return markedUpText;
		}

		public void setMarkedUpText(String markedUpText) {
			this.markedUpText = markedUpText;
		}
}
