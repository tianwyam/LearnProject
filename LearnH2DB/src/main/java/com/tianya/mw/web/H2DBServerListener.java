//package com.tianya.mw.web;
//
//import java.util.HashMap;
//import java.util.Map;
//
//import javax.servlet.ServletContext;
//import javax.servlet.ServletContextEvent;
//import javax.servlet.ServletContextListener;
//import javax.servlet.ServletRegistration.Dynamic;
//import javax.servlet.annotation.WebListener;
//
//import org.apache.log4j.Logger;
//import org.h2.server.web.WebServlet;
//import org.h2.tools.Server;
//
///**
// * Copyright: Copyright (c) 2019 tianwyam
// * 
// * @ClassName: H2DBServerListener.java
// * @Description: 在WEB应用中启动H2数据库服务的监听器
// * @version: v1.0.0
// * @author: tianwyam
// * @date: 2019年3月18日 上午9:56:21
// */
//@WebListener
//public class H2DBServerListener implements ServletContextListener {
//
//	private transient static final Logger log = Logger.getLogger(H2DBServerListener.class);
//	private Server server;
//	
//	public static final int H2_DB_SERVER_PORT = 8082 ;
//
//	@Override
//	public void contextInitialized(ServletContextEvent event) {
//		
//		
//		try {
//			
////			log.info("启动H2数据库...");
////			log.info(String.format("TCP客户端访问端口：%s", H2_DB_SERVER_PORT));
////			// 默认端口为8082
////			server = Server.createTcpServer(
////					"-tcpPort", 
////					String.valueOf(H2_DB_SERVER_PORT), 
////					"-tcpAllowOthers").start();
////			log.info("H2数据库启动成功...");
//			
//
//			// 注册 H2数据库 web 控制台  
//			// 添加 org.h2.server.web.WebServlet
//			ServletContext servletContext = event.getServletContext();
//			Dynamic webServlet = servletContext.addServlet("H2Console", WebServlet.class);
//			// 控制台 访问路径
//			webServlet.addMapping("/console/*");
//			webServlet.setLoadOnStartup(1);
//			// 设置配置
//			Map<String, String> initParameters = new HashMap<>();
//			initParameters.put("webAllowOthers", "true");
//			initParameters.put("trace", "true");
//			webServlet.setInitParameters(initParameters);
//			
//			log.info("H2 CONSOLE 默认访问URL：http://localhost:8080/[project_name]/console/");
//			
//			
//		} catch (Exception e) {
//			log.error("H2数据库启动失败！", e);
//		}
//
//	}
//
//	@Override
//	public void contextDestroyed(ServletContextEvent event) {
//
//		// 停止服务
//		if (server != null) {
//			log.info("关闭H2数据库...");
//			server.shutdown();
//			log.info("关闭H2数据库成功...");
//		}
//	}
//
//}
