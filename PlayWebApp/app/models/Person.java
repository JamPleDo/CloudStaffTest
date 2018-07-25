package models;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.*;

import org.hibernate.annotations.GenericGenerator;
import org.simpleframework.xml.Element;
import org.simpleframework.xml.ElementList;
import org.simpleframework.xml.Root;

import play.db.jpa.GenericModel;
import play.db.jpa.Model;

@Root(name = "person", strict = false)
@Entity
@Table(name = "Person")
public class Person extends GenericModel{
	
	@Element
	@Id
    @Column(name="person_id")
	private long id;
	
    @Element(name = "first-name", required = false)
    private String firstName;

    @Element(name = "last-name", required = false)
    private String lastName;

    @Element(required = false)
    private String title;

    @Element(name = "company-name", required = false)
    private String company;
    
    @ElementList(required = false)
    @ManyToMany(cascade=CascadeType.PERSIST)
    private List<Tag> tags;
    
    public Person(){
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getFullName() {
        return firstName + " " + lastName;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

	public long getPerson_id() {
		return id;
	}

	public void setPerson_id(long person_id) {
		this.id = person_id;
	}
    
    public List<Tag> getTags() {
        return tags;
    }
    
    public Person tagItWith(String name) {
        tags.add(Tag.findOrCreateByName(name));
        return this;
    }
    
}