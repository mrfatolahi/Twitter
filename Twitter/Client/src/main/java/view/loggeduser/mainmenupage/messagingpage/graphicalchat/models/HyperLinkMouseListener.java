package view.loggeduser.mainmenupage.messagingpage.graphicalchat.models;

import events.clientevents.graphicalevents.loggeduser.mainmenupage.messagingpage.AddNewMemberToGroupWithHyperLink;
import events.clientevents.graphicalevents.loggeduser.mainmenupage.messagingpage.hyperlinkevents.OpenBotChatHyperLinkEvent;
import events.clientevents.graphicalevents.loggeduser.mainmenupage.messagingpage.hyperlinkevents.OpenChatHyperLinkEvent;
import events.clientevents.graphicalevents.loggeduser.mainmenupage.messagingpage.hyperlinkevents.OpenGroupHyperLinkEvent;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class HyperLinkMouseListener implements MouseListener {
    private final GraphicalChatMessage graphicalChatMessage;

    public HyperLinkMouseListener(GraphicalChatMessage graphicalChatMessage) {
        this.graphicalChatMessage = graphicalChatMessage;
    }

    /**
     * Invoked when the mouse button has been clicked (pressed
     * and released) on a component.
     *
     * @param e
     */
    @Override
    public void mouseClicked(MouseEvent e) {
        switch (graphicalChatMessage.getChatMassage().getHyperlink().getType()){
            case GROUP_LINK:
                try {
                    graphicalChatMessage.sendClientEvent(new OpenGroupHyperLinkEvent(graphicalChatMessage.getChatMassage().getHyperlink()), false);
                } catch (Exception exception) {
                    exception.printStackTrace();
                }
                break;
            case PERSON_CHAT_LINK:
                try {
                    graphicalChatMessage.sendClientEvent(new OpenChatHyperLinkEvent(graphicalChatMessage.getChatMassage().getHyperlink()), false);
                } catch (Exception exception) {
                    exception.printStackTrace();
                }
                break;
            case BOT_CHAT_LINK:
                try {
                    graphicalChatMessage.sendClientEvent(new OpenBotChatHyperLinkEvent(graphicalChatMessage.getChatMassage().getHyperlink()), false);
                } catch (Exception exception) {
                    exception.printStackTrace();
                }
                break;
            case GROUP_INVITE_LINK:
                try {
                    graphicalChatMessage.sendClientEvent(new AddNewMemberToGroupWithHyperLink(graphicalChatMessage.getChatMassage().getUserId(), graphicalChatMessage.getChatMassage().getHyperlink().getToken()), false);
                } catch (Exception exception) {
                    exception.printStackTrace();
                }
                break;
        }
    }

    /**
     * Invoked when a mouse button has been pressed on a component.
     *
     * @param e
     */
    @Override
    public void mousePressed(MouseEvent e) {

    }

    /**
     * Invoked when a mouse button has been released on a component.
     *
     * @param e
     */
    @Override
    public void mouseReleased(MouseEvent e) {

    }

    /**
     * Invoked when the mouse enters a component.
     *
     * @param e
     */
    @Override
    public void mouseEntered(MouseEvent e) {

    }

    /**
     * Invoked when the mouse exits a component.
     *
     * @param e
     */
    @Override
    public void mouseExited(MouseEvent e) {

    }
}
