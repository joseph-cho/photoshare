DROP TABLE Pictures;
DROP TABLE Users;
DROP SEQUENCE Pictures_picture_id_seq;
DROP SEQUENCE Users_user_id_seq;

CREATE SEQUENCE Pictures_picture_id_seq
  INCREMENT 1
  MINVALUE 1
  MAXVALUE 9223372036854775807
  START 14
  CACHE 1;

CREATE TABLE Pictures
(
  picture_id int4 NOT NULL DEFAULT nextval('Pictures_picture_id_seq'),
  caption varchar(255) NOT NULL,
  imgdata bytea NOT NULL,
  size int4 NOT NULL,
  content_type varchar(255) NOT NULL,
  thumbdata bytea NOT NULL,
  owner_id int4 NOT NULL,
  CONSTRAINT pictures_pk PRIMARY KEY (picture_id),
  CONSTRAINT owner_id_fk FOREIGN KEY (owner_id) REFERENCES Users(user_id)
); 

CREATE SEQUENCE Users_user_id_seq
  INCREMENT 1
  MINVALUE 1
  MAXVALUE 9223372036854775807
  START 14
  CACHE 1;

CREATE TABLE Users
(
  user_id int4 NOT NULL DEFAULT nextval('Users_user_id_seq'),
  email varchar(255) NOT NULL,
  password varchar(255) NOT NULL,
  role_name varchar(255) NOT NULL DEFAULT 'RegisteredUser',
  CONSTRAINT users_pk PRIMARY KEY (user_id),
  firstname varchar(255) NOT NULL,
  lastname varchar(255) NOT NULL,
  gender varchar(1),
  hometown varchar(255),
  date_of_birth date NOT NULL 
);

CREATE SEQUENCE Albums_album_id_seq
  INCREMENT 1
  MINVALUE 1
  MAXVALUE 9223372036854775807
  START 14
  CACHE 1;

CREATE TABLE Albums 
(
  album_id int4 NOT NULL DEFAULT nextval('Albums_album_id_seq'),
  owner_id int4 NOT NULL,
  name varchar(255) NOT NULL,
  date_created TIMESTAMP,
  CONSTRAINT albums_pk PRIMARY KEY (album_id),
  CONSTRAINT owner_id FOREIGN KEY (owner_id) REFERENCES Users(user_id)
);

CREATE SEQUENCE Comments_comment_id_seq
  INCREMENT 1
  MINVALUE 1
  MAXVALUE 9223372036854775807
  START 14
  CACHE 1;

CREATE TABLE Comments 
(
  comment_id int4 NOT NULL DEFAULT nextval('Comments_comment_id_seq'),
  comment_text varchar(255),
  owner_id int4 NOT NULL,
  picture_id int4 NOT NULL,
  date_created date NOT NULL,
  CONSTRAINT comment_pk PRIMARY KEY (comment_id),
  CONSTRAINT owner_id_fk FOREIGN KEY (owner_id) REFERENCES Users(user_id),
  CONSTRAINT picture_id_fk FOREIGN KEY (picture_id) REFERENCES Pictures(picture_id)
);

CREATE SEQUENCE Picturetags_tag_id_seq
  INCREMENT 1
  MINVALUE 1
  MAXVALUE 9223372036854775807
  START 14
  CACHE 1;

CREATE TABLE Tags 
(
  picture_id int4 NOT NULL,
  tag_id int4 NOT NULL DEFAULT nextval('Picturetags_tag_id_seq'),
  word varchar(255),
  CONSTRAINT tags_pk PRIMARY KEY (tag_id),
  CONSTRAINT picture_id_fk FOREIGN KEY (picture_id) REFERENCES Pictures(picture_id)
);

CREATE TABLE Friends 
(
  user_id_1 int4 NOT NULL,
  user_id_2 int4 NOT NULL,
  CONSTRAINT friends_pk PRIMARY KEY (user_id_1, user_id_2),
  CONSTRAINT user_id_1_fk FOREIGN KEY (user_id_1) REFERENCES Users(user_id),
  CONSTRAINT user_id_2_fk FOREIGN KEY (user_id_2) REFERENCES Users(user_id)
);

CREATE TABLE Album_Contains
(
  album_id int4 NOT NULL,
  picture_id int4 NOT NULL,
  CONSTRAINT album_contains_pk PRIMARY KEY (album_id, picture_id),
  CONSTRAINT album_id_fk FOREIGN KEY (album_id) REFERENCES Albums(album_id),
  CONSTRAINT picture_id_fk FOREIGN KEY (picture_id) REFERENCES Pictures(picture_id)
);

INSERT INTO Users (email, password) VALUES ('test@bu.edu', 'test');









 