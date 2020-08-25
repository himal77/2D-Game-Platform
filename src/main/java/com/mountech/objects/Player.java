package com.mountech.objects;

import com.mountech.framework.GameObject;
import com.mountech.framework.ObjectId;

import java.awt.*;
import java.util.LinkedList;

public class Player extends GameObject {

    private float width = 32, height = 64, MAX_SPEED=10;

    private float gravity = 0.01f;

    public Player(float x, float y, ObjectId objectId) {
        super(x, y, objectId);
    }

    public void tick(LinkedList<GameObject> object) {
        x += velX;
        y += velY;

        if(falling || jumping){
            velY += gravity;

            if(velY > MAX_SPEED){
                velY = MAX_SPEED;
            }
        }
    }

    public void render(Graphics g) {
        g.setColor(Color.BLUE);
        g.fillRect((int)x, (int)y, (int)width, (int)height);
    }

    public Rectangle getBounds() {
        return new Rectangle((int)x, (int)y, (int)width, (int)height);
    }
}
