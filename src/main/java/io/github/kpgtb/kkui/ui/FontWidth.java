package io.github.kpgtb.kkui.ui;

import org.bukkit.configuration.file.FileConfiguration;

import java.util.HashMap;

public class FontWidth {
    public static HashMap<Character, Integer> customWidths = new HashMap<>();

    public static void initWidth(FileConfiguration config) {
        customWidths.put(' ', 4);
        customWidths.put('f', 5);
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
