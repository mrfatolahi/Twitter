package controller.logic.luserlogicalagent.interpreters;

import events.clientevents.graphicalevents.loggeduser.mainmenupage.personalpage.NewTweetEvent;
import events.clientevents.graphicalevents.loggeduser.mainmenupage.personalpage.PersonalPageEvent;
import events.clientevents.graphicalevents.loggeduser.mainmenupage.personalpage.RequestUserInfoEvent;
import events.clientevents.graphicalevents.loggeduser.mainmenupage.personalpage.RequestUserTweetsEvent;
import events.clientevents.graphicalevents.loggeduser.mainmenupage.personalpage.editprofilepage.EditProfilePageEvent;
import events.clientevents.graphicalevents.loggeduser.mainmenupage.personalpage.listspage.RequestBlockedUsersEvent;
import events.clientevents.graphicalevents.loggeduser.mainmenupage.personalpage.listspage.RequestFollowersEvent;
import events.clientevents.graphicalevents.loggeduser.mainmenupage.personalpage.listspage.RequestFollowingsEvent;
import events.clientevents.graphicalevents.loggeduser.mainmenupage.personalpage.notificationspage.requestspage.*;
import events.clientevents.main.ClientEvent;
import events.serverevents.interfaces.LogicEventManager;
import events.serverevents.luser.personalpage.listspage.BlockedUsersInfoEvent;
import events.serverevents.luser.personalpage.listspage.FollowersInfoEvent;
import events.serverevents.luser.personalpage.listspage.FollowingsInfoEvent;
import events.serverevents.luser.personalpage.notificationspage.SendReceivedRequestsEvent;
import events.serverevents.luser.personalpage.notificationspage.SendSentRequestsEvent;
import events.serverevents.luser.personalpage.tweetspage.SendUserTweetsEvent;
import events.serverevents.main.ServerEvent;
import models.tweet.Tweet;
import models.user.User;

import java.io.IOException;

public class PersonalPageEventInterpreter implements LogicEventManager {
    private final MainInterPreter mainInterPreter;

    public PersonalPageEventInterpreter(MainInterPreter mainInterPreter) {
        this.mainInterPreter = mainInterPreter;
    }

    private <U extends PersonalPageEvent> void interpretePersonalPageEvent(U personalPageEvent) throws Exception {
        try {
            mainInterPreter.getEditProfilePageEventInterpreter().receiveGraphicalEvent((EditProfilePageEvent) personalPageEvent);
        }catch (ClassCastException e){
            if (personalPageEvent.getClass() == NewTweetEvent.class){
                mainInterPreter.getlUserLogicalAgent().addTweet(new Tweet(((NewTweetEvent) personalPageEvent).getText(), mainInterPreter.getlUserLogicalAgent().getUser().getId(), ((NewTweetEvent) personalPageEvent).getTags(), ((NewTweetEvent) personalPageEvent).getImagePath(), mainInterPreter.getlUserLogicalAgent().getUser().getUsername().getText(), mainInterPreter.getlUserLogicalAgent().getUser().getProfileImagePath()));
            } else
            if (personalPageEvent.getClass() == RequestFollowersEvent.class){
                sendLogicalEvent(new FollowersInfoEvent(User.generateUserInfoEventFromUserList(mainInterPreter.getlUserLogicalAgent().getFollowers())));
            } else
            if (personalPageEvent.getClass() == RequestFollowingsEvent.class){
                sendLogicalEvent(new FollowingsInfoEvent(User.generateUserInfoEventFromUserList(mainInterPreter.getlUserLogicalAgent().getFollowings())));
            } else
            if (personalPageEvent.getClass() == RequestBlockedUsersEvent.class){
                sendLogicalEvent(new BlockedUsersInfoEvent(User.generateUserInfoEventFromUserList(mainInterPreter.getlUserLogicalAgent().getBlockedUsers())));
            } else
            if (personalPageEvent.getClass() == RequestSentRequestsEvent.class){
                sendLogicalEvent(new SendSentRequestsEvent(mainInterPreter.getlUserLogicalAgent().getUser().getSentFollowRequests()));
            } else
            if (personalPageEvent.getClass() == RequestReceivedRequestsEvent.class){
                sendLogicalEvent(new SendReceivedRequestsEvent(mainInterPreter.getlUserLogicalAgent().getUser().getReceivedFollowRequests()));
            } else
            if (personalPageEvent.getClass() == AcceptRequestEvent.class){
                mainInterPreter.getlUserLogicalAgent().acceptFollowingRequest(((AcceptRequestEvent) personalPageEvent).getFollowRequest());
            } else
            if (personalPageEvent.getClass() == DeclineRequestWithNotifEvent.class){
                mainInterPreter.getlUserLogicalAgent().declineFollowRequestWithNotif(((DeclineRequestWithNotifEvent) personalPageEvent).getFollowRequest());
            } else
            if (personalPageEvent.getClass() == DeclineRequestWithOutNotifEvent.class){
                mainInterPreter.getlUserLogicalAgent().declineFollowRequestWithoutNotif(((DeclineRequestWithOutNotifEvent) personalPageEvent).getFollowRequest());
            } else
            if (personalPageEvent.getClass() == RequestUserTweetsEvent.class){
                sendLogicalEvent(new SendUserTweetsEvent(mainInterPreter.getlUserLogicalAgent().getUser().getTweets()));
            } else
            if (personalPageEvent.getClass() == RequestUserInfoEvent.class){
                sendLogicalEvent(User.generateUserInfoForPP(mainInterPreter.getlUserLogicalAgent().getUser()));
            }
        }
    }

    @Override
    public <E extends ServerEvent> void sendLogicalEvent(E logicalEvent) throws IOException {
        mainInterPreter.sendLogicalEvent(logicalEvent);
    }

    @Override
    public <E extends ClientEvent> void receiveGraphicalEvent(E clientEvent) throws Exception {
        interpretePersonalPageEvent((PersonalPageEvent) clientEvent);
    }
}
