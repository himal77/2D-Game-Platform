package com.mountech.framework;


import java.awt.*;
import java.util.LinkedList;

public abstract class GameObject {
    protected float x, y;
    protected ObjectId objectId;
    protected float velX = 0, velY = 0;

    public GameObject(float x, float y, ObjectId objectId){
        this.x = x;
        this.y = y;
        this.objectId = objectId;
    }

    public abstract void tick(LinkedList<GameObject> object);
    public abstract void render(Graphics g);
    public abstract Rectangle getBounds();

    public float getX() {
        return x;
    }

    public void setX(float x) {
        this.x = x;
    }

    public float getY() {
        return y;
    }

    public void setY(float y) {
        this.y = y;
    }

    public ObjectId getObjectId() {
        return objectId;
    }

    public void setObjectId(ObjectId objectId) {
        this.objectId = objectId;
    }

    public float getVelX() {
        return velX;
    }

    public void setVelX(float velX) {
        this.velX = velX;
    }

    public float getVelY() {
        return velY;
    }

    public void setVelY(float velY) {
        this.velY = velY;
    }
}
