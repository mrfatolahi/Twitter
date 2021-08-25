package saversandloaders.database.dbloaders;

import models.bots.CalculatorBotInfo;
import models.bots.GameBotInfo;
import models.bots.QuestionBotInfo;
import org.hibernate.Session;
import org.hibernate.Transaction;
import saversandloaders.database.SessionGenerator;
import saversandloaders.database.converters.botinfoconverters.BotInfoConverter;
import saversandloaders.database.models.dbbotinfomodels.DBBotInfo;

public class DBLoader {
    public static GameBotInfo loadGameBotInfo(){
        Session session = SessionGenerator.getHibernateSession();
        Transaction transaction = session.beginTransaction();

        DBBotInfo dbBotInfo = session.get(DBBotInfo.class, 1);

        transaction.commit();
        session.close();

        return BotInfoConverter.convertDBBotInfoToGameBotInfo(dbBotInfo);
    }

    public static QuestionBotInfo loadQuestionBotInfo(){
        Session session = SessionGenerator.getHibernateSession();
        Transaction transaction = session.beginTransaction();

        DBBotInfo dbBotInfo = session.get(DBBotInfo.class, 2);

        transaction.commit();
        session.close();

        return BotInfoConverter.convertDBBotInfoToQuestionBotInfo(dbBotInfo);
    }

    public static CalculatorBotInfo loadCalculatorBotInfo(){
        Session session = SessionGenerator.getHibernateSession();
        Transaction transaction = session.beginTransaction();

        DBBotInfo dbBotInfo = session.get(DBBotInfo.class, 3);

        transaction.commit();
        session.close();

        return BotInfoConverter.convertDBBotInfoToCalculatorBotInfo(dbBotInfo);
    }
}
