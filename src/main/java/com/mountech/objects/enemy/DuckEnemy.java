package com.mountech.objects.enemy;

import com.mountech.Game;
import com.mountech.framework.GameObject;
import com.mountech.framework.ObjectId;
import com.mountech.handler.Handler;
import com.mountech.imageLoader.Texture;
import com.mountech.window.Animation;

import java.awt.*;
import java.awt.geom.Rectangle2D;
import java.util.LinkedList;

public class DuckEnemy extends GameObject {
    private int type;
    private Rectangle topRect = new Rectangle();
    private Rectangle bottomRect = new Rectangle();
    private Rectangle leftRect = new Rectangle();
    private Rectangle rightRect = new Rectangle();

    private Animation duckWalkingLeftAnimation;
    private Animation duckWalkingRightAnimation;
    private Texture texture = Game.getTexture();
    private Handler handler = null;

    public DuckEnemy(float x, float y, ObjectId objectId, Handler handler, int width, int height) {
        super(x, y, objectId);
        this.handler = handler;
        this.objectWidth = width;
        this.objectHeight = height;
        boundWidth = objectWidth - (int)(objectWidth * 42)/100;
        boundHeight = objectHeight;
        duckWalkingLeftAnimation = new Animation(10, texture.duckEnemy[0], texture.duckEnemy[1]);
        duckWalkingRightAnimation = new Animation(10, texture.duckEnemy[2], texture.duckEnemy[3]);

    }

    public void tick(LinkedList<GameObject> object) {
        collision();
        velX = (float) (facing * 1.1);
        x += velX;
        if(facing == -1)
            duckWalkingLeftAnimation.runAnimation();
        else
            duckWalkingRightAnimation.runAnimation();
    }

    public void collision(){
        for(int i = 0; i < handler.objects.size(); i++){
            GameObject tempObject = handler.objects.get(i);
            if(tempObject.getObjectId() == ObjectId.Block){
                if(getLeftRect().intersects(tempObject.getBounds())){
                    facing = 1;
                }
                if(getRightRect().intersects(tempObject.getBounds())){
                    facing = -1;
                }
            }
        }
    }

    public void render(Graphics g) {
        if (facing == -1)
            duckWalkingLeftAnimation.drawAnimation(g, (int)x, (int)y, objectWidth, objectHeight);
        else
            duckWalkingRightAnimation.drawAnimation(g, (int)x, (int)y, objectWidth, objectHeight);
    }

    public Rectangle2D getBounds() {
        return new Rectangle((int)x, (int)y, boundWidth, boundHeight);
    }


    public Rectangle topRect(){
        // Reducing the size of width by 40%
        // Starting 10% off from x w.r.t width
        // Starting 10% off from y w.r.t height
        topRect.setBounds((int)x + (int)(boundWidth * 20)/100, (int)y + (int)(boundHeight * 10)/100, boundWidth - (int)(boundWidth * 40)/100, boundHeight / 2);
        return topRect;
    }

    public Rectangle bottomRect(){
        // Reducing the size of width by 40%
        // Starting 10% off from x w.r.t width
        // Starting 10% off from y w.r.t height
        bottomRect.setBounds((int)x + (int)(boundWidth * 20)/100, (int)y + (int)(boundHeight * 50)/100, boundWidth - (int)(boundWidth * 40)/100, boundHeight / 2);
        return bottomRect;
    }

    public Rectangle getRightRect(){
        rightRect.setBounds((int)x + (boundWidth * 90)/100, (int)y+ (boundWidth * 5)/100, boundWidth - (boundWidth * 90) / 100,boundHeight - (boundHeight * 10)/100);
        return rightRect;
    }

    public Rectangle getLeftRect() {
        leftRect.setBounds((int)x, (int)y + (boundWidth * 50)/100, boundWidth - (boundWidth * 90) / 100, boundHeight - (boundHeight * 50)/100);
        return leftRect;
    }
}
