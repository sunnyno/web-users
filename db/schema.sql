CREATE DATABASE users;

USE users;

CREATE TABLE users (
  id         BIGINT PRIMARY KEY AUTO_INCREMENT,
  first_name VARCHAR(50),
  last_name  VARCHAR(50),
  age        INT
);