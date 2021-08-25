package saversandloaders.database.dbloaders;

import models.user.User;
import org.hibernate.Session;
import org.hibernate.Transaction;
import saversandloaders.database.SessionGenerator;
import saversandloaders.database.converters.userconverter.UserConverter;
import saversandloaders.database.models.dbusermodels.DBUser;
import saversandloaders.database.models.dbusermodels.DBUserInfo;

public class DBUserLoader {

    public static User loadUser(int userId){
        Session session = SessionGenerator.getHibernateSession();
        Transaction transaction = session.beginTransaction();

        DBUser dbUser = session.get(DBUser.class, userId);

        transaction.commit();
        session.close();

        return UserConverter.convertDBUserToUser(dbUser);
    }

    public static User loadUser(String username){
        Session session = SessionGenerator.getHibernateSession();
        Transaction transaction = session.beginTransaction();

        DBUser dbUser = session.get(DBUser.class, loadIdWithUsername(username));

        transaction.commit();
        session.close();

        return UserConverter.convertDBUserToUser(dbUser);
    }

    public static int loadIdWithUsername(String username) {
        Session session = SessionGenerator.getHibernateSession();
        Transaction transaction = session.beginTransaction();

        int output = 0;
        for (int i = 1; i < 10000; i++) {
            try {
                DBUserInfo dbUserInfo1 = session.get(DBUserInfo.class, i);
                if (dbUserInfo1.getUsername().equals(username)) {
                    output = dbUserInfo1.getId();
                    break;
                }
            }catch (NullPointerException ignored){}
        }

        transaction.commit();
        session.close();

        System.out.println("username = " + username);
        System.out.println("output = " + output);
        return output;
    }
}
