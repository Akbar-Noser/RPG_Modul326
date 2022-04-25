package ch.module.cardgame.graphics;

import ch.module.cardgame.field.PlayField;
import ch.module.cardgame.graphics.cardfield.VisualCardFieldRow;
import ch.module.cardgame.graphics.hand.VisualHand;
import ch.module.cardgame.graphics.player.VisualPlayerStats;
import ch.module.cardgame.graphics.playfield.VisualPlayerSide;
import ch.module.cardgame.player.Player;
import ch.module.cardgame.player.ai.EnemyAi;

import javax.swing.*;
import java.awt.*;
import java.awt.geom.AffineTransform;
import java.awt.geom.Ellipse2D;

public class Board extends JPanel {
    private static final Board instance = new Board();
    private Player playerUser;
    private VisualPlayerSide userPlayerSide;
    private Player playerAi;
    private VisualPlayerSide aiPlayerSide;

    private Board() {
        PlayField.getInstance().getCardFields().keySet().forEach(player -> {
            if (player instanceof EnemyAi)
                playerAi = player;
            else
                playerUser = player;
        });
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        userPlayerSide = new VisualPlayerSide(playerUser, this);
        aiPlayerSide = new VisualPlayerSide(playerAi, this);
        generateBoard();
        setVisible(true);
    }

    public static Board getInstance() {
        return instance;
    }

    public void generateBoard() {
        aiPlayerSide.renderBottomUp();
        add(Box.createVerticalGlue());
        userPlayerSide.renderTopDown();
    }

    public void rerender() {
        aiPlayerSide.rerender();
        userPlayerSide.rerender();
        revalidate();
        repaint();
    }
}
