package com.mountech.framework;


import java.awt.*;
import java.awt.geom.Rectangle2D;
import java.util.LinkedList;

public abstract class GameObject {
    protected int objectWidth, objectHeight;
    protected int boundWidth, boundHeight;
    protected float x, y;
    protected float velX = 0, velY = 0;
    protected boolean falling = true;
    protected boolean jumping = true;
    protected int facing = 1;

    protected ObjectId objectId;

    protected Rectangle topRect = new Rectangle();
    protected Rectangle leftRect = new Rectangle();
    protected Rectangle rightRect = new Rectangle();
    protected Rectangle bottomRect = new Rectangle();

    public GameObject(float x, float y, ObjectId objectId){
        this.x = x;
        this.y = y;
        this.objectId = objectId;
    }

    public abstract void tick(LinkedList<GameObject> object);
    public abstract void render(Graphics g);
    public abstract Rectangle2D getBounds();

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

    public boolean isFalling() {
        return falling;
    }

    public void setFalling(boolean falling) {
        this.falling = falling;
    }

    public boolean isJumping() {
        return jumping;
    }

    public void setJumping(boolean jumping) {
        this.jumping = jumping;
    }

    public int getFacing() {
        return facing;
    }

    public void setFacing(int facing) {
        this.facing = facing;
    }

    public int getObjectHeight() {
        return objectHeight;
    }

    public int getObjectWidth() {
        return objectWidth;
    }

    public int getBoundHeight() {
        return boundHeight;
    }

    public int getBoundWidth() {
        return boundWidth;
    }

    public Rectangle getTopRect() {
        return topRect;
    }

    public Rectangle getLeftRect() {
        return leftRect;
    }

    public Rectangle getRightRect() {
        return rightRect;
    }

    public Rectangle getBottomRect() {
        return bottomRect;
    }
}
