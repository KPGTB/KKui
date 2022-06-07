package io.github.kpgtb.kkui.ui;

public class BaseUI {

    private String text;
    private final Alignment alignment;
    private final int offset;
    private int width;

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

        for(int i = 0; i < pixels[0]; i++) {
            finalText = leftPixelsChar + finalText;
        }
        for(int i = 0; i < pixels[1]; i++) {
            finalText = finalText + rightPixelsChar;
        }

        textToShow = finalText;
    }

    private void update(String text) {
        this.text = text;
        build();
    }

    private Integer[] getLeftAndRightPixels() {
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
