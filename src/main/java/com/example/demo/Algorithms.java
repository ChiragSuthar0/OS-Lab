package com.example.demo;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.ArrayList;

public class Algorithms {

    private Stage stage;
    private Scene scene;
    private Parent root;

    public void changeToAlgorithms(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("Algorithms.fxml"));
        stage = (Stage)((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    public void changeToSimulation(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("Simulation.fxml"));
        stage = (Stage)((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    @FXML
            private TextField numberOfProcesses;
    @FXML
            private RadioButton radioButtonFCFS, radioButtonSJF, radioButtonSRTF, radioButtonPriority;
    @FXML
            private TextField BurstTime;
    @FXML
            private TextField ArrivalTime;
    @FXML
            protected TextField priority;
    int numberOfProcess;

    ArrayList<Integer> BT = new ArrayList<>();
    ArrayList<Integer> AT = new ArrayList<>();
    ArrayList<Integer> P = new ArrayList<>();
    public static String table;

    public void runSimulation(ActionEvent event) throws IOException {

        priority.setEditable(false);

        numberOfProcess = Integer.parseInt(numberOfProcesses.getText());
        System.out.println(numberOfProcess);

        String[] bt = BurstTime.getText().split(",");
        String[] at = ArrivalTime.getText().split(",");

        for (int i = 0; i < bt.length; i++) {
            BT.add(Integer.parseInt(bt[i]));
        }

        for (int i = 0; i < at.length; i++) {
            AT.add(Integer.parseInt(at[i]));
        }

        BT.forEach(integer -> System.out.print(integer + ",  "));

        if(radioButtonFCFS.isSelected()) {

            FirstComeFirstServe obj = new FirstComeFirstServe();
            obj.getProcessData(numberOfProcess, BT, AT);
            table = obj.firstComeFirstServeAlgorithm();
        }
        else if(radioButtonPriority.isSelected()) {
            priority.setEditable(true);

            String[] p = priority.getText().split(",");

            for (int i = 0; i < p.length; i++) {
                P.add(Integer.parseInt(bt[i]));
            }
            P.forEach(integer -> System.out.println(integer));

            NonPreemptivePriorityCPUSchedulingAlgorithm obj = new NonPreemptivePriorityCPUSchedulingAlgorithm();
            obj.getProcessData(numberOfProcess, BT, AT, P);
            table = obj.priorityNonPreemptiveAlgorithm();
        }
        else if(radioButtonSJF.isSelected()) {
            SJF sjf = new SJF();
            table = sjf.Main(numberOfProcess, BT, AT);
        }
        else if(radioButtonSRTF.isSelected()) {
            SRTF srtf = new SRTF();
            table = srtf.Main(numberOfProcess, BT, AT);
        }


        Parent root = FXMLLoader.load(getClass().getResource("Chart.fxml"));
        stage = (Stage)((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();

    }

}



















