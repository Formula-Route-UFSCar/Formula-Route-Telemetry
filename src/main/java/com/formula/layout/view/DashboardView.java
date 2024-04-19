package com.formula.layout.view;


import com.formula.layout.ApplicationWindow;
import com.formula.layout.LayoutSizeManager;
import com.formula.layout.components.Card;
import com.formula.layout.components.MinimizedCard;
import com.formula.layout.components.charts.CustomBarChart;
import com.formula.layout.controller.DashboardController;
import com.formula.layout.javafx.utils.BadgeStyle;
import com.formula.layout.javafx.utils.Page;
import javafx.scene.chart.*;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class DashboardView extends Page {

    public DashboardView(ApplicationWindow applicationWindow) {
        super(applicationWindow, "/formula/pages/DashboardCSS.css");
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
        AnchorPane.setLeftAnchor(scrollPane, 20.0);
        AnchorPane.setRightAnchor(scrollPane, 0.0);
        AnchorPane.setTopAnchor(scrollPane, 0.0);

        AnchorPane contentAnchorPane = new AnchorPane();
        contentAnchorPane.setPrefHeight(1500);
        contentAnchorPane.setMinHeight(1500);
        contentAnchorPane.setMaxHeight(contentAnchorPane.getPrefHeight());
        contentAnchorPane.getStyleClass().add("body");

        HBox hbox1 = new HBox();

        //Card testCard = new Card("","","");

        MinimizedCard testMinimizedCard = new MinimizedCard("Temperatura de Freio","22°C","+2.0%", BadgeStyle.DANGER);

        HBox.setMargin(testMinimizedCard, LayoutSizeManager.getResizedInsert(0.0, 0.0, 00.0, 0.0));

        hbox1.getChildren().addAll(testMinimizedCard);

        contentAnchorPane.getChildren().addAll(hbox1);
        scrollPane.setContent(contentAnchorPane);

        getChildren().add(scrollPane);
    }

}
