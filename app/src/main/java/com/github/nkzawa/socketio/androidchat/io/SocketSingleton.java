package com.github.nkzawa.socketio.androidchat.io;

import com.github.nkzawa.socketio.androidchat.Constants;

import java.net.URISyntaxException;

import io.socket.client.IO;
import io.socket.client.Socket;

/**
 * Created by Alejandro on 31/12/2015.
 */
public class SocketSingleton {
    private static SocketSingleton instance;

    private Socket mSocket;

    private SocketSingleton(){
        try {
            mSocket = IO.socket(Constants.CHAT_SERVER_URL);
        } catch (URISyntaxException e) {
            throw new RuntimeException();
        }

    }
    public static synchronized SocketSingleton getInstance(){
        if(instance == null)
            instance =  new SocketSingleton();
        return instance;
    }

    public Socket getSocket(){
        return mSocket;
    }

}
