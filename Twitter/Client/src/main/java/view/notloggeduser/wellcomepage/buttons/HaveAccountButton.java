package view.notloggeduser.wellcomepage.buttons;

import view.mainclasses.MButton;
import view.notloggeduser.wellcomepage.WellComePage;
import view.notloggeduser.wellcomepage.loginpage.page.LogInPage;

import java.awt.*;
import java.awt.event.MouseEvent;

public class HaveAccountButton extends MButton {
    private final WellComePage wellComePage;

    public HaveAccountButton(Color foregroundColor1, Color backgroundColor1, Color foregroundColor2, Color backgroundColor2, Font font, WellComePage wellComePage) {
        super(foregroundColor1, backgroundColor1, foregroundColor2, backgroundColor2, font);
        this.setText("Log In");
        this.wellComePage = wellComePage;
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        wellComePage.removeAll();
        wellComePage.add(new LogInPage(wellComePage));
        wellComePage.repaint();
    }
}
