package com.meetingroom.Meeting.Room.WebRtc.Config;

import org.springframework.stereotype.Component;

@Component
public class MediaStreamHandler {

    private final PeerConnectionFactory peerConnectionFactory;

    public MediaStreamHandler() {
        PeerConnectionFactory.initialize(PeerConnectionFactory.InitializationOptions.builder().createInitializationOptions());
        peerConnectionFactory = PeerConnectionFactory.builder().createPeerConnectionFactory();
    }

    public MediaStream createMediaStream(VideoCapturer videoCapturer) {
        VideoSource videoSource = peerConnectionFactory.createVideoSource(videoCapturer);
        VideoTrack videoTrack = peerConnectionFactory.createVideoTrack("video", videoSource);

        AudioSource audioSource = peerConnectionFactory.createAudioSource(new MediaConstraints());
        AudioTrack audioTrack = peerConnectionFactory.createAudioTrack("audio", audioSource);

        MediaStream mediaStream = peerConnectionFactory.createLocalMediaStream("stream");
        mediaStream.addTrack(videoTrack);
        mediaStream.addTrack(audioTrack);

        return mediaStream;
    }
}
