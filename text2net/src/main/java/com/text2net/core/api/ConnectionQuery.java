package com.text2net.core.api;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class ConnectionQuery {

	@XmlElement 
    public String lineBreak;
	@XmlElement 
	public String name;
	@XmlElement 
	public String namesList;
	@XmlElement 
	public String text;
	
	
	public String getLineBreak() {
		return lineBreak;
	}
	public String getName() {
		return name;
	}
	public String getNamesList() {
		return namesList;
	}
    public String getText() {
		return lineBreak + " " + text + " " + lineBreak;
	}
    
	public void setLineBreak(String lineBreak) {
		this.lineBreak = lineBreak;
	}
	public void setName(String name) {
		this.name = name;
	}
	public void setNamesList(String namesList) {
		this.namesList = namesList;
	}
	public void setText(String text) {
		this.text = text;
	}
}
