package ch.module.cardgame.graphics.playfield;

import ch.module.cardgame.graphics.utils.ColorPalette;
import ch.module.cardgame.graphics.utils.DimensionPresets;

import javax.swing.*;
import java.awt.*;

public class EndTurnButton extends JButton {
    public EndTurnButton() {
        setBackground(ColorPalette.ACTIVE_ACTION);
        setSize(DimensionPresets.BUTTON_DIMENSIONS);
        setPreferredSize(DimensionPresets.BUTTON_DIMENSIONS);
        setMaximumSize(DimensionPresets.BUTTON_DIMENSIONS);
        setForeground(Color.WHITE);
        setFocusPainted(false);
        setText("End Turn");
        setVisible(true);
    }
}
