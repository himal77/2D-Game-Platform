package com.mountech.objects.enemy;

import com.mountech.Game;
import com.mountech.framework.GameObject;
import com.mountech.framework.ObjectId;
import com.mountech.handler.Handler;
import com.mountech.imageLoader.Texture;

import java.awt.*;
import java.awt.geom.Rectangle2D;
import java.util.LinkedList;

public class DeadMushroom extends GameObject {
    private int lifeTime = 5;
    private double count = 0;

    private Texture texture = Game.getTexture();
    private Handler handler;

    public DeadMushroom(float x, float y, ObjectId objectId, int width, int height, int lifeTime, Handler handler) {
        super(x, y, objectId);
        this.x = x;
        this.y = y;
        this.lifeTime = lifeTime;
        boundWidth = width;
        boundHeight = height;
        this.handler = handler;
    }

    public void tick(LinkedList<GameObject> object) {
        count += 1/60f;
        if(count > 0.1) {

            for(int i = 0; i < handler.objects.size(); i++){
                if(handler.objects.get(i).getObjectId() == this.getObjectId()) {
                    handler.removeObject(this);
                }
            }
        }
    }

    public void render(Graphics g) {
        g.drawImage(texture.mushroomEnemy[2], (int)x, (int)y, boundWidth, boundHeight, null);
    }

    public Rectangle2D getBounds() {
        return null;
    }
}
