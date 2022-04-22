package ch.module.cardgame.graphics.card;

import ch.module.cardgame.graphics.utils.ColorPalette;
import ch.module.cardgame.graphics.utils.DimensionPresets;
import ch.module.cardgame.graphics.utils.ImageLibrary;

import javax.swing.*;
import java.awt.*;

public class VisualCard extends JPanel {
    public VisualCard() {
        setPreferredSize(DimensionPresets.CARD_DIMENSIONS);
        setBackground(ColorPalette.CARD_BACKGROUND_COLOR);
        setMaximumSize(DimensionPresets.CARD_DIMENSIONS);
        setVisible(true);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.drawImage(ImageLibrary.CARD_BACKGROUND.getImage(), 0,0, null);
    }
}
