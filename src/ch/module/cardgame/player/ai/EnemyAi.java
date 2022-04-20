package ch.module.cardgame.player.ai;

import ch.module.cardgame.mediator.PlayerPlayFieldMediator;
import ch.module.cardgame.player.Player;

public class EnemyAi extends Player {
    private ChoiceMaker choiceMaker;

    public EnemyAi(PlayerPlayFieldMediator playerPlayFieldMediator) {
        setPlayerPlayFieldMediator(playerPlayFieldMediator);
        this.choiceMaker = new ChoiceMaker(this);
    }

    @Override
    public void endTurn() {
        makeChoice();
        playerPlayFieldMediator.endTurn(this);
    }

    private void makeChoice() {
        drawCard();
    }



}
