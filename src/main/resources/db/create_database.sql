DROP DATABASE IF EXISTS projects;
DROP USER IF EXISTS projects_user;

USE mysql;
CREATE DATABASE projects DEFAULT CHARACTER SET utf8 COLLATE utf8_unicode_ci;
CREATE USER 'projects_user'@'%' IDENTIFIED BY 'pass';
GRANT ALL ON projects.* TO 'projects_user'@'%';
FLUSH PRIVILEGES;
