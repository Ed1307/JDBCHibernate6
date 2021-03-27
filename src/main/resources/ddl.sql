use library;

drop table author;
drop table book;

create table author(
id int not null auto_increment primary key,
name varchar(45),
last_name varchar(45));

create table book(
id int not null auto_increment primary key,
name varchar(45),
author_id int not NULL ,
foreign key(author_id) references author(id));

DROP TABLE hibernate_sequence;

DELETE FROM book;


