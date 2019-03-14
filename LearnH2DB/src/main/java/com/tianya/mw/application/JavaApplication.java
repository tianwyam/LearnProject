package com.tianya.mw.application;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

import org.junit.Test;

/**
* Copyright: Copyright (c) 2019 tianwyam
* 
* @ClassName: JavaApplication.java
* @Description: 1、针对Java应用的嵌入式H2数据库的使用
* @version: v1.0.0
* @author: tianwyam
* @date: 2019年3月14日 下午2:45:45
 */
public class JavaApplication {
	
	
	/**
	* @Function: JavaApplication.java
	* @Description: 1、嵌入式方式（本地）使用H2数据库操作
	* 这种连接方式默认情况下只允许有一个客户端连接到H2数据库，
	* 有客户端连接到H2数据库之后，此时数据库文件就会被锁定，那么其他客户端就无法再连接了。
	* @version: v1.0.0
	* @author: tianwyam
	* @date: 2019年3月14日 下午2:50:22
	 */
	@Test
	public void embeddedLocal() {
		
		// ~ 用户目录C:\Users\用户名\h2\db.mv.db
		String jdbcURL = "jdbc:h2:~/h2/db";
		//连接数据库时使用的用户名
		final String user = "tianya";
		//连接数据库时使用的密码
		String password = "123456";
		//连接H2数据库时使用的驱动类
		//org.h2.Driver这个类是由H2数据库自己提供的，在H2数据库的jar包中可以找到
		String driverClass="org.h2.Driver";
		
		
		try {
			
			// 1、加载驱动
			Class.forName(driverClass);
			
			// 2、获取连接
			Connection connection = DriverManager.getConnection(jdbcURL, user, password);
			Statement statement = connection.createStatement();
			
			// 3、执行操作
			// 3.1、先删除表，若存在
			statement.execute("drop table user_info if exists ");
			// 3.2、创建表
			statement.execute("create table user_info(id int primary key, name varchar(10), age int , sex varchar(2) )");
			
			// 4、新增
			statement.executeUpdate("insert into user_info(id,name,age,sex) values(1,'张三',23,'男' )");
			statement.executeUpdate("insert into user_info(id,name,age,sex) values(2,'李四',25,'男' )");
			statement.executeUpdate("insert into user_info(id,name,age,sex) values(3,'王五',33,'男' )");
			statement.executeUpdate("insert into user_info(id,name,age,sex) values(4,'珠帘',23,'女' )");
			statement.executeUpdate("insert into user_info(id,name,age,sex) values(5,'鲤鱼',20,'女' )");
			
			// 5、查询
			ResultSet rs = statement.executeQuery("select * from user_info");
			while (rs.next()) {
				System.out.println(rs.getInt(1) + " - " + rs.getString(2) 
				+ " - " + rs.getInt(3)+ " - " + rs.getString(4) );
			}
			
			// 释放资源
			statement.close();
			connection.close();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	
	
	
	/**
	* @Function: JavaApplication.java
	* @Description: 2、使用TCP/IP的服务器模式(远程连接)方式连接H2数据库(推荐)
	*	这种连接方式就和其他数据库类似了，是基于Service的形式进行连接的，因此允许多个客户端同时连接到H2数据库
	* @version: v1.0.0
	* @author: tianwyam
	* @date: 2019年3月14日 下午3:59:04
	 */
	@Test
	public void tcpipServer() {
		

		/*
		 *  使用嵌入式本地方式 和 TCP/IP服务方式的区别在于 
		 *  1、jdbcURL不同
		 *  2、嵌入式本地方式不用启动H2数据库服务,直接对文件读写
		 *  3、TCP/IP服务方式必须启动服务后，才能建立起连接
		 */
		// ~ 用户目录C:\Users\用户名\h2\db.mv.db
		String jdbcURL = "jdbc:h2:tcp://localhost/~/h2/db";
		//连接数据库时使用的用户名
		final String user = "tianya";
		//连接数据库时使用的密码
		String password = "123456";
		//连接H2数据库时使用的驱动类
		//org.h2.Driver这个类是由H2数据库自己提供的，在H2数据库的jar包中可以找到
		String driverClass="org.h2.Driver";
		
		
		try {
			
			// 1、加载驱动
			Class.forName(driverClass);
			
			// 2、获取连接
			Connection connection = DriverManager.getConnection(jdbcURL, user, password);
			Statement statement = connection.createStatement();
			
			// 3、新增
			statement.executeUpdate("insert into user_info(id,name,age,sex) values(10,'吴倩',23,'女' )");
			statement.executeUpdate("insert into user_info(id,name,age,sex) values(11,'梦溪',25,'女' )");
			
			// 4、查询
			ResultSet rs = statement.executeQuery("select * from user_info");
			while (rs.next()) {
				System.out.println(rs.getInt(1) + " - " + rs.getString(2) 
				+ " - " + rs.getInt(3)+ " - " + rs.getString(4) );
			}
			
			// 释放资源
			statement.close();
			connection.close();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	
		
		
	}
	
	
	
	
	
	/**
	* @Function: JavaApplication.java
	* @Description: 3、H2数据库的内存模式
	*	H2数据库被称为内存数据库，因为它支持在内存中创建数据库和表
	*	那么我们创建的数据库和表都只是保存在内存中，一旦服务器重启，那么内存中的数据库和表就不存在了。
	*	1、首先使用TCP/IP server 方式新建一个 jdbcURL = jdbc:h2:mem:h2db 的数据库
	*	2、然后启动服务连接
	*	3、最后使用 jdbcURL = jdbc:h2:tcp://localhost/mem:h2db 来连接刚建的那个内存数据库
	* @version: v1.0.0
	* @author: tianwyam
	* @date: 2019年3月14日 下午4:07:04
	 */
	@Test
	public void memH2() {
		

		

		/*
		 *  使用内存方式 和 TCP/IP服务方式的区别在于 
		 *  1、jdbcURL不同
		 *  	内存方式多了mem前缀
		 */
		String jdbcURL = "jdbc:h2:tcp://localhost/mem:h2db";
		//连接数据库时使用的用户名
		final String user = "tianya";
		//连接数据库时使用的密码
		String password = "123456";
		//连接H2数据库时使用的驱动类
		//org.h2.Driver这个类是由H2数据库自己提供的，在H2数据库的jar包中可以找到
		String driverClass="org.h2.Driver";
		
		
		try {
			
			// 1、加载驱动
			Class.forName(driverClass);
			
			// 2、获取连接
			Connection connection = DriverManager.getConnection(jdbcURL, user, password);
			Statement statement = connection.createStatement();
			
			
			// 3、执行操作
			// 3.1、先删除表，若存在
			statement.execute("drop table user_info if exists ");
			// 3.2、创建表
			statement.execute("create table user_info(id int primary key, name varchar(10), age int , sex varchar(2) )");
						
			
			// 4、新增
			statement.executeUpdate("insert into user_info(id,name,age,sex) values(10,'吴倩',23,'女' )");
			statement.executeUpdate("insert into user_info(id,name,age,sex) values(11,'梦溪',25,'女' )");
			
			// 5、查询
			ResultSet rs = statement.executeQuery("select * from user_info");
			while (rs.next()) {
				System.out.println(rs.getInt(1) + " - " + rs.getString(2) 
				+ " - " + rs.getInt(3)+ " - " + rs.getString(4) );
			}
			
			// 释放资源
			statement.close();
			connection.close();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	
		
		
	
		
	}
	
	
	
	
	
	
	
	
	
	
	
	

}
