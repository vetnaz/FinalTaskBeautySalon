package epam.nazaruk.final_project.db.bean;

import java.io.Serializable;
import java.util.Objects;

public class ServiceMasterBean implements Serializable {
    private String title;
    private String description;
    private int price;
    private String masterName;
    private String masterSurname;
    private int rating;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
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
        ServiceMasterBean that = (ServiceMasterBean) o;
        return price == that.price && rating == that.rating && Objects.equals(title, that.title) && Objects.equals(description, that.description) && Objects.equals(masterName, that.masterName) && Objects.equals(masterSurname, that.masterSurname);
    }

    @Override
    public int hashCode() {
        return Objects.hash(title, description, price, masterName, masterSurname, rating);
    }

    @Override
    public String toString() {
        return "ServiceMasterBean{" +
                "title='" + title + '\'' +
                ", description='" + description + '\'' +
                ", price=" + price +
                ", masterName='" + masterName + '\'' +
                ", masterSurname='" + masterSurname + '\'' +
                ", rating=" + rating +
                '}';
    }
}
