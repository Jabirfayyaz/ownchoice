CREATE SCHEMA `buyer` ;

CREATE TABLE `ref_user_type` (
  `ref_user_type_id` int(11) NOT NULL AUTO_INCREMENT,
  `description` varchar(255) DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`ref_user_type_id`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=latin1;


CREATE TABLE `user` (
  `user_id` int(11) NOT NULL AUTO_INCREMENT,
  `address` varchar(255) DEFAULT NULL,
  `company` varchar(255) DEFAULT NULL,
  `email` varchar(255) DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL,
  `password` varchar(255) DEFAULT NULL,
  `user_name` varchar(255) DEFAULT NULL,
  `ref_user_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`user_id`),
  UNIQUE KEY `UK_r8hdsms90jy27ogknpmthq2pl` (`user_name`),
  KEY `FK_aoxcsdtdongqg13d8kae53onk` (`ref_user_id`),
  CONSTRAINT `FK_aoxcsdtdongqg13d8kae53onk` FOREIGN KEY (`ref_user_id`) REFERENCES `ref_user_type` (`ref_user_type_id`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=latin1;



INSERT INTO ref_user_type(name, description)
VALUES('Admin','Administrator');

INSERT INTO ref_user_type(name, description)
VALUES('Suppliers','Suppliers - Who post their product information');

INSERT INTO ref_user_type(name, description)
VALUES('Corporates','Corporates - Who can View the products');


INSERT INTO ref_user_type(name, description)
VALUES('Employees','Employees - Who can do a subset of corporate login');


INSERT INTO user(user_name,ref_user_id, password,name,email,company,address)
VALUES('DefaultAdmin', 1, 'simple', 'Admin Name', 'test@test.com','New Company', 'USA');