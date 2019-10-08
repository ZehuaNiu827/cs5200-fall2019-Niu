DELIMITER $$
CREATE TRIGGER `websiterole_AFTER_INSERT` AFTER INSERT ON `websiterole` FOR EACH ROW
BEGIN

if NEW.role = "owner" or NEW.role = "admin" then
	insert into websitepriviledge VALUES (
    NULL,
    'create',
    NEW.developerID,
    NEW.websiteID);
    insert into websitepriviledge VALUES (
    NULL,
    'read',
    NEW.developerID,
    NEW.websiteID);
    insert into websitepriviledge VALUES (
    NULL,
    'update',
    NEW.developerID,
    NEW.websiteID);
    insert into websitepriviledge VALUES (
    NULL,
    'delete',
    NEW.developerID,
    NEW.websiteID);

    elseif NEW.role = "writer" then
	insert into websitepriviledge VALUES (
    NULL,
    'create',
    NEW.developerID,
    NEW.websiteID);
    insert into websitepriviledge VALUES (
    NULL,
    'read',
    NEW.developerID,
    NEW.websiteID);
    insert into websitepriviledge VALUES (
    NULL,
    'update',
    NEW.developerID,
    NEW.websiteID);
    
    elseif NEW.role = "editor" then
	insert into websitepriviledge VALUES (
    NULL,
    'read',
    NEW.developerID,
    NEW.websiteID);
    insert into websitepriviledge VALUES (
    NULL,
    'update',
    NEW.developerID,
    NEW.websiteID);
    
	elseif NEW.role = "reviewer" then
	insert into websitepriviledge VALUES (
    NULL,
    'read',
    NEW.developerID,
    NEW.websiteID);
    end if;
    
END $$
DELIMITER;

DELIMITER $$
CREATE TRIGGER `pagerole_AFTER_INSERT` AFTER INSERT ON `pagerole` FOR EACH ROW
BEGIN
	if NEW.role = "owner" or NEW.role = "admin" then
	insert into pagepriviledge VALUES (
    NULL,
    'create',
    NEW.developerID,
    NEW.pageID);
    insert into pagepriviledge VALUES (
    NULL,
    'read',
    NEW.developerID,
    NEW.pageID);
    insert into pagepriviledge VALUES (
    NULL,
    'update',
    NEW.developerID,
    NEW.pageID);
    insert into pagepriviledge VALUES (
    NULL,
    'delete',
    NEW.developerID,
    NEW.pageID);

    elseif NEW.role = "writer" then
	insert into pagepriviledge VALUES (
    NULL,
    'create',
    NEW.developerID,
    NEW.pageID);
    insert into pagepriviledge VALUES (
    NULL,
    'read',
    NEW.developerID,
    NEW.pageID);
    insert into pagepriviledge VALUES (
    NULL,
    'update',
    NEW.developerID,
    NEW.pageID);
    
    elseif NEW.role = "editor" then
	insert into pagepriviledge VALUES (
    NULL,
    'read',
    NEW.developerID,
    NEW.pageID);
    insert into pagepriviledge VALUES (
    NULL,
    'update',
    NEW.developerID,
    NEW.pageID);
    
	elseif NEW.role = "reviewer" then
	insert into pagepriviledge VALUES (
    NULL,
    'read',
    NEW.developerID,
    NEW.pageID);
    end if;
END$$
DELIMITER ;


DELIMITER $$
CREATE TRIGGER `websiterole_AFTER_UPDATE` AFTER UPDATE ON `websiterole` FOR EACH ROW
BEGIN
if NEW.role = "owner" or NEW.role = "admin" then
	insert into websitepriviledge VALUES (
    NULL,
    'create',
    NEW.developerID,
    NEW.websiteID);
    insert into websitepriviledge VALUES (
    NULL,
    'read',
    NEW.developerID,
    NEW.websiteID);
    insert into websitepriviledge VALUES (
    NULL,
    'update',
    NEW.developerID,
    NEW.websiteID);
    insert into websitepriviledge VALUES (
    NULL,
    'delete',
    NEW.developerID,
    NEW.websiteID);

    elseif NEW.role = "writer" then
	insert into websitepriviledge VALUES (
    NULL,
    'create',
    NEW.developerID,
    NEW.websiteID);
    insert into websitepriviledge VALUES (
    NULL,
    'read',
    NEW.developerID,
    NEW.websiteID);
    insert into websitepriviledge VALUES (
    NULL,
    'update',
    NEW.developerID,
    NEW.websiteID);
    
    elseif NEW.role = "editor" then
	insert into websitepriviledge VALUES (
    NULL,
    'read',
    NEW.developerID,
    NEW.websiteID);
    insert into websitepriviledge VALUES (
    NULL,
    'update',
    NEW.developerID,
    NEW.websiteID);
    
	elseif NEW.role = "reviewer" then
	insert into websitepriviledge VALUES (
    NULL,
    'read',
    NEW.developerID,
    NEW.websiteID);
    end if;
END$$
DELIMITER;

DELIMITER $$
CREATE TRIGGER `pagerole_AFTER_UPDATE` AFTER UPDATE ON `pagerole` FOR EACH ROW
BEGIN
if NEW.role = "owner" or NEW.role = "admin" then
	insert into pagepriviledge VALUES (
    NULL,
    'create',
    NEW.developerID,
    NEW.pageID);
    insert into pagepriviledge VALUES (
    NULL,
    'read',
    NEW.developerID,
    NEW.pageID);
    insert into pagepriviledge VALUES (
    NULL,
    'update',
    NEW.developerID,
    NEW.pageID);
    insert into pagepriviledge VALUES (
    NULL,
    'delete',
    NEW.developerID,
    NEW.pageID);

    elseif NEW.role = "writer" then
	insert into pagepriviledge VALUES (
    NULL,
    'create',
    NEW.developerID,
    NEW.pageID);
    insert into pagepriviledge VALUES (
    NULL,
    'read',
    NEW.developerID,
    NEW.pageID);
    insert into pagepriviledge VALUES (
    NULL,
    'update',
    NEW.developerID,
    NEW.pageID);
    
    elseif NEW.role = "editor" then
	insert into pagepriviledge VALUES (
    NULL,
    'read',
    NEW.developerID,
    NEW.pageID);
    insert into pagepriviledge VALUES (
    NULL,
    'update',
    NEW.developerID,
    NEW.pageID);
    
	elseif NEW.role = "reviewer" then
	insert into pagepriviledge VALUES (
    NULL,
    'read',
    NEW.developerID,
    NEW.pageID);
    end if;
END$$
DELIMITER ;