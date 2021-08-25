package view.loggeduser.mainmenupage.personalpage.editprofilepage;

import config.Config;
import events.EventManager;
import events.serverevents.main.ServerEvent;
import view.loggeduser.LUserPanel;
import view.loggeduser.mainmenupage.personalpage.PersonalPage;
import view.loggeduser.mainmenupage.personalpage.editprofilepage.buttons.*;

import java.awt.*;
import java.io.IOException;

public class EditProfilePage extends LUserPanel implements EventManager {
    private final EditProfileImageButton editProfileImageButton;
    private final EditNameButton editNameButton;
    private final EditLastNameButton editLastNameButton;
    private final EditUserNameButton editUserNameButton;
    private final EditPasswordButton editPasswordButton;
    private final EditBirthDateButton editBirthDateButton;
    private final EditEmailButton editEmailButton;
    private final EditPhoneNumberButton editPhoneNumberButton;
    private final EditBioButton editBioButton;
    public EditProfilePage(PersonalPage personalPage) {
        super(personalPage, personalPage.getMainPanel(), personalPage.getOwnerId());
        this.setLayout(null);

        Config config = Config.getConfig("Frame");
        this.setBackground(new Color(
                Integer.parseInt(config.getProperty("colorR")),
                Integer.parseInt(config.getProperty("colorG")),
                Integer.parseInt(config.getProperty("colorB"))
        ));

        this.setBounds(
                Integer.parseInt(config.getProperty("x")),
                Integer.parseInt(config.getProperty("y")),
                Integer.parseInt(config.getProperty("width")),
                Integer.parseInt(config.getProperty("height"))
        );

        editProfileImageButton = new EditProfileImageButton(Config.getConfig("MenuButtonsPro"), this);
        editProfileImageButton.setText("Edit Profile Image");
        editProfileImageButton.setBounds(250, 50, 300, 50);
        this.add(editProfileImageButton);

        editNameButton = new EditNameButton(Config.getConfig("MenuButtonsPro"), this);
        editNameButton.setText("Edit Name");
        editNameButton.setBounds(250, 120, 300, 50);
        this.add(editNameButton);

        editLastNameButton = new EditLastNameButton(Config.getConfig("MenuButtonsPro"), this);
        editLastNameButton.setText("Edit LastName");
        editLastNameButton.setBounds(250, 190, 300, 50);
        this.add(editLastNameButton);

        editUserNameButton = new EditUserNameButton(Config.getConfig("MenuButtonsPro"), this);
        editUserNameButton.setText("Edit Username");
        editUserNameButton.setBounds(250, 260, 300, 50);
        this.add(editUserNameButton);

        editPasswordButton = new EditPasswordButton(Config.getConfig("MenuButtonsPro"), this);
        editPasswordButton.setText("Edit Password");
        editPasswordButton.setBounds(250, 330, 300, 50);
        this.add(editPasswordButton);

        editBirthDateButton = new EditBirthDateButton(Config.getConfig("MenuButtonsPro"), this);
        editBirthDateButton.setText("Edit Birthdate");
        editBirthDateButton.setBounds(250, 400, 300, 50);
        this.add(editBirthDateButton);

        editEmailButton = new EditEmailButton(Config.getConfig("MenuButtonsPro"), this);
        editEmailButton.setText("Edit Email");
        editEmailButton.setBounds(250, 470, 300, 50);
        this.add(editEmailButton);

        editPhoneNumberButton = new EditPhoneNumberButton(Config.getConfig("MenuButtonsPro"), this);
        editPhoneNumberButton.setText("Edit PhoneNumber");
        editPhoneNumberButton.setBounds(250, 540, 300, 50);
        this.add(editPhoneNumberButton);

        editBioButton = new EditBioButton(Config.getConfig("MenuButtonsPro"), this);
        editBioButton.setText("EditBioButton");
        editBioButton.setBounds(250, 610, 300, 50);
        this.add(editBioButton);
    }

    @Override
    public <E extends ServerEvent> void receiveServerEvent(E logicalEvent) throws IOException {

    }
}
