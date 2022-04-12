package ch.module.cardgame;

import ch.module.cardgame.graphics.Board;
import ch.module.cardgame.player.Player;
import ch.module.cardgame.player.PlayerPlayFieldMediator;
import jdk.jfr.Event;

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
        Player user = new Player();
        Player ai = new Player();
        PlayerPlayFieldMediator mediator = new PlayerPlayFieldMediator(ai, user);

        EventQueue.invokeLater(() -> {
            Application ex = new Application();
            ex.setVisible(true);
        });
    }

}
