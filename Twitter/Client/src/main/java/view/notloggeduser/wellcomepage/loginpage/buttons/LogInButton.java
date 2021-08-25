package view.notloggeduser.wellcomepage.loginpage.buttons;

import events.clientevents.graphicalevents.notloggeduser.LogInPageEvent;
import view.mainclasses.MButton;
import view.notloggeduser.wellcomepage.loginpage.page.LogInPage;

import java.awt.*;
import java.awt.event.MouseEvent;
import java.io.IOException;

public class LogInButton extends MButton {
    private final LogInPage logInPage;
    public LogInButton(Color foregroundColor1, Color backgroundColor1, Color foregroundColor2, Color backgroundColor2, Font font, LogInPage logInPage) {
        super(foregroundColor1, backgroundColor1, foregroundColor2, backgroundColor2, font);
        this.setText("Log In");
        this.logInPage = logInPage;
    }

    public LogInPage getWellComePage() {
        return logInPage;
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        try {
            logInPage.sendClientEvent(new LogInPageEvent(logInPage.getUserNameTextField().getText(), logInPage.getPasswordTextField().getText()), true);
        } catch (IOException ioException) {
            ioException.printStackTrace();
        } catch (Exception exception) {
            exception.printStackTrace();
        }
    }
}
