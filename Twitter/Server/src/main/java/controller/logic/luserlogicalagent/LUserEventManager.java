package controller.logic.luserlogicalagent;

import events.serverevents.interfaces.LogicEventManager;
import events.serverevents.main.ServerEvent;
import events.clientevents.main.ClientEvent;

import java.io.IOException;

public class LUserEventManager implements LogicEventManager {
    private final LUserLogicalAgent lUserLogicalAgent;

    public LUserEventManager(LUserLogicalAgent lUserLogicalAgent) {
        this.lUserLogicalAgent = lUserLogicalAgent;
    }

//    public  <S extends LUserGraphicalEvent> void interpret(S lUserGraphicalEvent) throws Exception {
//        try {
//            interpreteGraphicalTweetEvent((GraphicalTweetEvent) lUserGraphicalEvent);
//        } catch (ClassCastException | IOException e){
//            try {
//                interpreteGraphicalProfileEvent((GraphicalProfileEvent) lUserGraphicalEvent);
//            } catch (ClassCastException w){
//                try {
//                    interpretePersonalPageEvent((PersonalPageEvent) lUserGraphicalEvent);
//                } catch (ClassCastException | IOException t){
//                    try {
//                        interpreteTimeLinePageEvent((TimeLinePageEvent) lUserGraphicalEvent);
//                    } catch (ClassCastException c){
//                        try {
//                            interpreteExplorerPageEvent((ExplorerPageEvent) lUserGraphicalEvent);
//                        } catch (ClassCastException v){
//                            try {
//                                interpreteMessagingPageEvent((MessagingPageEvent) lUserGraphicalEvent);
//                            } catch (ClassCastException k){
//                                try {
//                                    interpreteSettingPageEvent((SettingPageEvent) lUserGraphicalEvent);
//                                } catch (ClassCastException r){
//                                    try {
//                                        interpreteGraphicalChatEvent(((GraphicalChatEvent) lUserGraphicalEvent));
//                                    } catch (ClassCastException q){
//                                        try {
//                                            interpreteGraphicalGroupEvent(((GraphicalGroupEvent) lUserGraphicalEvent));
//                                        } catch (ClassCastException p){
//                                            System.out.println();
//                                        }
//                                    }
//                                }
//                            }
//                        }
//                    }
//                }
//            }
//        }
//    }
//
//    private <G extends GraphicalTweetEvent> void interpreteGraphicalTweetEvent(G graphicalTweetEvent) throws IOException {
//        if (graphicalTweetEvent.getClass() == NewCommentEvent.class){
//            lUserLogicalAgent.commentTweet(((NewCommentEvent) graphicalTweetEvent).getTweet(), ((NewCommentEvent) graphicalTweetEvent).getText());
//        } else
//        if (graphicalTweetEvent.getClass() == NewLikeEvent.class){
//            lUserLogicalAgent.likeTweet(((NewLikeEvent) graphicalTweetEvent).getTweet());
//        } else
//        if (graphicalTweetEvent.getClass() == TweetForwardEvent.class){
//            lUserLogicalAgent.forwardTweet(((TweetForwardEvent) graphicalTweetEvent).getTweet(), ((TweetForwardEvent) graphicalTweetEvent).getReceiverUsername());
//        } else
//        if (graphicalTweetEvent.getClass() == RetweetEvent.class){
//            lUserLogicalAgent.retweetTweet(((RetweetEvent) graphicalTweetEvent).getTweet(), ((RetweetEvent) graphicalTweetEvent).getText(), ((RetweetEvent) graphicalTweetEvent).getTags(), ((RetweetEvent) graphicalTweetEvent).getImagePath());
//        } else
//        if (graphicalTweetEvent.getClass() == BlockTweetOwnerEvent.class){
//            lUserLogicalAgent.blockThis(((BlockTweetOwnerEvent) graphicalTweetEvent).getTweet().getUserId());
//        } else
//        if (graphicalTweetEvent.getClass() == MuteTweetOwnerEvent.class){
//            lUserLogicalAgent.muteThis(((MuteTweetOwnerEvent) graphicalTweetEvent).getTweet().getUserId());
//        } else
//        if (graphicalTweetEvent.getClass() == ReportTweetEvent.class){
//            lUserLogicalAgent.reportTweet(((ReportTweetEvent) graphicalTweetEvent).getTweet());
//        } else
//        if (graphicalTweetEvent.getClass() == AddTweetToSavedMessages.class){
//            lUserLogicalAgent.addTweetToSomeBodySavedMessages(((AddTweetToSavedMessages) graphicalTweetEvent).getViewerId(), ((AddTweetToSavedMessages) graphicalTweetEvent).getTweet());
//        }
//    }
//
//    private <A extends GraphicalProfileEvent> void interpreteGraphicalProfileEvent(A graphicalProfileEvent) throws IOException, InterruptedException {
//        if (graphicalProfileEvent.getClass() == FollowSomeBodyEvent.class){
//            lUserLogicalAgent.followThis(UserLoader.loadUser((((FollowSomeBodyEvent) graphicalProfileEvent).getUserToFollowId())));
//        } else
//        if (graphicalProfileEvent.getClass() == UnFollowSomeBodyEvent.class){
//            lUserLogicalAgent.unfollowThis(UserLoader.loadUser((((UnFollowSomeBodyEvent) graphicalProfileEvent).getUserToUnFollowId())));
//        } else
//        if (graphicalProfileEvent.getClass() == BlockSomeBodyEvent.class){
//            lUserLogicalAgent.blockThis(UserLoader.loadUser((((BlockSomeBodyEvent) graphicalProfileEvent).getUserToBlockId())));
//        } else
//        if (graphicalProfileEvent.getClass() == UnBlockSomeBodyEvent.class){
//            lUserLogicalAgent.unblockThis(UserLoader.loadUser((((UnBlockSomeBodyEvent) graphicalProfileEvent).getUserToUnBlockId())));
//        }
//    }
//
//    private <U extends PersonalPageEvent> void interpretePersonalPageEvent(U personalPageEvent) throws Exception {
//        try {
//            interpreteEditProfilePageEvent((EditProfilePageEvent) personalPageEvent);
//        }catch (ClassCastException e){
//            if (personalPageEvent.getClass() == NewTweetEvent.class){
//                lUserLogicalAgent.addTweet(new Tweet(((NewTweetEvent) personalPageEvent).getText(), lUserLogicalAgent.getUser().getId(), ((NewTweetEvent) personalPageEvent).getTags(), ((NewTweetEvent) personalPageEvent).getImagePath(), lUserLogicalAgent.getUser().getUsername().getText(), lUserLogicalAgent.getUser().getProfileImagePath()));
//            } else
//            if (personalPageEvent.getClass() == RequestFollowersEvent.class){
//                sendLogicalEvent(new FollowersInfoEvent(User.generateUserInfoEventFromUserList(lUserLogicalAgent.getFollowers())));
//            } else
//            if (personalPageEvent.getClass() == RequestFollowingsEvent.class){
//                sendLogicalEvent(new FollowingsInfoEvent(User.generateUserInfoEventFromUserList(lUserLogicalAgent.getFollowings())));
//            } else
//            if (personalPageEvent.getClass() == RequestBlockedUsersEvent.class){
//                sendLogicalEvent(new BlockedUsersInfoEvent(User.generateUserInfoEventFromUserList(lUserLogicalAgent.getBlockedUsers())));
//            } else
//            if (personalPageEvent.getClass() == RequestSentRequestsEvent.class){
//                sendLogicalEvent(new SendSentRequestsEvent(lUserLogicalAgent.getUser().getSentFollowRequests()));
//            } else
//            if (personalPageEvent.getClass() == RequestReceivedRequestsEvent.class){
//                sendLogicalEvent(new SendReceivedRequestsEvent(lUserLogicalAgent.getUser().getReceivedFollowRequests()));
//            } else
//            if (personalPageEvent.getClass() == AcceptRequestEvent.class){
//                lUserLogicalAgent.acceptFollowingRequest(((AcceptRequestEvent) personalPageEvent).getFollowRequest());
//            } else
//            if (personalPageEvent.getClass() == DeclineRequestWithNotifEvent.class){
//                lUserLogicalAgent.declineFollowRequestWithNotif(((DeclineRequestWithNotifEvent) personalPageEvent).getFollowRequest());
//            } else
//            if (personalPageEvent.getClass() == DeclineRequestWithOutNotifEvent.class){
//                lUserLogicalAgent.declineFollowRequestWithoutNotif(((DeclineRequestWithOutNotifEvent) personalPageEvent).getFollowRequest());
//            } else
//            if (personalPageEvent.getClass() == RequestUserTweetsEvent.class){
//                sendLogicalEvent(new SendUserTweetsEvent(lUserLogicalAgent.getUser().getTweets()));
//            } else
//            if (personalPageEvent.getClass() == RequestUserInfoEvent.class){
//                sendLogicalEvent(User.generateUserInfoForPP(lUserLogicalAgent.getUser()));
//            }
//        }
//    }
//
//    private <N extends TimeLinePageEvent> void interpreteTimeLinePageEvent(N timeLinePageEvent) throws IOException {
//        sendLogicalEvent(new SendTimeLineTweetsEvent(lUserLogicalAgent.getTimelineTweets()));
//    }
//
//    private <K extends ExplorerPageEvent> void interpreteExplorerPageEvent(K explorerPageEvent) throws IOException {
//        if (explorerPageEvent.getClass() == RequestHotTweetsEvent.class){
//            this.sendLogicalEvent(new SendHotTweetsServerEvent(lUserLogicalAgent.getHotTweets_tweets()));
//        } else
//        if (explorerPageEvent.getClass() == ERequestUserInfoEvent.class){
//            try {
//                sendLogicalEvent(User.generateUserInfoEvent(UserLoader.loadUser(((ERequestUserInfoEvent) explorerPageEvent).getUsername())));
//            } catch (Exception e){
//                sendLogicalEvent(new UserInfoServerEvent(-1, -1, null, -1, null, null, null, null, null, false));
//            }
//        }
//    }
//
//    private <J extends MessagingPageEvent> void interpreteMessagingPageEvent(J messagingPageEvent) throws IOException {
//        if (messagingPageEvent.getClass() == RequestMakingNewChatEvent.class){
//            int userToChatId = UserLoader.loadIdWithUsername(((RequestMakingNewChatEvent) messagingPageEvent).getUserToChatUsername());
//            this.sendLogicalEvent(new DeclareNewChatMadeEvent(lUserLogicalAgent.startChatWith(userToChatId)));
//        } else
//        if (messagingPageEvent.getClass() == RequestUserChatsEvent.class){
//            this.sendLogicalEvent(new SendUserChatsEvent(lUserLogicalAgent.getUser().getChats()));
//        } else
//        if (messagingPageEvent.getClass() == CreateNewGroupEvent.class){
//            lUserLogicalAgent.createNewGroup(((CreateNewGroupEvent) messagingPageEvent).getGroupName(), ((CreateNewGroupEvent) messagingPageEvent).getMembersUsernames());
//        } else
//        if (messagingPageEvent.getClass() == RequestUserGroupsEvent.class){
//            sendLogicalEvent(new SendUserGroupsEvent(lUserLogicalAgent.getUser().getGroups()));
//        } else
//        if (messagingPageEvent.getClass() == SpreadEvent.class){
//            lUserLogicalAgent.spread(((SpreadEvent) messagingPageEvent).getText(), ((SpreadEvent) messagingPageEvent).getMembersUsernames());
//        } else
//        if (messagingPageEvent.getClass() == RequestUserSavedMessagesEvent.class){
//            System.out.println(lUserLogicalAgent.getUser().getSavedmassages());
//            sendLogicalEvent(new SendUserSavedMessagesEvent(lUserLogicalAgent.getUser().getSavedmassages()));
//        }
//    }
//
//    private <B extends GraphicalGroupEvent> void interpreteGraphicalGroupEvent(B graphicalGroupEvent) throws IOException {
//        if (graphicalGroupEvent.getClass() == NewGroupMessageEvent.class){
//            for (Group group : lUserLogicalAgent.getUser().getGroups()){
//                if (group.getUsersIds() == ((NewGroupMessageEvent) graphicalGroupEvent).getReceiversId()){
//                    group.sendMassage(lUserLogicalAgent.getUser().getId(), ((NewGroupMessageEvent) graphicalGroupEvent).getText(), ((NewGroupMessageEvent) graphicalGroupEvent).getImagePath());
//                  Q:for (int memberId : ((NewGroupMessageEvent) graphicalGroupEvent).getReceiversId()){
//                        User member = UserLoader.loadUser(memberId);
//                        for (Group memberGroup : member.getGroups()){
//                            if (memberGroup.getUsersIds().containsAll(group.getUsersIds())){
//                                member.getGroups().remove(memberGroup);
//                                member.getGroups().add(group);
//                                UserSaver.saveUser(member);
//                                continue  Q;
//                            }
//                        }
//                    }
//                break;
//                }
//            }
//        } else
//        if (graphicalGroupEvent.getClass() == AddMemberToGroupEvent.class){
//            for (Group group : lUserLogicalAgent.getUser().getGroups()){
//                if (group.getUsersIds() == ((AddMemberToGroupEvent) graphicalGroupEvent).getGroup().getUsersIds()){
//                    ArrayList<String> d = new ArrayList<>(((AddMemberToGroupEvent) graphicalGroupEvent).getNewMembersUsernames());
//                    ArrayList<Integer> f = new ArrayList<>(((AddMemberToGroupEvent) graphicalGroupEvent).getGroup().getUsersIds());
//                    for (String newMemberUsername : d){
//                        group.addMember(UserLoader.loadUser(String.valueOf(newMemberUsername)).getId(), newMemberUsername);
//                    }
//                    Q:for (int memberId : group.getUsersIds()){
//                        User member = UserLoader.loadUser(memberId);
//                        for (Group memberGroup : member.getGroups()){
//                            if (memberGroup.getUsersIds().containsAll(f)){
//                                member.getGroups().remove(memberGroup);
//                                member.getGroups().add(group);
//                                UserSaver.saveUser(member);
//                                continue  Q;
//                            }
//                        }
//
//                        member.getGroups().add(group);
//                        UserSaver.saveUser(member);
//
//                    }
//                    break;
//                }
//            }
//        }
//    }
//
//    private <H extends SettingPageEvent> void interpreteSettingPageEvent(H settingPageEvent) throws IOException {
//        if (settingPageEvent.getClass() == ActiveAccountEvent.class){
//            lUserLogicalAgent.activeAccount();
//        } else
//        if (settingPageEvent.getClass() == DeActiveAccountEvent.class){
//            lUserLogicalAgent.disableAccount();
//        } else
//        if (settingPageEvent.getClass() == MakeAccountPublicEvent.class){
//            lUserLogicalAgent.makeAccountPublic();
//        } else
//        if (settingPageEvent.getClass() == MakeAccountPrivateEvent.class){
//            lUserLogicalAgent.makeAccountPrivate();
//        } else
//        if (settingPageEvent.getClass() == DeleteAccountEvent.class){
//            lUserLogicalAgent.getUser().setDeleted(true);
//            UserSaver.saveUser(lUserLogicalAgent.getUser());
//            System.exit(0);
//        }
//    }
//
//    private <O extends GraphicalChatEvent> void interpreteGraphicalChatEvent(O graphicalChatEvent) throws IOException {
//        if (graphicalChatEvent.getClass() == NewChatMassageEvent.class){
//            for (Chat chat : lUserLogicalAgent.getUser().getChats()){
//                if (chat.getUser1Id() == ((NewChatMassageEvent) graphicalChatEvent).getReceiverId() || chat.getUser2Id() == ((NewChatMassageEvent) graphicalChatEvent).getReceiverId()){
//                    chat.sendMassage(lUserLogicalAgent.getUser().getId(), ((NewChatMassageEvent) graphicalChatEvent).getText(), ((NewChatMassageEvent) graphicalChatEvent).getImagePath());
//                    User receiver1 = UserLoader.loadUser(((NewChatMassageEvent) graphicalChatEvent).getReceiverId());
//                    for(int i = 0 ; i < receiver1.getChats().size() ; i++){
//                        if(receiver1.getChats().get(i).getUser1Id() == chat.getUser1Id() && receiver1.getChats().get(i).getUser2Id() == chat.getUser2Id()){
//                            ArrayList<Chat> ch = new ArrayList<>(receiver1.getChats());
//                            ch.remove(i);
//                            ch.add(chat);
//                            receiver1.setChats(ch);
//                            break;
//                        }
//                    }
//                    UserSaver.saveUser(receiver1); UserSaver.saveUser(lUserLogicalAgent.getUser());
//
//                }
//            }
//        }
//    }
//
//    private <F extends EditProfilePageEvent> void interpreteEditProfilePageEvent(F editProfilePageEvent) throws Exception {
//        if (editProfilePageEvent.getClass() == EditNameEvent.class){
//            lUserLogicalAgent.getUser().setName(new Name(lUserLogicalAgent.getUser(), ((EditNameEvent) editProfilePageEvent).getNewName()));
//        } else
//        if (editProfilePageEvent.getClass() == EditLastNameEvent.class){
//            lUserLogicalAgent.getUser().setLastname(new LastName(lUserLogicalAgent.getUser(), ((EditLastNameEvent) editProfilePageEvent).getNewLastName()));
//        } else
//        if (editProfilePageEvent.getClass() == EditUserNameEvent.class){
//            lUserLogicalAgent.getUser().setUsername(new UserName(lUserLogicalAgent.getUser(), ((EditUserNameEvent) editProfilePageEvent).getNewUserName()));
//        } else
//        if (editProfilePageEvent.getClass() == EditPasswordEvent.class){
//            lUserLogicalAgent.getUser().setPassword(new Password(lUserLogicalAgent.getUser(), ((EditPasswordEvent) editProfilePageEvent).getNewPassword()));
//        } else
//        if (editProfilePageEvent.getClass() == EditBirthDateEvent.class){
//            lUserLogicalAgent.getUser().setBirthdate(((EditBirthDateEvent) editProfilePageEvent).getNewBirthDate());
//        } else
//        if (editProfilePageEvent.getClass() == EditEmailEvent.class){
//            lUserLogicalAgent.getUser().setEmail(new Email(lUserLogicalAgent.getUser(), ((EditEmailEvent) editProfilePageEvent).getNewEmail()));
//        } else
//        if (editProfilePageEvent.getClass() == EditPhoneNumberEvent.class){
//            lUserLogicalAgent.getUser().setPhonenumber(new Phonenumber(((EditPhoneNumberEvent) editProfilePageEvent).getMainPart(), ((EditPhoneNumberEvent) editProfilePageEvent).getCountryCode(), lUserLogicalAgent.getUser()));
//        } else
//        if (editProfilePageEvent.getClass() == EditBioEvent.class){
//            lUserLogicalAgent.getUser().setBio(new Bio(lUserLogicalAgent.getUser(), ((EditBioEvent) editProfilePageEvent).getNewBio()));
//        } else
//        if (editProfilePageEvent.getClass() == EditProfileImageEvent.class){
//            lUserLogicalAgent.getUser().setProfileImagePath(((EditProfileImageEvent) editProfilePageEvent).getImagePath());
//        }
//        UserSaver.saveUser(lUserLogicalAgent.getUser());
//    }


    @Override
    public <E extends ServerEvent> void sendLogicalEvent(E logicalEvent) throws IOException {
//        lUserLogicalAgent.sendLogicalEvent(logicalEvent);
    }

    @Override
    public <E extends ClientEvent> void receiveGraphicalEvent(E graphicalEvent) throws Exception {
//        interpret((LUserGraphicalEvent) graphicalEvent);
    }
}
