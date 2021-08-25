package events.clientevents.graphicalevents.notloggeduser;

public class SignUpPageEvent extends NLUserGraphicalEvent {
    private final String name;
    private final String lastName;
    private final String userName;
    private final String password;
    private final String reTypedPassword;
    private final int birthDateYear;
    private final int birthDateMonth;
    private final int birthDateDay;
    private final String email;
    private final String phoneNumber;
    private final String bio;

    public SignUpPageEvent(String name, String lastName, String userName, String password, String reTypedPassword, int birthDate, int birthDateMonth, int birthDateDay, String email, String phoneNumber, String bio) {
        this.name = name;
        this.lastName = lastName;
        this.userName = userName;
        this.password = password;
        this.reTypedPassword = reTypedPassword;
        this.birthDateYear = birthDate;
        this.birthDateMonth = birthDateMonth;
        this.birthDateDay = birthDateDay;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.bio = bio;
    }

    public String getName() {
        return name;
    }

    public String getLastName() {
        return lastName;
    }

    public String getUserName() {
        return userName;
    }

    public String getPassword() {
        return password;
    }

    public String getReTypedPassword() {
        return reTypedPassword;
    }

    public int getBirthDateYear() {
        return birthDateYear;
    }

    public String getEmail() {
        return email;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public String getBio() {
        return bio;
    }

    public int getBirthDateMonth() {
        return birthDateMonth;
    }

    public int getBirthDateDay() {
        return birthDateDay;
    }
}
