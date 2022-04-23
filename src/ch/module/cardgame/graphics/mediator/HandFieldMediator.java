package ch.module.cardgame.graphics.mediator;

import ch.module.cardgame.graphics.card.VisualCard;
import ch.module.cardgame.graphics.cardfield.VisualCardFieldRow;
import ch.module.cardgame.graphics.hand.VisualHand;

public class HandFieldMediator {
    private VisualHand visualHand;
    private VisualCardFieldRow fieldRow;
    private VisualCard activeCard;

    public VisualCard getActiveCard() {
        return activeCard;
    }

    public void rerender() {
        visualHand.rerender();
        fieldRow.rerender();
    }

    public void setActiveCard(VisualCard activeCard) {
        this.activeCard = activeCard;
    }

    public void setVisualHand(VisualHand visualHand) {
        this.visualHand = visualHand;
    }

    public void setFieldRow(VisualCardFieldRow fieldRow) {
        this.fieldRow = fieldRow;
    }
}
