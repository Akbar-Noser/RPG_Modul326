package ch.module.cardgame.graphics.utils;

import java.awt.*;

public class DimensionPresets {
    public static final Dimension CARD_DIMENSIONS = new Dimension(100, 125);
    public static final Dimension WHITE_SPACE = new Dimension((int) DimensionPresets.CARD_DIMENSIONS.getWidth() * 3,
            (int) DimensionPresets.CARD_DIMENSIONS.getHeight());
    public static final Dimension BUTTON_DIMENSIONS = new Dimension((int) DimensionPresets.CARD_DIMENSIONS.getWidth(),
            40);

    private DimensionPresets() {
    }
}
