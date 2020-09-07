package com.mountech.objects;

import com.mountech.framework.GameObject;
import com.mountech.framework.ObjectId;

import java.awt.*;
import java.util.LinkedList;

public class Bullet extends GameObject {
    public Bullet(float x, float y, ObjectId objectId, int velX, int width, int height) {
        super(x, y, objectId);
        this.objectWidth = width;
        this.objectHeight = height;
        this.velX = velX;
    }

    public void tick(LinkedList<GameObject> object) {
        x += velX;
    }

    public void render(Graphics g) {
        g.setColor(Color.red);
        g.fillRect((int)x, (int)y, 16, 16);
    }

    public Rectangle getBounds() {
        return new Rectangle((int)x, (int)y, 16, 16);
    }
}
