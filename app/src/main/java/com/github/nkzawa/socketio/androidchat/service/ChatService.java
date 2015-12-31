package com.github.nkzawa.socketio.androidchat.service;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.widget.Toast;

import com.github.nkzawa.socketio.androidchat.BusProvider;
import com.github.nkzawa.socketio.androidchat.R;
import com.github.nkzawa.socketio.androidchat.event.OnNewMessage;
import com.github.nkzawa.socketio.androidchat.event.OnStopTyping;
import com.github.nkzawa.socketio.androidchat.event.OnTyping;
import com.github.nkzawa.socketio.androidchat.event.OnUserJoined;
import com.github.nkzawa.socketio.androidchat.event.OnUserLeft;
import com.github.nkzawa.socketio.androidchat.io.SocketSingleton;
import com.squareup.otto.Bus;

import org.json.JSONException;
import org.json.JSONObject;

import io.socket.client.Socket;
import io.socket.emitter.Emitter;

/**
 * Created by Alejandro on 31/12/2015.
 */
public class ChatService extends Service {


    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public void onCreate() {
        super.onCreate();
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        BusProvider.getBusInstance().register(this);
        SocketSingleton.getInstance().getSocket().on(Socket.EVENT_CONNECT_ERROR, onConnectError);
        SocketSingleton.getInstance().getSocket().on(Socket.EVENT_CONNECT_TIMEOUT, onConnectError);
        SocketSingleton.getInstance().getSocket().on("new message", onNewMessage);
        SocketSingleton.getInstance().getSocket().on("user joined", onUserJoined);
        SocketSingleton.getInstance().getSocket().on("user left", onUserLeft);
        SocketSingleton.getInstance().getSocket().on("typing", onTyping);
        SocketSingleton.getInstance().getSocket().on("stop typing", onStopTyping);
        SocketSingleton.getInstance().getSocket().connect();
        return START_STICKY;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        BusProvider.getBusInstance().register(this);
        SocketSingleton.getInstance().getSocket().disconnect();
        SocketSingleton.getInstance().getSocket().off(Socket.EVENT_CONNECT_ERROR, onConnectError);
        SocketSingleton.getInstance().getSocket().off(Socket.EVENT_CONNECT_TIMEOUT, onConnectError);
        SocketSingleton.getInstance().getSocket().off("new message", onNewMessage);
        SocketSingleton.getInstance().getSocket().off("user joined", onUserJoined);
        SocketSingleton.getInstance().getSocket().off("user left", onUserLeft);
        SocketSingleton.getInstance().getSocket().off("typing", onTyping);
        SocketSingleton.getInstance().getSocket().off("stop typing", onStopTyping);


    }

    private Emitter.Listener onConnectError = new Emitter.Listener() {
        @Override
        public void call(Object... args) {
            BusProvider.getBusInstance().post(new Object());
            getActivity().runOnUiThread(new Runnable() {

                @Override
                public void run() {
                    Toast.makeText(getActivity().getApplicationContext(),
                            R.string.error_connect, Toast.LENGTH_LONG).show();
                }
            });
        }
    };

    private Emitter.Listener onNewMessage = new Emitter.Listener() {
        @Override
        public void call(final Object... args) {
            JSONObject data = (JSONObject) args[0];
            String username;
            String message;
            try {
                username = data.getString("username");
                message = data.getString("message");
            } catch (JSONException e) {
                return;
            }
            BusProvider.getBusInstance().post(new OnNewMessage(username, message));
        }
    };

        private Emitter.Listener onUserJoined = new Emitter.Listener() {
            @Override
            public void call(final Object... args) {
                JSONObject data = (JSONObject) args[0];
                String username;
                int numUsers;
                try {
                    username = data.getString("username");
                    numUsers = data.getInt("numUsers");
                } catch (JSONException e) {
                    return;
                }
                BusProvider.getBusInstance().post(new OnUserJoined(username, numUsers));


            }
        };

        private Emitter.Listener onUserLeft = new Emitter.Listener() {
            @Override
            public void call(final Object... args) {
                JSONObject data = (JSONObject) args[0];
                String username;
                int numUsers;
                try {
                    username = data.getString("username");
                    numUsers = data.getInt("numUsers");
                } catch (JSONException e) {
                    return;
                }

                BusProvider.getBusInstance().post(new OnUserLeft(username, numUsers));
            }
        };

        private Emitter.Listener onTyping = new Emitter.Listener() {
            @Override
            public void call(final Object... args) {
                JSONObject data = (JSONObject) args[0];
                String username;
                try {
                    username = data.getString("username");
                } catch (JSONException e) {
                    return;
                }
                BusProvider.getBusInstance().post(new OnTyping(username));

            }
        };

        private Emitter.Listener onStopTyping = new Emitter.Listener() {
            @Override
            public void call(final Object... args) {
                JSONObject data = (JSONObject) args[0];
                String username;
                try {
                    username = data.getString("username");
                } catch (JSONException e) {
                    return;

                }
                BusProvider.getBusInstance().post(new OnStopTyping(username));

            }
        };

}
        /*
        private Runnable onTypingTimeout = new Runnable() {
            @Override
            public void run() {
                if (!mTyping) return;

                mTyping = false;
                mSocket.emit("stop typing");
            }
        };
    }
    */

