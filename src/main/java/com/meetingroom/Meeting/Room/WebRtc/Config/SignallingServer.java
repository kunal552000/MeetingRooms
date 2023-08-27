package com.meetingroom.Meeting.Room.WebRtc.Config;

import com.corundumstudio.socketio.AckRequest;
import com.corundumstudio.socketio.SocketIOClient;
import com.corundumstudio.socketio.SocketIOServer;
import com.corundumstudio.socketio.listener.DataListener;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class SignallingServer {
    @Autowired
    private SocketIOServer server;

    public SignallingServer() {
        server.addEventListener("message", JSONObject.class, new DataListener<JSONObject>() {
            @Override
            public void onData(SocketIOClient client, JSONObject data, AckRequest ackRequest) throws Exception {
                // Handle incoming message
            }
        });
    }
}
