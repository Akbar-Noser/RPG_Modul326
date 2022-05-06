package ch.module.cardgame.graphics.card;

import ch.module.cardgame.card.Card;
import ch.module.cardgame.graphics.text.NumberIndicator;
import ch.module.cardgame.graphics.utils.ColorPalette;
import ch.module.cardgame.graphics.utils.DimensionPresets;

import javax.swing.*;
import java.awt.*;

public class VisualCardStats extends JPanel {
    private Card card;

    public VisualCardStats(Card card) {
        this.card = card;
        setLayout(new BoxLayout(this, BoxLayout.X_AXIS));
        setOpaque(false);
        visualizeStats();
        setPreferredSize(new Dimension((int) DimensionPresets.CARD_DIMENSIONS.getWidth() - 10,
                (int) (DimensionPresets.CARD_DIMENSIONS.getHeight() / 4)));
        setVisible(true);
    }

    private void visualizeStats() {
        Font font = new Font(Font.MONOSPACED, Font.PLAIN, 16);
        NumberIndicator health = new NumberIndicator(card.getHealthPoints() + "");
        health.setForeground(ColorPalette.HEALTH);
        health.setFont(font);
        NumberIndicator energy = new NumberIndicator(card.getSummonEnergyPoints() + "");
        energy.setForeground(ColorPalette.ENERGY);
        energy.setFont(font);
        NumberIndicator attack = new NumberIndicator(card.getAttackPoints() + "");
        attack.setForeground(ColorPalette.ATTACK);
        attack.setFont(font);
        add(Box.createHorizontalGlue());
        add(attack);
        add(Box.createHorizontalGlue());
        add(health);
        add(Box.createHorizontalGlue());
        add(energy);
        add(Box.createHorizontalGlue());
    }
}
