package com.mountech.objects;

import com.mountech.framework.GameObject;
import com.mountech.framework.ObjectId;
import com.mountech.handler.Handler;

import java.awt.*;
import java.util.LinkedList;

public class Player extends GameObject {

    private float width = 48, height = 96, MAX_SPEED=10;

    private float gravity = 0.01f;

    private Handler handler;

    public Player(float x, float y,Handler handler, ObjectId objectId) {
        super(x, y, objectId);
        this.handler = handler;
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
        collision(object);
    }

    private void collision(LinkedList<GameObject> object){
        for(int i= 0; i < handler.objects.size(); i++){
            GameObject tempObject = handler.objects.get(i);

            if(tempObject.getObjectId() == ObjectId.Block) {

                if(getBoundsTop().intersects(tempObject.getBounds())){
                    y = tempObject.getVelY() + 40;
                    velY = 0;
                }


                if(getBounds().intersects(tempObject.getBounds())) {
                    y = tempObject.getY() - height;
                    velY = 0;
                    falling = false;
                    jumping = false;
                }else {
                    falling = true;
                }

                if(getBoundsRight().intersects(tempObject.getBounds())){
                    x = tempObject.getX() - width;
                }

                if(getBoundsLeft().intersects(tempObject.getBounds())){
                    x = tempObject.getX() + 35;
                }
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
