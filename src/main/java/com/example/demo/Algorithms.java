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
import java.util.Arrays;

public class Algorithms {

    private Stage stage;
    private Scene scene;
    private Parent root;

    public void changeToAlgorithms(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("Algorithms.fxml"));
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

    public void changeToMeetTeam(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("meetTheTeam.fxml"));
        stage = (Stage)((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.setResizable(false);
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
            private TextField priority;
    static int numberOfProcess;
    static String[] PID;
    static ArrayList<String> comparisonTable = new ArrayList<>();

    ArrayList<Integer> BT = new ArrayList<>();
    ArrayList<Integer> AT = new ArrayList<>();
    ArrayList<Integer> P = new ArrayList<>();
    public static String table;
    static int[] Finish;// = new int[Integer.parseInt(numberOfProcesses.getText())];

    public void assignData(){
        numberOfProcess = Integer.parseInt(numberOfProcesses.getText());
        System.out.println(numberOfProcess);
        Finish = new int[numberOfProcess];

        String[] bt = BurstTime.getText().split(",");
        String[] at = ArrivalTime.getText().split(",");

        if (priority.getText() != ""){
            String[] p = priority.getText().split(",");

            for (int i = 0; i < p.length; i++) {
                P.add(Integer.parseInt(p[i]));
            }
        }

        for (int i = 0; i < bt.length; i++) {
            BT.add(Integer.parseInt(bt[i]));
        }

        for (int i = 0; i < at.length; i++) {
            AT.add(Integer.parseInt(at[i]));
        }
    }

    public void runSimulation(ActionEvent event) throws IOException {

        priority.setEditable(false);

        assignData();

//        BT.forEach(integer -> System.out.print(integer + ",  "));

        if(radioButtonFCFS.isSelected()) {
            FirstComeFirstServe obj = new FirstComeFirstServe();
            obj.getProcessData(numberOfProcess, BT, AT);
            table = obj.firstComeFirstServeAlgorithm();
            Finish = obj.Finish.clone();
            PID = obj.ProcessId.clone();
        }
        else if(radioButtonPriority.isSelected()) {
            priority.setEditable(true);

            NonPreemptivePriorityCPUSchedulingAlgorithm obj = new NonPreemptivePriorityCPUSchedulingAlgorithm();
            obj.getProcessData(numberOfProcess, BT, AT, P);
            table = obj.priorityNonPreemptiveAlgorithm();
            Finish = obj.Finish.clone();
            PID = obj.ProcessID.clone();
        }
        else if(radioButtonSJF.isSelected()) {
            SJF sjf = new SJF();
            table = sjf.Main(numberOfProcess, BT, AT);
            Finish = sjf.Finish.clone();
            PID = new String[numberOfProcess];
            for (int i = 0; i < numberOfProcess; i++) {
                PID[i] = String.valueOf(sjf.ProcessID[i]);
            }
        }
        else if(radioButtonSRTF.isSelected()) {
            SRTF srtf = new SRTF();
            table = srtf.Main(numberOfProcess, BT, AT);
            PID = new String[numberOfProcess];
            for (int i = 0; i < numberOfProcess; i++) {
                PID[i] = String.valueOf(i);
            }
        }


        Parent root = FXMLLoader.load(getClass().getResource("Chart.fxml"));
        stage = (Stage)((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.setResizable(false);
        stage.show();
    }

    public void runComparison(ActionEvent event) throws IOException{
        priority.setEditable(true);

        assignData();

        FirstComeFirstServe obj = new FirstComeFirstServe();
        obj.getProcessData(numberOfProcess, BT, AT);
        comparisonTable.add(obj.firstComeFirstServeAlgorithm());


        NonPreemptivePriorityCPUSchedulingAlgorithm obj1 = new NonPreemptivePriorityCPUSchedulingAlgorithm();
        obj1.getProcessData(numberOfProcess, BT, AT, P);
        comparisonTable.add(obj1.priorityNonPreemptiveAlgorithm());

        SJF sjf = new SJF();
        comparisonTable.add(sjf.Main(numberOfProcess, BT, AT));

        SRTF srtf = new SRTF();
        comparisonTable.add(srtf.Main(numberOfProcess, BT, AT));

        Parent root = FXMLLoader.load(getClass().getResource("Comparison.fxml"));
        stage = (Stage)((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.setResizable(false);
        stage.show();
    }
}



















