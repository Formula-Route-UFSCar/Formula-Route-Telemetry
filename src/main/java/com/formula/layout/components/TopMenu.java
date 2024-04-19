package com.formula.layout.components;

import com.formula.FormulaRouteTelemetryMain;
import com.formula.layout.ApplicationWindow;
import com.formula.layout.LayoutSizeManager;
import javafx.geometry.Pos;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;

public class TopMenu extends AnchorPane {

    private ApplicationWindow applicationWindow;

    Pane navBarButtonViewContainer;
    ImageView navBarButton;
    ImageView centerImageView;
    Pane themeChangerButtonViewContainer;
    ImageView themeChangerButton;
    Pane notificationButtonViewContainer;
    ImageView notificationButton;

    boolean navBarIsOpen = true;

    public TopMenu(){

    }

    public TopMenu(ApplicationWindow applicationWindow){
        this.applicationWindow = applicationWindow;

        navBarButtonViewContainer = new AnchorPane();

        HBox centerButtonHBox = new HBox();
        centerButtonHBox.setAlignment(Pos.CENTER);

        centerImageView = new ImageView(new Image(FormulaRouteTelemetryMain.class.getResource("/formula/title logo.png").toString()));
        centerImageView.setFitHeight(180);
        centerImageView.setFitWidth(200);
        centerImageView.setPreserveRatio(true);

        HBox headerHBox = new HBox(10);
        headerHBox.setMaxWidth(Double.MAX_VALUE);

        setMaxWidth(Double.MAX_VALUE);
        setPrefHeight(50);

        HBox.setMargin(navBarButtonViewContainer, LayoutSizeManager.getResizedInsert(18.0, 0, 0, 15.0));
        HBox.setMargin(centerImageView, LayoutSizeManager.getResizedInsert(10.0, 0, 0, 5.0));

        headerHBox.setAlignment(Pos.CENTER_LEFT);

        Region temperatureFiller = new Region(); HBox.setHgrow(temperatureFiller, Priority.ALWAYS);

        headerHBox.getChildren().addAll(navBarButtonViewContainer, centerImageView);
        getChildren().add(headerHBox);
    }

}
