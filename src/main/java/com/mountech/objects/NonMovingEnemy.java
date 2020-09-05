package com.mountech.objects;

import com.mountech.Game;
import com.mountech.framework.GameObject;
import com.mountech.framework.ObjectId;
import com.mountech.imageLoader.Texture;

import java.awt.*;
import java.util.LinkedList;

public class NonMovingEnemy extends GameObject {

    private Texture texture = Game.getTexture();

    public NonMovingEnemy(float x, float y, ObjectId objectId, int width, int height) {
        super(x, y, objectId);
        this.width = width;
        this.height = height;
    }

    public void tick(LinkedList<GameObject> object) {

    }

    public void render(Graphics g) {
        g.setColor(Color.GREEN);
        g.drawImage(texture.nonMovingEnemy[0], (int)x,(int)y, (int)(width * 2.5), (int)(height * 2.5), null);
    }

    public Rectangle getBounds() {
        return null;
    }
}
