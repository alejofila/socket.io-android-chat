package com.github.nkzawa.socketio.androidchat.event;

/**
 * Created by Alejandro on 31/12/2015.
 */
public class OnUserJoined {
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public int getNumUsers() {
        return numUsers;
    }

    public void setNumUsers(int numUsers) {
        this.numUsers = numUsers;
    }

    String username;
    int numUsers;
    public OnUserJoined (String username, int numUsers){
        this.username = username;
        this.numUsers = numUsers;
    }
}
