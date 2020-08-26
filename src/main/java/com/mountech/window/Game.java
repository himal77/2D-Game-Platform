package com.mountech.window;

import com.mountech.framework.KeyInput;
import com.mountech.framework.ObjectId;
import com.mountech.objects.Player;

import java.awt.*;
import java.awt.image.BufferStrategy;

public class Game extends Canvas implements Runnable {

    public static int WIDTH;
    public static int HEIGHT;

    private boolean running = false;
    private Thread thread;
    private Handler handler;


    private void init(){
        WIDTH = getWidth();
        HEIGHT = getHeight();

        handler = new Handler();

        handler.addObject(new Player(100, 100, handler, ObjectId.Player));

        handler.createLevel();

        this.addKeyListener(new KeyInput(handler));
    }

    public synchronized void start() {
        if(running) return;
        thread = new Thread(this);
        running = true;
        thread.start();
    }

    public void run() {
        init();
        this.requestFocus();
//        long lastTime = System.nanoTime();
//        double amountOfTicks = 60.0;
//        double ns = 1000000000 / amountOfTicks;
//        double delta = 0;
//        long timer = System.currentTimeMillis();
//        int updates = 0;
//        int frames = 0;
//        while(running){
//            long now = System.nanoTime();
//            delta += (now - lastTime) / ns;
//            lastTime = now;
//            while(delta >= 1){
//                tick();
//                updates++;
//                delta--;
//            }
//            render();
//            frames++;
//
//            if((System.currentTimeMillis() - timer) > 1000){
//                timer += 1000;
//                frames = 0;
//                updates = 0;
//            }
//        }

        while(true) {
            try {
                Thread.sleep(10);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            tick();
            render();
        }
    }

    // Ticking is like  updating the view 60 tick per second not more than that
    public void tick() {
        handler.tick();
    }

    // This method is for rendering
    public void render() {
        // BufferStrategy is the no of buffered image behind screen, which are ready to get rendered on screen
        BufferStrategy bs = this.getBufferStrategy(); // this here refer to the canvas
        if(bs == null){
            this.createBufferStrategy(3);           // If not created, create 3
            return;
        }

        Graphics g = bs.getDrawGraphics();  // This is gonna get graphics context for our buffering

        ///// Everything is drawn here ///////

        g.setColor(Color.BLACK);
        g.fillRect(0,0,getWidth(), getHeight());
        g.setColor(Color.RED);
        handler.render(g);

        /////////////////////////////////////

        g.dispose();
        bs.show();
    }

    public static void main(String[] args) {
        new Window(800, 600, "2D_Game_Platform", new Game());
    }
}
