package config;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;

public class Config extends Properties {
    private static final String MAIN_ADDRESS = "src/main/resources/configurations/main.properties";
    private static final Config MAIN_CONFIG = new Config(MAIN_ADDRESS);

    public static Config getConfig(String name) {
        if ("mainConfig".equals(name)) {
            return MAIN_CONFIG;
        }else {
            String configAddress = MAIN_CONFIG.getProperty(name);
            return new Config(configAddress);
        }
    }

    private Config(String address) {
        try {
            File file = new File(address);
            FileReader fileReader = new FileReader(file);
            this.load(fileReader);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public int getPropertyAsInt(String key){
        return Integer.parseInt(getProperty(key));
    }
}
