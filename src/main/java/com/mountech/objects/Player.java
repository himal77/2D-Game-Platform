package com.mountech.objects;

import com.mountech.framework.GameObject;
import com.mountech.framework.ObjectId;

import java.awt.*;
import java.util.LinkedList;

public class Player extends GameObject {

    private float width = 48, height = 96, MAX_SPEED=10;

    private float gravity = 0.01f;

    public Player(float x, float y, ObjectId objectId) {
        super(x, y, objectId);
    }

    public void tick(LinkedList<GameObject> object) {
        x += velX;
        y += velY;

        if(falling || jumping){
            //velY += gravity;

            if(velY > MAX_SPEED){
                velY = MAX_SPEED;
            }
        }
    }

    public void render(Graphics g) {

        g.setColor(Color.BLUE);
        g.fillRect((int)x, (int)y, (int)width, (int)height);
        g.setColor(Color.RED);
        Graphics2D g2d = (Graphics2D) g;
        g2d.draw(getBounds());
        g2d.draw(getBoundsRight());
        g2d.draw(getBoundsLeft());
        g2d.draw(getBoundsTop());

    }

    public Rectangle getBounds() {
        return new Rectangle((int) ((int)x+(width/2) - ((width/2)/2)), (int)(y+height/2), (int)width/2, (int)height/2);
    }

    public Rectangle getBoundsTop() {
        return new Rectangle((int) ((int)x+(width/2) - ((width/2)/2)), (int)y, (int)width/2, (int)height/2);

    }
    public Rectangle getBoundsRight() {
        return new Rectangle((int) ((int)x+width - 5), (int)y+5, (int)5, (int)height-10);
    }
    public Rectangle getBoundsLeft() {
        return new Rectangle((int)x, (int)y+5, 5,(int) height-10);
    }
}
