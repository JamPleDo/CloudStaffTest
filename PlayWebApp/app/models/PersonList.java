package models;

import java.util.ArrayList;
import java.util.List;
import org.simpleframework.xml.ElementList;
import org.simpleframework.xml.Root;


@Root(strict = false)
public class PersonList{

    @ElementList(inline = true, entry = "person", required = false)
    protected List<Person> persons = new ArrayList<Person>();

    public List<Person> getPersons() {
        return persons;
    }

}
