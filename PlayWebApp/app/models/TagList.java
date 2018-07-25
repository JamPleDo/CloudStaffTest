package models;


import org.simpleframework.xml.ElementList;
import org.simpleframework.xml.Root;

import java.util.ArrayList;
import java.util.List;

@Root(name="taglist", strict = false)
public class TagList {

    @ElementList(inline = true, entry = "tag", required = false)
    protected List<Tag> tags = new ArrayList<Tag>();

    public List<Tag> getTags() {
        return tags;
    }

}
