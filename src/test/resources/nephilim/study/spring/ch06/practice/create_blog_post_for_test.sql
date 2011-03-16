/*
create tablespace test 
datafile 'C:\oraclexe\oradata\XE\TEST.DBF' 
size 100M
default storage ( 
    initial 50k 
    next 50k 
    minextents 2 
    maxextents 50 
    pctincrease 50
)

create user test
identified by test
default tablespace test
temporary tablespace temp

grant connect, resource to test
*/

drop sequence seq_blog_id;
drop sequence seq_post_id;

create sequence seq_blog_id 
start with 1
increment by 1;

create sequence seq_post_id 
start with 1
increment by 1;

create table blog
(	
	id		int 		  not null,
	name 	varchar2(200) not null,
	address	varchar2(200) not null,
	email   varchar2(200) not null
)

alter table blog 
add primary key (id)

create table post
(
	blog_id 	int 		not null,	
	id 			int 		not null,
	title		varchar2(400),
	content 	varchar2(1000),
	created 	timestamp
)

alter table post
add primary key (id)

alter table post
add foreign key (blog_id) references blog(id)

	