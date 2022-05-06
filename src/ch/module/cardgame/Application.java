package ch.module.cardgame;

import ch.module.cardgame.graphics.GameWindow;
import ch.module.cardgame.mediator.PlayerPlayFieldMediator;
import ch.module.cardgame.player.ai.EnemyAi;
import ch.module.cardgame.player.user.User;
import ch.module.cardgame.player.Player;

public class Application {

    public static void main(String[] args) {
        //Temporary players
        PlayerPlayFieldMediator mediator = new PlayerPlayFieldMediator();
        Player user = new User(mediator);
        Player ai = new EnemyAi(mediator);
        mediator.setPlayerAI(ai);
        mediator.setPlayerUser(user);
        new GameWindow();
    }

}
