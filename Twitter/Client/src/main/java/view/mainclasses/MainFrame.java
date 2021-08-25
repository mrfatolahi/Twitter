package view.mainclasses;

import config.Config;

import javax.swing.*;
import java.awt.*;

public class MainFrame extends JFrame{
    public MainFrame() throws HeadlessException {
        Config config = Config.getConfig("Frame");
        this.setSize(Integer.parseInt(config.getProperty("width")), Integer.parseInt(config.getProperty("height")));
        this.setResizable(false);
        this.setTitle("Twitter");
        Config logoConfig = Config.getConfig("ImagesPro");
        ImageIcon imageIcon = new ImageIcon(logoConfig.getProperty("LogoImage"));
        this.setIconImage(imageIcon.getImage());
        this.setLayout(null);
        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        this.setLocationRelativeTo(null);
        this.setVisible(true);
    }
}
