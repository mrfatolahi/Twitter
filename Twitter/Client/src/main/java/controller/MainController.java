package controller;

import controller.offlineController.OfflineMainController;
import events.EventManager;
import events.clientevents.graphicalevents.notloggeduser.LogInPageEvent;
import events.clientevents.graphicalevents.notloggeduser.SignUpPageEvent;
import events.clientevents.main.*;
import events.serverevents.main.SendAuthTokenSE;
import network.NetworkManager;
import events.serverevents.main.ServerEvent;
import view.loggeduser.LUserPanel;
import view.mainclasses.GraphicalAgent;

import java.io.IOException;
import java.util.Random;

public class MainController implements EventManager {
    private final NetworkManager networkManager;
    private final GraphicalAgent graphicalAgent;
    private final OfflineMainController offlineMainController;
    private Thread waitingThread;
    private String authToken;

    public MainController() throws IOException {
        this.networkManager = new NetworkManager(this);
        this.graphicalAgent = new GraphicalAgent(this);
        this.offlineMainController = new OfflineMainController(this);

    }

    @Override
    public <E extends ClientEvent> void sendClientEvent(E clientEvent, boolean hasAnswer) throws Exception {
        if (clientEvent.getClass() != LogInPageEvent.class && clientEvent.getClass() != SignUpPageEvent.class){
            clientEvent.setAuthToken(authToken);
        }

        if (clientEvent.getClass() == MakeClientNetworkManagerWaiting.class){
            try {
                try {
                    waitingThread.interrupt();
                }catch (Exception ignored){}
                networkManager.waitForServerAnswer();
            } catch (IOException | ClassNotFoundException e) {
                e.printStackTrace();
            }
            return;
        }else
        if (clientEvent.getClass() == ActivateWaintingThreadEvent.class){
            activateWaitingThread();
            return;
        } else
        if (clientEvent.getClass() == InterruptWaitingThread.class){
            try {
                System.out.println("[][][]\n[][][]\n[][][]");
                doInterruptOperation();
                waitingThread = null;
            }catch (Exception ignored){
                ignored.printStackTrace();
            }
            return;
        }



        if (networkManager.isConnectedToServer()){
            networkManager.sendClientEvent(clientEvent, hasAnswer);
            if (hasAnswer){
                try {
                    try {
                        if (waitingThread != null){
                            System.out.println("&^&^&^^&");
                            doInterruptOperation();
                        }
                    }catch (Exception ignored){
                        ignored.printStackTrace();
                    }
                    networkManager.waitForServerAnswer();
                } catch (IOException | ClassNotFoundException ignored) {
                    ignored.printStackTrace();
                }
            }
        } else {
            offlineMainController.receiveGraphicalEvent(clientEvent);
        }


    }

    public void tryToReconnect(){
        networkManager.tryToReconnect();
    }

    public void swichToOfflineMode(){
//        LUserPanel.setOnline(false);
    }

    public void manageReConnection(){
        try {
            sendClientEvent((new LogInWithIdEvent(graphicalAgent.getlUserGraphicalAgent().getOwnerId())), false);
        } catch (Exception e) {
            e.printStackTrace();
        }
        offlineMainController.manageReConnection();
    }

    private void activateWaitingThread(){
//        try {
//            waitingThread.interrupt();
//        }catch (Exception ignored){
//            ignored.printStackTrace();
//        }

        waitingThread = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    networkManager.waitForServerAnswer();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });

        waitingThread.start();
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

    private void doInterruptOperation() throws Exception {
        System.out.println("()\n()\n()\n()\n()\n()\n");
        sendClientEvent(new RequestInterrupterEventCE(), false);
    }

    @Override
    public <E extends ServerEvent> void receiveServerEvent(E logicalEvent) throws Exception {
        if (logicalEvent.getClass() == SendAuthTokenSE.class){
            this.authToken = ((SendAuthTokenSE) logicalEvent).getAuthToken();
            try {
                networkManager.waitForServerAnswer();
            } catch (IOException | ClassNotFoundException e) {
                e.printStackTrace();
            }
            return;
        }
        if (networkManager.isConnectedToServer()){
            offlineMainController.saveNeededInformations(logicalEvent);
        }
        graphicalAgent.receiveServerEvent(logicalEvent);
    }
}
