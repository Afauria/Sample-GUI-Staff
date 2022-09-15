SET character_set_client=utf8;
SET character_set_server=utf8;
SET character_set_database=utf8;

create database IF NOT EXISTS db_staff;

use db_staff;

create table IF NOT EXISTS tb_staff(
staff_id INT AUTO_INCREMENT PRIMARY KEY,
username VARCHAR(20) UNIQUE NOT NULL,
passwd VARCHAR(20) NOT NULL,
name VARCHAR(20),
phone VARCHAR(20),
department VARCHAR(20),
gender INT,
is_manager BOOLEAN DEFAULT FALSE
) DEFAULT CHARSET=utf8mb4; 

INSERT INTO tb_staff(username, passwd, name, phone, department, gender, is_manager) VALUES("111","111","小红","11111111111", "部门1", 1, FALSE);
INSERT INTO tb_staff(username, passwd, name, phone, department, gender, is_manager) VALUES("222","222","小明","22222222222", "部门2", 0, FALSE);
INSERT INTO tb_staff(username, passwd, name, phone, department, gender, is_manager) VALUES("333","333","小强","33333333333", "部门3", 0, TRUE);