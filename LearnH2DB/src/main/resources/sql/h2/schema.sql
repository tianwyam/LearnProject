

drop table if exists h2_user ;

-- 创建表 
create table h2_user(
	id int primary key ,
	name varchar(20),
	age int(3) ,
	height int(3),
	addr varchar(50)
);

-- 创建序列
 create sequence SEQ_H2_COMMON 
 start with 1 
 increment by 1;
 
 
 -- 插入表数据
  insert into h2_user(id,name,age,height,addr) 
 values(SEQ_H2_COMMON.nextval,"小米",23,167,"四川成都");
 
 insert into h2_user(id,name,age,height,addr) 
 values(SEQ_H2_COMMON.nextval,"小雪",22,165,"四川宜宾");
 
 insert into h2_user(id,name,age,height,addr) 
 values(SEQ_H2_COMMON.nextval,"轩辕",23,178,"重庆");
 
 insert into h2_user(id,name,age,height,addr) 
 values(SEQ_H2_COMMON.nextval,"大侠",20,167,"北京");
 
 -- 提交
 commit ;
 
 
 