package saversandloaders;

import config.Config;
import saversandloaders.database.models.dbusermodels.DBUserIdGenerator;
import saversandloaders.json.JUserIdGenerator;

public class UserIdGenerator {
    public static int generateAnId(){
        if (Config.getConfig("SavingType").getProperty("type").equals("JSON")){
            return JUserIdGenerator.generateAnId();
        } else {
            return DBUserIdGenerator.generateAnId();
        }
    }
}
