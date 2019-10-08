USE `cs5200_Assignment2`;

delete from address where personID in (select id from person where firstname = 'alice') and address.primary <> 0;

select @temp:= MAX(widget.order) as aaa from widget, page
where page.title = 'Contact'
	and widget.pageID = page.id;

 delete from widget
 where widget.order = @temp
	and widget.pageid in (select id from page
		where page.title = 'Contact');

select @temp:=max(page.updated) from page 
where page.websiteID in (select id from website where website.name = 'Wikipedia');

delete from page
where page.updated = @temp
	and page.websiteID in (select id from website where website.name = 'Wikipedia');
  
delete from websiterole
where websiterole.websiteID in (select id from website where website.name = 'CNET');

delete from websitepriviledge
where websitepriviledge.websiteID in (select id from website where website.name = 'CNET');

delete from pagerole
where pagerole.pageID in (select website.id from page join website on page.websiteID = website.id where website.name = 'CNET');

delete from pagepriviledge
where pagepriviledge.pageID in (select website.id from page join website on page.websiteID = website.id where website.name = 'CNET');

delete p from page p join website on p.websiteid = website.id where website.name = 'CNET';

delete from website
where website.name = 'CNET'
