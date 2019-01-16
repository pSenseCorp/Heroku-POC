package com.example.demo;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Map;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.web.bind.annotation.*;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;

import org.springframework.stereotype.*;

@Controller
@SpringBootApplication
public class DemoApplication {
	
	@Value("${spring.datasource.url}")
	private String dbUrl;
	
	@Autowired
	public DataSource dataSource;
	
	//Home page demoSfSync.jsp
	@RequestMapping("/")
    public String home(Map<String, Object> model) {
	  
	  model.put("heading", "Heroku-Salesforce Integration Demo");
      return "demoSfSync";
    }
	
	protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
		return application.sources(DemoApplication.class);
	}

	public static void main(String[] args) {
		SpringApplication.run(DemoApplication.class, args);
	}
	
	//Get contacts from Post gres DB and return to Demo page
	 @RequestMapping("/getContacts")
	 public String getContacts(Map<String, Object> model) {
	    try (Connection connection = dataSource.getConnection()) {
	      Statement stmt = connection.createStatement();
	      ResultSet rs = stmt.executeQuery("Select contactextid__c, Name, Phone from salesforcedemo.contact");

	      ArrayList<String> output = new ArrayList<String>();
	      ArrayList<Contact> contacts = new ArrayList<Contact>();
	      int count = 0;
	      while (rs.next()) {
	        output.add("Contact Name: " + rs.getString("Name"));
	        Contact contact = new Contact();
	        contact.setId(rs.getString("contactextid__c"));
	        contact.setName(rs.getString("Name"));
	        contact.setPhone(rs.getString("Phone"));
	        contacts.add(contact);
	        count++;
	      }
	      model.put("heading", "Heroku-Salesforce Integration Demo");
	      model.put("records", output);
	      model.put("contacts", contacts);
	      model.put("numberOfRecords", count);
	      return "demo";
	    } catch (Exception e) {
	    	ArrayList<Contact> contacts = new ArrayList<Contact>();
	    	 Contact contact = new Contact();
		        contact.setId("EXT-30");
		        contact.setName("test1");
		        contact.setPhone("1111111");
		        model.put("contacts", contacts);
		        contacts.add(contact);
	      model.put("message", e.getMessage());
	      return "demo";
	    }
	  }
	 
	 @Bean
	  public DataSource dataSource() throws SQLException {
	    if (dbUrl == null || dbUrl.isEmpty()) {
	      return new HikariDataSource();
	    } else {
	      HikariConfig config = new HikariConfig();
	      config.setJdbcUrl(dbUrl);
	      return new HikariDataSource(config);
	    }
	  }
	 
	//Get contacts from Post gres DB and return to JSON Object List 
	 @RequestMapping("/getSfContacts")
	 public @ResponseBody ArrayList<Contact> getSfContacts() {
	    try (Connection connection = dataSource.getConnection()) {
	      Statement stmt = connection.createStatement();
	      ResultSet rs = stmt.executeQuery("Select sfid, Name, Phone from salesforcedemo.contact");

	      ArrayList<String> output = new ArrayList<String>();
	      ArrayList<Contact> contacts = new ArrayList<Contact>();
	      while (rs.next()) {
	        output.add("Contact Name: " + rs.getString("Name"));
	        Contact contact = new Contact();
	        contact.setId(rs.getString("sfid"));
	        contact.setName(rs.getString("Name"));
	        contact.setPhone(rs.getString("Phone"));
	        contacts.add(contact);
	      }
	      connection.close();
	      return contacts;
	    } catch (Exception e) {
	    	ArrayList<Contact> contacts = new ArrayList<Contact>();
	    	 Contact contact = new Contact();
		        contact.setId("EXT-30");
		        contact.setName("test1");
		        contact.setPhone("1111111");
		        contacts.add(contact);
		        Contact contact1 = new Contact();
		        contact1.setId("EXT-31");
		        contact1.setName("test2");
		        contact1.setPhone("1111112");
		        contacts.add(contact1);
	      return contacts;
	    }
	  }
	 
	 //Save a contact to the postgres DB
	 @RequestMapping(value = "/saveSfContact", method = RequestMethod.POST)
	 public @ResponseBody boolean saveSfContact(@RequestBody Contact contact) {
		 System.out.println("Name:" + contact.getName() + "=====" + contact.getId());
		 System.out.println("update salesforcedemo.contact set name= '"+ contact.getName() + "', phone= '"+ contact.getPhone() +"' where contactextid__c = '" + contact.getId() + "'");
		 try (Connection connection = dataSource.getConnection()) {
		      Statement stmt = connection.createStatement();
		      stmt.executeUpdate("update salesforcedemo.contact set name= '"+ contact.getName() + "', phone= '"+ contact.getPhone() +"' where sfid = '" + contact.getId() + "'");
		      return true;
		    } catch (Exception e) {
		    	e.printStackTrace();
		      return false;
		    }
		 
	 }
}

