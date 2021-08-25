package view.notloggeduser.wellcomepage.buttons;

import view.mainclasses.MButton;
import view.notloggeduser.wellcomepage.WellComePage;
import view.notloggeduser.wellcomepage.signuppage.page.SignUpPage;

import java.awt.*;
import java.awt.event.MouseEvent;

public class RegisterButton extends MButton {
    private final WellComePage wellComePage;
    public RegisterButton(Color foregroundColor1, Color backgroundColor1, Color foregroundColor2, Color backgroundColor2, Font font, WellComePage wellComePage) {
        super(foregroundColor1, backgroundColor1, foregroundColor2, backgroundColor2, font);
        this.setText("Sign Up");
        this.wellComePage = wellComePage;

    }

    @Override
    public void mouseClicked(MouseEvent e) {
        wellComePage.removeAll();
        wellComePage.add(new SignUpPage(wellComePage));
        wellComePage.revalidate();
        wellComePage.repaint();
    }
}
