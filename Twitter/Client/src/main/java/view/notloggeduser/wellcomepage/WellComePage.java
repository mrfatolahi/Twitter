package view.notloggeduser.wellcomepage;

import config.Config;
import events.EventManager;
import events.clientevents.main.ClientEvent;
import events.serverevents.main.ServerEvent;
import events.serverevents.nluser.LogInResultEvent;
import events.serverevents.nluser.SignUpResultEvent;
import view.mainclasses.MainPanel;
import view.notloggeduser.NLUserGraphicalAgent;
import view.notloggeduser.wellcomepage.buttons.HaveAccountButton;
import view.notloggeduser.wellcomepage.buttons.RegisterButton;
import view.notloggeduser.wellcomepage.loginpage.page.LogInPage;
import view.notloggeduser.wellcomepage.signuppage.page.SignUpPage;

import javax.swing.*;
import java.awt.*;

public class WellComePage extends JPanel implements EventManager {
    private final NLUserGraphicalAgent nlUserGraphicalAgent;
    private final MainPanel mainPanel;
    private SignUpPage signUpPage;
    private LogInPage logInPage;
    private final RegisterButton registerButton;
    private final HaveAccountButton haveAccountButton;

    public WellComePage(NLUserGraphicalAgent nlUserGraphicalAgent, MainPanel mainPanel) {
        this.setLayout(null);
        this.nlUserGraphicalAgent = nlUserGraphicalAgent;
        this.signUpPage = null;
        this.logInPage = null;
        this.removeAll();
        this.setLayout(null);
        this.mainPanel = mainPanel;
        Config frameConfig = Config.getConfig("Frame");
        this.setBounds(0, 0, frameConfig.getPropertyAsInt("width"), frameConfig.getPropertyAsInt("height"));
        this.registerButton = new RegisterButton(Color.BLACK, Color.WHITE, Color.WHITE,
                Color.BLACK, new Font("Arial", Font.PLAIN, 25), this);
        registerButton.setBounds(250, 200, 300, 50);
        this.add(registerButton);

        this.haveAccountButton = new HaveAccountButton(Color.BLACK, Color.WHITE, Color.WHITE,
                Color.BLACK, new Font("Arial", Font.PLAIN, 25), this);
        haveAccountButton.setBounds(250, 400, 300, 50);
        this.add(haveAccountButton);

        this.repaint();
        this.revalidate();

        mainPanel.add(this);
        mainPanel.repaint();
    }

    public NLUserGraphicalAgent getNlUserGraphicalAgent() {
        return nlUserGraphicalAgent;
    }

    public SignUpPage getSignUpPage() {
        return signUpPage;
    }

    public void setSignUpPage(SignUpPage signUpPage) {
        this.signUpPage = signUpPage;
    }

    public LogInPage getLogInPage() {
        return logInPage;
    }

    public void setLogInPage(LogInPage logInPage) {
        this.logInPage = logInPage;
    }

    public MainPanel getMainPanel() {
        return mainPanel;
    }

    public RegisterButton getRegisterButton() {
        return registerButton;
    }

    public HaveAccountButton getHaveAccountButton() {
        return haveAccountButton;
    }


    @Override
    public <E extends ClientEvent> void sendClientEvent(E clientEvent, boolean hasAnswer) throws Exception {
        nlUserGraphicalAgent.sendClientEvent(clientEvent, hasAnswer);
    }

    @Override
    public <E extends ServerEvent> void receiveServerEvent(E logicalEvent) throws Exception {
        if (logicalEvent.getClass() == LogInResultEvent.class){
            this.setLogInPage(new LogInPage(this));
            logInPage.receiveServerEvent((LogInResultEvent) logicalEvent);
        }else if (logicalEvent.getClass() == SignUpResultEvent.class){
            this.setSignUpPage(new SignUpPage(this));
            signUpPage.receiveServerEvent((SignUpResultEvent) logicalEvent);
        }
    }
}
