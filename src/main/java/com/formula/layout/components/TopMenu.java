package com.formula.layout.components;

import com.formula.FormulaRouteTelemetryMain;
import com.formula.layout.ApplicationWindow;
import com.formula.layout.LayoutSizeManager;
import javafx.animation.FadeTransition;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Cursor;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Tooltip;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.*;
import javafx.util.Duration;
import org.kordamp.ikonli.javafx.FontIcon;

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

        navBarButton = new ImageView(Config.getIcon(Config.OPEN_NAVBAR_ICON));
        navBarButton.setFitHeight(15);
        navBarButton.setFitWidth(15);
        navBarButton.setPreserveRatio(true);

        navBarButtonViewContainer.setCursor(Cursor.HAND);

        navBarButtonViewContainer.setStyle("-fx-background-radius: 2px;");
        centerButtonHBox.getChildren().add(navBarButton);
        navBarButtonViewContainer.getChildren().add(centerButtonHBox);

        navBarButtonViewContainer.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                navBarIsOpen = !navBarIsOpen;
                if(!navBarIsOpen)
                    navBarButton.setImage(new Image(FormulaRouteTelemetryMain.class.getResource("/mspm/icons/menu-dots-vertical.png").toString()));
                else
                    navBarButton.setImage(Config.getIcon(Config.OPEN_NAVBAR_ICON));

                applicationWindow.getViewManager().getNavBar().minimizeNavBar(navBarIsOpen);
            }
        });

        centerImageView = new ImageView(new Image(FormulaRouteTelemetryMain.class.getResource("/mspm/resources/LogoPreto.PNG.-transformed.png").toString()));
        centerImageView.setFitHeight(30);
        centerImageView.setFitWidth(150);
        centerImageView.setPreserveRatio(true);

        themeChangerButtonViewContainer = new Pane();
        themeChangerButton = new ImageView(Config.getIcon(Config.CHANGE_THEME_ICON));
        themeChangerButton.setFitHeight(15);
        themeChangerButton.setFitWidth(15);
        themeChangerButton.setPreserveRatio(true);
        themeChangerButtonViewContainer.setCursor(Cursor.HAND);

        themeChangerButtonViewContainer.setStyle("-fx-background-radius: 6;");

        themeChangerButtonViewContainer.getChildren().add(themeChangerButton);

        notificationButtonViewContainer = new Pane();
        notificationButton = new ImageView(Config.getIcon(Config.NOTIFICATION_ICON));
        notificationButton.setFitHeight(15);
        notificationButton.setFitWidth(15);
        notificationButton.setPreserveRatio(true);
        notificationButtonViewContainer.setCursor(Cursor.DEFAULT);

        notificationButtonViewContainer.setStyle("-fx-background-radius: 6;");

        FadeTransition fadeTransition = new FadeTransition(Duration.millis(250), notificationButtonViewContainer);
        fadeTransition.setFromValue(1.0);
        fadeTransition.setToValue(0.7);

        notificationButtonViewContainer.setOnMouseEntered(event -> {
            fadeTransition.play();
            notificationButtonViewContainer.setStyle("-fx-background-color: "+Config.getColorPalleteProperties("primary-button-color")+";");
        });

        notificationButtonViewContainer.setOnMouseExited(event -> {
            fadeTransition.stop();
            notificationButtonViewContainer.setStyle("-fx-background-color: "+Config.getColorPalleteProperties("primary-mousehover-color")+";");
            notificationButtonViewContainer.opacityProperty().set(1);
        });

        Tooltip.install(navBarButtonViewContainer, new CustomToolTip("Minimizar"));

        notificationButtonViewContainer.setOnMouseClicked(event -> {

        });

        notificationButtonViewContainer.getChildren().add(notificationButton);

        HBox headerHBox = new HBox(10);
        headerHBox.setMaxWidth(Double.MAX_VALUE);

        setMaxWidth(Double.MAX_VALUE);
        setPrefHeight(50);

        HBox.setMargin(navBarButtonViewContainer, LayoutSizeManager.getResizedInsert(18.0, 0, 0, 15.0));
        HBox.setMargin(centerImageView, LayoutSizeManager.getResizedInsert(10.0, 0, 0, 5.0));

        HBox.setMargin(themeChangerButtonViewContainer, LayoutSizeManager.getResizedInsert(20.0, 0, 0, 5.0));
        HBox.setMargin(notificationButtonViewContainer, LayoutSizeManager.getResizedInsert(20.0, 0, 0, 5.0));

        headerHBox.setAlignment(Pos.CENTER_LEFT);

        Region temperatureFiller = new Region(); HBox.setHgrow(temperatureFiller, Priority.ALWAYS);

        headerHBox.getChildren().addAll(navBarButtonViewContainer, centerImageView);
        getChildren().add(headerHBox);
    }

}
