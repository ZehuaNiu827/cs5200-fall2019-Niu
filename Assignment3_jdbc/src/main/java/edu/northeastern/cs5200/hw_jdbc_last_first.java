package edu.northeastern.cs5200;

import java.sql.CallableStatement;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
//import java.text.DateFormat;
//import java.text.ParseException;
import java.util.ArrayList;
import java.util.Collection;

//import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import edu.northeastern.cs5200.models.*;
import edu.northeastern.cs5200.daos.*;

@SpringBootApplication
public class hw_jdbc_last_first {

	public static void main(String[] args) {
		
	//	SpringApplication.run(hw_jdbc_last_first.class, args);
		
		Phone[] charlie_phone = new Phone[1];
		charlie_phone[0] = new Phone(1, "222-111-222", true, 34);
		
		Address[] alice_address = new Address[1];
		alice_address[0] = new Address(1, "aliceaddr", "aliceaddr", "alicecity", "alicestate", "alicezip", true, 12 );
		
		Developer alice = new Developer("4321rewq", 12, "Alice", "Wonder", "alice", "alice", "alice@wonder.com", null, alice_address, null);
		Developer bob = new Developer("5432trew", 23, "Bob", "Marley", "bob", "bob", "bob@marley.com", null);
		Developer charlie = new Developer("6543ytre", 34, "Charles", "Garcia", "charlie", "charlie", "chuch@garcia.com", null, null, charlie_phone);
		//1//
		DeveloperDao developerdao = DeveloperDao.getInstance();
		developerdao.createDeveloper(alice);
		developerdao.createDeveloper(bob);
		developerdao.createDeveloper(charlie);
		UserDao userdao = UserDao.getInstance();
		userdao.createUser(new User(45, "Dan", "Martin", "dan", "dan", "dan@martin.com", null, false ));
		userdao.createUser(new User(56, "Ed", "Karaz", "ed", "ed", "ed@kar.com", null, false ));
		
		
		//2//
		WebsiteDao wdao = WebsiteDao.getInstance();
		Website facebook = new Website(123, "Facebook", "an online social media and social networking service", new Date(System.currentTimeMillis()), new Date(System.currentTimeMillis()), 1234234, 12);
		Website twitter = new Website(234, "Twitter", "an online news and social networking service", new Date(System.currentTimeMillis()), new Date(System.currentTimeMillis()), 4321543, 23);
		Website wiki = new Website(345, "Wikipedia", "a free online encyclopedia", new Date(System.currentTimeMillis()), new Date(System.currentTimeMillis()), 3456654, 34);
		Website cnn = new Website(456, "CNN", "an American basic cable and satellite television news channel", new Date(System.currentTimeMillis()), new Date(System.currentTimeMillis()), 6543345, 12);
		Website cnet = new Website(567, "CNET", "an American media website that publishes reviews, news, articles, blogs, podcasts and videos on technology and customer electronics", new Date(System.currentTimeMillis()), new Date(System.currentTimeMillis()), 5433455, 23);
		Website gizmodo = new Website(678, "Gizmodo", "a design, technology, science and science fiction website that also writes articles on politics", new Date(System.currentTimeMillis()), new Date(System.currentTimeMillis()), 4322345, 34);

		
		
		wdao.createWebsiteForDeveloper(12, facebook);
		wdao.createWebsiteForDeveloper(23, twitter);
		wdao.createWebsiteForDeveloper(34, wiki);
		wdao.createWebsiteForDeveloper(12, cnn);
		wdao.createWebsiteForDeveloper(23, cnet);
		wdao.createWebsiteForDeveloper(34, gizmodo);
		
		
		//0 owner 1 editor 2 admin
		RoleDao rdao = RoleDao.getInstance();
		
		rdao.assignWebsiteRole(alice.getId(), facebook.getId(), 0);
		rdao.assignWebsiteRole(bob.getId(), facebook.getId(), 1);
		rdao.assignWebsiteRole(charlie.getId(), facebook.getId(), 2);
	
		rdao.assignWebsiteRole(bob.getId(), twitter.getId(), 0);
		rdao.assignWebsiteRole(charlie.getId(), twitter.getId(), 1);
		rdao.assignWebsiteRole(alice.getId(), twitter.getId(), 2);
		
		rdao.assignWebsiteRole(charlie.getId(), wiki.getId(), 0);
		rdao.assignWebsiteRole(alice.getId(), wiki.getId(), 1);
		rdao.assignWebsiteRole(bob.getId(), wiki.getId(), 2);
		
		rdao.assignWebsiteRole(alice.getId(), cnn.getId(), 0);
		rdao.assignWebsiteRole(bob.getId(), cnn.getId(), 1);
		rdao.assignWebsiteRole(charlie.getId(), cnn.getId(), 2);
		
		rdao.assignWebsiteRole(bob.getId(), cnet.getId(), 0);
		rdao.assignWebsiteRole(charlie.getId(), cnet.getId(), 1);
		rdao.assignWebsiteRole(alice.getId(), cnet.getId(), 2);
		
		rdao.assignWebsiteRole(charlie.getId(), gizmodo.getId(), 0);
		rdao.assignWebsiteRole(alice.getId(), gizmodo.getId(), 1);
		rdao.assignWebsiteRole(bob.getId(), gizmodo.getId(), 2);
		
		
		PageDao pdao = PageDao.getInstance();
	
		Page home = new Page(123, "Home", "Landing page", Date.valueOf("2019-09-05"), Date.valueOf("2019-10-20"),123434, 567);
		Page about = new Page(234, "About", "Website description", Date.valueOf("2019-09-05"), Date.valueOf("2019-10-20"),234545, 678);
		Page contact = new Page(345, "Contact", "Addresses, phones, and contact info", Date.valueOf("2019-09-05"), Date.valueOf("2019-10-20"),345656, 345);
		Page preference = new Page(456, "Preference", "Where users can configure their preferences", Date.valueOf("2019-09-05"), Date.valueOf("2019-10-20"),456776, 456);
		Page profile = new Page(567, "Profile", "Users can configure their personal information", Date.valueOf("2019-09-05"), Date.valueOf("2019-10-20"), 567878, 567);
		
		
		pdao.createPageForWebsite(cnet.getId(), home);
		pdao.createPageForWebsite(cnet.getId(), about);
		pdao.createPageForWebsite(cnet.getId(), contact);
		pdao.createPageForWebsite(cnet.getId(), preference);
		pdao.createPageForWebsite(cnet.getId(), profile);
		
		//1 editor 3 reviewer 4 writer
		rdao.assignPageRole(alice.getId(), home.getId(), 1);
		rdao.assignPageRole(bob.getId(), home.getId(), 3);
		rdao.assignPageRole(charlie.getId(), home.getId(), 4);
		
		rdao.assignPageRole(bob.getId(), about.getId(), 1);
		rdao.assignPageRole(charlie.getId(), about.getId(), 3);
		rdao.assignPageRole(alice.getId(), about.getId(), 4);
		
		rdao.assignPageRole(charlie.getId(), contact.getId(), 1);
		rdao.assignPageRole(alice.getId(), contact.getId(), 3);
		rdao.assignPageRole(bob.getId(), contact.getId(), 4);
		
		rdao.assignPageRole(alice.getId(), preference.getId(), 1);
		rdao.assignPageRole(bob.getId(), preference.getId(), 3);
		rdao.assignPageRole(charlie.getId(), preference.getId(), 4);
		
		rdao.assignPageRole(bob.getId(), profile.getId(), 1);
		rdao.assignPageRole(charlie.getId(), profile.getId(), 3);
		rdao.assignPageRole(alice.getId(), profile.getId(), 4);
		
		WidgetDao widgetdao = WidgetDao.getInstance();
		Widget head345 = new HeadingWidget(3, "head345", 0, 0, null, null, "Hi", 1, 345, 2);
		Widget intro456 = new HtmlWidget(4, "intro456", 0, 0, null, null, "<h1>Hi</h1>", 2, 345, null);
		Widget image345 = new ImageWidget(5, "image345",50, 100, null, null, null, 3, 345, "/img/567.png");
		
		widgetdao.createWidgetForPage(123, new HeadingWidget(1, "head123", 0, 0, null, null, "Welcome", 0, 123, 2));
		widgetdao.createWidgetForPage(234, new HtmlWidget(2, "post234", 0, 0, null, null, "<p>Lorem</p>", 0, 234, null));
		widgetdao.createWidgetForPage(345, head345);
		widgetdao.createWidgetForPage(345, intro456);
		widgetdao.createWidgetForPage(345, image345);
		widgetdao.createWidgetForPage(456, new YouTubeWidget(6, "video456", 400, 300, null, null, null, 0, 456,"https://youtu.be/ h67VX51QXiQ", false, false));
		
		
		//update charlie primary phone
		Phone[] charlie_phones = charlie.getPhone();
		Phone charlie_new_phone = new Phone(0, "333-444-555", true, 34);
		if(charlie.getPhone() != null) {
			if (charlie.getPhone().length != 0) {
				for(int i = 0; i < charlie.getPhone().length; i++) {
					//charlie_phones[i] = charlie.getPhone()[i];
					if (charlie.getPhone()[i].isPrimary() == true) {
						charlie_phones[i] = charlie_new_phone;
					}
				}
			}
			else {
				charlie_phones = new Phone[1];
				charlie_phones[0] = charlie_new_phone;
			}
			charlie.setPhone(charlie_phones);
			developerdao.updateDeveloper(34, charlie);
		}
		
		
		
		//update widget order
		
		head345.setOrder((head345.getOrder()+1) % 3 + 1);
		intro456.setOrder((intro456.getOrder() + 1) % 3 + 1); 
		image345.setOrder((image345.getOrder() + 1) % 3 + 1); 
		widgetdao.updateWidget(3, head345);
		widgetdao.updateWidget(4, intro456);
		widgetdao.updateWidget(5, image345);
		
		
		//update page
		home.setTitle("CNET-" + home.getTitle());
		profile.setTitle("CNET-" + profile.getTitle());
		pdao.updatePage(home.getId(), home);
		pdao.updatePage(profile.getId(), profile);
		
		//update role
		rdao.deletePageRole(charlie.getId(), home.getId(), 4);
		rdao.deletePageRole(bob.getId(), home.getId(), 3);
		rdao.assignPageRole(charlie.getId(), home.getId(), 3);
		rdao.assignPageRole(bob.getId(), home.getId(), 4);
		
		
		//delete alice primary address
		ArrayList<Address> alice_addr = new ArrayList<Address>();
		if(alice.getAddress().length != 0) {
			for(int i = 0; i < alice.getAddress().length; i++) {
				if(alice.getAddress()[i].isPrimary() == false) {
					alice_addr.add(alice.getAddress()[i]);
				}
			}
		}
		
		alice.setAddress(alice_addr.toArray(new Address[0]));
		developerdao.updateDeveloper(12, alice);
		
		//delete widget
		Collection<Widget> widgets = widgetdao.findWidgetsForPage(contact.getId());
		if(widgets != null) {
			int maxOrder = 0;
			int delete_widget = 0;
			for(Widget i : widgets) {
				if (i.getOrder() > maxOrder) {
					maxOrder = i.getOrder();
					delete_widget = i.getId();
				}
			}	
			
			widgetdao.deleteWidget(delete_widget);
		}
		
		//remove last update page
		Collection<Page> pages = pdao.findPagesForWebsite(wiki.getId());
		if(pages != null) {
			Date update = new Date(0);
			int delete_page = 0;
			for(Page i : pages) {
				if(i.getUpdated().compareTo(update) > 0 ){
					update = i.getUpdated();
					delete_page = i.getId();
				}
			}
			pdao.deletePage(delete_page);
		}
		
		//delete CNET
		PriviledgeDao prdao = PriviledgeDao.getInstance();
			
		int pageId = 0;
		Collection<Page> page_delete = pdao.findPagesForWebsite(cnet.getId());
		for(Page p : page_delete) {
			pageId = p.getId();
			//System.out.println(pageId);
			prdao.deletePagePriviledgeById(pageId);
			rdao.deletePageRoleById(pageId);
			pdao.deletePage(pageId);
		}
		prdao.deleteWebsitePriviledgeById(cnet.getId());
		rdao.deleteWebsiteRoleById(cnet.getId());
		wdao.deleteWebsite(cnet.getId());
		
		
		//Store procedure
		try {
			CallableStatement c = Connection.getConnection().prepareCall("call getUnansweredQuestions");
			ResultSet r = c.executeQuery();
			while (r.next()) {
				System.out.print(r.getString(1) + ' ');
				System.out.print(r.getString(2)+ ' ');
				System.out.println(r.getString(3) + ' ');
			}
			
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		try {
			CallableStatement c2 = Connection.getConnection().prepareCall("Call endorsedUsersForWeek('2019-10-14')");
			ResultSet r2 = c2.executeQuery();
			while (r2.next()) {
				System.out.print(r2.getString(1)+ ' ');
				System.out.print(r2.getString(2)+ ' ');
				System.out.println(r2.getString(3) + ' ');
			}
		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
}
