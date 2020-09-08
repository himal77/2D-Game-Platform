package com.mountech.objects;

import com.mountech.Game;
import com.mountech.framework.GameObject;
import com.mountech.framework.ObjectId;
import com.mountech.imageLoader.Texture;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.LinkedList;

public class Block extends GameObject {
    private int type;

    private BufferedImage cloud;
    private Texture texture = Game.getTexture();

    public Block(float x, float y, int type, ObjectId objectId, int width, int height) {
        super(x, y, objectId);
        this.objectWidth = width;
        this.objectHeight = height;
        this.type = type;
    }

    public void tick(LinkedList<GameObject> object) {

    }

    public void render(Graphics g) {
        Graphics2D graphics2D = (Graphics2D) g;
        graphics2D.draw(getBounds());
        if(type == 0)
            g.drawImage(texture.block[0], (int)x, (int)y, null);
        if(type == 1)
            g.drawImage(texture.block[1], (int)x, (int)y, null);
    }

    public Rectangle getBounds() {
        return new Rectangle((int)x, (int)y, objectWidth - (objectWidth * 50)/100, objectHeight - (objectHeight * 50)/100);
    }
}
