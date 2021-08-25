package saversandloaders.database.models.dbtimemodels;

import javax.persistence.*;
import java.util.Objects;

@Embeddable
public class DBMDateTime {
    private int year;
    private int month;
    private int day;
    private int hour;
    private int minute;

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public int getMonth() {
        return month;
    }

    public void setMonth(int month) {
        this.month = month;
    }

    public int getDay() {
        return day;
    }

    public void setDay(int day) {
        this.day = day;
    }

    public int getHour() {
        return hour;
    }

    public void setHour(int hour) {
        this.hour = hour;
    }

    public int getMinute() {
        return minute;
    }

    public void setMinute(int minute) {
        this.minute = minute;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        DBMDateTime that = (DBMDateTime) o;
        return getYear() == that.getYear() && getMonth() == that.getMonth() && getDay() == that.getDay() && getHour() == that.getHour() && getMinute() == that.getMinute();
    }
}
