package models.messaging;

import java.io.Serializable;

public enum ChatMessageStatus implements Serializable {
    HAVE_NOT_RECORDED_IN_SERVER,
    RECEIVER_HAVE_NOT_CONNECTED_TO_SERVER_YET,
    RECEIVER_HAVE_NOT_SEEN_THE_MESSAGE,
    RECEIVER_HAVE_RECEIVED_THE_MESSAGE
}
