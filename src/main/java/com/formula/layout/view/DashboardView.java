package com.formula.layout.view;


import com.formula.layout.ApplicationWindow;
import com.formula.layout.components.Card;
import com.formula.layout.components.MinimizedCard;
import com.formula.layout.components.charts.CustomBarChart;
import com.formula.layout.controller.DashboardController;
import com.formula.layout.javafx.utils.Page;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.chart.*;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class DashboardView extends Page {

    private Card workorderStateCard;
    private Card workorderPriorityCard;
    private Card workordersByAssetCard;

    private MinimizedCard averageOSTimeCard;
    private MinimizedCard mpCard;
    private MinimizedCard mpdCard;

    private Card mtbfCard;
    private Card mttrCard;
    private Card mttaCard;

    private PieChart workorderStateChart;
    private PieChart workorderPriorityChart;
    private CustomBarChart workordersByAssetChart = new CustomBarChart();

    private CustomBarChart MTBFChart = new CustomBarChart();
    private CustomBarChart MTTRChart = new CustomBarChart();
    private CustomBarChart MTTAChart = new CustomBarChart();

    public DashboardView(ApplicationWindow applicationWindow) {
        super(applicationWindow, "/mspm/pages/DashboardCSS.css");
        this.controller = new DashboardController(applicationWindow);
        createView();
    }

    public void createView() {
        getStyleClass().add("body");
        setMinHeight(1100);

        ScrollPane scrollPane = new ScrollPane();
        scrollPane.setPrefHeight(1000);
        scrollPane.setPrefWidth(1000);

        scrollPane.setFitToHeight(true);
        scrollPane.setFitToWidth(true);
        scrollPane.setFocusTraversable(false);

        scrollPane.setHbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);

        scrollPane.getStyleClass().add("scroll");

        AnchorPane.setBottomAnchor(scrollPane, 0.0);
        AnchorPane.setLeftAnchor(scrollPane, 0.0);
        AnchorPane.setRightAnchor(scrollPane, 0.0);
        AnchorPane.setTopAnchor(scrollPane, 0.0);

        AnchorPane contentAnchorPane = new AnchorPane();
        contentAnchorPane.setPrefHeight(1500);
        contentAnchorPane.setMinHeight(1500);
        contentAnchorPane.setMaxHeight(contentAnchorPane.getPrefHeight());
        contentAnchorPane.getStyleClass().add("body");


        scrollPane.setContent(contentAnchorPane);

        getChildren().add(scrollPane);
    }

}
