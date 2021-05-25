package epam.nazaruk.final_project.db.bean;

import java.io.Serializable;
import java.util.Objects;

public class MasterRecordsBean implements Serializable {
    private int recordId;
    private String service;
    private String clientName;
    private String clientSurname;
    private String date;
    private int price;
    private String status;
    private String timeslot;
    private String masterName;
    private String masterSurname;
    private String comment;

    public String getService() {
        return service;
    }

    public void setService(String service) {
        this.service = service;
    }

    public String getClientName() {
        return clientName;
    }

    public void setClientName(String clientName) {
        this.clientName = clientName;
    }

    public String getClientSurname() {
        return clientSurname;
    }

    public void setClientSurname(String clientSurname) {
        this.clientSurname = clientSurname;
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

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public int getRecordId() {
        return recordId;
    }

    public void setRecordId(int recordId) {
        this.recordId = recordId;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        MasterRecordsBean that = (MasterRecordsBean) o;
        return recordId == that.recordId && price == that.price && Objects.equals(service, that.service) && Objects.equals(clientName, that.clientName) && Objects.equals(clientSurname, that.clientSurname) && Objects.equals(date, that.date) && Objects.equals(status, that.status) && Objects.equals(timeslot, that.timeslot) && Objects.equals(masterName, that.masterName) && Objects.equals(masterSurname, that.masterSurname) && Objects.equals(comment, that.comment);
    }

    @Override
    public int hashCode() {
        return Objects.hash(recordId, service, clientName, clientSurname, date, price, status, timeslot, masterName, masterSurname, comment);
    }

    @Override
    public String toString() {
        return "MasterRecordsBean{" +
                "recordId=" + recordId +
                ", service='" + service + '\'' +
                ", clientName='" + clientName + '\'' +
                ", clientSurname='" + clientSurname + '\'' +
                ", date='" + date + '\'' +
                ", price=" + price +
                ", status='" + status + '\'' +
                ", timeslot='" + timeslot + '\'' +
                ", masterName='" + masterName + '\'' +
                ", masterSurname='" + masterSurname + '\'' +
                ", comment='" + comment + '\'' +
                '}';
    }
}
