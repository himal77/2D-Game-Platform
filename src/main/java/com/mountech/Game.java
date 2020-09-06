package com.mountech;

import com.mountech.accessories.Camera;
import com.mountech.events.KeyInput;
import com.mountech.framework.ObjectId;
import com.mountech.handler.Handler;
import com.mountech.imageLoader.BufferedImageLoader;
import com.mountech.imageLoader.Texture;
import com.mountech.window.Window;

import java.awt.*;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;

public class Game extends Canvas implements Runnable {

    public static int WIDTH = 800;
    public static int HEIGHT = 600;

    private boolean running = false;
    private Thread thread;

    private Handler handler;
    private Camera camera;
    private BufferedImage level1Image = null, cloud = null;
    public static Texture texture;

    private void init(){

        BufferedImageLoader loader = new BufferedImageLoader();
        level1Image = loader.loadImage("/level1.png");  // Loading the image

        cloud = loader.loadImage("/cloud.jpg");
        texture = new Texture();
        handler = new Handler();
        camera = new Camera(0, 0);

        handler.createLevel(level1Image);

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
        for(int i = 0; i < handler.objects.size(); i++) {
            if(handler.objects.get(i).getObjectId() == ObjectId.Player) {
                camera.tick(handler.objects.get(i));
            }
        }

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
        Graphics2D g2d = (Graphics2D) g;

        ///// Everything is drawn here ///////

        g2d.translate(camera.getX(), camera.getY());  // Begin of camera
        for(int xx = 0; xx < cloud.getWidth() * 5; xx += cloud.getWidth())
            g.drawImage(cloud, xx, 0, null);
        handler.render(g);
        g2d.translate(-camera.getX(), -camera.getY()); // End of camera

        /////////////////////////////////////

        g.dispose();
        bs.show();
    }



    public static Texture getTexture(){
        return texture;
    }

    public static void main(String[] args) {
        new Window(WIDTH, HEIGHT, "2D_Game_Platform", new Game());
    }
}
