package org.kayteam.playerrequestapi.events;

import org.bukkit.Server;
import org.bukkit.entity.Player;
import org.bukkit.event.Cancellable;
import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;
import org.bukkit.plugin.java.JavaPlugin;
import org.kayteam.playerrequestapi.request.Request;

import java.util.UUID;

public class PlayerRequestAcceptEvent extends Event implements Cancellable {

    private final static HandlerList handlerList = new HandlerList();
    private boolean cancel = false;
    private final Request request;

    public PlayerRequestAcceptEvent(Request request) {
        this.request = request;
    }

    @Override
    public HandlerList getHandlers() {

        return handlerList;

    }

    public static HandlerList getHandlerList() {

        return handlerList;

    }

    @Override
    public boolean isCancelled() {

        return cancel;

    }

    @Override
    public void setCancelled(boolean cancel) {

        this.cancel = cancel;

    }

    public Request getRequest() {

        return request;

    }

    public Player getSender() {

        JavaPlugin javaPlugin = request.getJavaPlugin();

        Server server = javaPlugin.getServer();

        UUID uuid = request.getSender();

        return server.getPlayer(uuid);

    }

    public Player getReceiver() {

        JavaPlugin javaPlugin = request.getJavaPlugin();

        Server server = javaPlugin.getServer();

        UUID uuid = request.getReceiver();

        return server.getPlayer(uuid);

    }

}
