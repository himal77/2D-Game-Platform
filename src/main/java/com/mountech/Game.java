package com.mountech;

import com.mountech.accessories.Camera;
import com.mountech.events.KeyInput;
import com.mountech.framework.ObjectId;
import com.mountech.handler.Handler;
import com.mountech.imageLoader.BufferedImageLoader;
import com.mountech.imageLoader.Texture;
import com.mountech.objects.Block;
import com.mountech.objects.Player;
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
    private BufferedImage level = null;
    public static Texture texture;

    private void init(){

        BufferedImageLoader loader = new BufferedImageLoader();
        level = loader.loadImage("/level1.png");  // Loading the image

        texture = new Texture();
        handler = new Handler();
        camera = new Camera(0, 0);

        loadImageLevel(level);

      //  handler.addObject(new Player(100, 100, handler, ObjectId.Player));
      //  handler.createLevel();

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



        g.setColor(Color.BLACK);
        g.fillRect(0,0,getWidth(), getHeight());

        g2d.translate(camera.getX(), camera.getY());  // Begin of camera

        handler.render(g);

        g2d.translate(-camera.getX(), -camera.getY()); // End of camera

        /////////////////////////////////////

        g.dispose();
        bs.show();
    }

    private void loadImageLevel(BufferedImage image){
        int w = image.getWidth();
        int h = image.getHeight();

        for(int xx = 0; xx < h; xx++){
            for(int yy = 0; yy < w; yy++){
                int pixel = image.getRGB(xx, yy);
                int red = (pixel >> 16) & 0xff;
                int green = (pixel >> 8) & 0xff;
                int blue = (pixel) & 0xff;

                if(red == 255 && green == 255 && blue == 255){
                    handler.addObject(new Block(xx * 32, yy * 32, 0,  ObjectId.Block));
                }

                if(red == 00 && green == 00 && blue == 255){
                    handler.addObject(new Player(xx * 32, yy * 32, handler,  ObjectId.Player));
                }
            }
        }
    }

    public static Texture getTexture(){
        return texture;
    }

    public static void main(String[] args) {
        new Window(WIDTH, HEIGHT, "2D_Game_Platform", new Game());
    }
}
