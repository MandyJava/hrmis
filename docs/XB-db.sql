CREATE DATABASE hrmis;

use hrmis
go
create table tbl_employee(
emp_payRollNo      char(3)       primary key,
emp_telephoneCode  varchar(30)   not null,
emp_lastName       varchar(50)   not null,
emp_firstName      varchar(50)   not null,
emp_initial        char(1)       not null,
emp_deptNo         int           not null,
emp_jobTitle       varchar(50)   not null,
emp_hiringDate     varchar(50)   not null
);

create table tbl_users(
   user_no    char(6) primary key,
   user_pwd   varchar(50) not null,
   user_name  varchar(30) not null,
   user_pro   int  not  null
);

insert into tbl_users values('000102','e1adc3949ba59abbe56e057f2f883e','Mandy',1); 
insert into tbl_users values('000101','d93591bdf786e1e4ee2fca799911215','Cindy',1);
insert into tbl_users values('000103','698d51a19d8a121ce581499d7b701668','Sara',2);
insert into tbl_users values('000104','bcbe3365e6ac95ea2c343a2395834dd','Tom',2);