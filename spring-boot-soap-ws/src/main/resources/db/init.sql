ALTER USER 'root'@'localhost' IDENTIFIED WITH mysql_native_password BY 'password';
ALTER USER 'root'@'%' IDENTIFIED WITH mysql_native_password BY 'password';

DROP DATABASE IF EXISTS `springbootsoapws`;

CREATE DATABASE IF NOT EXISTS `springbootsoapws`;
USE `springbootsoapws`;

CREATE TABLE IF NOT EXISTS `contacts` (
  `contact_id` bigint(5) NOT NULL AUTO_INCREMENT,
  `name` varchar(30) NOT NULL,
  `nick_name` varchar(8),
  `category` varchar(30),
  `phone` varchar(30),
  `email` varchar(40),
  `city` varchar(30),
  `country` varchar(30),
  PRIMARY KEY (`contact_id`)
) ENGINE=InnoDB;

INSERT INTO `contacts` (`contact_id`, `name`, `nick_name`, `category`, `phone`, `email`, `city`, `country`) VALUES
	(1, 'Sofia T.', 'SOFT0001', 'Family', '2310234234', 'sofia@mail.com', 'Thessaloniki', 'Greece'),
	(2, 'Panagiota P.', 'PANP0001', 'Family', '2310123456', 'panagiota@mail.com', 'Athens', 'Greece'),
	(3, 'Petros P.', 'PETP0001', 'Family', '2310435235', 'petros@mail.com', 'Patra', 'Greece'),
	(4, 'Konstantinos L.', 'KONL0001', 'Friend', '2310554334', 'kostas@mail.com', 'Serres', 'Greece');


