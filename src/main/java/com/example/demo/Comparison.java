/*
 * Copyright (c) product to Chirag Suthar. You cannot use this software without permission from the Owner. You may face severe charges if you do so.
 */

package com.example.demo;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TextArea;
import javafx.stage.Stage;

import java.io.IOException;

public class Comparison {
    private Scene scene;
    private Stage stage;

    @FXML
    private TextArea table1;
    @FXML
    private TextArea table2;
    @FXML
    private TextArea table3;
    @FXML
    private TextArea table4;

    public void back(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("Algorithms.fxml"));
        stage = (Stage)((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.setResizable(false);
        stage.show();
    }

    public void showTables(ActionEvent event) throws IOException {
        Algorithms algorithms = new Algorithms();
        table1.setText(algorithms.comparisonTable.get(0));
        table2.setText(algorithms.comparisonTable.get(1));
        table3.setText(algorithms.comparisonTable.get(2));
        table4.setText(algorithms.comparisonTable.get(3));
    }
}
