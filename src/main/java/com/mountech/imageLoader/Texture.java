package com.mountech.imageLoader;

import com.mountech.objects.Player;

import java.awt.image.BufferedImage;

public class Texture {
    private SpriteSheet ps, bs;
    private BufferedImage block_sheet = null;
    private BufferedImage player_sheet = null;

    public BufferedImage[] block = new BufferedImage[2];
    public BufferedImage[] player = new BufferedImage[5];

    public Texture(){
        BufferedImageLoader loader = new BufferedImageLoader();
        try{
            block_sheet = loader.loadImage("/block_sheet.png");
            player_sheet = loader.loadImage("/player_sheet.png");
        }catch (Exception e){
            e.printStackTrace();
        }
        bs = new SpriteSheet(block_sheet);
        ps = new SpriteSheet(player_sheet);
        getTextures();
    }

    private void getTextures(){
        block[0] = bs.grabImage(1, 1, 32, 32); // Wall block
        block[1] = bs.grabImage(2, 1, 32, 32); // Grass block

        player[0] = ps.grabImage(1, 1,(int) Player.width, (int)Player.height);
        player[1] = ps.grabImage(2, 1,(int) Player.width, (int)Player.height);
        player[2] = ps.grabImage(3, 1,(int) Player.width, (int)Player.height);
        player[3] = ps.grabImage(4, 1,(int) Player.width, (int)Player.height);
        player[4] = ps.grabImage(5, 1,(int) Player.width, (int)Player.height);
    }
}
