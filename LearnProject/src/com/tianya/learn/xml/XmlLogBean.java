package com.tianya.learn.xml;

import java.sql.Timestamp;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;

@XmlRootElement(name="log")
public class XmlLogBean {
	
	
	private int num ;
	
	private String names  ;
	
	private java.sql.Timestamp startDate ;

	@XmlAttribute
	public int getNum() {
		return num;
	}

	public void setNum(int num) {
		this.num = num;
	}

	@XmlAttribute
	public String getNames() {
		return names;
	}

	public void setNames(String names) {
		this.names = names;
	}

	@XmlAttribute
	@XmlJavaTypeAdapter(DateConvertAdapter.class)
	public Timestamp getStartDate() {
		return startDate;
	}

	public void setStartDate(Timestamp startDate) {
		this.startDate = startDate;
	}

	
	
}