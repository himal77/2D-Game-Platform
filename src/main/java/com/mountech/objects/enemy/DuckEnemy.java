package com.mountech.objects.enemy;

import com.mountech.Game;
import com.mountech.framework.GameObject;
import com.mountech.framework.ObjectId;
import com.mountech.imageLoader.Texture;

import java.awt.*;
import java.util.LinkedList;

public class DuckEnemy extends GameObject {
    private int type;

    private Texture texture = Game.getTexture();

    public DuckEnemy(float x, float y, ObjectId objectId, int width, int height) {
        super(x, y, objectId);
        this.width = width;
        this.height = height;
    }

    public void tick(LinkedList<GameObject> object) {
        if(x < 0) velX += 0.01;
        if(x > 500); velX -= 0.01;
        x += velX;
    }

    public void render(Graphics g) {
        g.drawImage(texture.duckEnemy[3], (int)x, (int)y, width, height, null);
        Graphics2D graphics2D = (Graphics2D) g;
        graphics2D.draw(getBounds());
    }

    public Rectangle getBounds() {
        // Decreasing 42% of width of duck
        return new Rectangle((int)x, (int)y, width - (int)(width * 42)/100, height);

    }

}
