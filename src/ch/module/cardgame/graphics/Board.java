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
    private Player playerUser;
    private VisualPlayerSide userPlayerSide;
    private Player playerAi;
    private VisualPlayerSide aiPlayerSide;

    public Board() {
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

    public void generateBoard() {
        userPlayerSide.renderBottomUp();
        add(Box.createVerticalGlue());
        aiPlayerSide.renderTopDown();
    }

    private void drawDonut(Graphics g) {

        Graphics2D g2d = (Graphics2D) g;

        RenderingHints rh
                = new RenderingHints(RenderingHints.KEY_ANTIALIASING,
                RenderingHints.VALUE_ANTIALIAS_ON);

        rh.put(RenderingHints.KEY_RENDERING,
                RenderingHints.VALUE_RENDER_QUALITY);

        g2d.setRenderingHints(rh);

        Dimension size = getSize();
        double w = size.getWidth();
        double h = size.getHeight();

        Ellipse2D e = new Ellipse2D.Double(0, 0, 80, 130);
        g2d.setStroke(new BasicStroke(1));
        g2d.setColor(Color.gray);

        for (double deg = 0; deg < 360; deg += 5) {
            AffineTransform at
                    = AffineTransform.getTranslateInstance(w/2, h/2);
            at.rotate(Math.toRadians(deg));
            g2d.draw(at.createTransformedShape(e));
        }
    }
    /**
     * Testing purposes
     */

}
