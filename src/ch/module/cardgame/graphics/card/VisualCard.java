package ch.module.cardgame.graphics.card;

import ch.module.cardgame.card.Card;
import ch.module.cardgame.graphics.utils.ColorPalette;
import ch.module.cardgame.graphics.utils.DimensionPresets;
import ch.module.cardgame.graphics.utils.ImageLibrary;

import javax.swing.*;
import java.awt.*;

public class VisualCard extends JPanel {
    private Card card;
    private VisualCardStats visualCardStats;
    private boolean showCardContent;

    public VisualCard(Card card) {
        this.card = card;
        showCardContent = true;
        visualCardStats = new VisualCardStats(card);
        setPreferredSize(DimensionPresets.CARD_DIMENSIONS);
        setLayout(new BorderLayout());
        setBackground(ColorPalette.CARD_BACKGROUND_COLOR);
        setMaximumSize(DimensionPresets.CARD_DIMENSIONS);
        add(visualCardStats, BorderLayout.PAGE_END);
        setVisible(true);
    }

    /**
     * Only shows backside of card and no stats or images are shown.
     */
    public void flipCard() {
        removeAll();
        showCardContent = false;
        repaint();
        revalidate();
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        if (showCardContent)
            g.drawImage(ImageLibrary.CARD_BACKGROUND.getImage(), 0,0, null);
    }

    public Card getCard() {
        return card;
    }
}
