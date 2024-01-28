package io.github.realyusufismail.javadiscordbotcrafter;

import com.fasterxml.jackson.databind.JsonNode;
import io.github.realyusufismail.javadiscordbotcrafter.ws.WebSocketManager;
import io.github.realyusufismail.javadiscordbotcrafter.ws.util.GateWayIntent;
import io.github.realyusufismail.jconfig.JConfig;

public class Main {
    private static final JConfig config = JConfig.build();

    public static void main(String[] args) {
        JsonNode tokenNode = config.get("token");

        if (tokenNode == null) {
            System.err.println("No token found in config.json");
            System.exit(1);
        }


        new WebSocketManager(tokenNode.asText(), GateWayIntent.getDefaults()).connect();
    }
}
