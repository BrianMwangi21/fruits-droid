/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.fruitservice.tasks;

import com.mycompany.fruitservice.constants.Constants;
import com.mycompany.fruitservice.interfaces.TaskInterface;
import com.mycompany.fruitservice.models.Fruit;

import java.io.Serializable;

/**
 *
 * 
 */
public class UpdateFruitPrice implements TaskInterface,Serializable  {

    @Override
    public void execute(Fruit fruit) {
        System.out.println("updating fruit price of " + fruit.toString());
    }

    @Override
    public String getTaskName() {
        return Constants.UPDATE;
    }

}
