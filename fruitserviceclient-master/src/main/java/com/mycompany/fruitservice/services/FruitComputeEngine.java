/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.fruitservice.services;

import com.mycompany.fruitservice.interfaces.ComputeInterface;
import com.mycompany.fruitservice.interfaces.TaskInterface;
import com.mycompany.fruitservice.models.Fruit;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

/**
 *
 * @author haamani
 */
public class FruitComputeEngine extends UnicastRemoteObject implements ComputeInterface {

    public FruitComputeEngine() throws RemoteException {
        super();
    }

    @Override
    public void compute(TaskInterface task, Fruit fruit) {
        task.execute(fruit);
    }

}
