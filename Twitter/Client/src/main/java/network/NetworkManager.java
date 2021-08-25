package network;

import controller.MainController;
import events.EventManager;
import events.clientevents.main.ClientEvent;
import events.serverevents.main.SendInterrupterEventSE;
import events.serverevents.main.ServerEvent;

import javax.swing.*;
import java.io.*;
import java.net.Socket;
import java.util.Properties;

public class NetworkManager implements EventManager {
    private final MainController mainController;
    private ObjectOutputStream objectOutputStream;
    private ObjectInputStream objectInputStream;
    private Socket socket;
    private static boolean connectedToServer = true;
    public final static Object connectionNotifier = new Object();
    private final Object object1;

    public NetworkManager(MainController mainController) throws IOException {
        this.mainController = mainController;
        this.object1 = new Object();
        start();
    }

    public static boolean isConnectedToServer() {
        return connectedToServer;
    }

    public static void setConnectedToServer(boolean connectedToServer) {
        NetworkManager.connectedToServer = connectedToServer;
    }

    public ObjectOutputStream getObjectOutputStream() {
        return objectOutputStream;
    }

    public ObjectInputStream getObjectInputStream() {
        return objectInputStream;
    }

    public boolean start() throws IOException {
        try {
            Properties properties = new Properties();
            InputStream inputStream = new FileInputStream("conncetion.properties");
            properties.load(inputStream);

            this.socket = new Socket(properties.getProperty("address"), Integer.parseInt(properties.getProperty("port")));
        } catch (Exception e) {
            try {
                this.socket = new Socket("localhost", 8000);
            }catch (Exception r){
                return false;
            }
        }
        objectOutputStream = new ObjectOutputStream(socket.getOutputStream());
        objectInputStream = new ObjectInputStream(socket.getInputStream());

        return true;
    }

    public void tryToReconnect(){
        try {
            boolean result = this.start();
            if (result){
                NetworkManager.setConnectedToServer(true);
                mainController.manageReConnection();
                synchronized (connectionNotifier){
                    connectionNotifier.notifyAll();
                }

                JOptionPane.showMessageDialog(
                        null, "Connected :)",
                        "Result", JOptionPane.INFORMATION_MESSAGE
                );
            }else{
                JOptionPane.showMessageDialog(
                        null, "Can't connect :(",
                        "Result", JOptionPane.ERROR_MESSAGE
                );
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public <E extends ServerEvent> void receiveServerEvent(E serverEvent) {
        try {
            mainController.receiveServerEvent(serverEvent);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public <E extends ClientEvent> void sendClientEvent(E clientEvent, boolean hasAnswer) {
        synchronized (this){
            try {
                Thread.sleep(100);
                System.out.println("sent = " + clientEvent.getClass());
                objectOutputStream.writeObject(clientEvent);
            } catch (Exception e) {
                e.printStackTrace();
                NetworkManager.setConnectedToServer(false);
//                JOptionPane.showMessageDialog(
//                        null, "Connection lost :(",
//                        "Error", JOptionPane.ERROR_MESSAGE
//                );
            }
        }
    }

    public void waitForServerAnswer() throws IOException, ClassNotFoundException {
        try {
            Object object;
                object = objectInputStream.readObject();
            System.out.println("###received = " + object.getClass());
            if (object.getClass() == SendInterrupterEventSE.class){
                return;
            }
            receiveServerEvent((ServerEvent) object);
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
