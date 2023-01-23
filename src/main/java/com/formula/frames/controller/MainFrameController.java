package com.formula.frames.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.chart.LineChart;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;

import java.net.URL;
import java.util.ResourceBundle;

public class MainFrameController{

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private MenuItem connect_menu_button;

    @FXML
    private MenuItem debug_menu_button;

    @FXML
    private MenuBar menu_bar;

    @FXML
    private LineChart<?, ?> torque_velocity_chart;

    @FXML
    void onClickInConnectButton(ActionEvent event) {

    }

    @FXML
    void onClickInDebugButton(ActionEvent event) {

    }

    @FXML
    void initialize() {
        assert connect_menu_button != null : "fx:id=\"connect_menu_button\" was not injected: check your FXML file 'MainFrame.fxml'.";
        assert debug_menu_button != null : "fx:id=\"debug_menu_button\" was not injected: check your FXML file 'MainFrame.fxml'.";
        assert menu_bar != null : "fx:id=\"menu_bar\" was not injected: check your FXML file 'MainFrame.fxml'.";
        assert torque_velocity_chart != null : "fx:id=\"torque_velocity_chart\" was not injected: check your FXML file 'MainFrame.fxml'.";

    }

}
