package com.mountech.objects;

import com.mountech.Game;
import com.mountech.framework.GameObject;
import com.mountech.framework.ObjectId;
import com.mountech.handler.Handler;
import com.mountech.imageLoader.Texture;
import com.mountech.window.Animation;

import java.awt.*;
import java.util.LinkedList;

public class Player extends GameObject {

    public static float width = 51, height = 112, MAX_SPEED=10;

    private float gravity = 0.04f;

    private Handler handler;
    private Texture tex = Game.getTexture();
    private Animation playerWalk;

    public Player(float x, float y,Handler handler, ObjectId objectId) {
        super(x, y, objectId);
        this.handler = handler;
        playerWalk = new Animation(4, tex.player[0],tex.player[1],tex.player[2],tex.player[3],tex.player[4]);
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

        playerWalk.runAnimation();
    }

    private void collision(LinkedList<GameObject> object){
        for(int i= 0; i < handler.objects.size(); i++){
            GameObject tempObject = handler.objects.get(i);

            if(tempObject.getObjectId() == ObjectId.Block) {

                if(getBoundsTop().intersects(tempObject.getBounds())){
                    y = tempObject.getVelY() + height;
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

        if(jumping){
            g.drawImage(tex.player[3], (int) x, (int) y, (int) width, (int) height, null);
        }else{
            if(velX != 0){
                playerWalk.drawAnimation(g, (int)x, (int)y, (int) width, (int) height);
            } else {
                g.drawImage(tex.player[0], (int) x, (int) y, (int) width, (int) height, null);
            }
        }
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
