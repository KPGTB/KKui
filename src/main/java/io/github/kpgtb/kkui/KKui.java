package io.github.kpgtb.kkui;

import com.comphenix.protocol.ProtocolLibrary;
import com.comphenix.protocol.ProtocolManager;
import io.github.kpgtb.kkcore.manager.UsefulObjects;
import io.github.kpgtb.kkcore.manager.listener.ListenerManager;
import io.github.kpgtb.kkcore.util.MessageUtil;
import io.github.kpgtb.kkui.manager.UIManager;
import io.github.kpgtb.kkui.packetListener.PacketSendingListener;
import io.github.kpgtb.kkui.ui.FontWidth;
import org.bukkit.plugin.java.JavaPlugin;

public final class KKui extends JavaPlugin {

    private MessageUtil messageUtil;
    private PacketSendingListener packetSendingListener;
    private static UIManager uiManager;

    @Override
    public void onEnable() {
        saveDefaultConfig();
        messageUtil = new MessageUtil("&2KKui&r");

        messageUtil.sendInfoToConsole("Enabling plugin KKui created by KPG-TB");

        FontWidth.initWidth(getConfig());

        uiManager = new UIManager(this);

        ProtocolManager protocolManager = ProtocolLibrary.getProtocolManager();
        packetSendingListener = new PacketSendingListener(this, protocolManager);

        ListenerManager listenerManager = new ListenerManager(
                getFile(),
                this,
                new UsefulObjects(messageUtil, null, null, getConfig())
        );
        listenerManager.registerListeners("io.github.kpgtb.kkui.listener");
    }

    @Override
    public void onDisable() {
        messageUtil.sendInfoToConsole("Disabling plugin KKui created by KPG-TB");
    }

    public static UIManager getUiManager() {
        return uiManager;
    }
}
