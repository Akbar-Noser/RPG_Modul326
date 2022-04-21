package ch.module.cardgame;

import ch.module.cardgame.graphics.Board;
import ch.module.cardgame.mediator.PlayerPlayFieldMediator;
import ch.module.cardgame.player.ai.EnemyAi;
import ch.module.cardgame.player.user.User;
import ch.module.cardgame.player.Player;

import javax.swing.*;
import java.awt.*;

public class Application extends JFrame {

    public Application() {
        initUI();
    }

    private void initUI() {
        add(new Board());

        setSize(250, 200);

        setTitle("Cards of Cyberpunk 2077");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
    }

    public static void main(String[] args) {
        //Temporary players
        PlayerPlayFieldMediator mediator = new PlayerPlayFieldMediator();
        Player user = new User(mediator);
        Player ai = new EnemyAi(mediator);
        mediator.setPlayerAI(ai);
        mediator.setPlayerUser(user);

        EventQueue.invokeLater(() -> {
            Application ex = new Application();
            ex.setVisible(true);
        });
    }

}
