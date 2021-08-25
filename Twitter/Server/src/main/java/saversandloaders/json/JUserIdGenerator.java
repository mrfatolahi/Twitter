package saversandloaders.json;

import java.io.File;

public class JUserIdGenerator {
    public static int generateAnId(){
        File folder = new File("Database\\Users\\UserNames");
        return folder.listFiles().length + 1;
    }
}
