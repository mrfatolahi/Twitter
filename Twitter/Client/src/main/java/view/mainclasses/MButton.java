package view.mainclasses;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class MButton extends JButton implements MouseListener {
    private final Color foregroundColor1;
    private final Color backgroundColor1;
    private final Color foregroundColor2;
    private final Color backgroundColor2;

    public MButton(Color foregroundColor1, Color backgroundColor1,
                   Color foregroundColor2, Color backgroundColor2,
                    Font font) {

        this.foregroundColor1 = foregroundColor1;
        this.backgroundColor1 = backgroundColor1;

        this.foregroundColor2 = foregroundColor2;
        this.backgroundColor2 = backgroundColor2;

        this.setFont(font);

        this.setFocusable(false);
        this.addMouseListener(this);
        this.setNormalStyle();
        this.repaint();
    }

    public void setMouseInStyle(){
        this.setForeground(foregroundColor2);
        this.setBackground(backgroundColor2);
        this.repaint();
    }

    public void setNormalStyle(){
        this.setForeground(foregroundColor1);
        this.setBackground(backgroundColor1);
        this.repaint();
    }



    @Override
    public void mouseClicked(MouseEvent e) {

    }

    @Override
    public void mousePressed(MouseEvent e) {

    }

    @Override
    public void mouseReleased(MouseEvent e) {

    }

    @Override
    public void mouseEntered(MouseEvent e) {
        this.setMouseInStyle();
    }

    @Override
    public void mouseExited(MouseEvent e) {
        this.setNormalStyle();
    }


}
