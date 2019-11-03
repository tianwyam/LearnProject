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
	
	
	/**
	* @Function: BusiServiceUtils.java
	* @Description: 获取方法参数名称
	* @param clazz 目标对象
	* @param methodName 方法
	* @return 此方法对应的参数名称
	* @version: v1.0.0
	* @author: tianwyam
	* @date: 2019年11月3日 下午12:10:00
	 */
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
	
	
	/**
	* @Function: BusiServiceUtils.java
	* @Description: 获取方法的参数的名称
	* @param clazz 目标对象
	* @param methodName 方法名称
	* @param parameterTypes 参数类型
	* @return 返回参数名称
	* @version: v1.0.0
	* @author: tianwyam
	* @date: 2019年11月3日 下午12:18:05
	 */
	public static String[] getMethodParamName(Class<?> clazz, String methodName, Class<?>[] parameterTypes) {
		try {
			Method method = clazz.getDeclaredMethod(methodName, parameterTypes);
			LocalVariableTableParameterNameDiscoverer parameterNameDiscoverer = new LocalVariableTableParameterNameDiscoverer();
			return parameterNameDiscoverer.getParameterNames(method);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	
	
	
	
	
	
	

	public static void main(String[] args) {
		
		String[] methodParamName = getMethodParamName(BusiService.class, "getOrderInfo");
		for (String paramName : methodParamName) {
			System.out.println(paramName);
		}
		
	}
	
}
