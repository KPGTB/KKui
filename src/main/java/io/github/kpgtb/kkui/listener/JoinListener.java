/*
 * Copyright 2022 KPG-TB
 *
 *    Licensed under the Apache License, Version 2.0 (the "License");
 *    you may not use this file except in compliance with the License.
 *    You may obtain a copy of the License at
 *
 *        http://www.apache.org/licenses/LICENSE-2.0
 *
 *    Unless required by applicable law or agreed to in writing, software
 *    distributed under the License is distributed on an "AS IS" BASIS,
 *    WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *    See the License for the specific language governing permissions and
 *    limitations under the License.
 */

package io.github.kpgtb.kkui.listener;

import io.github.kpgtb.kkcore.manager.UsefulObjects;
import io.github.kpgtb.kkui.UiUsefulObjects;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scheduler.BukkitRunnable;

public class JoinListener implements Listener {
    private final FileConfiguration config;
    private final JavaPlugin plugin;

    public JoinListener(UsefulObjects usefulObjects) {
        this.config = usefulObjects.getConfig();
        this.plugin = ((UiUsefulObjects) usefulObjects).getPlugin();
    }

    @EventHandler
    public void onJoin(PlayerJoinEvent event) {
        final String resourcePack = config.getString("resourcePack");
        if(resourcePack != null & !resourcePack.equalsIgnoreCase("")) {
            new BukkitRunnable() {
                @Override
                public void run() {
                    event.getPlayer().setResourcePack(resourcePack);
                }
            }.runTaskLater(plugin, 40);
        }

    }
}
