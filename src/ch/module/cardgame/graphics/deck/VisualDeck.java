package ch.module.cardgame.graphics.deck;

import ch.module.cardgame.graphics.mediator.DeckHandMediator;
import ch.module.cardgame.graphics.utils.ColorPalette;
import ch.module.cardgame.graphics.text.NumberIndicator;
import ch.module.cardgame.graphics.utils.DimensionPresets;
import ch.module.cardgame.player.Player;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class VisualDeck extends JPanel {
    private final Player owner;
    private final NumberIndicator amountOfCards;
    private DeckHandMediator handMediator;

    public VisualDeck(Player owner, DeckHandMediator handMediator) {
        this.handMediator = handMediator;
        this.owner = owner;
        this.amountOfCards = new NumberIndicator(owner.getDeck().getCards().size() + "");
        setBackground(ColorPalette.CARD_BACKGROUND_COLOR);
        setLayout(new GridBagLayout());
        setPreferredSize(DimensionPresets.CARD_DIMENSIONS);
        setMaximumSize(DimensionPresets.CARD_DIMENSIONS);
        add(amountOfCards);
        addMouseListener(getOnClickFunction());
        setVisible(true);
    }

    public void rerender() {
        amountOfCards.setText(owner.getDeck().getCards().size() + "");
        revalidate();
    }

    private MouseAdapter getOnClickFunction() {
        return new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                owner.drawCard();
                handMediator.rerender();
            }
        };
    }

    public void setHandMediator(DeckHandMediator handMediator) {
        this.handMediator = handMediator;
    }
}
