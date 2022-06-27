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

package io.github.kpgtb.kkui.packetListener;

import com.comphenix.protocol.PacketType;
import com.comphenix.protocol.ProtocolManager;
import com.comphenix.protocol.events.PacketAdapter;
import com.comphenix.protocol.events.PacketContainer;
import com.comphenix.protocol.events.PacketEvent;
import com.comphenix.protocol.utility.MinecraftVersion;
import com.comphenix.protocol.wrappers.EnumWrappers;
import io.github.kpgtb.kkui.KKui;
import net.md_5.bungee.chat.ComponentSerializer;

public class PacketSendingListener {
    private final ProtocolManager protocolManager;
    private final PacketAdapter packetAdapter;

    public PacketSendingListener(KKui plugin, ProtocolManager protocolManager) {
        this.protocolManager = protocolManager;
        if (MinecraftVersion.atOrAbove(new MinecraftVersion("1.17"))) {
            packetAdapter = new Manager_1_17(plugin);
        } else {
            packetAdapter = new Manager_1_16(plugin);
        }
        protocolManager.addPacketListener(packetAdapter);
    }

    public void disable() { protocolManager.removePacketListener(packetAdapter); }

    private static class Manager_1_17 extends PacketAdapter {
        public Manager_1_17(KKui plugin) {
            super(plugin, PacketType.Play.Server.SET_ACTION_BAR_TEXT);
        }

        @Override
        public void onPacketSending(PacketEvent event) {
            if (event.isCancelled() || KKui.getUiManager().isSending()) { return; }
            KKui.getUiManager().addActionBar(
                    event.getPlayer().getUniqueId(),
                    ComponentSerializer.parse(event.getPacket().getChatComponents().read(0).getJson())[0].toLegacyText(),
                    plugin.getConfig().getInt("basicActionBarDuration")
                );
            event.setCancelled(true);
        }
    }

    private static class Manager_1_16 extends PacketAdapter {
        public Manager_1_16(KKui plugin) {
            super(plugin, PacketType.Play.Server.TITLE);
        }

        @Override
        public void onPacketSending(PacketEvent event) {
            if (event.isCancelled() || KKui.getUiManager().isSending()) { return; }
            PacketContainer packet = event.getPacket();
            if (!packet.getTitleActions().read(0).equals(EnumWrappers.TitleAction.ACTIONBAR)) { return; }
            KKui.getUiManager().addActionBar(
                    event.getPlayer().getUniqueId(),
                    ComponentSerializer.parse(event.getPacket().getChatComponents().read(0).getJson())[0].toLegacyText(),
                    plugin.getConfig().getInt("basicActionBarDuration")
            );
            event.setCancelled(true);
        }
    }
}
