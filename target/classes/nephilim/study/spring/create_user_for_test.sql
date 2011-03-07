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

create table users
(	
	id 			varchar2(100) not null,
	name 		varchar2(100),
	password 	varchar2(100)
)

alter table users
	add (
		user_level number(1) not null,
		login	   number(10) not null,
		recommended number(10) not null
	);



	