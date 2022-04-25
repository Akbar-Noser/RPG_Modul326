package ch.module.cardgame.graphics.hand;

import ch.module.cardgame.graphics.card.VisualCard;
import ch.module.cardgame.graphics.deck.VisualDeck;
import ch.module.cardgame.graphics.mediator.DeckHandMediator;
import ch.module.cardgame.graphics.mediator.HandFieldMediator;
import ch.module.cardgame.graphics.playfield.EndTurnButton;
import ch.module.cardgame.graphics.utils.ColorPalette;
import ch.module.cardgame.graphics.utils.DimensionPresets;
import ch.module.cardgame.player.Player;
import ch.module.cardgame.player.ai.EnemyAi;
import ch.module.cardgame.player.user.User;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.List;

public class VisualHand extends JPanel {
    private List<VisualCard> cards;
    private final Player owner;
    private final VisualDeck deck;
    private final Border activeCardBorder = BorderFactory.createLineBorder(ColorPalette.ACTIVE_ACTION, 5);
    private DeckHandMediator deckHandMediator;
    private final HandFieldMediator handFieldMediator;

    public VisualHand(Player owner, HandFieldMediator handFieldMediator) {
        this.owner = owner;
        this.handFieldMediator = handFieldMediator;
        this.deckHandMediator = new DeckHandMediator();
        this.deck = new VisualDeck(owner, deckHandMediator);
        deckHandMediator.setHand(this);
        deckHandMediator.setDeck(deck);
        cards = new ArrayList<>(owner.getHand().getCards().stream().map(VisualCard::new).toList());
        setLayout(new BoxLayout(this, BoxLayout.X_AXIS));
        visualizeHand();
        setVisible(true);
    }

    private void visualizeHand() {
        Dimension smallWhiteSpace = new Dimension((int) (DimensionPresets.CARD_DIMENSIONS.getWidth() / 2),
                (int) DimensionPresets.CARD_DIMENSIONS.getHeight());
        add(Box.createRigidArea(new Dimension((int) (DimensionPresets.CARD_DIMENSIONS.getWidth() / 2),
                (int) DimensionPresets.CARD_DIMENSIONS.getHeight())));
        add(deck);
        Dimension whiteSpace = new Dimension((int) (DimensionPresets.WHITE_SPACE.getWidth() / 2),
                (int) DimensionPresets.CARD_DIMENSIONS.getHeight());
        add(Box.createRigidArea(whiteSpace));
        add(Box.createHorizontalGlue());
        cards.forEach(visualCard -> {
            visualCard.addMouseListener(cardOnClickListener(visualCard));
            if (isEqualToActiveCard(visualCard))
                visualCard.setBorder(activeCardBorder);
            add(visualCard);
            add(Box.createHorizontalGlue());
        });
        if (owner instanceof User) {
            add(Box.createRigidArea(smallWhiteSpace));
            add(new EndTurnButton(owner));
            add(Box.createRigidArea(whiteSpace));
        }
        else
            add(Box.createRigidArea(DimensionPresets.WHITE_SPACE));
    }

    private MouseAdapter cardOnClickListener(VisualCard card) {
        return owner instanceof EnemyAi ? null : new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (isEqualToActiveCard(card))
                    handFieldMediator.setActiveCard(null);
                else
                    handFieldMediator.setActiveCard(card);
                rerender();
            }
        };
    }

    private boolean isEqualToActiveCard(VisualCard visualCard) {
        return handFieldMediator.getActiveCard() != null &&
                visualCard.getCard().equals(handFieldMediator.getActiveCard().getCard());
    }

    public void rerender() {
        cards = owner.getHand().getCards().stream().map(VisualCard::new).toList();
        removeAll();
        visualizeHand();
        revalidate();
        repaint();
    }

    public void setDeckHandMediator(DeckHandMediator deckHandMediator) {
        this.deckHandMediator = deckHandMediator;
    }
}
