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

    private float gravity = 0.1f;
    private int MAX_SPEED = 10;

    private Handler handler;
    private Texture tex = Game.getTexture();
    private Animation playerWalkRight;
    private Animation playerWalkLeft;

    public Player(float x, float y,Handler handler, ObjectId objectId, int width, int height) {
        super(x, y, objectId);
        this.width = width;
        this.height = height;
        this.handler = handler;
        playerWalkRight = new Animation(4, tex.playerFaceRight[0],
                tex.playerFaceRight[1],tex.playerFaceRight[2],
                tex.playerFaceRight[3],tex.playerFaceRight[4]);
        playerWalkLeft = new Animation(4, tex.playerFaceLeft[0],
                tex.playerFaceLeft[1],tex.playerFaceLeft[2],
                tex.playerFaceLeft[3],tex.playerFaceLeft[4]);
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
        if(facing == 1)
            playerWalkRight.runAnimation();
        else
            playerWalkLeft.runAnimation();
    }

    private void collision(LinkedList<GameObject> object){
        for(int i= 0; i < handler.objects.size(); i++){
            GameObject tempObject = handler.objects.get(i);

            if(tempObject.getObjectId() == ObjectId.Block) {

                if(getBoundsTop().intersects(tempObject.getBounds())){
                    jumping = false;
                    velY = 0;
                    y += 1;
                }
                if(getBoundsBottom().intersects(tempObject.getBounds())) {
                    velY = 0;
                    y -= 1;
                    jumping = false;
                    falling = false;
                }else{
                    falling = true;
                }
                if(getBoundsRight().intersects(tempObject.getBounds())){
                    velX = 0;
                    x = this.getX() - 1;
                }
                if(getBoundsLeft().intersects(tempObject.getBounds())){
                    velX = 0;
                    x = this.getX() + 1;
                }
            }
        }
    }

    public void render(Graphics g) {
        Graphics2D g2d = (Graphics2D) g;
        g.setColor(Color.BLUE);

        if(jumping){
            if(facing == 1)
                g.drawImage(tex.playerFaceRight[3], (int) x, (int) y, (int) width, (int) height, null);
            else
                g.drawImage(tex.playerFaceLeft[1], (int) x, (int) y, (int) width, (int) height, null);
        }else{
            if(velX != 0 && facing == 1){
                playerWalkRight.drawAnimation(g, (int)x, (int)y, (int) width, (int) height);
            } else if(velX != 0 && facing == -1){
               playerWalkLeft.drawAnimation(g, (int)x, (int)y, (int) width, (int) height);
            }else{
                if(facing == 1)
                    g.drawImage(tex.playerFaceRight[0], (int) x, (int) y, (int) width, (int) height, null);
                else
                    g.drawImage(tex.playerFaceLeft[4], (int) x, (int) y, (int) width, (int) height, null);
            }
        }
    }

    public Rectangle getBounds(){
        return new Rectangle((int)x + 15, (int)y + 15, width - (int)(width * 50)/100, height - (int)(height*25)/100);
    }

    public Rectangle getBoundsBottom() {
        int boundX = (int)getBounds().getX();
        int boundY = (int)getBounds().getY();
        int boundW = (int)getBounds().getWidth(); // 25
        int boundH = (int)getBounds().getHeight(); // 75
        return new Rectangle(boundX + 7, boundY + (int)(boundH / 2), boundW - 7, (int)(boundH / 2));
    }

    public Rectangle getBoundsTop() {
        int boundX = (int)getBounds().getX();
        int boundY = (int)getBounds().getY();
        int boundW = (int)getBounds().getWidth();
        int boundH = (int)getBounds().getHeight();
        return new Rectangle(boundX + 7, boundY, boundW - 7, (int)(boundH / 2));
    }
    public Rectangle getBoundsRight() {
        int boundX = (int)getBounds().getX();
        int boundY = (int)getBounds().getY();
        int boundW = (int)getBounds().getWidth();
        int boundH = (int)getBounds().getHeight();
        return new Rectangle(boundX + boundW - 7 , boundY + 5, 7, boundH - 15);
    }
    public Rectangle getBoundsLeft() {
        int boundX = (int)getBounds().getX();
        int boundY = (int)getBounds().getY();
        int boundW = (int)getBounds().getWidth();
        int boundH = (int)getBounds().getHeight();
        return new Rectangle(boundX, boundY + 5, 7, boundH - 15);
    }
}
