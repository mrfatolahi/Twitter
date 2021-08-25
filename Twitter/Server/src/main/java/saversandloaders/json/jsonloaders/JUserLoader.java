package saversandloaders.json.jsonloaders;

import XLogger.MLogger;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.ObjectMapper;
import models.user.User;
import models.user.information.Phonenumber;
import models.user.information.UserName;

import java.io.File;
import java.io.IOException;

public class JUserLoader {
    public static User jLoadUser(int userId) throws IOException {
        MLogger.log("INFO:  UserLoader.loadUser");
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.configure(JsonParser.Feature.AUTO_CLOSE_SOURCE, true);
        User user = objectMapper.readValue(new File("Database\\Users\\"+String.valueOf(userId)+".json"), User.class);
        Phonenumber phonenumber1 = objectMapper.readValue(new File("Database\\Users\\PhoneNumbers\\"+userId+".json"), Phonenumber.class);
        user.setPhonenumber(phonenumber1);
        return user;
    }

    public static User jLoadUser(String username) throws IOException {
        MLogger.log("INFO:  UserLoader.loadUser");
        int userId = jLoadIdWithUsername(username);
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.configure(JsonParser.Feature.AUTO_CLOSE_SOURCE, true);
        User user = objectMapper.readValue(new File("Database\\Users\\"+String.valueOf(userId)+".json"), User.class);
        Phonenumber phonenumber1 = objectMapper.readValue(new File("Database\\Users\\PhoneNumbers\\"+userId+".json"), Phonenumber.class);
        user.setPhonenumber(phonenumber1);
        return user;
    }

    public static int jLoadIdWithUsername(UserName userName) throws IOException {
        MLogger.log("INFO:  UserLoader.loadId_with_Username");
        File folder = new File("Database\\Users\\UserNames\\"+userName.getText()+".json");

        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.configure(JsonParser.Feature.AUTO_CLOSE_SOURCE, true);
        return objectMapper.readValue(folder, int.class);
    }

    public static int jLoadIdWithUsername(String userName) throws IOException {
        MLogger.log("INFO:  UserLoader.loadId_with_Username");
        File file = new File("Database\\Users\\UserNames\\"+userName+".json");

        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.configure(JsonParser.Feature.AUTO_CLOSE_SOURCE, true);
        return objectMapper.readValue(file, int.class);
    }
}
