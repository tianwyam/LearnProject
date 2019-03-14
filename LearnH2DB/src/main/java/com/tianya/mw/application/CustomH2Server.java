package com.tianya.mw.application;

import java.sql.SQLException;

import org.h2.tools.Server;


/**
* Copyright: Copyright (c) 2019 tianwyam
* @ClassName: CostomH2Server.java
* @Description: 自定义 代码实现 H2数据库服务开启
* @version: v1.0.0
* @author: tianwyam
* @date: 2019年3月14日 下午5:36:59
 */
public class CustomH2Server {
	
	private static Server server ;
	
	
	/**
	* @Description: 开启 H2数据库服务
	* @version: v1.0.0
	* @author: tianwyam
	* @date: 2019年3月14日 下午5:36:41
	 */
	public static void start() {
		
		/**
		 * -web：启动支持H2 Console的服务
		 * -webPort <port>：服务启动端口，默认为8082
		 * -browser：启动H2 Console web管理页面
		 * -tcp：使用TCP server模式启动
		 * -pg：使用PG server模式启动
		 */
		try {
			System.out.println("H2数据库服务开启...");
			server = Server.createTcpServer().start();
			System.out.println("H2数据库服务开启成功...");
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("H2数据库服务开启失败...");
		}
		
	}
	
	
	
	/**
	* @Description: 关闭  H2数据库服务
	* @version: v1.0.0
	* @author: tianwyam
	* @date: 2019年3月14日 下午5:35:42
	 */
	public static void stop() {
		System.out.println("H2数据库服务关闭...");
		if (server != null) {
			server.stop();
			System.out.println("H2数据库服务关闭成功...");
		}
	}

}
