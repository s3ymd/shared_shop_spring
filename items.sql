create table items (id int primary key, name varchar2(100), price int);
create sequence item_seq start with 1;
insert into items values (item_seq.nextval, 'りんご', 100);
insert into items values (item_seq.nextval, 'みかん', 200);
insert into items values (item_seq.nextval, 'よくわかるJava', 2000);
insert into items values (item_seq.nextval, 'よくわかるOracle', 3000);
commit;
