package org.kayteam.playerrequestapi;

import org.bukkit.Server;
import org.bukkit.entity.Player;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;
import org.kayteam.playerrequestapi.events.PlayerRequestSendEvent;
import org.kayteam.playerrequestapi.listeners.*;
import org.kayteam.playerrequestapi.request.*;

import java.util.HashMap;
import java.util.UUID;

public class PlayerRequestManager {

    private final JavaPlugin javaPlugin;
    private final HashMap<UUID, Requests> requests;

    public PlayerRequestManager(JavaPlugin javaPlugin) {

        this.javaPlugin = javaPlugin;

        requests = new HashMap<>();

    }

    public JavaPlugin getJavaPlugin() {

        return javaPlugin;

    }

    public void registerManager() {

        Server server = javaPlugin.getServer();

        PluginManager pluginManager = server.getPluginManager();

        pluginManager.registerEvents(new PlayerJoinListener(this), javaPlugin);

        pluginManager.registerEvents(new PlayerQuitListener(this), javaPlugin);

        for ( Player player:server.getOnlinePlayers() )   addRequests(player.getUniqueId());

    }

    public HashMap<UUID, Requests> getRequests() {

        return requests;

    }

    public void addRequests(UUID uuid) {

        Requests r = new Requests();

        r.setJavaPlugin(javaPlugin);

        r.setPlayerRequestManager(this);

        requests.put(uuid, r);

    }

    public void removeRequests(UUID uuid) {

        requests.remove(uuid);

    }

    public boolean hasRequests(UUID uuid) {

        return requests.containsKey(uuid);

    }

    public Requests getRequests(UUID uuid) {

        return requests.get(uuid);

    }

    public void executeRequest(Request request) {

        // Set JavaPlugin
        request.setJavaPlugin(javaPlugin);
        // Set PlayerRequestManager
        request.setPlayerRequestManager(this);
        // Get Server
        Server server = javaPlugin.getServer();
        // Get PluginManager
        PluginManager pluginManager = server.getPluginManager();
        // Create PlayerRequestSendEvent event
        PlayerRequestSendEvent playerRequestSendEvent = new PlayerRequestSendEvent(request);
        // Calling to the PlayerRequestSendEvent event
        pluginManager.callEvent(playerRequestSendEvent);
        // Return if the PlayerRequestSendEvent event is cancelled
        if ( playerRequestSendEvent.isCancelled() )   return;
        // Get sender UUID
        UUID sender = request.getSender();
        // Get receiver UUID
        UUID receiver = request.getReceiver();
        // Get sender Requests
        Requests senderRequests = requests.get(sender);
        // Add request to sender Requests
        senderRequests.getRequestsSubmitted().put(receiver, request);
        // Get receiver Requests
        Requests receiverRequests = requests.get(receiver);
        // Add request to receiver Requests
        receiverRequests.getRequestsReceived().put(sender,request);
        // Execute request
        request.executeRequest();

    }

}