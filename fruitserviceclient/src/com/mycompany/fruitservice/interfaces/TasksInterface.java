package com.mycompany.fruitservice.interfaces;

import com.mycompany.fruitservice.models.Fruit;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.ArrayList;

public interface TasksInterface<T> extends Remote {

    public ArrayList<Fruit> initFruits() throws RemoteException;

    public ArrayList<Fruit> addFruit(Fruit fruit) throws RemoteException;

    public ArrayList<Fruit> updateFruit(Fruit newfruit, int index) throws RemoteException;

    public ArrayList<Fruit> deleteFruit(int index) throws RemoteException;

    public int calculateCost(Fruit fruit, int quantity) throws RemoteException;

    public int calculateChange(int totalAmount, int cartAmount) throws RemoteException;
}
