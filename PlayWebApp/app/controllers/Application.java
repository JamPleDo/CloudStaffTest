package controllers;

import java.util.ArrayList;
import java.util.List;

import javassist.bytecode.Descriptor.Iterator;
import models.Person;
import models.PersonList;
import models.Tag;
import models.TagList;

import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;

import play.data.validation.Required;
import play.mvc.*;
import sun.misc.BASE64Encoder;

public class Application extends Controller {
 
    public static void index() {
        render();
    }
    
    public static void allContacts(@Required(message="Token is required")String token, String tag){
    	
    	HighriseAPI highrise = new HighriseAPI(token);
    	
    	PersonList personList = highrise.getContactByTag(tag);

    	if( personList == null ){
    		validation.addError("Token", "Unable to connect to the server", "Please input valid Highrise Token");
    	}
    	
    	if(validation.hasErrors()) {
            render("Application/index.html");
        }
    	
    	for(Person p : personList.getPersons()){
    		
    		if( Person.find("person_id", p.getPerson_id()).first() != null ){
    			continue;
    		}
    		
        	if( p.getTags() != null ){
        		List<Tag> temp = new ArrayList<Tag>(p.getTags());
        		p.getTags().clear();
	        	for(Tag t : temp){
	            	p.tagItWith(t.getName());
	        	}
        	}
        	p.save();
    	}
    	
    	List<Tag> tags = Tag.findAll();
    	tags.add(new Tag(""));
    	
    	List<Person> persons = Person.findAll();
    	
    	render(persons, tags);
    	
    }
    
    public static void findTaggedWith(long tagid) {
    	List<Person> persons;
    	if( tagid != 0 ){
    		persons = Person
        	.find("select distinct p from Person p join p.tags as t where t.id in (:tagid) group by p.id, p.company, p.title")
        	.bind("tagid", tagid)
        	.fetch();
    	}else{
    		persons = Person.findAll();
    	}
    	
    	List<Tag> tags = Tag.findAll();
    	tags.add(new Tag(""));
    	
    	render (("Application/allContacts.html"), persons, tags);
    }
}