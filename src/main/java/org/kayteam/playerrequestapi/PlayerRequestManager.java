package org.kayteam.playerrequestapi;

import org.bukkit.Server;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;
import org.kayteam.playerrequestapi.listeners.*;

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

    }

    public HashMap<UUID, Requests> getRequests() {

        return requests;

    }

    public void addRequests(UUID uuid) {

        Requests r = new Requests(uuid);

        r.setJavaPlugin(javaPlugin);

        requests.put(uuid, new Requests(uuid));

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

}