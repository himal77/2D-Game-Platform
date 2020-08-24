package com.mountech.window;

import java.awt.*;

public class Game extends Canvas implements Runnable{

    private boolean running = false;
    private Thread thread;

    public synchronized void start(){
        if(running) return;
        running = true;
        thread = new Thread(this);
        thread.start();
    }

    public void run() {
        System.out.println("This is running");
    }

    public static void main(String[] args) {
        new Window(800, 600, "2D_Game_Platform", new Game());
    }
}
