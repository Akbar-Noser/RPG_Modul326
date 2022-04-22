package ch.module.cardgame.graphics.cardfield;

import ch.module.cardgame.graphics.utils.ColorPalette;
import ch.module.cardgame.graphics.utils.DimensionPresets;

import javax.swing.*;
import java.awt.*;

public class VisualCardField extends JPanel {
    public VisualCardField() {
        setBackground(ColorPalette.EMPTY_CARD_FIELD);
        setPreferredSize(DimensionPresets.CARD_DIMENSIONS);
        setMaximumSize(DimensionPresets.CARD_DIMENSIONS);
        setVisible(true);
    }
}
