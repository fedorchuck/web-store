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
  CONSTRAINT users_pkey PRIMARY KEY (id)
)
WITH (
  OIDS=FALSE
);
ALTER TABLE users
  OWNER TO postgres;

-- Table: goods
-- DROP TABLE goods;
CREATE TABLE goods
(
  id serial NOT NULL,
  name text NOT NULL,
  manufacturer text NOT NULL,
  cost numeric NOT NULL,
  characteristics text NOT NULL,
  description text,
  CONSTRAINT goods_pkey PRIMARY KEY (id)
)
WITH (
OIDS=FALSE
);
ALTER TABLE goods
OWNER TO postgres;