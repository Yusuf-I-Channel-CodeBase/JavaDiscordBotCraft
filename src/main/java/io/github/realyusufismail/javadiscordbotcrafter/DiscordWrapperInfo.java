package io.github.realyusufismail.javadiscordbotcrafter;

public enum DiscordWrapperInfo {
    DISCORD_GATEWAY_URL("wss://gateway.discord.gg/"),
    DISCORD_GATEWAY_VERSION("?v=10"),
    JSON_ENCODING("&encoding=json");

    private final String value;

    DiscordWrapperInfo(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
