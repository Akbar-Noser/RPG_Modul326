package ch.module.cardgame.graphics.cardfield;

import ch.module.cardgame.card.CardField;
import ch.module.cardgame.field.PlayField;
import ch.module.cardgame.graphics.mediator.HandFieldMediator;
import ch.module.cardgame.graphics.utils.DimensionPresets;
import ch.module.cardgame.player.Player;

import javax.swing.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

public class VisualCardFieldRow extends JPanel {
    private final List<VisualCardField> fields;
    private final HandFieldMediator handFieldMediator;
    private final Player owner;

    public VisualCardFieldRow(Player owner,HandFieldMediator handFieldMediator) {
        this.owner = owner;
        this.handFieldMediator = handFieldMediator;
        setLayout(new BoxLayout(this, BoxLayout.X_AXIS));
        fields = getFields();
        visualizeRow();
        setVisible(true);
    }

    private List<VisualCardField> getFields() {
        List<CardField> cardFields = PlayField.getInstance().getCardFields().get(owner);
        List<VisualCardField> visualCardFields = new ArrayList<>(PlayField.getMaxAmountCardFields());
        for (int i = 0; i < cardFields.size(); i++) {
            VisualCardField visualCardField =  new VisualCardField(cardFields.get(i));
            visualCardField.addMouseListener(getCardFieldOnClickFunction(i));
            visualCardFields.add(i, visualCardField);
        }
        return visualCardFields;
    }

    public void rerender() {
        removeAll();
        visualizeRow();
        revalidate();
        repaint();
    }

    private void visualizeRow() {
        add(Box.createRigidArea(DimensionPresets.WHITE_SPACE));
        add(Box.createHorizontalGlue());
        fields.forEach(visualCardField -> {
            visualCardField.rerender();
            add(visualCardField);
            add(Box.createHorizontalGlue());
        });
        add(Box.createRigidArea(DimensionPresets.WHITE_SPACE));
    }

    private MouseAdapter getCardFieldOnClickFunction(int index) {
        return new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (handFieldMediator.getActiveCard() != null) {
                    owner.playCard(handFieldMediator.getActiveCard().getCard(), index);
                    handFieldMediator.rerender();
                }
            }
        };
    }
}
