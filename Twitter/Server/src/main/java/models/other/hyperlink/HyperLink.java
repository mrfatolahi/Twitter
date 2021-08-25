package models.other.hyperlink;

import models.tweet.Tweet;

import java.io.Serializable;

public class HyperLink  implements Serializable {
    private static final long serialVersionUID = 1234567L;

    private String text;
    private String token;
    private HyperLinkType type;

    public HyperLink() {
    }

    public HyperLink(String text, String token, HyperLinkType type) {
        this.text = text;
        this.token = token;
        this.type = type;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public HyperLinkType getType() {
        return type;
    }

    public void setType(HyperLinkType type) {
        this.type = type;
    }
}
