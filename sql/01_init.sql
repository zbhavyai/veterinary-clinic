-- database schema creation
CREATE DATABASE IF NOT EXISTS vetdb;
USE vetdb;

-- user creation
CREATE USER IF NOT EXISTS 'vetapp'@'%' IDENTIFIED BY 'vetpassword';
GRANT ALL ON vetdb.* TO 'vetapp'@'%';
