package saversandloaders.database.models.dbusermodels;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Projections;
import saversandloaders.database.SessionGenerator;

public class DBUserIdGenerator {
    public static int generateAnId(){
        Session session = SessionGenerator.getHibernateSession();
        Transaction transaction = session.beginTransaction();

        Long count1 = ((Long) session.createQuery("select count(*) from DBUser").uniqueResult());
        int numberOfRows = count1.intValue();

        transaction.commit();
        session.close();

        int output = numberOfRows + 1;

        return output;
    }
}
