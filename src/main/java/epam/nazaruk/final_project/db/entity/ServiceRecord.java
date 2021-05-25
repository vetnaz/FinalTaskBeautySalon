package epam.nazaruk.final_project.db.entity;

import java.io.Serializable;
import java.util.Objects;

public class ServiceRecord implements Serializable {
    private int id;
    private String date;
    private int statusId;
    private int userId;
    private int userRolesId;
    private int serviceId;
    private int mastersId;
    private String timeslot;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getTimeslot() {
        return timeslot;
    }

    public void setTimeslot(String timeslot) {
        this.timeslot = timeslot;
    }

    public int getMastersId() {
        return mastersId;
    }

    public void setMastersId(int mastersId) {
        this.mastersId = mastersId;
    }

    public int getStatusId() {
        return statusId;
    }

    public void setStatusId(int statusId) {
        this.statusId = statusId;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public int getUserRolesId() {
        return userRolesId;
    }

    public void setUserRolesId(int userRolesId) {
        this.userRolesId = userRolesId;
    }

    public int getServiceId() {
        return serviceId;
    }

    public void setServiceId(int serviceId) {
        this.serviceId = serviceId;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ServiceRecord that = (ServiceRecord) o;
        return id == that.id && statusId == that.statusId && userId == that.userId && userRolesId == that.userRolesId && serviceId == that.serviceId && mastersId == that.mastersId && Objects.equals(date, that.date) && Objects.equals(timeslot, that.timeslot);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, date, statusId, userId, userRolesId, serviceId, mastersId, timeslot);
    }

    @Override
    public String toString() {
        return "ServiceRecord{" +
                "id=" + id +
                ", date='" + date + '\'' +
                ", statusId=" + statusId +
                ", userId=" + userId +
                ", userRolesId=" + userRolesId +
                ", serviceId=" + serviceId +
                ", mastersId=" + mastersId +
                ", timeslot='" + timeslot + '\'' +
                '}';
    }
}
