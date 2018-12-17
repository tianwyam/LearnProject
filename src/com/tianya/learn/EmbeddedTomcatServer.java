package com.tianya.learn;

import java.io.File;

import org.apache.catalina.LifecycleException;
import org.apache.catalina.core.AprLifecycleListener;
import org.apache.catalina.core.StandardServer;
import org.apache.catalina.startup.Tomcat;

public class EmbeddedTomcatServer {
	
	private static Tomcat tomcat ;
	
	public static void start(int port, String contextPath, String docBase) {
		
		try {
			
			// 创建一个tomcat实例
			tomcat = new Tomcat();
			
			// 设置端口
			tomcat.setPort(port);
			// 设置基本目录
			tomcat.setBaseDir(".");
			// 设置编码
			tomcat.getConnector().setURIEncoding("UTF-8");
			
			// 监听生命周期
			StandardServer server = (StandardServer)tomcat.getServer();
			AprLifecycleListener listener = new AprLifecycleListener();
			server.addLifecycleListener(listener);
			
			// 设置 项目根目录 和 webApp目录
			tomcat.addWebapp(contextPath, docBase);
			
			// 开启
			tomcat.start();
			tomcat.getServer().await();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	
	public static void stop() {
		
		if (tomcat != null) {
			try {
				tomcat.stop();
			} catch (LifecycleException e) {
				e.printStackTrace();
			}
		}
	}

	
	
	public static void main(String[] args) {
		
		int port = 8099 ;
		String contextPath = "/" ;
		String docBase = "" ;
		
		File file = new File("resources");
		if (!file.exists()) {
			file.mkdirs();
		}
		docBase = file.getAbsolutePath();
		
		// 启动访问
		EmbeddedTomcatServer.start(port, contextPath, docBase);
		
	}
	
	
}
