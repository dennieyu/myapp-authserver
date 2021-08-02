-- ////////////////////////////////////////////////////////////////
-- postgresql
-- ////////////////////////////////////////////////////////////////
create database myapp encoding 'UTF-8';
create user myapp password '1234' CREATEDB;
create schema if not exists myapp authorization myapp;
grant all privileges on database myapp to myapp;

-- ////////////////////////////////////////////////////////////////
-- create
-- ////////////////////////////////////////////////////////////////
CREATE TABLE myapp.oauth_client_details (
  client_id character varying(255) NOT NULL,
  resource_ids character varying(255),
  client_secret character varying(255),
  scope character varying(255),
  authorized_grant_types character varying(255),
  web_server_redirect_uri character varying(255),
  authorities character varying(255),
  access_token_validity integer,
  refresh_token_validity integer,
  additional_information character varying(4096),
  autoapprove character varying(255),
  CONSTRAINT oauth_client_details_pkey PRIMARY KEY (client_id)
);

CREATE TABLE myapp.oauth_client_token (
  token_id VARCHAR(256),
  token bytea,
  authentication_id VARCHAR(256),
  user_name VARCHAR(256),
  client_id VARCHAR(256)
);

CREATE TABLE myapp.oauth_access_token (
  token_id VARCHAR(256),
  token bytea,
  authentication_id VARCHAR(256),
  user_name VARCHAR(256),
  client_id VARCHAR(256),
  authentication bytea,
  refresh_token VARCHAR(256)
);

CREATE TABLE myapp.oauth_refresh_token (
  token_id VARCHAR(256),
  token bytea,
  authentication bytea
);

CREATE TABLE myapp.oauth_code (
  code VARCHAR(256), authentication bytea
);

CREATE TABLE myapp.oauth_approvals (
  userId VARCHAR(256),
  clientId VARCHAR(256),
  scope VARCHAR(256),
  status VARCHAR(10),
  expiresAt TIMESTAMP,
  lastModifiedAt TIMESTAMP
);

CREATE TABLE myapp.tbl_oauth_permission (
  id serial NOT NULL,
  name character varying(60),
  CONSTRAINT permission_pkey PRIMARY KEY (id)
);

CREATE TABLE myapp.tbl_oauth_role (
  id serial NOT NULL,
  name character varying(60),
  CONSTRAINT role_pkey PRIMARY KEY (id)
);

CREATE TABLE myapp.tbl_oauth_user (
  id serial NOT NULL,
  username character varying(24) NOT NULL,
  password character varying(255) NOT NULL,
  type character varying(8) NOT NULL,
  enabled bool NOT NULL,
  account_expired bool NOT NULL,
  credentials_expired bool NOT NULL,
  account_locked bool NOT NULL,
  CONSTRAINT user_pkey PRIMARY KEY (id)
);

CREATE TABLE myapp.tbl_oauth_permission_role (
  permission_id integer,
  role_id integer,
  CONSTRAINT permission_role_permission_id_fkey FOREIGN KEY (permission_id)
      REFERENCES myapp.tbl_oauth_permission (id) MATCH SIMPLE
      ON UPDATE NO ACTION ON DELETE NO ACTION,
  CONSTRAINT permission_role_role_id_fkey FOREIGN KEY (role_id)
      REFERENCES myapp.tbl_oauth_role (id) MATCH SIMPLE
      ON UPDATE NO ACTION ON DELETE NO ACTION
);

CREATE TABLE myapp.tbl_oauth_role_user (
  role_id integer,
  user_id integer,
  CONSTRAINT role_user_role_id_fkey FOREIGN KEY (role_id)
      REFERENCES myapp.tbl_oauth_role (id) MATCH SIMPLE
      ON UPDATE NO ACTION ON DELETE NO ACTION,
  CONSTRAINT role_user_user_id_fkey FOREIGN KEY (user_id)
      REFERENCES myapp.tbl_oauth_user (id) MATCH SIMPLE
      ON UPDATE NO ACTION ON DELETE NO ACTION
);

-- ////////////////////////////////////////////////////////////////
-- insert
-- ////////////////////////////////////////////////////////////////
INSERT INTO myapp.oauth_client_details (
	CLIENT_ID, CLIENT_SECRET,  
	RESOURCE_IDS, 
	SCOPE, 
	AUTHORIZED_GRANT_TYPES, 
	WEB_SERVER_REDIRECT_URI, AUTHORITIES, 
	ACCESS_TOKEN_VALIDITY, REFRESH_TOKEN_VALIDITY, 
	ADDITIONAL_INFORMATION, AUTOAPPROVE)
	VALUES
	('client', 'password', 
	'myapp', 
	'read_profile', 
	'authorization_code,implicit,password,client_credentials,refresh_token', 
	'http://localhost:9000/callback', 'role_message', 
	3600, 86400, 
	'{}', NULL);

INSERT INTO myapp.tbl_oauth_permission (
	NAME) VALUES 
	('create_brand'), 
	('select_brand'), 
	('update_brand'), 
	('delete_brand'), 
	('create_chatbot'), 
	('select_chatbot'), 
	('update_chatbot'), 
	('delete_chatbot'), 
	('create_template'), 
	('select_template'), 
	('update_template'), 
	('delete_template'), 
	('create_message');

INSERT INTO myapp.tbl_oauth_role (
	NAME) 
	VALUES 
	('role_brand'), ('role_chatbot'), ('role_template'), ('role_message');

INSERT INTO myapp.tbl_oauth_permission_role (
	PERMISSION_ID, ROLE_ID) 
	VALUES 
	(1, 1),
	(2, 1),
	(3, 1),
	(4, 1),
	(5, 2),
	(6, 2),
	(7, 2),
	(8, 2),
	(9, 3),
	(10, 3),
	(11, 3),
	(12, 3),
	(13, 4);

INSERT INTO myapp.tbl_oauth_user (
	USERNAME, PASSWORD, TYPE, ENABLED, ACCOUNT_EXPIRED, CREDENTIALS_EXPIRED, ACCOUNT_LOCKED) 
	VALUES 
	('admin', 'pass', 'ADMIN', true, false, false, false), 
	('user', 'pass', 'USER', true, false, false, false);

INSERT INTO myapp.tbl_oauth_role_user (
	ROLE_ID, USER_ID)
	VALUES 
	(1, 1), 
	(2, 1), 
	(3, 1), 
	(4, 1), 
	(1, 2);
