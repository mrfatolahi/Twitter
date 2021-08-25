package view.notloggeduser.wellcomepage.signuppage.buttons;

import events.clientevents.graphicalevents.notloggeduser.SignUpPageEvent;
import view.mainclasses.MButton;
import view.notloggeduser.wellcomepage.signuppage.page.SignUpPage;


import java.awt.*;
import java.awt.event.MouseEvent;

public class SignUpButton extends MButton {
    private final SignUpPage signUpPage;

    public SignUpButton(Color foregroundColor1, Color backgroundColor1, Color foregroundColor2, Color backgroundColor2, Font font, SignUpPage signUpPage) {
        super(foregroundColor1, backgroundColor1, foregroundColor2, backgroundColor2, font);
        this.setText("Sign Up");
        this.signUpPage = signUpPage;
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        try {
            signUpPage.sendClientEvent(new SignUpPageEvent(
                    signUpPage.getNameTextField().getText(), signUpPage.getLastNameTextField().getText(),
                    signUpPage.getUserNameTextField().getText(), signUpPage.getPasswordTextField().getText(),
                    signUpPage.getReTypedPasswordTextField().getText(), (int) signUpPage.getYearSpinner().getValue(),
                    (int) signUpPage.getMonthSpinner().getValue(), (int) signUpPage.getDaySpinner().getValue(), signUpPage.getEmailTextField().getText(), signUpPage.getPhoneNumberTextField().getText(),
                    signUpPage.getBioTextArea().getText()), true);
        } catch (Exception ioException) {
            ioException.printStackTrace();
        }
    }
}
