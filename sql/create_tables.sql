CREATE SEQUENCE IF NOT EXISTS USERS_ID_SEQ
  CACHE 10;

CREATE TABLE IF NOT EXISTS USERS (
  id integer primary key,
  username varchar(100) not null,
  user_type varchar(20) not null
);

CREATE SEQUENCE IF NOT EXISTS CONTACTS_ID_SEQ
  CACHE 10;
CREATE TABLE IF NOT EXISTS CONTACTS (
  id integer primary key,
  phone_number varchar(100),
  owned_by integer not null,
  CONSTRAINT CONTACTS_FK FOREIGN  key (owned_by) references USERS(id) on delete cascade
);




