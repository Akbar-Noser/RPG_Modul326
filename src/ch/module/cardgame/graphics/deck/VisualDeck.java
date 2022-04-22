package ch.module.cardgame.graphics.deck;

import ch.module.cardgame.graphics.utils.ColorPalette;
import ch.module.cardgame.graphics.text.NumberIndicator;
import ch.module.cardgame.graphics.utils.DimensionPresets;
import ch.module.cardgame.player.Player;

import javax.swing.*;
import java.awt.*;

public class VisualDeck extends JPanel {
    private final Player owner;
    private final NumberIndicator amountOfCards;

    public VisualDeck(Player owner) {
        this.owner = owner;
        this.amountOfCards = new NumberIndicator(owner.getDeck().getCards().size() + "");
        setBackground(ColorPalette.CARD_BACKGROUND_COLOR);
        setLayout(new GridBagLayout());
        setPreferredSize(DimensionPresets.CARD_DIMENSIONS);
        setMaximumSize(DimensionPresets.CARD_DIMENSIONS);
        add(amountOfCards);
        setVisible(true);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        add(amountOfCards);
    }
}
