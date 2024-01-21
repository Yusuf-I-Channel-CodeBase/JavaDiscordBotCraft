package io.github.realyusufismail.javadiscordbotcrafter.ws.util;

public enum GateWayIntent {
    GUILD_MEMBERS(1),
    GUILD_BANS(2),
    GUILD_WEBHOOKS(5),
    GUILD_INVITES(6),
    GUILD_VOICE_STATES(7),
    GUILD_PRESENCES(8),
    GUILD_MESSAGES(9),
    GUILD_MESSAGE_REACTIONS(10),
    GUILD_MESSAGE_TYPING(11),
    DIRECT_MESSAGES(12),
    DIRECT_MESSAGE_REACTIONS(13),
    DIRECT_MESSAGE_TYPING(14),
    MESSAGE_CONTENT(15),
    AUTO_MODERATION_CONFIGURATION(20),
    CHANNEL_STATES(21),
    AUTO_MODERATION_EXECUTION(22);

    private final int value;

    GateWayIntent(int value) {
        this.value = value;
    }

    public static int getBitmask(GateWayIntent[] intents) {
        int bitmask = 0;
        for (GateWayIntent intent : intents) {
            bitmask |= intent.value;
        }
        return bitmask;
    }

    public static GateWayIntent[] getDefaults() {
        return new GateWayIntent[] {
                GUILD_MEMBERS,
                GUILD_BANS,
                GUILD_WEBHOOKS,
                GUILD_INVITES,
                GUILD_VOICE_STATES,
                GUILD_PRESENCES,
                GUILD_MESSAGES,
                GUILD_MESSAGE_REACTIONS,
                DIRECT_MESSAGES,
                DIRECT_MESSAGE_REACTIONS,
                DIRECT_MESSAGE_TYPING,
        };
    }
}
