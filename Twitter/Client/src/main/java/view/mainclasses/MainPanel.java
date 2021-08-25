package view.mainclasses;

import config.Config;

import javax.swing.*;
import java.awt.*;

public class MainPanel extends JPanel {

    public MainPanel() throws HeadlessException {
        this.setLayout(null);
        Config config = Config.getConfig("Frame");
        this.setBounds(0, 0, Integer.parseInt(config.getProperty("width")), Integer.parseInt(config.getProperty("height")));
    }

}
