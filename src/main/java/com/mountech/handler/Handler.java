package com.mountech.handler;

import com.mountech.framework.GameObject;
import com.mountech.framework.ObjectId;
import com.mountech.objects.Block;
import com.mountech.objects.enemy.DuckEnemy;
import com.mountech.objects.enemy.MushroomEnemy;
import com.mountech.objects.Player;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.LinkedList;

public class Handler {

    public LinkedList<GameObject> objects = new LinkedList<GameObject>();

    private GameObject tempObject;

    public void tick() {
        for(int i = 0;  i < objects.size(); i++) {
            tempObject = objects.get(i);
            tempObject.tick(objects);
        }
    }

    public void render(Graphics g) {
        for(int i = 0;  i < objects.size(); i++){
            tempObject = objects.get(i);
            tempObject.render(g);
        }
    }

    public void addObject(GameObject object) {
        this.objects.add(object);
    }

    public void removeObject(GameObject object) {
        this.objects.remove(object);
    }
    public void createLevel(BufferedImage image){
        int w = image.getWidth();
        int h = image.getHeight();

        for(int xx = 0; xx < h; xx++){
            for(int yy = 0; yy < w; yy++){
                int pixel = image.getRGB(xx, yy);
                int red = (pixel >> 16) & 0xff;
                int green = (pixel >> 8) & 0xff;
                int blue = (pixel) & 0xff;
                // Block
                if(red == 255 && green == 255 && blue == 255){
                    addObject(new Block(xx * 32, yy * 32, 0,  ObjectId.Block, 60, 60));
                }
                // Player
                if(red == 00 && green == 00 && blue == 255){
                    addObject(new Player(xx * 32, yy * 32, this,  ObjectId.Player, 50, 100));
                }
                // mushroomEnemy
                if(red == 0 && green == 255 && blue == 00){
                    addObject(new MushroomEnemy(xx * 32, yy * 32,  ObjectId.mushroomEnemy, 40, 27));
                }
                // duckEnemy
                if(red == 255 && green == 0 && blue == 00){
                    addObject(new DuckEnemy(xx * 32, yy * 32, ObjectId.duckEnemy, this,  100, 65));
                }
            }
        }
    }
}
