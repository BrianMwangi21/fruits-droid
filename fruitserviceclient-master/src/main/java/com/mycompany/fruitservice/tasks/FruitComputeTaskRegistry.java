/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.fruitservice.tasks;

import com.mycompany.fruitservice.constants.Constants;
import com.mycompany.fruitservice.interfaces.ComputeInterface;

import java.rmi.AlreadyBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

/**
 *
 * @author haamani
 */
public class FruitComputeTaskRegistry {

    ComputeInterface engine;

    public FruitComputeTaskRegistry(ComputeInterface engine) {
        this.engine = engine;
    }

    public void runTask() throws  AlreadyBoundException, RemoteException {
        Registry registry = LocateRegistry.createRegistry(Constants.RMI_PORT);
        registry.bind(Constants.RMI_ID, this.engine);
    }
}
