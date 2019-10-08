USE `cs5200_Assignment2`;

CREATE 
    ALGORITHM = UNDEFINED 
    SQL SECURITY DEFINER
VIEW `developer_roles_and_privileges` AS
    SELECT 
        `person`.`firstName` AS `firstName`,
        `person`.`lastName` AS `lastName`,
        `person`.`username` AS `username`,
        `person`.`email` AS `email`,
        `website`.`name` AS `name`,
        `website`.`visits` AS `visits`,
        `website`.`updated` AS `wupdated`,
        `wr`.`role` AS `wrole`,
        `wp`.`priviledge` AS `wpriviledge`,
        `page`.`title` AS `title`,
        `page`.`views` AS `views`,
        `page`.`updated` AS `pupdated`,
        `pr`.`role` AS `prole`,
        `pp`.`priviledge` AS `ppriviledge`
    FROM
        (((((((`person`
        JOIN `developer`)
        JOIN `website`)
        JOIN `websiterole` `wr`)
        JOIN `websitepriviledge` `wp`)
        JOIN `page`)
        JOIN `pagerole` `pr`)
        JOIN `pagepriviledge` `pp`)
    WHERE
        ((`person`.`id` = `developer`.`id`)
            AND (`wr`.`developerID` = `developer`.`id`)
            AND (`wr`.`websiteID` = `website`.`id`)
            AND (`wp`.`developerID` = `developer`.`id`)
            AND (`wp`.`websiteID` = `website`.`id`)
            AND (`page`.`websiteID` = `website`.`id`)
            AND (`pr`.`developerID` = `developer`.`id`)
            AND (`pr`.`pageID` = `page`.`id`)
            AND (`pp`.`developerID` = `developer`.`id`)
            AND (`pp`.`pageID` = `page`.`id`))