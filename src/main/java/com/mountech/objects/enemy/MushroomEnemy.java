package com.mountech.objects.enemy;

import com.mountech.Game;
import com.mountech.framework.GameObject;
import com.mountech.framework.ObjectId;
import com.mountech.imageLoader.Texture;
import com.mountech.window.Animation;

import java.awt.*;
import java.util.LinkedList;

public class MushroomEnemy extends GameObject {
    private int type;

    private Animation mushroomAnimation;
    private Texture texture = Game.getTexture();

    public MushroomEnemy(float x, float y, ObjectId objectId, int width, int height) {
        super(x, y, objectId);
        this.objectWidth = width;
        this.objectHeight = height;
        mushroomAnimation = new Animation(10, texture.mushroomEnemy[0], texture.mushroomEnemy[1]);
    }

    public void tick(LinkedList<GameObject> object) {
        mushroomAnimation.runAnimation();
    }

    public void render(Graphics g) {
        mushroomAnimation.drawAnimation(g, (int)x, (int)y, objectWidth + (objectWidth * 150)/100, objectHeight + (objectHeight * 200)/100);
    }

    public Rectangle getBounds() {
        return new Rectangle((int)x , (int)y + (objectHeight * 30)/100, objectWidth + (objectWidth * 150)/100, objectHeight + (objectHeight * 200)/100);
    }
}
