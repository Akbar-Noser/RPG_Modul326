package ch.module.cardgame.graphics.hand;

import ch.module.cardgame.graphics.card.VisualCard;
import ch.module.cardgame.graphics.deck.VisualDeck;
import ch.module.cardgame.graphics.mediator.DeckHandMediator;
import ch.module.cardgame.graphics.utils.DimensionPresets;
import ch.module.cardgame.player.Player;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class VisualHand extends JPanel {
    private List<VisualCard> cards;
    private final Player owner;
    private final VisualDeck deck;
    private DeckHandMediator mediator;

    public VisualHand(Player owner) {
        this.owner = owner;
        this.mediator = new DeckHandMediator();
        this.deck = new VisualDeck(owner, mediator);
        mediator.setHand(this);
        mediator.setDeck(deck);
        cards = new ArrayList<>(owner.getHand().getCards().stream().map(VisualCard::new).toList());
        setLayout(new BoxLayout(this, BoxLayout.X_AXIS));
        visualizeHand();
        setVisible(true);
    }

    private void visualizeHand() {
        add(Box.createRigidArea(new Dimension((int) (DimensionPresets.CARD_DIMENSIONS.getWidth() / 2),
                (int) DimensionPresets.CARD_DIMENSIONS.getHeight())));
        add(deck);
        Dimension whiteSpaceRight = DimensionPresets.WHITE_SPACE;
        Dimension whiteSpaceLeft = new Dimension((int) (DimensionPresets.WHITE_SPACE.getWidth() / 2),
                (int) DimensionPresets.CARD_DIMENSIONS.getHeight());
        add(Box.createRigidArea(whiteSpaceLeft));
        add(Box.createHorizontalGlue());
        cards.forEach(visualCard -> {
            add(visualCard);
            add(Box.createHorizontalGlue());
        });
        add(Box.createRigidArea(whiteSpaceRight));
    }

    public void rerender() {
        cards = owner.getHand().getCards().stream().map(VisualCard::new).toList();
        removeAll();
        visualizeHand();
        revalidate();
        repaint();
    }

    public void setMediator(DeckHandMediator mediator) {
        this.mediator = mediator;
    }
}
