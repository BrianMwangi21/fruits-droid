package com.mycompany;

import com.mycompany.fruitservice.constants.Constants;
import com.mycompany.fruitservice.interfaces.TasksInterface;
import com.mycompany.fruitservice.models.Fruit;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;

import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.ArrayList;

public class Controller {
    @FXML
    TableView<Fruit> fruitsTable, cartTable;

    @FXML
    TableColumn fruitNameCol, fruitPriceCol, cartfruitNameCol, cartfruitPriceCol, cartfruitQuantityCol;

    @FXML
    TextField quantityTextField, newFruitNameTextField, newFruitPriceTextField, updateFruitNameTextField, updateFruitPriceTextField, amountTextField;

    @FXML
    Label totalLabel, statusLabel, cartTotalLabel, changeAmountLabel;


    ObservableList<Fruit> initData = FXCollections.observableArrayList();
    ObservableList<Fruit> cartData = FXCollections.observableArrayList();
    Registry registry;
    TasksInterface engine;
    Fruit selectedFruit = null;

    @FXML
    public void initialize() throws RemoteException, NotBoundException {
        statusLabel.setText("Connecting to server.......");
        try {
            registry = LocateRegistry.getRegistry(Constants.RMI_PORT);
            engine = (TasksInterface) registry.lookup(Constants.RMI_ID);
            statusLabel.setText("Connection to server successful");

            // Init the fruits and then display on the table
            ArrayList<Fruit> initFruits = engine.initFruits();
            for( int i = 0; i < initFruits.size(); ++i ) {
                initData.add( initFruits.get(i) );
            }

            // Initialize fruitsTable
            fruitNameCol.setCellValueFactory(new PropertyValueFactory<Fruit, String>("name"));
            fruitPriceCol.setCellValueFactory(new PropertyValueFactory<Fruit, Integer>("price"));
            fruitsTable.setItems(initData);
            fruitsTable.setOnMouseClicked((MouseEvent event) -> {
                if (event.getButton().equals(MouseButton.PRIMARY)) {
                    int index = fruitsTable.getSelectionModel().getSelectedIndex();
                    selectedFruit = fruitsTable.getItems().get(index);
                    statusLabel.setText(selectedFruit.getName() + " selected");
                }
            });

            // Initialize cartTable
            cartfruitNameCol.setCellValueFactory(new PropertyValueFactory<Fruit, String>("name"));
            cartfruitPriceCol.setCellValueFactory(new PropertyValueFactory<Fruit, Integer>("price"));
            cartfruitQuantityCol.setCellValueFactory(new PropertyValueFactory<Fruit, Integer>("quantity"));
        }catch(Exception e) {
            e.printStackTrace();
            statusLabel.setText("Error connecting to server. Run server and try again.");
        }
    }

    @FXML
    private void calculateTotal() throws RemoteException, NotBoundException {
        if( selectedFruit == null ) {
            statusLabel.setText("Please select fruit from list to perform action");
            return;
        }

        if( quantityTextField.getText().isEmpty() ) {
            statusLabel.setText("Please enter quantity to perform action");
            return;
        }

        try {
            int quantity = Integer.parseInt( quantityTextField.getText() );
            int total = engine.calculateCost( selectedFruit, quantity );
            totalLabel.setText("TOTAL : " + Integer.toString(total) + "/=");
        }catch( NumberFormatException e ) {
            statusLabel.setText("Quantity must be a digit");
            return;
        }

        // Clear fields
        quantityTextField.setText("");
    }

    @FXML
    private void saveFruit() throws RemoteException, NotBoundException {
        if( newFruitNameTextField.getText().isEmpty() ) {
            statusLabel.setText("Please enter new fruit name to perform action");
            return;
        }

        if( newFruitPriceTextField.getText().isEmpty() ) {
            statusLabel.setText("Please enter new fruit price to perform action");
            return;
        }

        try {
            String newName = newFruitNameTextField.getText();
            int newPrice = Integer.parseInt( newFruitPriceTextField.getText() );

            ArrayList<Fruit> newFruits = engine.addFruit( new Fruit(newName, newPrice) );
            initData.clear();
            for( int i = 0; i < newFruits.size(); ++i ) {
                initData.add( newFruits.get(i) );
            }
            fruitsTable.setItems(initData);
            statusLabel.setText("New fruit [ " + newName + " ] added, view table for changes");
        }catch( NumberFormatException e ) {
            statusLabel.setText("New fruit price must be a digit");
            return;
        }

        // Clear fields
        newFruitNameTextField.setText("");
        newFruitPriceTextField.setText("");
    }

    @FXML
    private void deleteFruit() throws RemoteException, NotBoundException {
        if( selectedFruit == null ) {
            statusLabel.setText("Please select fruit from list to perform action");
            return;
        }

        int index = fruitsTable.getSelectionModel().getSelectedIndex();

        try {
            ArrayList<Fruit> newFruits = engine.deleteFruit(index);
            initData.clear();
            for( int i = 0; i < newFruits.size(); ++i ) {
                initData.add( newFruits.get(i) );
            }
            fruitsTable.setItems(initData);
            statusLabel.setText("Fruit [ " + selectedFruit.getName() + " ] deleted, view table for changes");
        }catch(Exception e) {
            statusLabel.setText("Error when deleting fruit. Please try again");
            return;
        }
    }

    @FXML
    private void updateFruit() throws RemoteException, NotBoundException {
        if( selectedFruit == null ) {
            statusLabel.setText("Please select fruit from list to perform action");
            return;
        }

        if( updateFruitNameTextField.getText().isEmpty() ) {
            statusLabel.setText("Please enter updated fruit name to perform action");
            return;
        }

        if( updateFruitPriceTextField.getText().isEmpty() ) {
            statusLabel.setText("Please enter updated fruit price to perform action");
            return;
        }

        int index = fruitsTable.getSelectionModel().getSelectedIndex();

        try {
            String updateName = updateFruitNameTextField.getText();
            int updatePrice = Integer.parseInt( updateFruitPriceTextField.getText() );

            ArrayList<Fruit> newFruits = engine.updateFruit(new Fruit(updateName, updatePrice), index);
            initData.clear();
            for( int i = 0; i < newFruits.size(); ++i ) {
                initData.add( newFruits.get(i) );
            }
            fruitsTable.setItems(initData);
            statusLabel.setText("Fruit at index [ " + (index + 1) + " ] updated, view table for changes");
        }catch( NumberFormatException e ) {
            statusLabel.setText("Updated fruit price must be a digit");
            return;
        }

        // Clear fields
        updateFruitNameTextField.setText("");
        updateFruitPriceTextField.setText("");
    }

    @FXML
    private void addToCart() throws RemoteException, NotBoundException {
        if( selectedFruit == null ) {
            statusLabel.setText("Please select fruit from list to perform action");
            return;
        }

        int cartTotal = 0;

        // Add quantity
        selectedFruit.setQuantity( selectedFruit.getQuantity() + 1 );

        // Check if cart data is empty, else, add to cart
        if( cartData.isEmpty() ) {
            cartData.add(selectedFruit);
        }else {
            boolean newEntry = true;

            for( int i = 0; i < cartData.size(); ++i ) {
                if( cartData.get(i).getName().equals( selectedFruit.getName() ) ) {
                    cartData.set( i, selectedFruit );
                    newEntry = false;
                }
            }

            if( newEntry ) {
                cartData.add(selectedFruit);
            }
        }

        // Get cartTotal
        for( int i = 0; i < cartData.size(); ++i ) {
            cartTotal += engine.calculateCost( cartData.get(i), cartData.get(i).getQuantity() );
        }

        // Save to cartTable
        cartTotalLabel.setText("CART TOTAL: " + Integer.toString(cartTotal)  + "/=");
        cartTable.setItems(cartData);
        statusLabel.setText("Fruit [ " + selectedFruit.getName() + " ] added to cart, view cart for changes");
    }

    @FXML
    public void calculateChange() throws RemoteException, NotBoundException {
        if( cartData.isEmpty() ) {
            statusLabel.setText("Add items to cart to perform action");
            return;
        }

        try {
            int amountTotal = Integer.parseInt( amountTextField.getText() );
            int cartTotal = 0;
            for( int i = 0; i < cartData.size(); ++i ) {
                cartTotal += engine.calculateCost( cartData.get(i), cartData.get(i).getQuantity() );
            }

            // Amount must be greater than cartTotal
            if( amountTotal < cartTotal ) {
                statusLabel.setText("Amount total must exceed cart total");
                return;
            }

            int changeAmount = engine.calculateChange(amountTotal, cartTotal);
            changeAmountLabel.setText("CHANGE AMOUNT: " + Integer.toString(changeAmount) + "/=");
            statusLabel.setText("Change amount calculated");
        }catch( NumberFormatException e ) {
            statusLabel.setText("Amount must be a digit");
            return;
        }
    }

    @FXML
    private void deleteCart() throws RemoteException, NotBoundException {
        cartData.clear();
        for( int i = 0; i < initData.size(); ++i ) {
            initData.get(i).setQuantity( 0 );
        }

        // Clear fields
        cartTotalLabel.setText("CART TOTAL: ");
        amountTextField.setText("");
        changeAmountLabel.setText("CHANGE AMOUNT: ");
    }
}
