package ch.module.cardgame.graphics.mediator;

import ch.module.cardgame.graphics.card.VisualCard;
import ch.module.cardgame.graphics.cardfield.VisualCardFieldRow;
import ch.module.cardgame.graphics.hand.VisualHand;
import ch.module.cardgame.graphics.player.VisualPlayerStats;

public class HandFieldMediator {
    private VisualHand visualHand;
    private VisualCardFieldRow fieldRow;
    private VisualPlayerStats stats;
    private VisualCard activeCard;

    public VisualCard getActiveCard() {
        return activeCard;
    }

    public void rerender() {
        visualHand.rerender();
        fieldRow.rerender();
        stats.rerender();
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

    public void setStats(VisualPlayerStats stats) {
        this.stats = stats;
    }
}
