package com.mountech.objects;

import com.mountech.framework.GameObject;
import com.mountech.framework.ObjectId;

import java.awt.*;
import java.util.LinkedList;

public class MovingEnemy extends GameObject {
    private int width = 50, height = 100;
    public MovingEnemy(float x, float y, ObjectId objectId) {
        super(x, y, objectId);
    }

    public void tick(LinkedList<GameObject> object) {
        if(x < 0) velX += 0.01;
        if(x > 500); velX -= 0.01;
        x += velX;
    }

    public void render(Graphics g) {
        g.setColor(Color.RED);
        g.fillRect((int)x, (int)y, width, height);
    }

    public Rectangle getBounds() {
        return null;
    }

    public Rectangle getBoundsRight() {
        return new Rectangle((int) ((int)x+width - 5), (int)y+5, (int) 5, (int)height-10);
    }
    public Rectangle getBoundsLeft() {
        return new Rectangle((int)x, (int)y + 5, 5,(int) height-10);
    }

}
