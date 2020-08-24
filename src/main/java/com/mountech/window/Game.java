package com.mountech.window;

import com.mountech.framework.ObjectId;
import com.mountech.objects.Test;

import java.awt.*;
import java.awt.image.BufferStrategy;
import java.util.Random;

public class Game extends Canvas implements Runnable {

    private boolean running = false;
    private Thread thread;
    private Handler handler;


    private void init(){
        handler = new Handler();
        handler.addObject(new Test(100, 100, ObjectId.Test));
    }

    public synchronized void start() {
        if(running) return;
        running = true;
        thread = new Thread(this);
        thread.start();
    }

    public void run() {
        init();
        this.requestFocus();
        long lastTime = System.nanoTime();
        double amountOfTicks = 60.0;
        double ns = 1000000000.0 / amountOfTicks;
        double delta  = 0;
        long timer = System.currentTimeMillis();
        int updates = 0;
        int frames = 0;
        while(running){
            long now = System.nanoTime();
            delta += (now - lastTime) / ns;
            lastTime = now;
            while(delta >= 1){
                tick();
                updates++;
                delta--;
            }
            render();
            frames++;

            if(System.currentTimeMillis() - timer > 1000){
                timer += 1000;
                System.out.println("FPS: " + frames + " Ticks: " + updates);
                frames = 0;
                updates = 0;
            }
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
