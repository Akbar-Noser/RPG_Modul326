package ch.module.cardgame.player.ai;

import ch.module.cardgame.player.Player;

public class EnemyAi extends Player {
    @Override
    public void endTurn() {
        makeChoice();
        playerPlayFieldMediator.endTurn(this);
    }

    private void makeChoice() {
        drawCard();
    }


}
