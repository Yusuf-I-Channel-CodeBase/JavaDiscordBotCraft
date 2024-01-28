package io.github.realyusufismail.javadiscordbotcrafter.ws;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.JsonNodeFactory;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.neovisionaries.ws.client.*;
import io.github.realyusufismail.javadiscordbotcrafter.DiscordWrapperInfo;
import io.github.realyusufismail.javadiscordbotcrafter.ws.util.GateWayIntent;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.util.List;
import java.util.Map;

public class WebSocketManager extends WebSocketAdapter implements WebSocketListener {
    private final Logger logger = LoggerFactory.getLogger(WebSocketManager.class);
    private final JsonNodeFactory jsonNodeFactory = JsonNodeFactory.instance;
    private final ObjectMapper objectMapper = new ObjectMapper();

    private WebSocket webSocket;

    // variables needed for the websocket
    private String resumeUrl;

    private String sessionId;
    private int sequenceNumber;


    // You will need a token and set up gateway intents
    private final String token;

    // Intents
    private final GateWayIntent[] intents;

    public WebSocketManager(String token, GateWayIntent[] intents) {
        this.token = token;
        this.intents = intents;
    }

    public synchronized void connect() {
        String url = (resumeUrl != null ? resumeUrl : DiscordWrapperInfo.DISCORD_GATEWAY_URL.getValue())
                + DiscordWrapperInfo.DISCORD_GATEWAY_VERSION.getValue()
                + DiscordWrapperInfo.JSON_ENCODING.getValue();

        try {
            webSocket = new WebSocketFactory()
                    .createSocket(url)
                    .addListener(this)
                    .connect();
        } catch (WebSocketException | IOException e) {
            resumeUrl = null;
            sessionId = null;

            // TODO : Handle failed connection

            logger.error("Error connecting to websocket", e);
        }
    }

    @Override
    public void onConnected(WebSocket websocket, Map<String, List<String>> headers) throws Exception {
        logger.info("Connected to websocket");
        
        if (sessionId == null) {
            sendIdentify();
        } else {
            sendResume();
        }
    }

    @Override
    public void onTextMessage(WebSocket websocket, String text) throws Exception {
        logger.info("Received message: {}", text);

        JsonNode payload = objectMapper.readTree(text);

        if (payload.hasNonNull("s")) {
            sequenceNumber = payload.get("s").asInt();
        }

        JsonNode data = payload.get("d");
        int opCode = payload.get("op").asInt();
        onOpCode(opCode, data);
    }

    private void onOpCode(int opCode, JsonNode data) {
        
    }

    private void sendIdentify() {
        ObjectNode data = jsonNodeFactory.objectNode()
                .put("token", token)
                .put("intents", GateWayIntent.getBitmask(intents))
                .set("properties", jsonNodeFactory.objectNode()
                        .put("os", System.getProperty("os.name"))
                        .put("browser", "Java Discord Bot Crafter")
                        .put("device", "Java Discord Bot Crafter")
                );

        JsonNode jsonNode = jsonNodeFactory.objectNode()
                .put("op", 2)
                .set("d", data);

        webSocket.sendText(jsonNode.toString());
    }

    private void sendResume() {
        ObjectNode objectNode =
                jsonNodeFactory.objectNode()
                        .put("token", token)
                        .put("session_id", sessionId)
                        .put("seq", sequenceNumber);

        JsonNode jsonNode = jsonNodeFactory.objectNode()
                .put("op", 6)
                .set("d", objectNode);

        webSocket.sendText(jsonNode.toString());
    }
}
