package com.meetingroom.Meeting.Room.WebRtc.Config;

import com.corundumstudio.socketio.SocketIOServer;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class SignalingHandler {

    private final SocketIOServer socketIOServer;
    private final MediaStreamHandler mediaStreamHandler;
    private MediaStreamHandler mediaStream;

    @Autowired
    public SignalingHandler(SocketIOServer socketIOServer, MediaStreamHandler mediaStreamHandler) {
        this.socketIOServer = socketIOServer;
        this.mediaStreamHandler = mediaStreamHandler;
    }

    public void handleOffer(JSONObject message) {
        // handle the offer message
        String id = message.getString("id");
        String type = message.getString("type");
        String sdp = message.getString("sdp");

        // do something with the offer
    }

    public void handleAnswer(JSONObject message) {
        // handle the answer message
        String id = message.getString("id");
        String type = message.getString("type");
        String sdp = message.getString("sdp");

        // do something with the answer
    }

    // add handlers for any additional message types as needed

    public void handleSignalingMessage(JSONObject message) {
        String type = message.getString("type");

        switch (type) {
            case "offer":
                handleOffer(message);
                break;
            case "answer":
                handleAnswer(message);
                break;
            // add cases for any additional message types as needed
            default:
                // handle unknown message types
                break;
        }
    }

    public void handleJoin(JSONObject message) {
        // handle the join message
        String roomId = message.getString("roomId");

        // create the media stream
        mediaStream = mediaStreamHandler.createMediaStream(videoCapturer);

        // join the room and send the media
        socketIOServer.getRoomOperations(roomId).sendEvent("media", mediaStream);

        // send a message to all clients that the user has joined
        JSONObject response = new JSONObject();
        response.put("type", "join");
        response.put("id", socketIOServer.getSession(socketId).getId());
        socketIOServer.getRoomOperations(roomId).sendEvent("signaling", response);
    }

    public void handleLeave(JSONObject message) {
        // handle the leave message
        String roomId = message.getString("roomId");

        // send a message to all clients that the user has left
        JSONObject response = new JSONObject();
        response.put("type", "leave");
        response.put("id", socketIOServer.getSession(socketId).getId());
        socketIOServer.getRoomOperations(roomId).sendEvent("signaling", response);

        // leave the room
        socketIOServer.getRoomOperations(roomId).removeSession(socketIOServer.getSession(socketId));
    }
}
