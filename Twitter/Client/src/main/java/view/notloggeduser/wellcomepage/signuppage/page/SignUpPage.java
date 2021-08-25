package view.notloggeduser.wellcomepage.signuppage.page;

import config.Config;
import events.EventManager;
import events.clientevents.main.ClientEvent;
import events.serverevents.interfaces.ServerAnswerInterpreter;
import events.serverevents.main.ServerEvent;
import events.serverevents.nluser.SignUpResultEvent;
import view.notloggeduser.wellcomepage.WellComePage;
import view.notloggeduser.wellcomepage.signuppage.buttons.SignUpButton;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.IOException;

public class SignUpPage extends JPanel implements EventManager, ServerAnswerInterpreter {

    private final WellComePage wellComePage;
    private final JTextField nameTextField;
    private final JTextField LastNameTextField;
    private final JTextField userNameTextField;
    private final JTextField passwordTextField;
    private final JTextField reTypedPasswordTextField;
    private final JSpinner yearSpinner;
    private final JSpinner monthSpinner;
    private final JSpinner daySpinner;
    private final JTextField emailTextField;
    private final JTextField phoneNumberTextField;
    private final JTextArea bioTextArea;
    private final SignUpButton signUpButton;
    private JLabel resultLabel;


    public SignUpPage(WellComePage wellComePage) {
        this.wellComePage = wellComePage;
        wellComePage.setSignUpPage(this);
        this.removeAll();
        this.setLayout(null);
        Config frameConfig = Config.getConfig("Frame");
        this.setBounds(0, 0, frameConfig.getPropertyAsInt("width"), frameConfig.getPropertyAsInt("height"));


        JLabel nameLabel = new JLabel("Name:", JLabel.CENTER);
        nameLabel.setFont(new Font("Arial", Font.PLAIN, 20));
        nameLabel.setBounds(50, 130, 150, 50);
        this.add(nameLabel);
        nameTextField = new JTextField();
        nameTextField.setBounds(200, 130, 150, 50);
        nameTextField.setToolTipText("Enter Name");
        this.add(nameTextField);

        JLabel lastNameLabel = new JLabel("Last Name:", JLabel.CENTER);
        lastNameLabel.setFont(new Font("Arial", Font.PLAIN, 20));
        lastNameLabel.setBounds(50, 200, 150, 50);
        this.add(lastNameLabel);
        LastNameTextField = new JTextField();
        LastNameTextField.setBounds(200, 200, 150, 50);
        LastNameTextField.setToolTipText("Enter Lastname");
        this.add(LastNameTextField);

        JLabel passwordLabel = new JLabel("Password:", JLabel.CENTER);
        passwordLabel.setFont(new Font("Arial", Font.PLAIN, 20));
        passwordLabel.setBounds(50, 270, 150, 50);
        this.add(passwordLabel);
        passwordTextField = new JTextField();
        passwordTextField.setBounds(200, 270, 150, 50);
        passwordTextField.setToolTipText("Enter Password");
        this.add(passwordTextField);

        JLabel reTypePasswordLabel = new JLabel("Retype Password:", JLabel.CENTER);
        reTypePasswordLabel.setFont(new Font("Arial", Font.PLAIN, 15));
        reTypePasswordLabel.setBounds(50, 340, 150, 50);
        this.add(reTypePasswordLabel);
        reTypedPasswordTextField = new JTextField();
        reTypedPasswordTextField.setBounds(200, 340, 150, 50);
        reTypedPasswordTextField.setToolTipText("ReType Password");
        this.add(reTypedPasswordTextField);

        JLabel usernaneLabel = new JLabel("Username:", JLabel.CENTER);
        usernaneLabel.setFont(new Font("Arial", Font.PLAIN, 20));
        usernaneLabel.setBounds(50, 410, 150, 50);
        this.add(usernaneLabel);
        userNameTextField = new JTextField();
        userNameTextField.setBounds(200, 410, 150, 50);
        userNameTextField.setToolTipText("Enter Username");
        this.add(userNameTextField);

        JLabel birthdateLabel = new JLabel("Birthdate: (Y/M/D)", JLabel.LEFT);
        birthdateLabel.setFont(new Font("Arial", Font.PLAIN, 18));
        birthdateLabel.setBounds(400, 130, 150, 50);
        this.add(birthdateLabel);
        yearSpinner = new JSpinner();
        yearSpinner.setModel(new SpinnerNumberModel(2000, 1900, 2020, 1));
        yearSpinner.setBounds(550, 130, 50, 50);
        this.add(yearSpinner);

        monthSpinner = new JSpinner();
        monthSpinner.setBounds(610, 130, 40, 50);
        monthSpinner.setModel(new SpinnerNumberModel(1, 1, 12, 1));
        monthSpinner.setValue(1);
        this.add(monthSpinner);


        daySpinner = new JSpinner();
        daySpinner.setBounds(660, 130, 40, 50);
        daySpinner.setModel(new SpinnerNumberModel(1, 1, 31, 1));
        daySpinner.setValue(1);
        this.add(daySpinner);



        JLabel emailLabel = new JLabel("Email:", JLabel.CENTER);
        emailLabel.setFont(new Font("Arial", Font.PLAIN, 20));
        emailLabel.setBounds(400, 200, 150, 50);
        this.add(emailLabel);
        emailTextField = new JTextField();
        emailTextField.setBounds(550, 200, 150, 50);
        emailTextField.setToolTipText("Enter Email");
        this.add(emailTextField);

        JLabel phonenumberLabel = new JLabel("PhoneNumber:", JLabel.CENTER);
        phonenumberLabel.setFont(new Font("Arial", Font.PLAIN, 20));
        phonenumberLabel.setBounds(400, 270, 150, 50);
        this.add(phonenumberLabel);
        phoneNumberTextField = new JTextField();
        phoneNumberTextField.setBounds(550, 270, 150, 50);
        phoneNumberTextField.setToolTipText("Enter Phonenumber");
        this.add(phoneNumberTextField);

        JLabel bioLabel = new JLabel("Bio:", JLabel.CENTER);
        bioLabel.setFont(new Font("Arial", Font.PLAIN, 20));
        bioLabel.setBounds(400, 375, 150, 50);
        this.add(bioLabel);
        bioTextArea = new JTextArea();
        bioTextArea.setLineWrap(true);
        bioTextArea.setBounds(550, 340, 150, 120);
        bioTextArea.setToolTipText("Enter bio");
        Border border = BorderFactory.createLineBorder(Color.BLACK);
        bioTextArea.setBorder(BorderFactory.createCompoundBorder(border, BorderFactory.createEmptyBorder(10, 10, 10, 10)));
        this.add(bioTextArea);

        signUpButton = new SignUpButton(Color.BLACK, Color.WHITE, Color.WHITE,
                Color.BLACK, new Font("Arial", Font.PLAIN, 20), this);
        signUpButton.setBounds(250, 530, 300 ,50);
        this.add(signUpButton);

        this.resultLabel = new JLabel("|||||||||||||||||||");
        this.resultLabel.setBackground(Color.GREEN);
        resultLabel.setHorizontalAlignment(SwingConstants.CENTER);
        resultLabel.setFont(new Font("Arial", Font.PLAIN, 20));
        this.resultLabel.setBounds(250, 470, 300, 50);
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

        this.revalidate();
        this.repaint();
    }

    private void setResultLabelText(String text){
        this.remove(resultLabel);
        this.resultLabel = new JLabel(text);
        resultLabel.setHorizontalAlignment(SwingConstants.CENTER);
        resultLabel.setFont(new Font("Arial", Font.PLAIN, 20));
        this.resultLabel.setBounds(250, 470, 300, 50);
        this.add(resultLabel);
        this.repaint();
    }

    public WellComePage getWellComePage() {
        return wellComePage;
    }

    public JTextField getNameTextField() {
        return nameTextField;
    }

    public JTextField getLastNameTextField() {
        return LastNameTextField;
    }

    public JTextField getUserNameTextField() {
        return userNameTextField;
    }

    public JTextField getPasswordTextField() {
        return passwordTextField;
    }

    public JTextField getReTypedPasswordTextField() {
        return reTypedPasswordTextField;
    }

    public JSpinner getYearSpinner() {
        return yearSpinner;
    }

    public JTextField getEmailTextField() {
        return emailTextField;
    }

    public JTextField getPhoneNumberTextField() {
        return phoneNumberTextField;
    }

    public JTextArea getBioTextArea() {
        return bioTextArea;
    }

    public JSpinner getMonthSpinner() {
        return monthSpinner;
    }

    public JSpinner getDaySpinner() {
        return daySpinner;
    }

    public SignUpButton getSignUpButton() {
        return signUpButton;
    }

    public JLabel getResultLabel() {
        return resultLabel;
    }


    @Override
    public <E extends ClientEvent> void sendClientEvent(E clientEvent, boolean hasAnswer) throws Exception {
        wellComePage.sendClientEvent(clientEvent, hasAnswer);
    }

    @Override
    public <E extends ServerEvent> void receiveServerEvent(E logicalEvent) throws IOException {
        interpretServerAnswer(logicalEvent);
    }

    public <E extends ServerEvent> void interpretServerAnswer(E serverEvent) {
        switch (((SignUpResultEvent) serverEvent).getSignUpResult()){
            case NAME_IS_EMPTY:
                JOptionPane.showMessageDialog(null, "NAME_IS_EMPTY");
                setResultLabelText("NAME_IS_EMPTY");
                break;
            case INVALID_NAME:
                JOptionPane.showMessageDialog(null, "INVALID_NAME");
                setResultLabelText("INVALID_NAME");
                break;
            case LASTNAME_IS_EMPTY:
                JOptionPane.showMessageDialog(null, "LASTNAME_IS_EMPTY");
                setResultLabelText("LASTNAME_IS_EMPTY");
                break;
            case INVALID_LASTNAME:
                JOptionPane.showMessageDialog(null, "INVALID_LASTNAME");
                setResultLabelText("INVALID_LASTNAME");
                break;
            case USERNAME_IS_EMPTY:
                JOptionPane.showMessageDialog(null, "USERNAME_IS_EMPTY");
                setResultLabelText("USERNAME_IS_EMPTY");
                break;
            case INVALID_USERNAME:
                JOptionPane.showMessageDialog(null, "INVALID_USERNAME");
                setResultLabelText("INVALID_USERNAME");
                break;
            case USERNAME_IS_TAKEN:
                JOptionPane.showMessageDialog(null, "USERNAME_IS_TAKEN");
                setResultLabelText("USERNAME_IS_TAKEN");
                break;
            case PASSWORD_IS_EMPTY:
                JOptionPane.showMessageDialog(null, "PASSWORD_IS_EMPTY");
                setResultLabelText("PASSWORD_IS_EMPTY");
                break;
            case INVALID_PASSWORD:
                JOptionPane.showMessageDialog(null, "INVALID_PASSWORD");
                setResultLabelText("INVALID_PASSWORD");
                break;
            case RETYPED_PASSWORD_IS_EMPTY:
                JOptionPane.showMessageDialog(null, "RETYPED_PASSWORD_IS_EMPTY");
                setResultLabelText("RETYPED_PASSWORD_IS_EMPTY");
                break;
            case PASSWORDS_ARE_NOT_SAME:
                JOptionPane.showMessageDialog(null, "PASSWORDS_ARE_NOT_SAME");
                setResultLabelText("PASSWORDS_ARE_NOT_SAME");
                break;
            case EMAIL_IS_EMPTY:
                JOptionPane.showMessageDialog(null, "EMAIL_IS_EMPTY");
                setResultLabelText("EMAIL_IS_EMPTY");
                break;
            case INVALID_EMAIL:
                JOptionPane.showMessageDialog(null, "INVALID_EMAIL");
                setResultLabelText("INVALID_EMAIL");
                break;
            case EMAIL_IS_TAKEN:
                JOptionPane.showMessageDialog(null, "EMAIL_IS_TAKEN");
                setResultLabelText("EMAIL_IS_TAKEN");
                break;
            case PHONENUMBER_IS_EMPTY:
                JOptionPane.showMessageDialog(null, "PHONENUMBER_IS_EMPTY");
                setResultLabelText("PHONENUMBER_IS_EMPTY");
                break;
            case INVALID_PHONENUMBER:
                JOptionPane.showMessageDialog(null, "INVALID_PHONENUMBER");
                setResultLabelText("INVALID_PHONENUMBER");
                break;
            case BIRTHDATE_IN_EMPTY:
                JOptionPane.showMessageDialog(null, "BIRTHDATE_IN_EMPTY");
                setResultLabelText("BIRTHDATE_IN_EMPTY");
                break;
            case NO_PROBLEM:
                wellComePage.getNlUserGraphicalAgent().getGraphAgent().setlUserGraphicalAgent(((SignUpResultEvent) serverEvent).getId());
                break;
        }
    }
}
