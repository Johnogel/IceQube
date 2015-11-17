/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.johnogel.iceqube;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.physics.box2d.*;
import java.text.DecimalFormat;

/**
 *
 * @author johno-gel
 */
public class Player extends Sprite implements GameObject, Observer{
private SpriteBatch sprite;
public static final String 
        PNG = ".png",
        JPEG = ".jpg";
private int frame, prev_frame, anim_incr;
private final int max_frame;
private Texture[] images;
private int x_pos, y_pos;
private short time;
private String player_image, img_ext;
private Body body;
    public Player(int num_images, String img_name, String extension, float x, float y){
        frame = 0;     
        anim_incr = 1;
        prev_frame = frame -1;
        max_frame = num_images;
        player_image = img_name;
        img_ext = extension;
        sprite = new SpriteBatch();
        images = new Texture[num_images];
        DecimalFormat dc = new DecimalFormat("0000");
        this.setSize(150, 150);
        this.setPosition(x, y);
        time = 1;
        for(int i = 0; i < max_frame; i++){
            int tmp = i + 1;
            images[i] = new Texture(player_image+dc.format(tmp)+img_ext);
        }
        this.setTexture(images[0]);
        BodyDef body_def = new BodyDef();
        body_def.type = BodyDef.BodyType.DynamicBody;
        body_def.position.set(this.getX(), this.getY());
        
        body = SpriteManager.world.createBody(body_def);
        
        PolygonShape shape = new PolygonShape();
        
        shape.setAsBox(this.getWidth()/2, this.getHeight()/2);
        
        FixtureDef fixture_def = new FixtureDef();
        fixture_def.shape = shape;
        fixture_def.density = 1f;
        
        
        Fixture fixture = body.createFixture(fixture_def);
        
        
        shape.dispose();
        
        
    }

    @Override
    public void update() {
        
        this.setPosition(body.getPosition().x, body.getPosition().y);
        if(time % 3 == 0){
            prev_frame = frame;
            if (anim_incr > 0 && frame == max_frame - 1){
                anim_incr = -1;       
            }
            else if(anim_incr < 0 && frame == 0){
                anim_incr = 1;
            }
            frame += anim_incr;
        }
        
        if(time != 100){
            time++;
        }
        else{
            time = 1;
        }
        System.out.println("X: "+getX()+" Y: "+getY());
        this.setTexture(images[frame]);
    }

    @Override
    public void render() {
    }
    
    public void jump(){
        
    }
    
    @Override
    public void receive() {
        System.out.println("RECEIVE");
        int[] reg_keys = InputListener.registered_keys;
        for(int i = 0; i < InputListener.num_registered_keys; i++){
            if(Gdx.input.isKeyPressed(reg_keys[i])){
                switch (reg_keys[i]){
                     case Keys.A:
                         System.out.println("A");
                         x_pos -= 5;
                         break;
                     case Keys.D:
                         System.out.println("D");
                         x_pos += 5;
                         break;
                     case Keys.S:
                         System.out.println("S");
                         y_pos -= 5;
                         break;
                     case Keys.W:
                         System.out.println("W");
                         y_pos += 5;
                         break;
                     default:
                         break;
                }
                    
            }
               
        }
       
    }
    
}
