package com.mountech.objects;

import com.mountech.Game;
import com.mountech.framework.GameObject;
import com.mountech.framework.ObjectId;
import com.mountech.imageLoader.Texture;

import java.awt.*;
import java.util.LinkedList;
import java.util.Random;

public class Block extends GameObject {
    private int type;

    private Texture texture = Game.getTexture();

    public Block(float x, float y, int type, ObjectId objectId) {
        super(x, y, objectId);
        this.type = type;
    }

    public void tick(LinkedList<GameObject> object) {

    }

    public void render(Graphics g) {
        if(type == 0)
            g.drawImage(texture.block[0], (int)x, (int)y, null);
        if(type == 1)
            g.drawImage(texture.block[1], (int)x, (int)y, null);
    }

    public Rectangle getBounds() {
        return new Rectangle((int)x, (int)y, 32, 32);
    }
}
