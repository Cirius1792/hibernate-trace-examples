CREATE SEQUENCE HR.USERS_ID_SEQ
  CACHE 10;

CREATE TABLE HR.USERS (
  id integer primary key,
  username varchar(100) not null,
  user_type varchar(20) not null
);

CREATE SEQUENCE HR.CONTACTS_ID_SEQ
  CACHE 10;
CREATE TABLE  HR.CONTACTS (
  id integer primary key,
  phone_number varchar(100),
  owned_by integer not null,
  CONSTRAINT CONTACTS_FK FOREIGN  key (owned_by) references HR.USERS(id) on delete cascade
);




