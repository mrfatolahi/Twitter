package controller.botmanager;

import config.Config;

import java.net.URL;
import java.net.URLClassLoader;
import java.util.ArrayList;
import java.util.Scanner;

public class Tester3 {
    public static void main(String[] args) throws Exception {
        String address = Config.getConfig("BotsURLs").getProperty("questionBot");
        URLClassLoader urlClassLoader;
        urlClassLoader = new URLClassLoader(new URL[]{new URL(address)});
        Class botClass = urlClassLoader.loadClass("Bot");

        ArrayList<String> usernames = new ArrayList<>();
        usernames.add("q");
        usernames.add("a");
        usernames.add("z");
        botClass.getMethod("initialize", new Class[]{ArrayList.class}).invoke(null, usernames);
        System.out.println(((String) botClass.getMethod("getFirstQuestion", new Class[]{}).invoke(null)));

        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("\n");
            ArrayList<ArrayList<String>> m = new ArrayList<>();
            ArrayList<String> g1 = new ArrayList<>();
            System.out.println("Name:");
            g1.add(scanner.nextLine());
            System.out.println("Answer:");
            g1.add(scanner.nextLine());
            m.add(g1);
            ArrayList<String> g2 = new ArrayList<>();
            System.out.println("Name:");
            g2.add(scanner.nextLine());
            System.out.println("Answer:");
            g2.add(scanner.nextLine());
            m.add(g2);
            ArrayList<String> g3 = new ArrayList<>();
            System.out.println("Name:");
            g3.add(scanner.nextLine());
            System.out.println("Answer:");
            g3.add(scanner.nextLine());
            m.add(g3);

            System.out.println("m = " + m);

            System.out.println(((String) botClass.getMethod("receiveAnswers", new Class[]{ArrayList.class}).invoke(null, m)));
            System.out.println();
        }
    }
}
