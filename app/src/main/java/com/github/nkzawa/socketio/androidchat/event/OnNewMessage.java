package com.github.nkzawa.socketio.androidchat.event;

/**
 * Created by Alejandro on 31/12/2015.
 */
public class OnNewMessage {
    private String username;
    private String message;

    public OnNewMessage(String username,String message){
        this.username=username;
        this.message=message;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
