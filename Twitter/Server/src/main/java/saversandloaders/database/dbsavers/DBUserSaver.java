package saversandloaders.database.dbsavers;

import XLogger.MLogger;
import models.user.User;
import org.hibernate.Session;
import org.hibernate.Transaction;
import saversandloaders.database.SessionGenerator;
import saversandloaders.database.converters.userconverter.UserConverter;
import saversandloaders.database.models.dbusermodels.DBUser;
import saversandloaders.database.models.dbusermodels.DBUserInfo;

public class DBUserSaver {

    public synchronized static void saveUser(User user){
        DBUser dbUser = UserConverter.convertUserToDBUser(user);

        Session session = SessionGenerator.getHibernateSession();
        Transaction transaction = session.beginTransaction();
        MLogger.log("INFO: Transaction began");

        if (session.get(DBUser.class, dbUser.getId()) == null) {
            session.save(dbUser);
        }else {
            try {
                DBUser dbUser1 = session.get(DBUser.class, dbUser.getId());
                session.delete(dbUser1);
                session.save(dbUser);
            }catch (Exception e){
                e.printStackTrace();
            }
        }

        saveInfo(user);

        transaction.commit();
        session.close();
    }

    private static void saveInfo(User user){
        Session session = SessionGenerator.getHibernateSession();
        Transaction transaction = session.beginTransaction();

        DBUserInfo dbUserInfo = new DBUserInfo();
        dbUserInfo.setId(user.getId());
        dbUserInfo.setUsername(user.getUsername().getText());
        dbUserInfo.setPhonenumber(user.getPhonenumber().getCountryCode()+user.getPhonenumber().getMainPart());
        dbUserInfo.setEmail(user.getEmail().getText());

        if (session.get(DBUserInfo.class, dbUserInfo.getId()) == null) {
            session.save(dbUserInfo);
        }else {
            try {
                DBUserInfo dbUserInfo1 = session.get(DBUserInfo.class, dbUserInfo.getId());
                session.delete(dbUserInfo1);
                session.save(dbUserInfo);
            }catch (Exception e){
                e.printStackTrace();
            }
        }

        transaction.commit();
        MLogger.log("INFO: Transaction committed");
        session.close();
    }


}
