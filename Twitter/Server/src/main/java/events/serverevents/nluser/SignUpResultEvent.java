package events.serverevents.nluser;

public class SignUpResultEvent extends NLUserServerEvent {
    private int id;
    private final SignUpResults signUpResult;
    public SignUpResultEvent(SignUpResults signUpResult, int id) {
        this.id = id;
        this.signUpResult = signUpResult;
    }

    public SignUpResultEvent(SignUpResults signUpResult) {
        this.signUpResult = signUpResult;
    }

    public SignUpResults getSignUpResult() {
        return signUpResult;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
