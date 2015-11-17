/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.johnogel.iceqube;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import java.util.ArrayList;

/**
 *
 * @author johno-gel
 */
public class InputListener implements Subject{
private ArrayList<Observer> observers;
public static int[] registered_keys;
public static int num_registered_keys;
    public InputListener(){
        num_registered_keys = 4;
        observers = new ArrayList();
        registered_keys = new int[10];
        registered_keys[0] = Keys.A;
        registered_keys[1] = Keys.S;
        registered_keys[2] = Keys.D;
        registered_keys[3] = Keys.W;
        
        
    }
    
    public void checkInput(){
        for(int i = 0; i < num_registered_keys; i++){
            if(Gdx.input.isKeyPressed(registered_keys[i])){
                notifyObservers();
                break;
            }
        }
        
    }
    
    public int[] getKeys(){
        return registered_keys;
    }
    
    public int getNumKeys(){
        return num_registered_keys;
    }
    
    @Override
    public void registerObserver(Observer o) {
        observers.add(o);
    }

    @Override
    public void removeObserver(Observer o) {
        observers.remove(o);
    }

    @Override
    public void notifyObservers() {
        for (Observer o : observers){
            o.receive();
        }
    }
    
    
    
}
