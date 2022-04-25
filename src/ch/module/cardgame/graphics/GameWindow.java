package ch.module.cardgame.graphics;

import javax.swing.*;
import java.awt.*;

public class GameWindow extends JFrame {

    public GameWindow() {
        initUI();
    }

    private void initUI() {
        setLayout(new BorderLayout());
        setPreferredSize(new Dimension(450, 250));
        add(Board.getInstance(), BorderLayout.CENTER);
        setExtendedState(MAXIMIZED_BOTH);
        setTitle("Cards of Cyberpunk 2077");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setVisible(true);
    }
}
