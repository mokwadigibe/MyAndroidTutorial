package com.amg.myappamg.Model;

/**
 * @Author: AMG
 * @Date: Thursday 19/10/2023 October 2023
 * @Time: 16:14
 */

public class Chat {

    private String chatName;
    private String lastMessage;

    // Default constructor is required for Firestore
    public Chat() {}

    public Chat(String chatName, String lastMessage) {
        this.chatName = chatName;
        this.lastMessage = lastMessage;
    }

    public String getChatName() {
        return chatName;
    }

    public void setChatName(String chatName) {
        this.chatName = chatName;
    }

    public String getLastMessage() {
        return lastMessage;
    }

    public void setLastMessage(String lastMessage) {
        this.lastMessage = lastMessage;
    }
}
