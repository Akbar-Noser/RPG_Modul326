package ch.module.cardgame;

import ch.module.cardgame.player.Player;
import ch.module.cardgame.player.PlayerPlayFieldMediator;

public class Main {

    public static void main(String[] args) {

        //Temporary players
        Player user = new Player();
        Player ai = new Player();
        PlayerPlayFieldMediator mediator = new PlayerPlayFieldMediator(ai, user);
    }
}
