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

package io.github.kpgtb.kkui.ui;

import org.bukkit.configuration.file.FileConfiguration;

import java.util.HashMap;

public class FontWidth {
    public static HashMap<Character, Integer> customWidths = new HashMap<>();

    public static void initWidth(FileConfiguration config) {
        customWidths.put(' ', 4);
        customWidths.put('f', 5);
        customWidths.put('t', 4);
        customWidths.put('i', 2);
        customWidths.put('k', 5);
        customWidths.put('l', 3);
        customWidths.put('I', 4);
        customWidths.put('\'', 2);
        customWidths.put('.', 2);
        customWidths.put(',', 2);
        customWidths.put(';', 2);
        customWidths.put(':', 2);
        customWidths.put('[', 4);
        customWidths.put(']', 4);
        customWidths.put('{', 4);
        customWidths.put('}', 4);
        customWidths.put('*', 4);
        customWidths.put('!', 2);
        customWidths.put('"', 4);
        customWidths.put('(', 4);
        customWidths.put(')', 4);
        customWidths.put('|', 2);
        customWidths.put('`', 3);
        customWidths.put('<', 5);
        customWidths.put('>', 5);
        customWidths.put('@',7 );
        customWidths.put('~', 7);

        customWidths.put('\uF801', -1);
        customWidths.put('\uF802', -2);
        customWidths.put('\uF803', -3);
        customWidths.put('\uF804', -4);
        customWidths.put('\uF805', -5);
        customWidths.put('\uF806', -6);
        customWidths.put('\uF807', -7);
        customWidths.put('\uF808', -8);
        customWidths.put('\uF809', -16);
        customWidths.put('\uF80A', -32);
        customWidths.put('\uF80B', -64);
        customWidths.put('\uF80C', -128);
        customWidths.put('\uF80D', -256);
        customWidths.put('\uF80E', -512);
        customWidths.put('\uF80F', -1024);

        customWidths.put('\uF821', 1);
        customWidths.put('\uF822', 2);
        customWidths.put('\uF823', 3);
        customWidths.put('\uF824', 4);
        customWidths.put('\uF825', 5);
        customWidths.put('\uF826', 6);
        customWidths.put('\uF827', 7);
        customWidths.put('\uF828', 8);
        customWidths.put('\uF829', 16);
        customWidths.put('\uF82A', 32);
        customWidths.put('\uF82B', 64);
        customWidths.put('\uF82C', 128);
        customWidths.put('\uF82D', 256);
        customWidths.put('\uF82E', 512);
        customWidths.put('\uF82F', 1024);

        for(String key : config.getConfigurationSection("customFontWidth").getKeys(false)) {
            customWidths.put(key.charAt(0), config.getInt("customFontWidth."+key));
        }
    }

    public static void registerCustomChar(Character character, int width) {
        if(customWidths.containsKey(character)) {
            customWidths.remove(character);
        }

        customWidths.put(character, width);
    }

    public static Integer getWidth(Character character) {
        return customWidths.getOrDefault(character, 6);
    }
}
