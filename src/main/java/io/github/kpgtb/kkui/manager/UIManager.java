package io.github.kpgtb.kkui.manager;

import io.github.kpgtb.kkui.ui.Alignment;
import io.github.kpgtb.kkui.ui.BaseUI;
import net.md_5.bungee.api.ChatMessageType;
import net.md_5.bungee.api.chat.TextComponent;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.scheduler.BukkitTask;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.UUID;

public class UIManager {
    private final HashMap<UUID, ArrayList<BaseUI>> ui = new HashMap<>();
    private final HashMap<UUID, ArrayList<String>> standardActionBars = new HashMap<>();

    private final JavaPlugin plugin;

    public UIManager(JavaPlugin plugin) {
        this.plugin = plugin;

        runActionBar();
    }

    private boolean sending = false;

    private void runActionBar() {
        BukkitTask task = new BukkitRunnable() {
            @Override
            public void run() {
                for(Player player : Bukkit.getOnlinePlayers()) {
                    String actionBarText = "";

                    if(ui.containsKey(player.getUniqueId())) {
                        for(BaseUI baseUI : ui.get(player.getUniqueId())) {
                            actionBarText = actionBarText + baseUI.getTextToShow();
                        }
                    }

                    if(standardActionBars.containsKey(player.getUniqueId())) {
                        String text = "";
                        for(String s : standardActionBars.get(player.getUniqueId())) {
                            text = s;
                        }

                        BaseUI baseUI = new BaseUI(text, Alignment.CENTER, 0);
                        actionBarText = actionBarText + baseUI.getTextToShow();
                    }

                    if(!actionBarText.equalsIgnoreCase("")) {
                        sending = true;
                        player.spigot().sendMessage(ChatMessageType.ACTION_BAR, TextComponent.fromLegacyText(actionBarText));
                        sending = false;
                    }
                }
            }
        }.runTaskTimer(plugin, plugin.getConfig().getInt("actionBarRefreshRate"),plugin.getConfig().getInt("actionBarRefreshRate"));
    }

    public boolean isSending() {
        return sending;
    }

    public void addUI(UUID uuid, BaseUI baseUI) {
        if(!ui.containsKey(uuid)) {
            ui.put(uuid, new ArrayList<>());
        }

        ArrayList<BaseUI> uis = ui.get(uuid);
        uis.add(baseUI);

        ui.replace(uuid, uis);
    }
    public void removeUI(UUID uuid, BaseUI baseUI) {
        if(!ui.containsKey(uuid)) {
            ui.put(uuid, new ArrayList<>());
        }

        ArrayList<BaseUI> uis = ui.get(uuid);
        uis.remove(baseUI);

        ui.replace(uuid, uis);
    }

    public void addActionBar(UUID uuid, String text, int time) {
        if(!standardActionBars.containsKey(uuid)) {
            standardActionBars.put(uuid, new ArrayList<>());
        }

        ArrayList<String> actionBars = standardActionBars.get(uuid);
        actionBars.add(text);

        standardActionBars.replace(uuid, actionBars);

        BukkitTask task = new BukkitRunnable() {
            @Override
            public void run() {
                removeActionBar(uuid, text);
            }
        }.runTaskLater(plugin, time);
    }
    public void removeActionBar(UUID uuid, String text) {
        if(!standardActionBars.containsKey(uuid)) {
            standardActionBars.put(uuid, new ArrayList<>());
        }

        ArrayList<String> actionbars = standardActionBars.get(uuid);
        actionbars.remove(text);

        standardActionBars.replace(uuid, actionbars);
    }

    public ArrayList<BaseUI> getUI(UUID uuid) {
        if(!ui.containsKey(uuid)) {
            return new ArrayList<>();
        }

        return ui.get(uuid);
    }

    public ArrayList<String> getStandardActionBars(UUID uuid) {
        if(!standardActionBars.containsKey(uuid)) {
            return new ArrayList<>();
        }

        return standardActionBars.get(uuid);
    }
}
