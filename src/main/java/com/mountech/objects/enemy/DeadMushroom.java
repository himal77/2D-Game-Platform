package com.mountech.objects.enemy;

import com.mountech.Game;
import com.mountech.framework.GameObject;
import com.mountech.framework.ObjectId;
import com.mountech.imageLoader.Texture;

import java.awt.*;
import java.awt.geom.Rectangle2D;
import java.util.LinkedList;

public class DeadMushroom extends GameObject {
    private int lifeTime = 5;

    private Texture texture = Game.getTexture();

    public DeadMushroom(float x, float y, ObjectId objectId, int width, int height, int lifeTime) {
        super(x, y, objectId);
        this.x = x;
        this.y = y;
        this.lifeTime = lifeTime;
        boundWidth = width;
        boundHeight = height;
    }

    public void tick(LinkedList<GameObject> object) {

    }

    public void render(Graphics g) {
        g.drawImage(texture.mushroomEnemy[2], (int)x, (int)y, boundWidth, boundHeight, null);
    }

    public Rectangle2D getBounds() {
        return null;
    }
}
