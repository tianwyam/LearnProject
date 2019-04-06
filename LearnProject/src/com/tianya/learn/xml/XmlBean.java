package com.tianya.learn.xml;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name="info")
public class XmlBean {
	
	private XmlLogBean log ;
	
	
	private int id ;
	
	private String name ;

	@XmlElement
	public XmlLogBean getLog() {
		return log;
	}

	public void setLog(XmlLogBean log) {
		this.log = log;
	}

	@XmlAttribute
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	@XmlAttribute
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	

}

