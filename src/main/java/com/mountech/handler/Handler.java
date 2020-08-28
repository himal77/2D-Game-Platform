package com.mountech.handler;

import com.mountech.framework.GameObject;
import com.mountech.framework.ObjectId;
import com.mountech.objects.Block;
import com.mountech.window.Game;

import java.awt.*;
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

    public void createLevel(){
        // Creating bottom block
        for(int xx = 0; xx < Game.WIDTH+32; xx += 32){
            addObject(new Block(xx, Game.HEIGHT - 28, ObjectId.Block));
        }

        // Creating right side block
        for(int xx = 0; xx < Game.HEIGHT+32; xx += 32){
            addObject(new Block(Game.WIDTH - 48, xx, ObjectId.Block));
        }

        // Creating left side block
        for(int xx = 0; xx < Game.HEIGHT+32; xx += 32){
            addObject(new Block(0, xx, ObjectId.Block));
        }

        // Creating middle block
        for(int xx = 32 * 5; xx < Game.HEIGHT+32; xx += 32){
            addObject(new Block(xx, Game.HEIGHT - 32 *  4, ObjectId.Block));
        }

    }
}
