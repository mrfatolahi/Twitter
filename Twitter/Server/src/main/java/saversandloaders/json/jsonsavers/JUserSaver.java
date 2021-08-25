package saversandloaders.json.jsonsavers;

import XLogger.MLogger;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import models.user.User;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class JUserSaver {
    public static void jSaveUser(User user) throws IOException {
        File folder = new File("Database\\Users\\"+user.getId()+".json");
        if(!folder.exists()){folder.createNewFile();}
        MLogger.log("INFO:  UserSaver.Static_saveUser");

        ObjectMapper objectMapper = new ObjectMapper();
        FileWriter fileWriter = new FileWriter(folder);
        objectMapper.enable(SerializationFeature.INDENT_OUTPUT);
        objectMapper.configure(JsonParser.Feature.AUTO_CLOSE_SOURCE, true);
        objectMapper.writeValue(fileWriter, user);
        jSaveUsername(user);
        jSaveEmail(user);
        jSavePhoneNumber(user);
    }

    private static void jSavePhoneNumber(User user) throws IOException {
        File folder = new File("Database\\Users\\PhoneNumbers\\"+user.getId()+".json");
        if(!folder.exists()){folder.createNewFile();}
        MLogger.log("INFO:  UserSaver.Static_SavePhoneNumber");

        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.configure(JsonParser.Feature.AUTO_CLOSE_SOURCE, true);
        FileWriter fileWriter = new FileWriter(folder);
        objectMapper.enable(SerializationFeature.INDENT_OUTPUT);
        objectMapper.writeValue(fileWriter, user.getPhonenumber());
    }

    private static void jSaveEmail(User user) throws IOException {
        File folder = new File("Database\\Users\\Emails\\"+user.getId()+".json");
        if(!folder.exists()){folder.createNewFile();}
        MLogger.log("INFO:  UserSaver.Static_SaveEmail");

        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.configure(JsonParser.Feature.AUTO_CLOSE_SOURCE, true);
        FileWriter fileWriter = new FileWriter(folder);
        objectMapper.enable(SerializationFeature.INDENT_OUTPUT);
        objectMapper.writeValue(fileWriter, user.getEmail());
    }

    private static void jSaveUsername(User user) throws IOException {
        File previous_folder = new File("Database\\Users\\UserNames\\"+user.getPrevious_username()+".json");
        if(previous_folder.exists()){previous_folder.delete();}
        File folder = new File("Database\\Users\\UserNames\\"+user.getUsername()+".json");
        if(!folder.exists()){folder.createNewFile();}
        MLogger.log("INFO:  UserSaver.Static_SaveUsername");

        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.configure(JsonParser.Feature.AUTO_CLOSE_SOURCE, true);
        FileWriter fileWriter = new FileWriter(folder);
        objectMapper.enable(SerializationFeature.INDENT_OUTPUT);
        objectMapper.writeValue(fileWriter, user.getId());
    }
}
