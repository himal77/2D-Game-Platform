package com.mountech.objects.enemy;

import com.mountech.Game;
import com.mountech.framework.GameObject;
import com.mountech.framework.ObjectId;
import com.mountech.handler.Handler;
import com.mountech.imageLoader.Texture;
import com.mountech.window.Animation;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.LinkedList;

public class MushroomEnemy extends GameObject {
    private int type;
    private double deadTimeCount = 0.0;

    private Rectangle topRect = new Rectangle();
    private Rectangle leftRect = new Rectangle();
    private Rectangle rightRect = new Rectangle();

    private Animation mushroomAnimation;
    private Texture texture = Game.getTexture();
    private Handler handler;

    public MushroomEnemy(float x, float y, ObjectId objectId, int width, int height, Handler handler) {
        super(x, y, objectId);
        this.objectWidth = width;
        this.objectHeight = height;
        mushroomAnimation = new Animation(10, texture.mushroomEnemy[0], texture.mushroomEnemy[1]);
        boundWidth = objectWidth + (objectWidth * 36)/100; //increased with by 36%
        boundHeight = objectHeight + (objectHeight * 100)/100; // increased height by 150%
        this.handler = handler;
    }

    public void tick(LinkedList<GameObject> object) {
        //for deleteing ememy after dead
        if (isDead) {
            deadTimeCount += 1 / 60f;
            if (deadTimeCount > 0.2) {
                for (int i = 0; i < handler.objects.size(); i++) {
                    if (handler.objects.get(i).getObjectId() == this.getObjectId()) {
                        handler.removeObject(this);
                    }
                }
            }
        }
        mushroomAnimation.runAnimation();
    }

    public void render(Graphics g) {
        mushroomAnimation.drawAnimation(g, (int)x, (int)y, objectWidth + (objectWidth * 150)/100, objectHeight + (objectHeight * 200)/100);
    }

    public Rectangle getBounds() {
        // Bound y starts at 45% down of height
        return new Rectangle((int)x , (int)y + (objectHeight * 45)/100, boundWidth, boundHeight);
    }

    public Rectangle getTopRect() {
        //starting 25% further from x   w.r.t boundWidth, and finish at 50% less than total boundWidth
         topRect.setBounds((int)getBounds().getX() + (boundWidth*25)/100, (int)getBounds().getY(),
                boundWidth - (boundWidth * 50)/100, boundHeight / 2);
        return topRect;
    }

    public Rectangle getLeftRect() {
        // Staring 30% down from y w.r.t boundHeight, and finish at 60% less w.r.t boundHeight
        leftRect.setBounds((int)getBounds().getX(), (int)getBounds().getY() + (boundHeight * 30) / 100,
                boundWidth - (boundWidth * 80)/100, boundHeight - (boundHeight * 60) / 100);
        return leftRect;
    }

    public Rectangle getRightRect() {
        // Staring 80% further from x w.r.t boundWidth
        // Staring 30% down from y w.r.t boundHeight, and finish at 60% less w.r.t boundHeight
        rightRect.setBounds((int)getBounds().getX() + (boundWidth * 80) / 100, (int)getBounds().getY() + (boundHeight * 30) / 100,
                boundWidth - (boundWidth * 80)/100, boundHeight - (boundHeight * 60) / 100);
        return rightRect;
    }
}
