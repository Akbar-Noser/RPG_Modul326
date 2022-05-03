package ch.module.cardgame.player.user;

import ch.module.cardgame.mediator.PlayerPlayFieldMediator;
import ch.module.cardgame.player.Player;

public class User extends Player {
    public User(PlayerPlayFieldMediator mediator) {
        super(mediator);
        playerPlayFieldMediator.setPlayerUser(this);
    }

    @Override
    public void finalizeEndTurn() {
        playerPlayFieldMediator.endTurn(this);
    }
}
