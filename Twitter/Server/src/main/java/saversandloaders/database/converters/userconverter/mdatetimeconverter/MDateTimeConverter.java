package saversandloaders.database.converters.userconverter.mdatetimeconverter;

import saversandloaders.database.models.dbtimemodels.DBMDateTime;
import models.time.MDateTime;

public class MDateTimeConverter {
    public static MDateTime convertDBMDateTimeToMDateTime(DBMDateTime dbmDateTime){
        MDateTime mDateTime = new MDateTime();
        mDateTime.setYear(dbmDateTime.getYear());
        mDateTime.setMonth(dbmDateTime.getMonth());
        mDateTime.setDay(dbmDateTime.getDay());
        mDateTime.setHour(dbmDateTime.getHour());
        mDateTime.setMinute(dbmDateTime.getMinute());
        return mDateTime;
    }

    public static DBMDateTime convertMDateTimeToDBMDateTime(MDateTime mDateTime){
        DBMDateTime dbmDateTime = new DBMDateTime();
        dbmDateTime.setYear(mDateTime.getYear());
        dbmDateTime.setMonth(mDateTime.getMonth());
        dbmDateTime.setDay(mDateTime.getDay());
        dbmDateTime.setHour(mDateTime.getHour());
        dbmDateTime.setMinute(mDateTime.getMinute());
        return dbmDateTime;
    }
}
