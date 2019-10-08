USE `cs5200_Assignment2`;

INSERT INTO `person` VALUES (12,'Alice','Wonder','alice','alice','alice@wonder.com',NULL),(23,'Bob','Marley','bob','bob','bob@marley.com',NULL),(34,'Charles','Garcia','charlie','charlie','charlie@garcia.com',NULL),(45,'Dan','Martin','dan','dan','dan@martin.com',NULL),(56,'Ed','Karaz','ed','ed','ed@kar.com',NULL);

INSERT INTO `developer` VALUES (12,'4321rewq'),(23,'5432trew'),(34,'6543ytre');

INSERT INTO `user` VALUES (45,NULL,'7654fda'),(56,NULL,'5678dfgh');

INSERT INTO `website` VALUES (123,'Facebook','an online social media and social networking ',NOW(),NOW(),1234234),(234,'Twitter','an online news and social networking service',NOW(),NOW(),4321543),(345,'Wikipedia','a free online encyclopedia',NOW(),NOW(),3456654),(456,'CNN','an American basic cable and satellite television news channel',NOW(),NOW(),6543345),(567,'CNET','an American media website that publishes reviews, news, articles, blogs, podcasts and videos on technology and consumer electronics',NOW(),NOW(),5433455),(678,'Gizmodo','a design, technology, science and science fiction website that also writes articles on politics', NOW(), NOW(),4322345);

INSERT INTO `page` VALUES (123,'Home','Landing Page','2018-09-05','2018-10-08',123434,567),(234,'About',' Website description','2018-09-05','2018-10-08',234545,678),(345,'Contact','Addresses, phones and contact info','2018-09-05','2018-10-08',345656,345),(456,' Preferences',' Where users can configure their preferences','2018-09-05','2018-10-08',456776,456),(567,' Profile',' Users can configure their personal information','2018-09-05','2018-10-08',567878,567);

INSERT INTO `widget` VALUES (123,'head123',NULL,NULL,NULL,NULL,'Welcome',0,'HEADING',NULL,NULL,NULL,NULL,NULL,2,123),(234,'post234',NULL,NULL,NULL,NULL,'<p>Lorem</p>',0,'HTML',NULL,NULL,NULL,NULL,NULL,0,234),(345,'head345',NULL,NULL,NULL,NULL,'Hi',1,'HEADING',NULL,NULL,NULL,NULL,NULL,2,345),(456,'intro456',NULL,NULL,NULL,NULL,'<h1>Hi</h1>',2,'HTML',NULL,NULL,NULL,NULL,NULL,0,345),(567,'image345',50,100,NULL,NULL,NULL,3,NULL,NULL,NULL,NULL,'/img/567.png',NULL,0,345),(678,'video456',400,300,NULL,NULL,NULL,0,NULL,'https://youtu.be/h6 7VX51QXiQ',NULL,NULL,NULL,NULL,2,456);

INSERT INTO `address` VALUES (1,'123 Adam St.',NULL,'Alton',NULL,'01234',1,12),(2,'234 Birch St.',NULL,' Boston',NULL,'02345',0,12),(3,'345 Charles St.',NULL,'Chelms',NULL,'03455',1,23),(4,'456 Down St.',NULL,'Dalton,',NULL,'04566',0,23),(5,'543 East St.',NULL,'Everett',NULL,'01112',0,23),(6,'654 Frank St.',NULL,'Foulton,',NULL,'04322',1,34);

INSERT INTO `phone` VALUES (1,'123-234-3456',1,12),(2,'234-345-4566',0,12),(3,'345-456-5677',1,23),(4,'321-432-5435',1,34),(5,'432-432-5433',0,34),(6,'543-543-6544',0,34);

INSERT INTO `Role` VALUES ('admin'),('editor'),('owner'),('reviewer'),('writer');

INSERT INTO `Priviledge` VALUES ('create'),('delete'),('read'),('update');

INSERT INTO `websiterole` VALUES (1,'owner',12,123),(2,'editor',23,123),(3,'admin',34,123),(4,'owner',23,234),(5,'editor',34,234),(6,'admin',12,234),(7,'owner',34,345),(8,'editor',12,345),(9,'admin',23,345),(10,'owner',12,456),(11,'editor',23,456),(12,'admin',34,456),(13,'owner',23,567),(14,'editor',34,567),(15,'admin',12,567),(16,'owner',34,678),(17,'editor',12,678),(18,'admin',23,678);

INSERT INTO `pagerole` VALUES (1,'editor',12,567),(2,'reviewer',23,567),(3,'writer',34,567),(4,'editor',23,678),(5,'reviewer',34,678),(6,'writer',12,678),(7,'editor',34,345),(8,'reviewer',12,345),(9,'writer',23,345),(10,'editor',12,456),(11,'reviewer',23,456),(12,'writer',34,456),(13,'editor',23,567),(14,'reviewer',34,567),(15,'writer',12,567);
