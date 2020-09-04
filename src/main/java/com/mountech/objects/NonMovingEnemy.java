package com.mountech.objects;

import com.mountech.framework.GameObject;
import com.mountech.framework.ObjectId;

import java.awt.*;
import java.util.LinkedList;

public class NonMovingEnemy extends GameObject {
    private int width = 50, height = 100;
    public NonMovingEnemy(float x, float y, ObjectId objectId) {
        super(x, y, objectId);
    }

    public void tick(LinkedList<GameObject> object) {

    }

    public void render(Graphics g) {
        g.setColor(Color.GREEN);
        g.fillRect((int)x, (int)y, width, height);
    }

    public Rectangle getBounds() {
        return null;
    }
}
