/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.fruitservice.interfaces;

import com.mycompany.fruitservice.models.Fruit;

import java.rmi.Remote;
import java.rmi.RemoteException;

/**
 *
 * 
 */
public interface ComputeInterface extends Remote {

    public void compute(TaskInterface task, Fruit fruit)throws RemoteException; 
}
