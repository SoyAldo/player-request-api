package org.kayteam.playerrequestapi;

import org.bukkit.Server;
import org.bukkit.entity.Player;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;
import org.kayteam.playerrequestapi.events.PlayerRequestAcceptEvent;
import org.kayteam.playerrequestapi.events.PlayerRequestRejectedEvent;

import java.util.UUID;

public abstract class Request {

    private JavaPlugin javaPlugin;
    private final UUID uuid;
    private final UUID author;

    public Request(UUID author) {

        this.uuid = UUID.randomUUID();

        this.author = author;

    }

    public JavaPlugin getJavaPlugin() {

        return javaPlugin;

    }

    public void setJavaPlugin(JavaPlugin javaPlugin) {

        this.javaPlugin = javaPlugin;

    }

    public UUID getUuid() {

        return uuid;

    }

    public UUID getAuthor() {

        return author;

    }

    public void acceptRequest(Player player) {

        PlayerRequestAcceptEvent playerRequestAcceptEvent = new PlayerRequestAcceptEvent(player, this);

        Server server = javaPlugin.getServer();

        PluginManager pluginManager = server.getPluginManager();

        pluginManager.callEvent(playerRequestAcceptEvent);

        if ( playerRequestAcceptEvent.isCancelled() ) return;

        onRequestAcceptAction();

    }

    public void rejectRequest(Player player) {

        PlayerRequestRejectedEvent playerRequestRejectedEvent = new PlayerRequestRejectedEvent(player, this);

        Server server = javaPlugin.getServer();

        PluginManager pluginManager = server.getPluginManager();

        pluginManager.callEvent(playerRequestRejectedEvent);

        if ( playerRequestRejectedEvent.isCancelled() ) return;

        onRequestRejectAction();

    }

    public abstract void onRequestAcceptAction();

    public abstract void onRequestRejectAction();

}