package saversandloaders.database.models.dbtweetmodels;

import javax.persistence.*;
import java.util.Objects;

@Entity
public class DBTag {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String name;
    private int numberOfUses;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getNumberOfUses() {
        return numberOfUses;
    }

    public void setNumberOfUses(int numberOfUses) {
        this.numberOfUses = numberOfUses;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        DBTag dbTag = (DBTag) o;
        return getId() == dbTag.getId() && getNumberOfUses() == dbTag.getNumberOfUses() && Objects.equals(getName(), dbTag.getName());
    }
}
