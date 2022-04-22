package ch.module.cardgame.graphics.player;

import ch.module.cardgame.graphics.text.NumberIndicator;
import ch.module.cardgame.graphics.utils.ColorPalette;
import ch.module.cardgame.player.Player;

import javax.swing.*;
import java.awt.*;

public class VisualStats extends JPanel {
    private final Player owner;

    public VisualStats(Player owner) {
        this.owner = owner;
        visualizeStats();
        setVisible(true);
    }

    private void visualizeStats() {
        NumberIndicator health = new NumberIndicator(owner.getStats().getHealth() + "");
        health.setForeground(ColorPalette.HEALTH);
        NumberIndicator energy = new NumberIndicator(owner.getStats().getEnergy() + "");
        energy.setForeground(ColorPalette.ENERGY);
        add(health, BorderLayout.WEST);
        add(energy, BorderLayout.EAST);

    }
}
