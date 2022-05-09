package ch.module.cardgame.graphics.player;

import ch.module.cardgame.graphics.text.NumberIndicator;
import ch.module.cardgame.graphics.utils.ColorPalette;
import ch.module.cardgame.graphics.utils.ImageLibrary;
import ch.module.cardgame.player.Player;

import javax.swing.*;
import java.awt.*;

public class VisualPlayerStats extends JPanel {
    private final JPanel container;
    private final Player owner;
    private final NumberIndicator health;
    private final NumberIndicator energy;

    public VisualPlayerStats(Player owner) {
        this.container = new JPanel();
        this.owner = owner;
        health = new NumberIndicator(owner.getStats().getHealth() + "");
        energy = new NumberIndicator(owner.getStats().getEnergy() + "");
        health.setForeground(ColorPalette.HEALTH);
        energy.setForeground(ColorPalette.ENERGY);
        adjustLayout();
        visualizeStats();
        setVisible(true);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.drawImage(ImageLibrary.PLAYER_HEALTH_BAR.getImage(), 0,0, null);
        g.drawImage(ImageLibrary.SUMMON_ENERGY_BAR.getImage(), 270,0, null);
    }

    private void adjustLayout() {
        container.setLayout(new BoxLayout(container, BoxLayout.X_AXIS));
        container.add(Box.createHorizontalGlue());
        container.add(this);
        container.add(Box.createHorizontalGlue());
        setLayout(new BorderLayout());
        setPreferredSize(new Dimension(526, 62));
        setMaximumSize(new Dimension(526, 128));
        Dimension preferredSize = new Dimension(64, 80);
        health.setPreferredSize(preferredSize);
        health.setHorizontalAlignment(SwingConstants.CENTER);
        energy.setPreferredSize(preferredSize);
        energy.setHorizontalAlignment(SwingConstants.CENTER);
    }

    private void visualizeStats() {
        add(health, BorderLayout.WEST);
        add(energy, BorderLayout.EAST);
    }

    public void rerender() {
        health.setText(owner.getStats().getHealth() + "");
        energy.setText(owner.getStats().getEnergy() + "");
        revalidate();
    }
}
