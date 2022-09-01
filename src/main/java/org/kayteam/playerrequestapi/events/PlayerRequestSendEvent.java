package org.kayteam.playerrequestapi.events;

import org.bukkit.entity.Player;
import org.bukkit.event.Cancellable;
import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;
import org.kayteam.playerrequestapi.Request;

public class PlayerRequestSendEvent extends Event implements Cancellable {

    private final static HandlerList handlerList = new HandlerList();
    private boolean cancel = false;
    private final Player player;
    private final Request request;

    public PlayerRequestSendEvent(Player player, Request request) {
        this.player = player;
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

    public Player getPlayer() {

        return player;

    }

    public Request getRequest() {

        return request;

    }

}
