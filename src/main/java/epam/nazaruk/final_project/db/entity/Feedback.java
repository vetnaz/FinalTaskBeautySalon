package epam.nazaruk.final_project.db.entity;

import java.io.Serializable;
import java.util.Objects;

public class Feedback implements Serializable {
    private int id;
    private  String feedbackDate;
    private String comment;
    private int serviceRecordId;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFeedbackDate() {
        return feedbackDate;
    }

    public void setFeedbackDate(String feedbackDate) {
        this.feedbackDate = feedbackDate;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public int getServiceRecordId() {
        return serviceRecordId;
    }

    public void setServiceRecordId(int serviceRecordId) {
        this.serviceRecordId = serviceRecordId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Feedback feedback = (Feedback) o;
        return id == feedback.id && serviceRecordId == feedback.serviceRecordId && Objects.equals(feedbackDate, feedback.feedbackDate) && Objects.equals(comment, feedback.comment);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, feedbackDate, comment, serviceRecordId);
    }

    @Override
    public String toString() {
        return "Feedback{" +
                "id=" + id +
                ", feedbackDate='" + feedbackDate + '\'' +
                ", comment='" + comment + '\'' +
                ", serviceRecordId=" + serviceRecordId +
                '}';
    }
}
