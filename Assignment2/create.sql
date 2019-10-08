USE `cs5200_Assignment2`;

CREATE TABLE `person` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `firstName` varchar(45) DEFAULT NULL,
  `lastName` varchar(45) DEFAULT NULL,
  `username` varchar(45) DEFAULT NULL,
  `password` varchar(45) DEFAULT NULL,
  `email` varchar(45) DEFAULT NULL,
  `dob` datetime DEFAULT NULL,
  PRIMARY KEY (`id`)
);

CREATE TABLE `developer` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `developerkey` varchar(45) DEFAULT NULL,
  KEY `developer_person_generalization_idx` (`id`),
  CONSTRAINT `developer_person_generalization` FOREIGN KEY (`id`) REFERENCES `person` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
);

CREATE TABLE `user` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `userAgreement` tinyint(4) DEFAULT NULL,
  `userkey` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`id`),
  CONSTRAINT `user_person_generalization` FOREIGN KEY (`id`) REFERENCES `person` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
);

CREATE TABLE `website` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(45) DEFAULT NULL,
  `description` varchar(255) DEFAULT NULL,
  `created` datetime DEFAULT NULL,
  `updated` datetime DEFAULT NULL,
  `visits` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`)
);

CREATE TABLE `page` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `title` varchar(45) DEFAULT NULL,
  `description` varchar(255) DEFAULT NULL,
  `created` date DEFAULT NULL,
  `updated` date DEFAULT NULL,
  `views` int(11) DEFAULT NULL,
  `websiteID` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`)
);

CREATE TABLE `widget` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(45) DEFAULT NULL,
  `width` int(11) DEFAULT NULL,
  `height` int(11) DEFAULT NULL,
  `cssClass` varchar(45) DEFAULT NULL,
  `cssStyle` varchar(45) DEFAULT NULL,
  `text` varchar(45) DEFAULT NULL,
  `order` int(11) DEFAULT NULL,
  `DTYPE` enum('WIDGET','HEADING','HTML','YOUTUBE','IMAGE') DEFAULT NULL,
  `url` varchar(45) DEFAULT NULL,
  `shareable` tinyint(4) DEFAULT NULL,
  `expandable` tinyint(4) DEFAULT NULL,
  `src` varchar(45) DEFAULT NULL,
  `html` varchar(45) DEFAULT NULL,
  `size` int(11) DEFAULT '2',
  `pageID` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`)
);

CREATE TABLE `address` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `street1` varchar(45) DEFAULT NULL,
  `street2` varchar(45) DEFAULT NULL,
  `city` varchar(45) DEFAULT NULL,
  `state` varchar(45) DEFAULT NULL,
  `zip` varchar(45) DEFAULT NULL,
  `primary` tinyint(4) DEFAULT NULL,
  `personID` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`)
);

CREATE TABLE `phone` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `phone` varchar(45) DEFAULT NULL,
  `primary` tinyint(4) DEFAULT NULL,
  `personID` int(11) DEFAULT NULL,
 PRIMARY KEY (`id`)
);

CREATE TABLE `Role` (
  `name` varchar(45) NOT NULL,
  PRIMARY KEY (`name`)
);

CREATE TABLE `Priviledge` (
  `name` varchar(45) NOT NULL,
  PRIMARY KEY (`name`)
);

CREATE TABLE `websiterole` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `role` varchar(45) DEFAULT NULL,
  `developerID` int(11) DEFAULT NULL,
  `websiteID` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `Role_idx` (`role`),
  CONSTRAINT `Role` FOREIGN KEY (`role`) REFERENCES `Role` (`name`)
);

CREATE TABLE `pagerole` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `role` varchar(45) DEFAULT NULL,
  `developerID` int(11) DEFAULT NULL,
  `pageID` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `Role_idx` (`role`),
  CONSTRAINT `Role1` FOREIGN KEY (`role`) REFERENCES `Role` (`name`)
);

CREATE TABLE `pagepriviledge` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `priviledge` varchar(45) DEFAULT NULL,
  `developerID` int(11) DEFAULT NULL,
  `pageID` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `Priviledge_idx` (`priviledge`),
  CONSTRAINT `Priviledge` FOREIGN KEY (`priviledge`) REFERENCES `Priviledge` (`name`)
);

CREATE TABLE `websitepriviledge` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `priviledge` varchar(45) DEFAULT NULL,
  `developerID` int(11) DEFAULT NULL,
  `websiteID` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `priviledge1_idx` (`priviledge`),
  CONSTRAINT `priviledge1` FOREIGN KEY (`priviledge`) REFERENCES `Priviledge` (`name`) ON DELETE NO ACTION ON UPDATE NO ACTION
);