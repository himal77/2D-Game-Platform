package com.mountech.window;

import com.mountech.Game;

import javax.swing.*;
import java.awt.*;

public class Window {

    public static JFrame frame;

    public Window(int width, int height, String title, Game game){
        game.setPreferredSize(new Dimension(width, height));
        frame = new JFrame(title);
        frame.add(game);
        frame.pack();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setResizable(false);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);

        game.start();
    }
}
