use testdb;
create table tbl_member
(
    userid varchar(50) not null primary key,
    userpw varchar(100) not null,
    username varchar(100) not null,
    regdate datetime default now(),
    updatedate datetime default now(),
    enabled char(1) default '1'
);

create table tbl_member_auth(
    userid varchar(50) not null,
    auth varchar(50) not null,
    constraint fk_member_auth foreign key(userid) references tbl_member(userid)
);