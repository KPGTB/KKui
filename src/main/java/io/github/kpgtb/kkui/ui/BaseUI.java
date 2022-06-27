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

        String finalText = text;

        String leftPixelsChar = pixels[0] < 0 ? "\uF801" : "\uF821";
        String rightPixelsChar = pixels[1] < 0 ? "\uF801" : "\uF821";

        for(int i = 0; i < Math.abs(pixels[0]); i++) {
            finalText = leftPixelsChar + finalText;
        }
        for(int i = 0; i < Math.abs(pixels[1]); i++) {
            finalText = finalText + rightPixelsChar;
        }

        textToShow = finalText;
    }

    public void update(String text) {
        this.text = text;
        build();
    }

    private Integer[] getLeftAndRightPixels() {
        int width = 0;
        for(Character character : text.toCharArray()) {
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
