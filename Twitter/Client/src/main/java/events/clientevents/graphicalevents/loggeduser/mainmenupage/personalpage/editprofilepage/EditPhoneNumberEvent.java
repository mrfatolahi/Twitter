package events.clientevents.graphicalevents.loggeduser.mainmenupage.personalpage.editprofilepage;

public class EditPhoneNumberEvent extends EditProfilePageEvent {
    private final String countryCode;
    private final String mainPart;

    public EditPhoneNumberEvent(String countryCode, String mainPart) {
        this.countryCode = countryCode;
        this.mainPart = mainPart;
    }

    public String getCountryCode() {
        return countryCode;
    }

    public String getMainPart() {
        return mainPart;
    }
}
