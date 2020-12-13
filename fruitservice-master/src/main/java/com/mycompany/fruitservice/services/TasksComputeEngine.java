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
        System.out.println("initFruits called");

        fruits.clear();
        for( int i = 0; i < _fruits.length; ++i ) {
            fruits.add( new Fruit( _fruits[i], _prices[i] ) );
        }
        return fruits;
    }

    @Override
    public ArrayList<Fruit> addFruit(Fruit fruit) throws RemoteException {
        System.out.println("addFruits called with value " + fruit.toString());

        fruits.add( fruit );
        return fruits;
    }

    @Override
    public ArrayList<Fruit> updateFruit(Fruit newFruit, int index) throws RemoteException {
        System.out.println("updateFruit called with value " + index + " and " + newFruit.toString() );

        fruits.set(index, newFruit);
        return fruits;
    }

    @Override
    public ArrayList<Fruit> deleteFruit(int index) throws RemoteException {
        System.out.println("deleteFruits called with value " + Integer.toString(index));

        fruits.remove(index);
        return fruits;
    }

    @Override
    public int calculateCost(Fruit fruit, int quantity) throws RemoteException {
        System.out.println("calculateFruits called with value " + fruit.toString() + " and " + Integer.toString(quantity));
        return (fruit.getPrice() * quantity);
    }

    @Override
    public int calculateChange(int totalAmount, int cartAmount) throws RemoteException {
        System.out.println("calculateChange called with value " + totalAmount + " and " + cartAmount);
        return (totalAmount - cartAmount);
    }
}
