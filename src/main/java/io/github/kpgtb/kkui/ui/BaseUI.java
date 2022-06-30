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

import org.bukkit.Bukkit;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class BaseUI {

    private String text;
    private final Alignment alignment;
    private final int offset;

    private String textToShow;


    public BaseUI(String text, Alignment alignment, int offset) {
        this.text = text;
        this.alignment = alignment;
        this.offset = offset;

        build();
    }

    private void build() {
        Integer[] pixels = getLeftAndRightPixels();

        /*String finalText = text;

        String leftPixelsChar = pixels[0] < 0 ? "\uF801" : "\uF821";
        String rightPixelsChar = pixels[1] < 0 ? "\uF801" : "\uF821";

        for(int i = 0; i < Math.abs(pixels[0]); i++) {
            finalText = leftPixelsChar + finalText;
        }
        for(int i = 0; i < Math.abs(pixels[1]); i++) {
            finalText = finalText + rightPixelsChar;
        }

        textToShow = finalText;*/
        textToShow = FontWidth.getSpaces(pixels[0]) + text + FontWidth.getSpaces(pixels[1]);
    }

    public void update(String text) {
        this.text = text;
        build();
    }

    private static String fixString(String text) {
        String fixed = text;

        Pattern colorPattern = Pattern.compile("§[a-fA-F0-9]");
        Matcher colorMatcher = colorPattern.matcher(text);
        while (colorMatcher.find()) {
            fixed = fixed.replace(colorMatcher.group(), "");
        }

        if(Integer.parseInt(
                Bukkit.getBukkitVersion()
                        .split("-")[0] // ex. 1.17
                        .split("\\.")[1] // ex. 17
        ) >= 16) {
            Pattern hexPattern = Pattern.compile("§[a-fA-F0-9]{6}");
            Matcher hexMatcher = hexPattern.matcher(text);
            while (hexMatcher.find()) {
                fixed = fixed.replace(hexMatcher.group(), "");
            }
         }
        return fixed;
    }

    private Integer[] getLeftAndRightPixels() {
        int width = 0;

        for(Character character : fixString(text).toCharArray()) {
            width += FontWidth.getWidth(character);
        }

        Integer[] pixels = new Integer[2];

        switch (alignment) {
            case LEFT:
                pixels[0] = offset;
                pixels[1] = -offset-width;
                break;
            case RIGHT:
                pixels[0] = offset-width;
                pixels[1] = -offset;
                break;
            case CENTER:
                pixels[0] = offset-width/2;
                pixels[1] = -pixels[0]-width;
                break;
        }

        return pixels;
    }


    public String getTextToShow() {
        return textToShow;
    }
}
