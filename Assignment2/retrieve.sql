USE `cs5200_Assignment2`;

select * from person, developer
	where person.id = developer.id;

select * from person join developer on
person.id = developer.id and person.id = 34;

select firstName from person, developer, websiterole
where person.id = developer.id
	and websiterole.role <> 'owner'
    and websiterole.websiteID = 234
    and websiterole.developerID = developer.id;
    
select firstName from person, developer, page, pagerole
where person.id = developer.id
	and pagerole.role = 'reviewer'
    and pagerole.pageID = page.id
    and page.views < 300000
    and pagerole.developerID = developer.id;
    
select firstName from person, developer, website, widget, page, pagerole
where person.id = developer.id
	and website.name = 'CNET'
	and page.title = 'Home'
    and page.websiteID = website.id
	and pagerole.role = 'writer'
    and pagerole.pageID = page.id
    and pagerole.developerID = developer.id
    and widget.DTYPE= 'Heading'
    and widget.pageID = page.id;
    
    
    
    
select name, visits from (select MIN(visits) as minimum from website )s, website where visits = minimum;

select name from website
where id = 678;

select website.name from person, developer, website, page, widget, pagerole
where person.id = developer.id 
	and person.firstname = 'bob'
    and pagerole.role = 'reviewer'
    and pagerole.developerID = developer.id
    and pagerole.pageID = page.id
	and website.name = 'CNN'
    and widget.DTYPE = 'YOUTUBE'
    and widget.pageID = page.id
    and page.id = website.id;
    
select website.name from person, developer, website, websiterole
where person.id = developer.id
	and person.firstname = 'alice'
    and websiterole.role = 'owner'
    and websiterole.developerID = developer.id
    and websiterole.websiteID = website.id;

select website.name from person, developer, website, websiterole
where person.id = developer.id
	and person.firstname = 'charlie'
    and websiterole.developerID = developer.id
    and websiterole.role = 'admin'
    and websiterole.websiteID = website.id
    and website.visits > 6000000;
    
    
    
select title, views from(select max(views) as max from page) m, page where views = max;

select title from page
where page.id = 234;

select title from person, developer, page, pagerole
where person.id = developer.id
	and person.firstName = 'alice'
    and pagerole.role = 'editor'
    and pagerole.developerID = developer.id
    and page.id = pagerole.pageID;
    
select sum(page.views) from page, website
where website.name = 'CNET'
	and page.websiteID = website.id;
    
select avg(page.views) from page, website
where website.name = 'Wikipedia'
	and page.websiteID = website.id;
  
  select widget.name from widget, website, page
where website.name = 'CNET'
	and widget.pageID = page.id
    and page.websiteID = website.id;
    
select widget.name from widget, website, page
where widget.DTYPE = 'YOUTUBE'
	and website.name = 'CNN'
    and widget.pageID = page.id
    and page.websiteID = website.id;

select widget.name from widget, page, person, developer, pagerole
where person.id = developer.id
	and person.firstName = 'alice'
    and pagerole.developerID = developer.id
    and pagerole.role = 'reviewer'
    and widget.pageID = page.id
    and widget.DTYPE = 'image';
    
select count(widget.name) from widget, page, website
where website.name = 'Wikipedia'
	and widget.pageID = page.id
    and page.websiteID - website.id;
    

select website.name from website, person, developer, websitepriviledge
where person.firstName = 'bob'
	and person.id = developer.id
	and websitepriviledge.priviledge = 'delete'
    and websitepriviledge.websiteID = website.id
    and websitepriviledge.developerID = developer.id;
    
select page.title from page, person, developer, pagepriviledge
where person.firstName = 'Charlie'
	and person.id = developer.id
    and pagepriviledge.priviledge = 'create'
    and pagepriviledge.pageID = page.id
    and pagepriviledge.developerID = developer.id