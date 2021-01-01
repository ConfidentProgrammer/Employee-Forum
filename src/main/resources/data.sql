DROP TABLE IF EXISTS threadComment;
DROP TABLE IF EXISTS threads;
create table threads(
	id LONG PRIMARY KEY AUTO_INCREMENT,
	name varchar(20),
	content varchar(1200) not null,
	department varchar(1200) not null,
	threadDate varchar(40),
	threadTime varchar(30)
);
INSERT into threads(name, content, department,threadDate, threadTime) VALUES ('Deep', 'this is very good app i liked it haha', 'Software Engineering',  '2020-01-01', '12:00:00 PM');
INSERT into threads(name, content, department,threadDate, threadTime) VALUES ('Nikolai Sir', 'You are very good employee Deep', 'HR department',  '2023-01-01', '12:00:00 AM');
INSERT into threads(name, content, department,threadDate, threadTime) VALUES ('Anchal', 'Our infrastucture needs to get some improvement', 'Cyber Security',  '2020-01-01', '7:00:00 AM');
INSERT into threads(name, content, department,threadDate, threadTime) VALUES ('Leone', 'our companys revenue is growing day by day that is so good', 'Designing',  '2024-01-01', '6:00:00 AM');


create table threadComment(
	cid LONG,
	namethread varchar(20) not null,
	contentthread varchar(1200) not null,
	FOREIGN KEY (cid) REFERENCES threads(id)
);
insert into threadComment(namethread, contentthread, cid) values ('Bottle', 'yeah you are absolutely right',1);
insert into threadComment(namethread, contentthread, cid) values ('Summy', 'yeah you are absolutely right',1);
insert into threadComment(namethread, contentthread, cid) values ('Paani', 'yeah you are absolutely right',4);