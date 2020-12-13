/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.fruitservice.services;

import com.mycompany.fruitservice.interfaces.TasksInterface;
import com.mycompany.fruitservice.models.Fruit;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;

/**
 *
 * @author haamani
 */
public class TasksComputeEngine extends UnicastRemoteObject implements TasksInterface {

    private ArrayList<Fruit> fruits = new ArrayList<>();
    private String _fruits[] = new String[]{"Apple", "Watermelon", "Passion", "Banana"};
    private int _prices[] = new int[]{30, 40, 35, 50};

    public TasksComputeEngine() throws RemoteException {
        super();
    }

    @Override
    public ArrayList<Fruit> initFruits() throws RemoteException {
        for( int i = 0; i < _fruits.length; ++i ) {
            fruits.add( new Fruit( _fruits[i], _prices[i] ) );
        }

        return fruits;
    }

    @Override
    public ArrayList<Fruit> addFruit(Fruit fruit) throws RemoteException {
        fruits.add( fruit );

        return fruits;
    }

    @Override
    public ArrayList<Fruit> updateFruit(Fruit oldFruit, Fruit newFruit) throws RemoteException {
        for( int i = 0; i < fruits.size(); ++i ) {
            if( fruits.get(i) == oldFruit ) {
                fruits.get(i).setName( newFruit.getName() );
                fruits.get(i).setPrice( newFruit.getPrice() );
            }
        }

        return fruits;
    }

    @Override
    public ArrayList<Fruit> deleteFruit(int index) throws RemoteException {
        fruits.remove(index);
        return fruits;
    }

    @Override
    public int calculateCost(Fruit fruit, int quantity) throws RemoteException {
        return (fruit.getPrice() * quantity);
    }
}
