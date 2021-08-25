package XLogger;

import java.io.*;
import java.time.LocalTime;

public class MLogger {
    public static void clearFile() throws IOException {
        File file = new File("Database\\Log.txt");
        if(!file.exists()){file.createNewFile();}
        FileOutputStream fount = new FileOutputStream(file , false);
        PrintStream writer  = new PrintStream(fount);
        writer.print("");
    }

    public static void log(String text) {
        File file = new File("Database\\Log.txt");
        FileOutputStream fount = null;
        try {
            fount = new FileOutputStream(file , true);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        PrintStream writer  = new PrintStream(fount);
        writer.print(text+"         "+ LocalTime.now() +"\n\n");
    }
}
