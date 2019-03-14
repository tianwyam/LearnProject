

## H2内嵌数据库的使用



> **H2**是一个开源的嵌入式数据库引擎，采用java语言编写，不受平台的限制。
>
> 同时H2提供了一个十分方便的web控制台用于操作和管理数据库内容。
>
> H2还提供兼容模式，可以兼容一些主流的数据库，因此采用H2作为开发期的数据库非常方便。 
>
> H2作为一个嵌入型的数据库，它最大的好处就是可以嵌入到我们的Web应用中，和我们的Web应用绑定在一起，成为我们Web应用的一部分。



### 运行方式

H2数据库有三种运行方式实现：

1. 嵌入式(embedded)：可以同应用程序打包在一起发布，这样可以非常方便地存储少量结构化数据
2. TCP/IP server
3. 内存方式：可以作为缓存，作为NoSQL的一个补充。当某些场景下数据模型必须为关系型，可以拿它当Memcached使，作为后端MySQL/Oracle的一个缓冲层，缓存一些不经常变化但需要频繁访问的数据，比如字典表、权限表。



### JDBC URL



1、内嵌模式不用启动服务，已内嵌，不用显示启动服务

~~~java
String jdbcURL = "jdbc:h2:~/h2/db";
~~~



2、TCP/IP server 服务器模式，必须要显示启动服务

~~~java
String jdbcURL = "jdbc:h2:tcp://localhost/~/h2/db";
~~~

3、内存模式 

~~~java
String jdbcURL = "jdbc:h2:mem:h2db";
// 或者
String jdbcURL = "jdbc:h2:tcp://localhost/mem:h2db";
~~~



### 操作实例

H2数据库基本操作：

~~~java
// ~ 用户目录C:\Users\用户名\
String jdbcURL = "不同模式下，不同的jdbcURL，其他操作一样";

//连接数据库时使用的用户名
final String user = "tianya";

//连接数据库时使用的密码
String password = "123456";

//连接H2数据库时使用的驱动类
//org.h2.Driver 
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
~~~









