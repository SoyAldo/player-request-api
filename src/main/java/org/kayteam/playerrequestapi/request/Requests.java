package org.kayteam.playerrequestapi.request;

import org.bukkit.plugin.java.JavaPlugin;
import org.kayteam.playerrequestapi.PlayerRequestManager;

import java.util.HashMap;
import java.util.UUID;

public class Requests {

    private JavaPlugin javaPlugin;
    private PlayerRequestManager playerRequestManager;
    private final HashMap<UUID, Request> requestsSubmitted;
    private final HashMap<UUID, Request> requestsReceived;


    public Requests() {

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

    public HashMap<UUID, Request> getRequestsSubmitted() {

        return requestsSubmitted;

    }

    public HashMap<UUID, Request> getRequestsReceived() {

        return requestsReceived;

    }

}