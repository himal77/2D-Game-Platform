package com.mountech.window;

import javax.swing.*;
import java.awt.*;

public class Window {

    private int width;
    private int height;
    private String title;

    public Window(int width, int height, String title, Game game){
        game.setPreferredSize(new Dimension(width, height));
        JFrame frame = new JFrame(title);
        frame.add(game);
        frame.pack();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setResizable(false);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);

        game.start();
    }
}
