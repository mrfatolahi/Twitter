package controller.logic.nluserlogicalagent;

import XLogger.MLogger;
import events.serverevents.interfaces.LogicEventManager;
import controller.logic.LogicalAgent;
import events.serverevents.main.ServerEvent;
import events.serverevents.nluser.LogInResultEvent;
import events.serverevents.nluser.LogInResults;
import events.serverevents.nluser.SignUpResultEvent;
import events.clientevents.graphicalevents.notloggeduser.LogInPageEvent;
import events.clientevents.graphicalevents.notloggeduser.SignUpPageEvent;
import events.clientevents.main.ClientEvent;
import events.serverevents.nluser.SignUpResults;
import models.time.MDate;
import models.user.User;
import models.user.information.*;
import saversandloaders.loaders.UserLoader;
import saversandloaders.savers.UserSaver;

import java.io.FileNotFoundException;
import java.io.IOException;

public class NLUserLogicalAgent implements LogicEventManager {
    private final LogicalAgent logicalAgent;
    private int loadedUserId;

    public NLUserLogicalAgent(LogicalAgent logicalAgent) {
        this.logicalAgent = logicalAgent;
    }

    public LogInResults logIn(LogInPageEvent logInGraphEvent) throws IOException {

        MLogger.log("INFO: NLUserLogicalAgent.logIn");
        String username = logInGraphEvent.getUsername();
        String password = logInGraphEvent.getPassword();

        int userId = -1;

        if (username.length() == 0){
            return LogInResults.USERNAME_IS_EMPTY;
        }

        if (password.length() == 0){
            return LogInResults.PASSWORD_IS_EMPTY;
        }

        try {
            userId = UserLoader.loadIdWithUsername(username);
        }catch (FileNotFoundException fileNotFoundException){
            return LogInResults.USERNAME_NOT_FOUND;
        }

        if (UserLoader.loadUser(userId).isDeleted()){
            return LogInResults.USERNAME_NOT_FOUND;
        }

        /*
        Disabled account have not handeled
         */

        if(!password.equals(UserLoader.loadUser(userId).getPassword().getText())){
            return LogInResults.WRONG_PASSWORD;
        }

        if(UserLoader.loadUser(userId).isDeleted()){
            return LogInResults.DELETED_ACCOUNT;
        }

        logicalAgent.setlUserLogicalAgent(UserLoader.loadUser(userId), true);
        loadedUserId = userId;
        logicalAgent.getClientHandler().setId(userId);
        return LogInResults.NO_PROBLEM;
    }

    public SignUpResults checkSignUpInformation(SignUpPageEvent signUpPageEvent){
        if (signUpPageEvent.getName().length() == 0){
            return SignUpResults.NAME_IS_EMPTY;
        }
        if (signUpPageEvent.getLastName().length() == 0){
            return SignUpResults.LASTNAME_IS_EMPTY;
        }
        if (signUpPageEvent.getUserName().length() == 0){
            return SignUpResults.USERNAME_IS_EMPTY;
        }
        if (signUpPageEvent.getPassword().length() == 0){
            return SignUpResults.PASSWORD_IS_EMPTY;
        }
        if (signUpPageEvent.getReTypedPassword().length() == 0){
            return SignUpResults.RETYPED_PASSWORD_IS_EMPTY;
        }
        if (!signUpPageEvent.getReTypedPassword().equals(signUpPageEvent.getPassword())){
            return SignUpResults.PASSWORDS_ARE_NOT_SAME;
        }
        if (signUpPageEvent.getBirthDateYear() == 0){
            return SignUpResults.BIRTHDATE_IN_EMPTY;
        }
        if (signUpPageEvent.getEmail().length() == 0){
            return SignUpResults.EMAIL_IS_EMPTY;
        }
        if (signUpPageEvent.getPhoneNumber().length() == 0){
            return SignUpResults.PHONENUMBER_IS_EMPTY;
        }

        return SignUpResults.NO_PROBLEM;
    }

    public SignUpResults signUp(SignUpPageEvent signUpPageEvent) throws Exception {

        MLogger.log("INFO: NLUserLogicalAgent.signUp");

        if (checkSignUpInformation(signUpPageEvent) != SignUpResults.NO_PROBLEM){
            return checkSignUpInformation(signUpPageEvent);
        }

        User user = new User(
                null, null, null, null,
                null, null, null, null
        );


        user.setName(new Name(user, signUpPageEvent.getName()));
        user.setLastname(new LastName(user, signUpPageEvent.getLastName()));
        user.setUsername(new UserName(user, signUpPageEvent.getUserName()));
        user.setPassword(new Password(user, signUpPageEvent.getPassword()));
        user.setBirthdate(new MDate(signUpPageEvent.getBirthDateYear(), signUpPageEvent.getBirthDateMonth() ,signUpPageEvent.getBirthDateDay()));
        user.setEmail(new Email(user, signUpPageEvent.getEmail()));
        user.setPhonenumber(new Phonenumber("98", signUpPageEvent.getPhoneNumber(), user));
        user.setBio(new Bio(user, signUpPageEvent.getBio()));

        UserSaver.saveUser(user);

        logicalAgent.setlUserLogicalAgent(user, true);

        loadedUserId = user.getId();
        logicalAgent.getClientHandler().setId(user.getId());
        return SignUpResults.NO_PROBLEM;
    }

    public int getLoadedUserId() {
        return loadedUserId;
    }

    @Override
    public <E extends ServerEvent> void sendLogicalEvent(E logicalEvent) throws IOException {
        this.logicalAgent.sendLogicalEvent(logicalEvent);
    }

    @Override
    public <E extends ClientEvent> void receiveGraphicalEvent(E graphicalEvent) throws Exception {
        MLogger.log("Event: "+graphicalEvent.getClass().toString());
        if (graphicalEvent.getClass() == LogInPageEvent.class){
            LogInResultEvent logInResultEvent = new LogInResultEvent(this.logIn((LogInPageEvent) graphicalEvent));
            if (logInResultEvent.getLogInResult() == LogInResults.NO_PROBLEM){
                logInResultEvent.setId(loadedUserId);
            }
            this.sendLogicalEvent(logInResultEvent);
        }else if (graphicalEvent.getClass() == SignUpPageEvent.class){
            SignUpResultEvent signUpResultEvent = new SignUpResultEvent(this.signUp((SignUpPageEvent) graphicalEvent));
            if (signUpResultEvent.getSignUpResult() == SignUpResults.NO_PROBLEM){
                signUpResultEvent.setId(loadedUserId);
            }
            this.sendLogicalEvent(signUpResultEvent);
        }
    }
}