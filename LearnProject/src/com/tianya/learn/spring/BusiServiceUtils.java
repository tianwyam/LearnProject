package com.tianya.learn.spring;

import java.lang.reflect.Method;

import org.springframework.core.LocalVariableTableParameterNameDiscoverer;

import com.tianya.learn.spring.service.BusiService;


/**
* Copyright: Copyright (c) 2019 tianwyam
* 
* @ClassName: BusiServiceUtils.java
* @Description: 获取方法参数名称
* @version: v1.0.0
* @author: tianwyam
* @date: 2019年11月3日 上午11:39:06
 */
public class BusiServiceUtils {
	
	
	public static String[] getMethodParamName(Class<?> clazz, String methodName)  {
		
		try {
			Method[] methods = clazz.getDeclaredMethods();
			for (Method method : methods) {
				if (methodName.equals(method.getName())) {
					LocalVariableTableParameterNameDiscoverer parameterNameDiscoverer = new LocalVariableTableParameterNameDiscoverer();
					return parameterNameDiscoverer.getParameterNames(method);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return new String[0];
	}
	

	public static void main(String[] args) {
		
		String[] methodParamName = getMethodParamName(BusiService.class, "getOrderInfo");
		for (String paramName : methodParamName) {
			System.out.println(paramName);
		}
		
	}
	
}
