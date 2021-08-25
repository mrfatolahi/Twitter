package saversandloaders.database.models.dbtimemodels;

import javax.persistence.*;
import java.util.Objects;

@Entity
public class DBMDate {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private int year;
    private int month;
    private int day;

//    @OneToOne
//    private DBUser dbUser;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        DBMDate dbmDate = (DBMDate) o;
        return getId() == dbmDate.getId() && getYear() == dbmDate.getYear() && getMonth() == dbmDate.getMonth() && getDay() == dbmDate.getDay();
    }
}
