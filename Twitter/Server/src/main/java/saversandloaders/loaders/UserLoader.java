package saversandloaders.loaders;

import XLogger.MLogger;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.ObjectMapper;
import config.Config;
import models.user.User;
import models.user.information.Phonenumber;
import models.user.information.UserName;
import org.hibernate.Criteria;
import org.hibernate.criterion.Projections;
import saversandloaders.database.dbloaders.DBUserLoader;
import saversandloaders.json.jsonloaders.JLoader;
import saversandloaders.json.jsonloaders.JUserLoader;
import saversandloaders.json.jsonsavers.JUserSaver;

import java.io.File;
import java.io.IOException;

public class UserLoader {
    public static User loadUser(int userId) throws IOException {
        if (Config.getConfig("SavingType").getProperty("type").equals("JSON")){
            return JUserLoader.jLoadUser(userId);
        } else {
            return DBUserLoader.loadUser(userId);
        }
    }

    public static User loadUser(String username) throws IOException {
        if (Config.getConfig("SavingType").getProperty("type").equals("JSON")){
            return JUserLoader.jLoadUser(username);
        } else {
            return DBUserLoader.loadUser(loadIdWithUsername(username));
        }
    }

    public static int loadIdWithUsername(UserName userName) throws IOException {

        if (Config.getConfig("SavingType").getProperty("type").equals("JSON")){
            return JUserLoader.jLoadIdWithUsername(userName);
        } else {
            return DBUserLoader.loadIdWithUsername(userName.getText());
        }
    }

    public static int loadIdWithUsername(String userName) throws IOException {
        if (Config.getConfig("SavingType").getProperty("type").equals("JSON")){
            return JUserLoader.jLoadIdWithUsername(userName);
        } else {
            return DBUserLoader.loadIdWithUsername(userName);
        }
    }

    /*ObjectMapper objectMapper = new ObjectMapper();
    objectMapper.configure(JsonParser.Feature.AUTO_CLOSE_SOURCE, true);
    System.out.println
            (
            "--------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------"+
            "LoadedUser:\n"+objectMapper.writerWithDefaultPrettyPrinter().writeValueAsString(user)+
            "--------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------"
            );*/
}
