package com.example.demo;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.chart.StackedBarChart;
import javafx.scene.chart.XYChart;
import javafx.scene.control.TextArea;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.ArrayList;

public class Chart {

    private Scene scene;
    private Stage stage;
    private Parent root;
    @FXML private StackedBarChart chart;
    @FXML private TextArea table;

    Algorithms algorithms = new Algorithms();

    public void back(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("Algorithms.fxml"));
        stage = (Stage)((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.setResizable(false);
        stage.show();
    }

    public void showTable() {
        table.setText(algorithms.table);


        XYChart.Series<Number, String> series = new XYChart.Series<>();
        ArrayList<XYChart.Series<Number, String>> list = new ArrayList<>();

        for (int i = 0; i < algorithms.numberOfProcess; i++) {
            series.setName(algorithms.PID[i]);
            if(i == 0) {
                series.getData().add(new XYChart.Data<>(algorithms.Finish[i], "Finish Time"));
            }
            else{
                series.getData().add(new XYChart.Data<>(algorithms.Finish[i] - algorithms.Finish[i-1], "Finish Time"));
            }
            list.add(series);
            series = new XYChart.Series<>();
        }
        chart.getData().setAll(list);
        list.clear();
    }
}
