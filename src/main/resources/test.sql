DROP TABLE IF EXISTS `user`;
CREATE TABLE `user` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(50) DEFAULT NULL,
  `introduce` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`id`)
);