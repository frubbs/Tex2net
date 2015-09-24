package com.text2net.core.api;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class ConnectionQuery {
	@XmlElement 
	private String text;
    @XmlElement 
    private String lineBreak;
    
	public String getText() {
		return lineBreak + " " + text + " " + lineBreak;
	}
	public void setText(String text) {
		this.text = text;
	}
	public String getLineBreak() {
		return lineBreak;
	}
	public void setLineBreak(String lineBreak) {
		this.lineBreak = lineBreak;
	}
}
