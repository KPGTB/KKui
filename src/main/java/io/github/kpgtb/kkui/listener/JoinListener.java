package io.github.kpgtb.kkui.listener;

import io.github.kpgtb.kkcore.manager.UsefulObjects;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

public class JoinListener implements Listener {
    private final FileConfiguration config;

    public JoinListener(UsefulObjects usefulObjects) {
        this.config = usefulObjects.getConfig();
    }

    @EventHandler
    public void onJoin(PlayerJoinEvent event) {
        event.getPlayer().setResourcePack(config.getString("resourcePack"));
    }
}
