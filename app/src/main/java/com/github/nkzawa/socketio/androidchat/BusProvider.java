package com.github.nkzawa.socketio.androidchat;

import com.squareup.otto.Bus;
import com.squareup.otto.ThreadEnforcer;

/**
 * Created by Alejandro on 31/12/2015.
 */
public class BusProvider {
    private static Bus bus;

    public static Bus getBusInstance() {
        if (bus == null)
            bus = new Bus(ThreadEnforcer.MAIN);
        return bus;
    }
}
