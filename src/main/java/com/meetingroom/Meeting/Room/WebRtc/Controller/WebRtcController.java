package com.meetingroom.Meeting.Room.WebRtc.Controller;

import com.corundumstudio.socketio.SocketIOServer;
import com.meetingroom.Meeting.Room.WebRtc.Config.MediaStreamHandler;
import com.meetingroom.Meeting.Room.WebRtc.Config.SignalingHandler;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/webrtc")
public class WebRTCController {

    private final SocketIOServer socketIOServer;
    private final MediaStreamHandler mediaStreamHandler;
    private final SignalingHandler signalingHandler;

    @Autowired
    public WebRTCController(SocketIOServer socketIOServer, MediaStreamHandler mediaStreamHandler, SignalingHandler signalingHandler) {
        this.socketIOServer = socketIOServer;
        this.mediaStreamHandler = mediaStreamHandler;
        this.signalingHandler = signalingHandler;
    }

    @GetMapping("/join/{roomId}")
    public String joinRoom(@PathVariable String roomId) {
        // add the client to the room
        socketIOServer.getRoomOperations(roomId).addSession(socketIOServer.getSession(socketId));

        // send a message to all clients that the user has joined
        JSONObject response = new JSONObject();
        response.put("type", "join");
        response.put("id", socketIOServer.getSession(socketId).getId());
        socketIOServer.getRoomOperations(roomId).sendEvent("signaling", response);

        return "webrtc";
    }

    @GetMapping("/leave/{roomId}")
    public String leaveRoom(@PathVariable String roomId) {
        // remove the client from the room
        socketIOServer.getRoomOperations(roomId).removeSession(socketIOServer.getSession(socketId));

        // send a message to all clients that the user has left
        JSONObject response = new JSONObject();
        response.put("type", "leave");
        response.put("id", socketIOServer.getSession(socketId).getId());
        socketIOServer.getRoomOperations(roomId).sendEvent("signaling", response);

        return "redirect:/";
    }

    @GetMapping("/video")
    public String videoPage() {
        return "video";
    }

    @GetMapping("/audio")
    public String audioPage() {
        return "audio";
    }
}