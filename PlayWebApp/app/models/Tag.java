package models;

import java.util.List;

import javax.persistence.*;

import org.hibernate.annotations.GenericGenerator;
import org.simpleframework.xml.Element;
import org.simpleframework.xml.Root;

import play.db.jpa.GenericModel;
import play.db.jpa.Model;

@Root(name = "tag")
@Entity
@Table(name = "Tag")
public class Tag extends GenericModel implements Comparable<Tag> {
	
	@Element
	@Id
	@GeneratedValue
	private long id;
	
	@Element
	private String name;

	public Tag() {
	}

	public Tag(String name) {
		this.name = name;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public long getTagId() {
		return id;
	}

	public void setTagId(long tag_id) {
		this.id = tag_id;
	}

	@Override
	public int compareTo(Tag otherTag) {
		return name.compareTo(otherTag.name);
	}

	public static Tag findOrCreateByName(String name) {
		Tag tag = Tag.find("byName", name).first();
		if (tag == null) {
			tag = new Tag(name);
		}
		return tag;
	}

}
