-- Database: webstore
-- DROP DATABASE webstore;
CREATE DATABASE webstore
WITH OWNER = --your user
ENCODING = 'UTF8'
TABLESPACE = pg_default
LC_COLLATE = 'en_US.UTF-8'
LC_CTYPE = 'en_US.UTF-8'
CONNECTION LIMIT = -1;
-- Table: users
-- DROP TABLE users;
CREATE TABLE users
(
  id serial NOT NULL,
  username text NOT NULL,
  password text NOT NULL,
  email text NOT NULL,
  firstname text NOT NULL,
  lastname text NOT NULL,
  role integer,
  user_id uuid,
  CONSTRAINT users_pkey PRIMARY KEY (id)
)
WITH (
  OIDS=FALSE
);
-- should run script under correct user
-- ALTER TABLE users
-- OWNER TO postgres;

-- Table: goods
-- DROP TABLE goods;
CREATE TABLE goods
(
  id serial NOT NULL,
  commodity_id uuid,
  name text NOT NULL,
  manufacturer text NOT NULL,
  cost numeric NOT NULL,
  quantity integer,
  sell_out boolean,
  characteristics text NOT NULL,
  description text,
  category text,
  addedBy uuid,
  CONSTRAINT goods_pkey PRIMARY KEY (id)
)
WITH (
OIDS=FALSE
);
-- should run script under correct user
-- ALTER TABLE goods
-- OWNER TO postgres;





