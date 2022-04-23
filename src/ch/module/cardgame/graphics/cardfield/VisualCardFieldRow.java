package ch.module.cardgame.graphics.cardfield;

import ch.module.cardgame.field.PlayField;
import ch.module.cardgame.graphics.mediator.HandFieldMediator;
import ch.module.cardgame.graphics.utils.DimensionPresets;

import javax.swing.*;
import java.util.ArrayList;
import java.util.stream.Stream;

public class VisualCardFieldRow extends JPanel {
    private final ArrayList<VisualCardField> fields;
    private final HandFieldMediator handFieldMediator;

    public VisualCardFieldRow(HandFieldMediator handFieldMediator) {
        this.handFieldMediator = handFieldMediator;
        setLayout(new BoxLayout(this, BoxLayout.X_AXIS));
        fields = new ArrayList<>(Stream.generate(VisualCardField::new).limit(PlayField.getMaxAmountCardFields()).toList());
        visualizeRow();
        setVisible(true);
    }

    private void visualizeRow() {
        add(Box.createRigidArea(DimensionPresets.WHITE_SPACE));
        add(Box.createHorizontalGlue());
        fields.forEach(visualCardField -> {
            add(visualCardField);
            add(Box.createHorizontalGlue());
        });
        add(Box.createRigidArea(DimensionPresets.WHITE_SPACE));
    }
}
