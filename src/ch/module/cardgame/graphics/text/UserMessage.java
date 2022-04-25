package ch.module.cardgame.graphics.text;

import javax.swing.*;
import java.awt.*;

public class UserMessage extends JLabel {
    public UserMessage(String text) {
        super(text);
        setHorizontalAlignment(SwingConstants.CENTER);
        setVerticalAlignment(SwingConstants.CENTER);
        setSize(100, 80);
        setFont(new Font(Font.DIALOG, Font.PLAIN, 24));
        setVisible(true);
    }
}
