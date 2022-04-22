package ch.module.cardgame.graphics.hand;

import ch.module.cardgame.graphics.card.VisualCard;
import ch.module.cardgame.graphics.deck.VisualDeck;
import ch.module.cardgame.graphics.utils.DimensionPresets;
import ch.module.cardgame.player.Player;
import ch.module.cardgame.player.PlayerHand;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.stream.Stream;

public class VisualHand extends JPanel {
    private final ArrayList<VisualCard> cards;
    private final Player owner;

    public VisualHand(Player owner) {
        this.owner = owner;
        cards = new ArrayList<>(Stream.generate(VisualCard::new).limit(PlayerHand.getMAX_CARDS()).toList());
        setLayout(new BoxLayout(this, BoxLayout.X_AXIS));
        setBorder(BorderFactory.createEmptyBorder(10, 0, 10, 0));
        visualizeHand();
        setVisible(true);
    }

    private void visualizeHand() {
        add(Box.createRigidArea(new Dimension((int) (DimensionPresets.CARD_DIMENSIONS.getWidth() / 2),
                (int) DimensionPresets.CARD_DIMENSIONS.getHeight())));
        add(new VisualDeck(owner), Component.LEFT_ALIGNMENT);
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
}
