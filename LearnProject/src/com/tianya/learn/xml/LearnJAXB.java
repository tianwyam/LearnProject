package com.tianya.learn.xml;

import java.io.File;
import java.sql.Timestamp;
import java.util.Date;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.Marshaller;

import org.junit.Test;


public class LearnJAXB {
	
	@org.junit.Test
	public void testBean2Xml() throws Exception {
		
		XmlLogBean xmlLogBean = new XmlLogBean();
		xmlLogBean.setNum(10);
		xmlLogBean.setNames(" log name");
		xmlLogBean.setStartDate(new Timestamp(new Date().getTime()));
		
		XmlBean xmlBean = new XmlBean();
		xmlBean.setId(21);
		xmlBean.setName("大家好");
		xmlBean.setLog(xmlLogBean);
		
		JAXBContext context = JAXBContext.newInstance(XmlBean.class);
		Marshaller marshaller = context.createMarshaller();
		marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
		
		
		File output = new File("./bean2xml.xml");
		marshaller.marshal(xmlBean, output);
	}
	
	
	
	
	
	
	@Test
	public void testSingle() throws Exception {
		
		XmlSingleBean xmlBean = new XmlSingleBean();
		xmlBean.setAge(23);
		xmlBean.setId(1000);
		xmlBean.setName("jack");
		xmlBean.setSex(true);
		

		JAXBContext context = JAXBContext.newInstance(XmlSingleBean.class);
		Marshaller marshaller = context.createMarshaller();
		marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
		
		
		File output = new File("./bean2xmlSingle.xml");
		marshaller.marshal(xmlBean, output);
		
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	

}
