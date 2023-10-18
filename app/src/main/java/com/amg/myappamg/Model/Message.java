package com.amg.myappamg.Model;

/**
 * @Author: AMG
 * @Date: Wednesday 18/10/2023 October 2023
 * @Time: 02:31
 */

public class Message {
    private String userID, text;
    private long timestamp;

    public Message(){}

    public Message(String userID, String text, long timestamp) {
        this.userID = userID;
        this.text = text;
        this.timestamp = timestamp;
    }

    public String getUserID() {
        return userID;
    }

    public void setUserID(String userID) {
        this.userID = userID;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public long getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(long timestamp) {
        this.timestamp = timestamp;
    }
}
