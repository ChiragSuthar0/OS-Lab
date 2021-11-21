/*
 * Copyright (c) product to Chirag Suthar. You cannot use this software without permission from the Owner. You may face severe charges if you do so.
 */

package com.example.demo;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class Home {
    private Stage stage;
    private Scene scene;
    private Parent root;

    public void changeToHome(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("Home.fxml"));
        stage = (Stage)((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.setResizable(false);
        stage.show();
    }

    public void changeToAlgorithms(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("Algorithms.fxml"));
        stage = (Stage)((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.setResizable(false);
        stage.show();
    }

    public void changeToMeetTeam(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("meetTheTeam.fxml"));
        stage = (Stage)((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.setResizable(false);
        stage.show();
    }

    public void changeToSimulation(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("Simulation.fxml"));
        stage = (Stage)((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.setResizable(false);
        stage.show();
    }
}
