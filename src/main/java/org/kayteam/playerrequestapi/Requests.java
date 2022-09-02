package org.kayteam.playerrequestapi;

import org.bukkit.plugin.java.JavaPlugin;

import java.util.HashMap;
import java.util.UUID;

public class Requests {

    private JavaPlugin javaPlugin;
    private PlayerRequestManager playerRequestManager;
    private final UUID uuid;
    private final HashMap<UUID, Request> requestsSubmitted;
    private final HashMap<UUID, Request> requestsReceived;


    public Requests(UUID uuid) {

        this.uuid = uuid;

        requestsSubmitted = new HashMap<>();

        requestsReceived = new HashMap<>();

    }

    public JavaPlugin getJavaPlugin() {

        return javaPlugin;

    }

    public void setJavaPlugin(JavaPlugin javaPlugin) {

        this.javaPlugin = javaPlugin;

    }

    public PlayerRequestManager getPlayerRequestManager() {

        return playerRequestManager;

    }

    public void setPlayerRequestManager(PlayerRequestManager playerRequestManager) {

        this.playerRequestManager = playerRequestManager;

    }

    public UUID getUuid() {

        return uuid;

    }

    public HashMap<UUID, Request> getRequestsSubmitted() {

        return requestsSubmitted;

    }

    public HashMap<UUID, Request> getRequestsReceived() {

        return requestsReceived;

    }

}