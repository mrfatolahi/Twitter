package controller;

import XLogger.MLogger;
import events.clientevents.graphicalevents.notloggeduser.LogInPageEvent;
import events.clientevents.graphicalevents.notloggeduser.SignUpPageEvent;
import events.clientevents.main.DeclareClientExitCE;
import events.clientevents.main.LogInWithIdEvent;
import events.clientevents.main.RequestInterrupterEventCE;
import events.serverevents.interfaces.LogicEventManager;
import controller.logic.LogicalAgent;
import events.serverevents.main.SendAuthTokenSE;
import events.serverevents.main.SendInterrupterEventSE;
import events.serverevents.main.ServerEvent;
import events.clientevents.main.ClientEvent;
import network.MyObjectOutputStream;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

public class ClientHandler implements LogicEventManager {
    private String authToken;
    private final MainController mainController;
    private final LogicalAgent logicalAgent;
    private final ObjectOutputStream objectOutputStream;
    private final ObjectInputStream objectInputStream;
    private final MyObjectOutputStream myObjectOutputStream;
    private final Socket socket;
    private boolean mustDie;
    private boolean a;
    private int id;

    public ClientHandler(MainController mainController, Socket socket) throws IOException {
        this.mainController = mainController;
        this.socket = socket;
        this.objectOutputStream = new ObjectOutputStream(socket.getOutputStream());
        this.objectInputStream = new ObjectInputStream(socket.getInputStream());
        this.myObjectOutputStream = new MyObjectOutputStream(socket.getOutputStream());
        this.logicalAgent = new LogicalAgent(this);
        this.id = -1;
        this.a = false;
        this.mustDie = false;
    }

    public void start() throws IOException, ClassNotFoundException {
        manageClientEvents();
    }

    private void manageClientEvents() throws IOException {
        while (!mustDie) {
            try {
                System.out.println("@@@@@@@@@@");
                ClientEvent clientEvent = (ClientEvent) objectInputStream.readObject();
                if (clientEvent.getClass() != LogInPageEvent.class && clientEvent.getClass() != SignUpPageEvent.class && clientEvent.getClass() != LogInWithIdEvent.class){
                    if (!checkAuthToken(clientEvent)){
                        System.out.println("???????");
                        MLogger.log("WARN: Wrong authToken received from a connection, connection disconneted because of security risks.");
                        continue;
                    }
                }
                System.out.println("received = " + clientEvent.getClass());
                MLogger.log("EVENT_RECEIVED: "+clientEvent.getClass().toString());
                if (clientEvent.getClass() == DeclareClientExitCE.class){
                    this.close();
                    continue;
                }
                if (clientEvent.getClass() == RequestInterrupterEventCE.class){
                    sendLogicalEvent(new SendInterrupterEventSE());
                    continue;
                }
                try {
                    receiveGraphicalEvent(clientEvent);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }

        }
    }

    private void close(){
        mainController.getConnectedClients().remove(this);
        try {
            socket.close();
        } catch (IOException ignored) {}
        mustDie = true;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public MainController getMainController() {
        return mainController;
    }

    public ClientHandler getClientById(int id){
        return mainController.getClientById(id);
    }

    public void updateLoggedInUserInfoFromFile(){
        logicalAgent.updateLoggedInUserInfoFromFile();
    }

    public String getAuthToken() {
        return authToken;
    }

    public void setAuthToken(String authToken) {
        this.authToken = authToken;
    }

    public void assignAuthToken(){
        authToken = MainController.generateRandomString();
        try {
            objectOutputStream.writeObject(new SendAuthTokenSE(authToken));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private boolean checkAuthToken(ClientEvent clientEvent){
        if (!clientEvent.getAuthToken().equals(authToken)){
            try {
                throw new Exception("Wrong authToken received from a connection, connection disconneted because of security risks.");
            } catch (Exception ignored) {}
            close();
            return false;
        }

        return true;
    }

    @Override
    public <E extends ServerEvent> void sendLogicalEvent(E logicalEvent) throws IOException {
        try {
            Thread.sleep(100);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("sent = " + logicalEvent.getClass());
        MLogger.log("EVENT_SENT: "+logicalEvent.getClass().toString());
        objectOutputStream.reset();
        objectOutputStream.writeObject(logicalEvent);

    }

    @Override
    public <E extends ClientEvent> void receiveGraphicalEvent(E clientEvent) throws Exception {
        logicalAgent.receiveGraphicalEvent(clientEvent);
    }


}
