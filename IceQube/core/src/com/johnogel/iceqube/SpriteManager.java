/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.johnogel.iceqube;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.physics.box2d.World;
import java.util.ArrayList;

/**
 *
 * @author johno-gel
 */
public class SpriteManager {
private SpriteBatch batch;
private ArrayList<Sprite> sprites;
public static World world;
    public SpriteManager(World world){
        SpriteManager.world = world;
        batch = new SpriteBatch(); 
        sprites = new ArrayList();
    }
    
    public void addSprite(Sprite sprite){
        sprites.add(sprite);
    }
    
    public void removeSprite(Sprite sprite){
        sprites.remove(sprite);
    }
    
    //ticks physics timer and stuff
    public void update(){
        SpriteManager.world.step(Gdx.graphics.getDeltaTime(), 6, 2);
    }
    
    public void render(){
        batch.begin();
        for(Sprite s : sprites){
            batch.draw(s.getTexture(), s.getX(), s.getY());
        }
        
        batch.end();
    }
}
