package com.mountech.objects;

import com.mountech.framework.GameObject;
import com.mountech.framework.ObjectId;

import java.awt.*;
import java.util.LinkedList;

public class Flag extends GameObject  {
    public Flag(float x, float y, ObjectId objectId, int width, int height) {
        super(x, y, objectId);
        this.width = width;
        this.height = height;
    }

    public void tick(LinkedList<GameObject> object) {

    }

    public void render(Graphics g) {
        g.setColor(Color.yellow);
        g.fillRect((int)x, (int)y, 32, 16);
    }

    public Rectangle getBounds() {
        return null;
    }
}
