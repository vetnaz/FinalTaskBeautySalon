package epam.nazaruk.final_project.db.entity;

import java.io.Serializable;
import java.util.Objects;

public class Master implements Serializable {
    private int id;
    private String masterName;
    private String masterSurname;
    private int rating;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getMasterName() {
        return masterName;
    }

    public void setMasterName(String masterName) {
        this.masterName = masterName;
    }

    public String getMasterSurname() {
        return masterSurname;
    }

    public void setMasterSurname(String masterSurname) {
        this.masterSurname = masterSurname;
    }

    public int getRating() {
        return rating;
    }

    public void setRating(int rating) {
        this.rating = rating;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Master master = (Master) o;
        return id == master.id && rating == master.rating && Objects.equals(masterName, master.masterName) && Objects.equals(masterSurname, master.masterSurname);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, masterName, masterSurname, rating);
    }

    @Override
    public String toString() {
        return "Master{" +
                "id=" + id +
                ", masterName='" + masterName + '\'' +
                ", masterSurname='" + masterSurname + '\'' +
                ", rating=" + rating +
                '}';
    }
}
