package com.mountech.objects.enemy;

import com.mountech.Game;
import com.mountech.framework.GameObject;
import com.mountech.framework.ObjectId;
import com.mountech.imageLoader.Texture;

import java.awt.*;
import java.util.LinkedList;

public class MushroomEnemy extends GameObject {
    private int type;

    private Texture texture = Game.getTexture();

    public MushroomEnemy(float x, float y, ObjectId objectId, int width, int height) {
        super(x, y, objectId);
        this.width = width;
        this.height = height;
    }

    public void tick(LinkedList<GameObject> object) {

    }

    public void render(Graphics g) {
        g.setColor(Color.GREEN);
        g.drawImage(texture.mushroomEnemy[0], (int)x,(int)y, (int)(width * 2.5), (int)(height * 2.5), null);
        Graphics2D graphics2D = (Graphics2D) g;
        graphics2D.draw(getBounds());
    }

    public Rectangle getBounds() {
        return new Rectangle((int)x , (int)y + 10, width + (width * 100)/100, height + (height * 200)/100);
    }
}
