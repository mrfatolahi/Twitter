package view.mainclasses;


import controller.MainController;
import events.EventManager;
import events.clientevents.main.ClientEvent;
import events.serverevents.main.ServerEvent;
import view.loggeduser.LUserGraphicalAgent;
import view.notloggeduser.NLUserGraphicalAgent;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class GraphicalAgent implements EventManager {
    private final MainController mainController;
    private LUserGraphicalAgent lUserGraphicalAgent;
    private NLUserGraphicalAgent nlUserGraphicalAgent;
    private final MainFrame mainFrame;
    private final MainPanel mainPanel;

    public GraphicalAgent(MainController mainController) {
        this.mainController = mainController;
        this.mainFrame = new MainFrame();
        this.mainPanel = new MainPanel();
        mainFrame.add(mainPanel);
        this.lUserGraphicalAgent = null;
        this.nlUserGraphicalAgent = new NLUserGraphicalAgent(this);
    }

    public void setlUserGraphicalAgent(LUserGraphicalAgent lUserGraphicalAgent) {
        this.lUserGraphicalAgent = lUserGraphicalAgent;
    }

    public MainFrame getMainFrame() {
        return mainFrame;
    }

    public MainPanel getMainPanel() {
        return mainPanel;
    }

    public LUserGraphicalAgent getlUserGraphicalAgent() {
        return lUserGraphicalAgent;
    }

    public void setlUserGraphicalAgent(int id) {
        System.out.println("id = " + id);
        this.lUserGraphicalAgent = new LUserGraphicalAgent(id, mainPanel, this);
    }

    public NLUserGraphicalAgent getNlUserGraphicalAgent() {
        return nlUserGraphicalAgent;
    }

    public void setNlUserGraphicalAgent(NLUserGraphicalAgent nlUserGraphicalAgent) {
        this.nlUserGraphicalAgent = nlUserGraphicalAgent;
    }

//    public LogicalAgent getLogicalAgent() {
//        return logicalAgent;
//    }


    public LUserGraphicalAgent getUserGraphicalAgent() {
        return lUserGraphicalAgent;
    }

    public static ImageIcon reSizeImage(int width, int height, String path){
        BufferedImage img = null;
        try {
            File file = new File(path);
            img = ImageIO.read(file);
        } catch (IOException e) {
            e.printStackTrace();
        }
        Image dimg = img.getScaledInstance(width, height, Image.SCALE_SMOOTH);
        return new ImageIcon(dimg);
    }

    public void openAWellComePage(){
        nlUserGraphicalAgent.openAWellComePage();
    }

    public void tryToReconnect(){
        mainController.tryToReconnect();
    }


    @Override
    public <E extends ClientEvent> void sendClientEvent(E clientEvent, boolean hasAnswer) throws Exception {
        mainController.sendClientEvent(clientEvent, hasAnswer);
    }

    @Override
    public <E extends ServerEvent> void receiveServerEvent(E logicalEvent) throws Exception {
        try {
            lUserGraphicalAgent.receiveServerEvent(logicalEvent);
        }catch (Exception e){
            nlUserGraphicalAgent.receiveServerEvent(logicalEvent);
        }
    }
}
