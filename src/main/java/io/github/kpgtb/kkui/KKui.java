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

package io.github.kpgtb.kkui;

import com.comphenix.protocol.ProtocolLibrary;
import com.comphenix.protocol.ProtocolManager;
import io.github.kpgtb.kkcore.manager.listener.ListenerManager;
import io.github.kpgtb.kkcore.util.MessageUtil;
import io.github.kpgtb.kkui.manager.UIManager;
import io.github.kpgtb.kkui.packetListener.PacketSendingListener;
import io.github.kpgtb.kkui.ui.FontWidth;
import org.bukkit.plugin.java.JavaPlugin;

public final class KKui extends JavaPlugin {

    private MessageUtil messageUtil;
    private static UIManager uiManager;

    @Override
    public void onEnable() {
        saveDefaultConfig();
        messageUtil = new MessageUtil("&2KKui&r");

        messageUtil.sendInfoToConsole("Enabling plugin KKui created by KPG-TB");

        FontWidth.initWidth(getConfig());
        uiManager = new UIManager(this);

        ProtocolManager protocolManager = ProtocolLibrary.getProtocolManager();
        new PacketSendingListener(this, protocolManager);

        ListenerManager listenerManager = new ListenerManager(
                getFile(),
                this,
                new UiUsefulObjects(messageUtil, null, null, getConfig(), this)
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
