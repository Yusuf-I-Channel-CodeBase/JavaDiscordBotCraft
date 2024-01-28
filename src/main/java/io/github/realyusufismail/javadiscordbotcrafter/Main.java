package io.github.realyusufismail.javadiscordbotcrafter;

import io.github.realyusufismail.javadiscordbotcrafter.ws.WebSocketManager;
import io.github.realyusufismail.javadiscordbotcrafter.ws.util.GateWayIntent;
import io.github.realyusufismail.jconfig.JConfig;

public class Main {
    private static final JConfig jConfig = JConfig.build();

    public static void main(String[] args) {
        if (jConfig.get("token") == null){
            throw new RuntimeException("Token not found in config.json");
        }

        new WebSocketManager(jConfig.get("token").asText(), GateWayIntent.getDefaults()).connect();
    }
}
