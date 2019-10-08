USE `cs5200_Assignment2`;

update phone
set phone = '333-444-5555'
where personID in (select id from person where firstname = 'charlie') and phone.primary <> 0;

update widget 
set widget.order = (widget.order + 1) mod 3 + 1 
where pageID = 345;

update page
set title = concat('CNET -', ifnull(title,''))
where websiteID in (select id from website where website.name = 'CNET');

select @tempbob:=role from pagerole 
where developerID in (select developer.id from developer join person on developer.id = person.id where firstName = 'Bob')
	and pageID in (select page.id from website join page on page.websiteID = website.id where website.name = 'CNET' and page.title = 'CNET -Home' );

select @tempcharlie:=role from pagerole 
where developerID in (select developer.id from developer join person on developer.id = person.id where firstName = 'Charlie')
	and pageID in (select page.id from website join page on page.websiteID = website.id where website.name = 'CNET' and page.title = 'CNET -Home' );

update pagerole
set role = @tempcharlie
where developerID in (select developer.id from developer join person on developer.id = person.id where firstName = 'Bob')
	and pageID in (select page.id from website join page on page.websiteID = website.id where website.name = 'CNET' and page.title = 'CNET -Home' );
    
update pagerole
set role = @tempbob
where developerID in (select developer.id from developer join person on developer.id = person.id where firstName = 'Charlie')
	and pageID in (select page.id from website join page on page.websiteID = website.id where website.name = 'CNET' and page.title = 'CNET -Home' )
