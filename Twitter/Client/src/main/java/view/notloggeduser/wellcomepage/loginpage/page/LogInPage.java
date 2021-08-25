package view.notloggeduser.wellcomepage.loginpage.page;

import events.EventManager;
import events.clientevents.main.ClientEvent;
import events.serverevents.interfaces.ServerAnswerInterpreter;
import events.serverevents.main.ServerEvent;
import events.serverevents.nluser.LogInResultEvent;
import events.serverevents.nluser.SignUpResultEvent;
import view.notloggeduser.wellcomepage.WellComePage;
import view.notloggeduser.wellcomepage.loginpage.buttons.LogInButton;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.IOException;

public class LogInPage extends JPanel implements EventManager, ServerAnswerInterpreter {
    private final WellComePage wellComePage;
    private final JTextField userNameTextField;
    private final JPasswordField passwordTextField;
    private final JLabel userNameLabel;
    private final JLabel passwordLabel;
    private final LogInButton logInButton;
    private JLabel resultLabel;


    public LogInPage(WellComePage wellComePage) {
        this.wellComePage = wellComePage;
        wellComePage.setLogInPage(this);
        this.setLayout(null);
        this.setBounds(0, 0, 800, 760);


        userNameTextField = new JTextField();
        userNameTextField.setBounds(400, 200, 200, 50);
        this.add(userNameTextField);

        passwordTextField = new JPasswordField();
        passwordTextField.setBounds(400, 270, 200, 50);
        this.add(passwordTextField);

        logInButton = new LogInButton(Color.BLACK, Color.WHITE, Color.WHITE,
                Color.BLACK, new Font("Arial", Font.PLAIN, 25), this);
        logInButton.setBounds(250, 340, 300 ,50);
        this.add(logInButton);

        userNameLabel = new JLabel("Username: ", SwingConstants.CENTER);
        userNameLabel.setBounds(200, 200, 200, 50);
        userNameLabel.setFont(new Font("Arial", Font.PLAIN, 25));
        this.add(userNameLabel);

        passwordLabel = new JLabel("Password: ", SwingConstants.CENTER);
        passwordLabel.setBounds(200, 270, 200, 50);
        passwordLabel.setFont(new Font("Arial", Font.PLAIN, 25));
        this.add(passwordLabel);

        this.resultLabel = new JLabel();
        this.resultLabel.setBackground(Color.GREEN);
        this.resultLabel.setBounds(250, 410, 300, 50);
        this.add(resultLabel);

        JButton backButton = new JButton("Back");
        backButton.setBounds(720, 640, 70, 30);
        backButton.setBackground(Color.WHITE);
        backButton.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                wellComePage.getMainPanel().removeAll();
                wellComePage.getMainPanel().add(new WellComePage(wellComePage.getNlUserGraphicalAgent(), wellComePage.getMainPanel()));
                wellComePage.getMainPanel().repaint();
            }
            public void mousePressed(MouseEvent e){}
            public void mouseReleased(MouseEvent e){}
            public void mouseEntered(MouseEvent e) {
                backButton.setBackground(Color.CYAN);
            }
            public void mouseExited(MouseEvent e) {
                backButton.setBackground(Color.WHITE);
            }
        });
        this.add(backButton);

        JButton exitButton = new JButton("Exit");
        exitButton.setBounds(720, 670, 70, 30);
        exitButton.setBackground(Color.WHITE);
        exitButton.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                System.exit(0);
            }
            public void mousePressed(MouseEvent e){}
            public void mouseReleased(MouseEvent e){}
            public void mouseEntered(MouseEvent e) {
                exitButton.setBackground(Color.RED);
            }
            public void mouseExited(MouseEvent e) {
                exitButton.setBackground(Color.WHITE);
            }
        });
        this.add(exitButton);

        this.repaint();
        this.revalidate();
    }

    private void setResultLabelText(String text){
        this.remove(resultLabel);
        this.resultLabel = new JLabel(text);
        resultLabel.setHorizontalAlignment(SwingConstants.CENTER);
        resultLabel.setFont(new Font("Arial", Font.PLAIN, 20));
        this.resultLabel.setBounds(250, 410, 300, 50);
        this.add(resultLabel);

        this.repaint();
    }

    public JTextField getUserNameTextField() {
        return userNameTextField;
    }

    public WellComePage getWellComePage() {
        return wellComePage;
    }

    public JPasswordField getPasswordTextField() {
        return passwordTextField;
    }

    public JLabel getResultLabel() {
        return resultLabel;
    }

    public LogInButton getLogInButton() {
        return logInButton;
    }

    @Override
    public <E extends ClientEvent> void sendClientEvent(E clientEvent, boolean hasAnswer) throws Exception {
        wellComePage.sendClientEvent(clientEvent, hasAnswer);
    }

    @Override
    public <E extends ServerEvent> void receiveServerEvent(E logicalEvent) throws IOException {
        if (logicalEvent.getClass() == LogInResultEvent.class){
            interpretServerAnswer((LogInResultEvent) logicalEvent);
        }
    }

    public <E extends ServerEvent> void interpretServerAnswer(E serverEvent) {
        switch (((LogInResultEvent) serverEvent).getLogInResult()){
            case USERNAME_NOT_FOUND:
                JOptionPane.showMessageDialog(null, "Username Not Found");
                setResultLabelText("Username Not Found");
                break;
            case USERNAME_IS_EMPTY:
                JOptionPane.showMessageDialog(null, "Username can't be empty");
                setResultLabelText("Username can't be empty");
                break;
            case INVALID_USERNAME:
                JOptionPane.showMessageDialog(null, "Invalid Username");
                setResultLabelText("Invalid Username");
                break;
            case PASSWORD_IS_EMPTY:
                JOptionPane.showMessageDialog(null, "Password can't be empty");
                setResultLabelText("Password can't be empty");
                break;
            case WRONG_PASSWORD:
                JOptionPane.showMessageDialog(null, "Wrong Password");
                setResultLabelText("Wrong Password");
                break;
            case INVALID_PASSWORD:
                JOptionPane.showMessageDialog(null, "Invalid Password");
                setResultLabelText("Invalid Password");
                break;
            case DELETED_ACCOUNT:
                JOptionPane.showMessageDialog(null, "Deleted Account");
                setResultLabelText("Deleted Account");
                break;
            case NO_PROBLEM:
                wellComePage.getNlUserGraphicalAgent().getGraphAgent().setlUserGraphicalAgent(((LogInResultEvent) serverEvent).getId());
        }
    }
}
