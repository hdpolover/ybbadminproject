package com.hdpolover.ybbadminproject.Models;

public class ModelFeedback {
    String email, fId, feedback, timestamp, uid;

    public ModelFeedback() {

    }

    public ModelFeedback(String email, String fId, String feedback, String timestamp, String uid) {
        this.email = email;
        this.fId = fId;
        this.feedback = feedback;
        this.timestamp = timestamp;
        this.uid = uid;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getfId() {
        return fId;
    }

    public void setfId(String fId) {
        this.fId = fId;
    }

    public String getFeedback() {
        return feedback;
    }

    public void setFeedback(String feedback) {
        this.feedback = feedback;
    }

    public String getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(String timestamp) {
        this.timestamp = timestamp;
    }

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }
}
