-- It is the database structure for SQL DB. I use PostgreSQL in this tutorial,
-- hence you need to adopt with script for your SQL DB.

-- main table, contain acl name
create table ss_acl (
	id serial primary key,
	name varchar(200)
);

create table ss_key (
	id serial primary key,
	key varchar(2000),
	acl_id int references ss_acl(id)
);

create table ss_class (
  id serial primary key,
  acl_id int references ss_acl(id),
  class_name varchar(2000),
  canCreate boolean,
  canUpdate boolean,
  canRead boolean,
  canDelete boolean
);

create table ss_instance (
  id serial primary key,
  acl_id int references ss_acl(id),
  class_name varchar(2000),
  instance_id varchar(2000),
  canCreate boolean,
  canUpdate boolean,
  canRead boolean,
  canDelete boolean
);

-- bussiness models

create table client(
	id serial primary key,
	login varchar(2000),
  password varchar(2000)
);

-- insert block

insert into client(login, password) values('Petr Arsentev', 'password');
insert into client(login, password) values('Stive', 'password');
insert into client(login, password) values('Max', 'password');

insert into ss_acl(name) values('Admin acl');
insert into ss_key(key, acl_id) values('admin', (select id from ss_acl where name='Admin acl' limit 1));
insert into ss_class(acl_id, class_name, canCreate, canUpdate, canRead, canDelete)
values((select id from ss_acl where name='Admin acl' limit 1), 'ru.mongo.acl.models.Client', true, true, true, true);
insert into ss_instance(acl_id, class_name, instance_id, canCreate, canUpdate, canRead, canDelete)
values((select id from ss_acl where name='Admin acl' limit 1), 'ru.mongo.acl.models.Client', (select id from client where login='Petr Arsentev' limit 1), true, true, true, true);