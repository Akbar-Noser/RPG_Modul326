package ch.module.cardgame.player.user;

import ch.module.cardgame.player.Player;

public class User extends Player {
    @Override
    public void endTurn() {
        playerPlayFieldMediator.endTurn(this);
    }
}
