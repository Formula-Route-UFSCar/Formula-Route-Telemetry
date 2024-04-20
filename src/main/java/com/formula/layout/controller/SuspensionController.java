package com.formula.layout.controller;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.formula.layout.ApplicationWindow;
import com.formula.layout.javafx.utils.Controller;
import com.formula.layout.view.DashboardView;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.chart.PieChart;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

public class SuspensionController extends Controller {
    DashboardView dashboardView;
    ApplicationWindow applicationWindow;

    public SuspensionController(ApplicationWindow applicationWindow) {
        this.applicationWindow = applicationWindow;
    }

    @Override
    public void init() {
        dashboardView = applicationWindow.getViewManager().getDashboardView();
    }

    @Override
    public void close() {

    }

    @Override
    public void update() {

    }

}
