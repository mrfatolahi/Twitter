package models.user.information;

public class UserInformation {
    private int userId;

    public UserInformation() {
    }

    public UserInformation(int userId) throws Exception{
        this.userId = userId;
    }

    public int getUserId() {
        return userId;
    }

    public static boolean checkValidation(String text){
        return false;
    }

}
