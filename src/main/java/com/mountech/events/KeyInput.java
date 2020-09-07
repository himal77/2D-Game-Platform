package com.mountech.events;

import com.mountech.framework.GameObject;
import com.mountech.framework.ObjectId;
import com.mountech.handler.Handler;
import com.mountech.objects.Block;
import com.mountech.objects.Bullet;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class KeyInput extends KeyAdapter {

    private Handler handler;
    GameObject tempObject;

    public KeyInput(Handler handler){
        this.handler = handler;
    }

    public void keyPressed(KeyEvent e){
        int key = e.getKeyCode();
        for(int i = 0; i < handler.objects.size(); i++) {
            tempObject = handler.objects.get(i);

            if(tempObject.getObjectId() == ObjectId.Player){
                if(key == KeyEvent.VK_D || key == KeyEvent.VK_RIGHT){
                    tempObject.setFacing(1);
                    tempObject.setVelX(5);
                }
                if(key == KeyEvent.VK_A || key == KeyEvent.VK_LEFT){
                    tempObject.setFacing(-1);
                    tempObject.setVelX(-5);
                }
                if((key == KeyEvent.VK_W && tempObject.isJumping() == false) || (key == KeyEvent.VK_UP && tempObject.isJumping() == false)){
                    tempObject.setVelY(-7);
                    tempObject.setJumping(true);
                }
                if(key == KeyEvent.VK_SPACE){
                    handler.addObject(new Bullet(tempObject.getX(), tempObject.getY() + 40, ObjectId.Bullet, tempObject.getFacing() * 5, 10, 10));
                }
            }
        }
    }

    public void keyReleased(KeyEvent e){
        int key = e.getKeyCode();
        for(int i = 0; i < handler.objects.size(); i++){
            tempObject = handler.objects.get(i);

            if(tempObject.getObjectId() == ObjectId.Player){
                if(key == KeyEvent.VK_D || key == KeyEvent.VK_RIGHT) tempObject.setVelX(0);
                if(key == KeyEvent.VK_A ||  key == KeyEvent.VK_LEFT) tempObject.setVelX(0);
                if(key == KeyEvent.VK_SPACE) tempObject.setVelY(0);
            }
        }
    }
}
