package io.github.realyusufismail.javadiscordbotcrafter.ws.util;

public enum OpCode {
    DISPATCH(0, false, true),
    HEARTBEAT(1, true, false),
    IDENTIFY(2, true, false),
    PRESENCE_UPDATE(3, true, false),
    VOICE_STATE_UPDATE(4, true, false),
    RESUME(6, true, false),
    RECONNECT(7, true, false),
    REQUEST_GUILD_MEMBERS(8, true, false),
    INVALID_SESSION(9, false, true),
    HELLO(10, false, true),
    HEARTBEAT_ACK(11, false, true),
    UNKNOWN(-1, false, false);

    private final int value;
    private final boolean send;
    private final boolean receive;

    OpCode(int value, boolean send, boolean receive) {
        this.value = value;
        this.send = send;
        this.receive = receive;
    }

    public int getValue() {
        return value;
    }

    public boolean isSend() {
        return send;
    }

    public boolean isReceive() {
        return receive;
    }

    public static OpCode fromValue(int value) {
        for (OpCode opCode : values()) {
            if (opCode.value == value) {
                return opCode;
            }
        }
        return UNKNOWN;
    }
}
