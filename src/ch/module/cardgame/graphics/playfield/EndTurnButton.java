package ch.module.cardgame.graphics.playfield;

import ch.module.cardgame.graphics.Board;
import ch.module.cardgame.graphics.utils.ColorPalette;
import ch.module.cardgame.graphics.utils.DimensionPresets;
import ch.module.cardgame.player.Player;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.concurrent.TimeUnit;

public class EndTurnButton extends JButton {
    private final Player owner;

    public EndTurnButton(Player owner) {
        this.owner = owner;
        setBackground(ColorPalette.ACTIVE_ACTION);
        setSize(DimensionPresets.BUTTON_DIMENSIONS);
        setPreferredSize(DimensionPresets.BUTTON_DIMENSIONS);
        setMaximumSize(DimensionPresets.BUTTON_DIMENSIONS);
        setForeground(Color.WHITE);
        setFocusPainted(false);
        setText("End Turn");
        addActionListener(getOnClickFunction());
        setVisible(true);
    }

    private ActionListener getOnClickFunction() {
        return e -> {
            owner.endTurn();
            owner.getPlayerPlayFieldMediator().getOtherPlayer(owner).endTurn();
            Board.getInstance().rerender();
        };
    }
}
