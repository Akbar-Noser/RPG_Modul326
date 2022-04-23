package ch.module.cardgame.graphics.playfield;

import ch.module.cardgame.graphics.Board;
import ch.module.cardgame.graphics.cardfield.VisualCardFieldRow;
import ch.module.cardgame.graphics.hand.VisualHand;
import ch.module.cardgame.graphics.mediator.HandFieldMediator;
import ch.module.cardgame.graphics.player.VisualPlayerStats;
import ch.module.cardgame.player.Player;

import javax.swing.*;

public class VisualPlayerSide {
    private final Player owner;
    private final Board board;
    private final VisualPlayerStats stats;
    private final VisualHand hand;
    private final VisualCardFieldRow cardFieldRow;
    private final HandFieldMediator handFieldMediator;

    public VisualPlayerSide(Player owner, Board board) {
        this.board = board;
        this.owner = owner;
        handFieldMediator = new HandFieldMediator();
        stats = new VisualPlayerStats(owner);
        hand = new VisualHand(owner, handFieldMediator);
        handFieldMediator.setVisualHand(hand);
        cardFieldRow = new VisualCardFieldRow(handFieldMediator);
        handFieldMediator.setFieldRow(cardFieldRow);
    }

    public void renderBottomUp() {
        board.add(stats);
        board.add(Box.createVerticalGlue());
        board.add(hand);
        board.add(Box.createVerticalGlue());
        board.add(cardFieldRow);
    }

    public void renderTopDown() {
        board.add(cardFieldRow);
        board.add(Box.createVerticalGlue());
        board.add(hand);
        board.add(Box.createVerticalGlue());
        board.add(stats);
    }
}
