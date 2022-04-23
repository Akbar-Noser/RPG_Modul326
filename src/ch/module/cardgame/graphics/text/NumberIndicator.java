package ch.module.cardgame.graphics.text;

import javax.swing.*;
import java.awt.*;

public class NumberIndicator extends JLabel {
    public NumberIndicator(String text) {
        super(text);
        setSize(100, 80);
        setFont(new Font(Font.DIALOG, Font.PLAIN, 24));
        setVisible(true);
    }
}
