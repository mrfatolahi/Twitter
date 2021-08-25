package saversandloaders.savers;

import XLogger.MLogger;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import config.Config;
import models.user.User;
import saversandloaders.database.dbloaders.DBUserLoader;
import saversandloaders.database.dbsavers.DBSaver;
import saversandloaders.database.dbsavers.DBUserSaver;
import saversandloaders.json.jsonsavers.JSaver;
import saversandloaders.json.jsonsavers.JUserSaver;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class UserSaver {
    public static void saveUser(User user) throws IOException {
        MLogger.log("INFO: UserSaver.saveUser");
        if (Config.getConfig("SavingType").getProperty("type").equals("JSON")){
            JUserSaver.jSaveUser(user);
        } else {
            DBUserSaver.saveUser(user);
        }
    }
}
