package saversandloaders.database.dbsavers;

import XLogger.MLogger;
import models.bots.*;
import org.hibernate.Session;
import org.hibernate.Transaction;
import saversandloaders.database.SessionGenerator;
import saversandloaders.database.converters.botinfoconverters.BotInfoConverter;
import saversandloaders.database.models.dbbotinfomodels.DBBotInfo;

public class DBSaver {
    public static void saveBotInfo(BotInfo botInfo){
        Session session = SessionGenerator.getHibernateSession();
        Transaction transaction = session.beginTransaction();
        MLogger.log("INFO: Transaction began");

        DBBotInfo dbBotInfo = null;

        if (botInfo.getClass() == GameBotInfo.class){
            dbBotInfo = BotInfoConverter.convertBotInfoToDBBotInfo(botInfo, BotInfoTypes.GAMEBOT_INFO);
        } else
        if (botInfo.getClass() == QuestionBotInfo.class){
            dbBotInfo = BotInfoConverter.convertBotInfoToDBBotInfo(botInfo, BotInfoTypes.QUESTIONBOT_INFO);
        } else
        if (botInfo.getClass() == CalculatorBotInfo.class){
            dbBotInfo = BotInfoConverter.convertBotInfoToDBBotInfo(botInfo, BotInfoTypes.CALCULATORBOT_INFO);
        }

        if (session.get(DBBotInfo.class, dbBotInfo.getId()) == null) {
            session.save(dbBotInfo);
        }else {
            try {
                DBBotInfo dbBotInfo1 = session.get(DBBotInfo.class, dbBotInfo.getId());
                session.delete(dbBotInfo1);
                session.save(dbBotInfo);
            }catch (Exception e){
                e.printStackTrace();
            }
        }

        transaction.commit();
        MLogger.log("INFO: Transaction committed");
        session.close();

    }
}
