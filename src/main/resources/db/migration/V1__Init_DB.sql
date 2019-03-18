create sequence hibernate_sequence start 1 increment 1;

create table client (
  id int8 not null,
  fio_id int8,
  org_id int8,
  primary key (id)
);

create table fio (
  id int8 not null,
  fio varchar(255),
  primary key (id)
);

create table organization (
  id int8 not null,
  org_name varchar(255),
primary key (id)
);

create table statement (
  id int8 not null,
  comment varchar(2048),
  filename varchar(255),
  type varchar(255),
  packfilename varchar(255),
  zlfilename varchar(255),
  reg_date timestamp,
  status varchar(255),
  catalog_number varchar(255),
  user_id int8,
  client_id int8,
  exec_id int8,
  primary key (id)
);

create table user_role (
  user_id int8 not null,
  roles varchar(255)
);

create table usr (
  id int8 not null,
  username varchar(255) not null,
  password varchar(255) not null,
  active boolean not null,


  fio_id int8,
  primary key (id)
);

alter table if exists client
  add constraint client_fio_fk
  foreign key (fio_id) references fio;

alter table if exists client
  add constraint client_org_fk
  foreign key (org_id) references organization;

alter table if exists statement
  add constraint statement_user_fk
  foreign key (user_id) references usr;

alter table if exists statement
  add constraint statement_client_fk
  foreign key (client_id) references client;

alter table if exists statement
  add constraint statement_exec_fk
  foreign key (exec_id) references usr;

alter table if exists user_role
  add constraint role_user_fk
  foreign key (user_id) references usr;

alter table if exists usr
  add constraint user_fio_fk
  foreign key (fio_id) references fio;