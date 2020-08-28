package com.mountech.framework;

import com.mountech.handler.Handler;

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
        for(int i = 0; i < handler.objects.size(); i++){
            tempObject = handler.objects.get(i);

            if(tempObject.getObjectId() == ObjectId.Player){
                if(key == KeyEvent.VK_D) tempObject.setVelX(5);
                if(key == KeyEvent.VK_A) tempObject.setVelX(-5);
                if(key == KeyEvent.VK_SPACE && !tempObject.isJumping()){
                    tempObject.setJumping(true);
                    tempObject.setVelY(-10);
                }
            }
        }
    }

    public void keyReleased(KeyEvent e){
        int key = e.getKeyCode();
        for(int i = 0; i < handler.objects.size(); i++){
            tempObject = handler.objects.get(i);

            if(tempObject.getObjectId() == ObjectId.Player){
                if(key == KeyEvent.VK_D) tempObject.setVelX(0);
                if(key == KeyEvent.VK_A) tempObject.setVelX(0);
                if(key == KeyEvent.VK_SPACE) tempObject.setVelY(0);
            }
        }
    }
}
