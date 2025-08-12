package com.ecnu.back.model;


import java.util.List;
import java.util.Map;

public class ChatRequest{

    private String chatID;
    private String message;
    private Boolean re_chat;

    public String getChatID() {
        return chatID;
    }

    public void setChatID(String chatID) {
        this.chatID = chatID;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Boolean getRe_chat() {
        return re_chat;
    }

    public void setRe_chat(Boolean re_chat) {
        this.re_chat = re_chat;
    }
}