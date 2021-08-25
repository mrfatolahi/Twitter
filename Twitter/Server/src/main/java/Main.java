import XLogger.MLogger;
import controller.MainController;

import java.io.IOException;

public class Main {
    public static void main(String[] args) {
        MLogger.log("INFO: Main.main");
        MainController mainController = null;
        try {
            mainController = new MainController();
            mainController.start();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
