package io.github.kpgtb.kkui.listener;

import io.github.kpgtb.kkcore.manager.UsefulObjects;
import io.github.kpgtb.kkui.KKui;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerQuitEvent;

import java.util.UUID;

public class QuitListener implements Listener {
    public QuitListener(UsefulObjects usefulObjects) {}

    @EventHandler
    public void onQuit(PlayerQuitEvent event) {
        Player player = event.getPlayer();
        UUID uuid = player.getUniqueId();

        KKui.getUiManager().removeAllUI(uuid);
        KKui.getUiManager().removeAllActionBars(uuid);
    }
}
