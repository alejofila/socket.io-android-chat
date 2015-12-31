package com.github.nkzawa.socketio.androidchat.event;

/**
 * Created by Alejandro on 31/12/2015.
 */
public class OnTyping {

    public OnTyping(String username) {
        this.username = username;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    String  username ;


}
