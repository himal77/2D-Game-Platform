package com.mountech.handler;

import com.mountech.framework.GameObject;
import com.mountech.framework.ObjectId;
import com.mountech.objects.Block;
import com.mountech.Game;

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

}
