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

public class Chart {

    private Scene scene;
    private Stage stage;
    private Parent root;
    @FXML private Chart chart;
    @FXML private TextArea table;

    Algorithms algorithms = new Algorithms();


    public void back(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("Algorithms.fxml"));
        stage = (Stage)((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    public void showTable() {
        System.out.println(algorithms.table);
        table.setText(algorithms.table);
    }
}
