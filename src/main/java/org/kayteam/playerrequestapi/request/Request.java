package org.kayteam.playerrequestapi.request;

import org.bukkit.Server;
import org.bukkit.entity.Player;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;
import org.kayteam.playerrequestapi.PlayerRequestManager;
import org.kayteam.playerrequestapi.events.PlayerRequestAcceptEvent;
import org.kayteam.playerrequestapi.events.PlayerRequestRejectedEvent;

import java.util.UUID;

public abstract class Request {

    private JavaPlugin javaPlugin;
    private PlayerRequestManager playerRequestManager;
    private final UUID sender;
    private final UUID receiver;

    public Request(UUID sender, UUID receiver) {

        this.sender = sender;

        this.receiver = receiver;

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

    public UUID getSender() {

        return sender;

    }

    public UUID getReceiver() {

        return receiver;

    }

    public void executeRequest() {

        onExecuteActions();

    }

    public void acceptRequest(Player player) {

        PlayerRequestAcceptEvent playerRequestAcceptEvent = new PlayerRequestAcceptEvent(this);

        Server server = javaPlugin.getServer();

        PluginManager pluginManager = server.getPluginManager();

        pluginManager.callEvent(playerRequestAcceptEvent);

        if ( playerRequestAcceptEvent.isCancelled() ) return;

        Requests senderRequests = playerRequestManager.getRequests(sender);

        if ( senderRequests != null )   senderRequests.getRequestsSubmitted().remove(receiver);

        Requests receiverRequests = playerRequestManager.getRequests(receiver);

        if ( receiverRequests != null )   receiverRequests.getRequestsReceived().remove(sender);

        onRequestAcceptActions();

    }

    public void rejectRequest(Player player) {

        PlayerRequestRejectedEvent playerRequestRejectedEvent = new PlayerRequestRejectedEvent(this);

        Server server = javaPlugin.getServer();

        PluginManager pluginManager = server.getPluginManager();

        pluginManager.callEvent(playerRequestRejectedEvent);

        if ( playerRequestRejectedEvent.isCancelled() )   return;

        Requests senderRequests = playerRequestManager.getRequests(sender);

        if ( senderRequests != null )   senderRequests.getRequestsSubmitted().remove(receiver);

        Requests receiverRequests = playerRequestManager.getRequests(receiver);

        if ( receiverRequests != null )   receiverRequests.getRequestsReceived().remove(sender);

        onRequestRejectActions();

    }

    public void expireRequest() {

        Requests senderRequests = playerRequestManager.getRequests(sender);

        if ( senderRequests != null )   senderRequests.getRequestsSubmitted().remove(receiver);

        Requests receiverRequests = playerRequestManager.getRequests(receiver);

        if ( receiverRequests != null )   receiverRequests.getRequestsReceived().remove(sender);

        onRequestExpireActions();

    }

    public abstract void onExecuteActions();

    public abstract void onRequestAcceptActions();

    public abstract void onRequestRejectActions();

    public abstract void onRequestExpireActions();

}