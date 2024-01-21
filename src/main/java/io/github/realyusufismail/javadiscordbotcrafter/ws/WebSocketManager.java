package io.github.realyusufismail.javadiscordbotcrafter.ws;

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

    private WebSocket webSocket;

    // variables needed for the websocket
    private String resumeUrl;

    private String sessionId;


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

    private void sendIdentify() {
        // TODO : Send identify payload
    }

    private void sendResume() {
        // TODO : Send resume payload
    }
}
