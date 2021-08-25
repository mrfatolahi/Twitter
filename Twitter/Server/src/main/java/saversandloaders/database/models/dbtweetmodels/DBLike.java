package saversandloaders.database.models.dbtweetmodels;

import saversandloaders.database.models.dbtimemodels.DBMDateTime;

import javax.persistence.*;
import java.util.Objects;

@Entity
public class DBLike {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private int userId;
    private DBMDateTime DateAndTimeCreated;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public DBMDateTime getDateAndTimeCreated() {
        return DateAndTimeCreated;
    }

    public void setDateAndTimeCreated(DBMDateTime dateAndTimeCreated) {
        DateAndTimeCreated = dateAndTimeCreated;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        DBLike dbLike = (DBLike) o;
        return getId() == dbLike.getId() && getUserId() == dbLike.getUserId() && Objects.equals(getDateAndTimeCreated(), dbLike.getDateAndTimeCreated());
    }
}
