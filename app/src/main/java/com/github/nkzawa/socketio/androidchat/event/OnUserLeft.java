package com.github.nkzawa.socketio.androidchat.event;

/**
 * Created by Alejandro on 31/12/2015.
 */
public class OnUserLeft {
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    String username;

    public int getNumUsers() {
        return numUsers;
    }

    public void setNumUsers(int numUsers) {
        this.numUsers = numUsers;
    }

    int numUsers ;
    public OnUserLeft(String username, int numUsers){
        this.username = username;
        this.numUsers = numUsers;
    }
}
