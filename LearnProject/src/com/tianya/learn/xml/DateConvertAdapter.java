package com.tianya.learn.xml;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;

import javax.xml.bind.annotation.adapters.XmlAdapter;


/**
* Copyright: Copyright (c) 2019 tianwyam
* 
* @ClassName: DateConvertAdapter.java
* @Description: 日期转换适配器
* @version: v1.0.0
* @author: tianwyam
* @date: 2019年4月6日 下午5:07:33
 */
public class DateConvertAdapter extends XmlAdapter<String, Timestamp>{
	
	private SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

	@Override
	public Timestamp unmarshal(String v) throws Exception {
		return new Timestamp(format.parse(v).getTime());
	}

	@Override
	public String marshal(Timestamp v) throws Exception {
		return format.format(v);
	}

}
