package ch.module.cardgame.graphics.cardfield;

import ch.module.cardgame.card.CardField;
import ch.module.cardgame.graphics.card.VisualCard;
import ch.module.cardgame.graphics.utils.ColorPalette;
import ch.module.cardgame.graphics.utils.DimensionPresets;

import javax.swing.*;

public class VisualCardField extends JPanel {
    private final CardField cardField;

    public VisualCardField(CardField cardField) {
        this.cardField = cardField;
        setBackground(ColorPalette.EMPTY_CARD_FIELD);
        setPreferredSize(DimensionPresets.CARD_DIMENSIONS);
        setMaximumSize(DimensionPresets.CARD_DIMENSIONS);
        setVisible(true);
    }

    public void rerender() {
        removeAll();
        if (cardField.getCard() != null) {
            setBackground(ColorPalette.CARD_BACKGROUND_COLOR);
            add(new VisualCard(cardField.getCard()));
        } else
            setBackground(ColorPalette.EMPTY_CARD_FIELD);
        revalidate();
        repaint();
    }
}
