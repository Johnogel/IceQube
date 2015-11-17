/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.johnogel.iceqube;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.World;
import java.util.ArrayList;

/**
 *
 * @author johno-gel
 */
public class GameManager implements Controller{
GL20 gl = Gdx.graphics.getGL20();
ShapeRenderer sr;
InputListener input_listener;
SpriteManager sprite_manager;
World world;
Player player;
private ArrayList<GameObject> game_objects;
    public GameManager(){
        sr = new ShapeRenderer();
        //create Box2D world for physics and stuff
        world = new World(new Vector2(0, -98f), true);
        sprite_manager = new SpriteManager(world);
        player = new Player(250, "cube/cube", Player.PNG, 100, 400);
        game_objects = new ArrayList();
        game_objects.add(player);
        sprite_manager.addSprite(player);
        input_listener = new InputListener();
        input_listener.registerObserver((Observer)game_objects.get(0));
    }
    
    public void update(){
        input_listener.checkInput();
        sprite_manager.update();
        for (GameObject obj : game_objects){
            obj.update();
        }
    }
    
@Override
    public void render(){
        sprite_manager.render();
    }

    @Override
    public void keyDown(int keycode) {
        switch (keycode){
            case Keys.A:
                sr.begin();
                sr.ellipse(20, 30, 20, 30);
                System.out.println("OVAL SHOULD BE HERE");
                sr.end();
                
        }
    }

    @Override
    public void keyUp(int keycode) {
    }
    
}
