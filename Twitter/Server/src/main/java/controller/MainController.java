package controller;

import XLogger.MLogger;
import controller.botmanager.BotController;
import controller.botmanager.BotManager;
import network.NetworkManager;

import java.io.IOException;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Random;

public class MainController {
    private final NetworkManager networkManager;
    private final ArrayList<ClientHandler> connectedClients;
    private final BotController botController;

    public MainController() throws IOException {
        this.networkManager = new NetworkManager(this);
        this.connectedClients = new ArrayList();
        this.botController = new BotController(this);
    }

    public void start() {
        MLogger.log("INFO: Database connected");
        while (true){
            Socket socket = networkManager.waitForACliet();
            (new Thread(new Runnable() {
                @Override
                public void run() {
                    try {
                        ClientHandler client = new ClientHandler(MainController.this, socket);
                        connectedClients.add(client);
                        client.start();
                    } catch (IOException | ClassNotFoundException ignored) {}
                }
            })).start();
        }
    }


    public ArrayList<ClientHandler> getConnectedClients() {
        return connectedClients;
    }

    public NetworkManager getNetworkManager() {
        return networkManager;
    }

    public BotController getBotController() {
        return botController;
    }

    public ClientHandler getClientById(int id){
        for (ClientHandler clientHandler : connectedClients){
            if (clientHandler.getId() == -1){continue;}
            if (clientHandler.getId() == id){
                return clientHandler;
            }
        }

        MLogger.log("WARN: Can't load client and update it.");
        return null;
    }


    public static String generateRandomString(){
        String chars = "1234567890abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ!@#$%^&*()_+=}{:;?/|>.<,";
        Random random = new Random();

        String authToken = "";

        for (int i = 0; i < 200; i++) {
            authToken = authToken + chars.charAt(random.nextInt(chars.length()));
        }

        return authToken;
    }
}
