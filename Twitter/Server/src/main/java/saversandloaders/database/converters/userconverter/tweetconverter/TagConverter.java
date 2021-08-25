package saversandloaders.database.converters.userconverter.tweetconverter;

import saversandloaders.database.models.dbtweetmodels.DBTag;
import models.tweet.Tag;

public class TagConverter {
    public static Tag convertDBTagToTag(DBTag dbTag){
        Tag tag = new Tag();

        tag.setName(dbTag.getName());
        tag.setNumberOfUses(dbTag.getNumberOfUses());
        return tag;
    }

    public static DBTag convertTagToDBTag(Tag tag){
        DBTag dbTag = new DBTag();
        dbTag.setName(tag.getName());
        dbTag.setNumberOfUses(tag.getNumberOfUses());
        return dbTag;
    }
}
