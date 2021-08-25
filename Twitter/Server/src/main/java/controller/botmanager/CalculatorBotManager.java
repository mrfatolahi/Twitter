package controller.botmanager;

import XLogger.MLogger;
import config.Config;
import events.clientevents.main.ClientEvent;
import events.serverevents.main.ServerEvent;
import models.bots.CalculatorBotInfo;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLClassLoader;
import java.util.ArrayList;

public class CalculatorBotManager extends BotManager{
    private final CalculatorBotInfo calculatorBotInfo;
    public CalculatorBotManager(BotController botController, CalculatorBotInfo calculatorBotInfo) {
        super(botController);
        this.calculatorBotInfo = calculatorBotInfo;
        start();
    }

    public String calculate(double firstNumber, double secondNumber, char opertation) throws Exception{
        return String.valueOf(((double) botClass.getMethod("calculate", new Class[]{double.class, double.class, char.class}).invoke(null, firstNumber, secondNumber, opertation)));
    }

    @Override
    public void start() {
        (new Thread(new Runnable() {
            @Override
            public void run() {
                checkConfigFile();
            }
        })).start();
    }

    @Override
    protected void checkConfigFile(){
        while (true){
            try {
                Thread.sleep(10000);
                String address = Config.getConfig("BotsURLs").getProperty("calculatorBot");
                if (receivedURLs.contains(address)) {
                    continue;
                }
                receivedURLs.add(address);
                URLClassLoader urlClassLoader;
                urlClassLoader = new URLClassLoader(new URL[]{new URL(address)});

                botClass = urlClassLoader.loadClass("Bot");
                System.out.println("Bot Loaded");
                MLogger.log("INFO CalculatorBot Loaded");
                busy = false;
            } catch (Exception e){
                busy = true;
                MLogger.log("WARN: Can't load CalculatorBot class");
            }
        }
    }

    public CalculatorBotInfo getCalculatorBotInfo() {
        return calculatorBotInfo;
    }

    @Override
    public <E extends ServerEvent> void sendLogicalEvent(E logicalEvent) throws IOException {

    }

    @Override
    public <E extends ClientEvent> void receiveGraphicalEvent(E clientEvent) throws Exception {

    }
}
