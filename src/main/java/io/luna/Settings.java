package io.luna;

/**
 * Holds settings parsed from the {@code ./data/luna-client.toml} file. Effectively constants, as they are only modified by GSON.
 *
 * @author searledan
 */
public final class Settings {

    public String name;
    public String host;
    public int port;
    public boolean decodeRsa;
    public int npcBits;
    public boolean debug;

    /**
     * The name of the client.
     */
    public String name() {
        return name;
    }

    /**
     * The host that the client will connect to.
     */
    public String host() {
        return host;
    }

    /**
     * The port that the client will use to connect to the host.
     */
    public int port() {
        return port;
    }

    /**
     * Should the client decode the RSA key.
     */
    public boolean decodeRsa() {
        return decodeRsa;
    }

    /**
     * The amount of bits for an NPC.
     */
    public int npcBits() {
        return npcBits;
    }

    /**
     * Is the client in debugging mode.
     */
    public boolean debug() {
        return debug;
    }

    /**
     * To prevent public instantiation.
     */
    private Settings() {

    }
}