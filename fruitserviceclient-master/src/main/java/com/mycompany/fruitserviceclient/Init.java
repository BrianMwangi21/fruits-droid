/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.fruitserviceclient;

import com.mycompany.fruitservice.constants.Constants;
import com.mycompany.fruitservice.interfaces.ComputeInterface;
import com.mycompany.fruitservice.interfaces.TaskInterface;
import com.mycompany.fruitservice.interfaces.TasksInterface;
import com.mycompany.fruitservice.models.Fruit;
import com.mycompany.fruitservice.tasks.AddFruitPrice;
import com.mycompany.fruitservice.tasks.DeleteFruitPrice;
import com.mycompany.fruitservice.tasks.UpdateFruitPrice;
import java.net.MalformedURLException;
import static java.nio.file.Files.delete;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

/**
 *
 * 
 */
public class Init {

    /**
     * @param args the command line arguments
     * @throws java.rmi.RemoteException
     * @throws java.rmi.NotBoundException
     * @throws java.net.MalformedURLException
     */
    public static void main(String[] args) throws RemoteException, NotBoundException, MalformedURLException {
        Registry registry = LocateRegistry.getRegistry(Constants.RMI_PORT);
        TasksInterface engine = (TasksInterface) registry.lookup(Constants.RMI_ID);

        System.out.println(engine.initFruits());
        System.out.println(engine.deleteFruit(0));
    }
}