package ch.module.cardgame.player.ai;

import ch.module.cardgame.mediator.PlayerPlayFieldMediator;
import ch.module.cardgame.player.Player;

public class EnemyAi extends Player {
    private ChoiceMaker choiceMaker;

    public EnemyAi(PlayerPlayFieldMediator playerPlayFieldMediator) {
        super(playerPlayFieldMediator);
        playerPlayFieldMediator.setPlayerAI(this);
        this.choiceMaker = new ChoiceMaker(this);
    }

    @Override
    public void endTurn() {
        super.endTurn();
        makeChoice();
        playerPlayFieldMediator.endTurn(this);
    }

    private void makeChoice() {
        drawCard();
        Choice optimalChoice = choiceMaker.getOptimalChoice();
        if (optimalChoice != null)
            optimalChoice.getCardsToBePlayed().forEach((index, card) -> playCard(card, index));
    }

    public ChoiceMaker getChoiceMaker() {
        return choiceMaker;
    }
}
