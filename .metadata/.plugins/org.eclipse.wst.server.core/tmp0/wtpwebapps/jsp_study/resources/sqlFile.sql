-- 2024-10-22
-- javaUser / mysql / javadb

-- 게시판(board)
create table board (
 bno int auto_increment,
 title varchar(100) not null,
 writer varchar(100) not null,
 content text,
 regdate datetime default now(),
 moddate datetime default now(),
 primary key(bno) );

 -- 2024-10-23
create table comment (
 cno int auto_increment,
 bno int not null,
 content varchar(2000) not null,
 writer varchar(500) default "unknown",
 regdate datetime default now(),
 primary key(cno)
);

-- 2024-10-25
create table member(
id varchar(100) pk not null,
pwd varchar(100) not null,
email varchar(200) not null,
phone varchar(50),
regdate datetime default now(),
lastlogin datetime default now(),
primary key(id)
);
