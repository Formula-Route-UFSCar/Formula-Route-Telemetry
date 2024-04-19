package com.formula.layout.components.navbar;

import com.formula.FormulaRouteTelemetryMain;
import com.formula.layout.ApplicationWindow;
import com.formula.layout.LayoutSizeManager;
import com.formula.layout.javafx.utils.Page;
import javafx.animation.Animation;
import javafx.animation.FadeTransition;
import javafx.animation.Interpolator;
import javafx.animation.Transition;
import javafx.geometry.Insets;
import javafx.scene.Cursor;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.util.Duration;
import lombok.Getter;
import lombok.Setter;
import org.kordamp.ikonli.javafx.FontIcon;

import javax.swing.text.Element;
import java.util.ArrayList;
import java.util.List;

@Getter @Setter
public class NavBar extends VBox {

    private ApplicationWindow applicationWindow;

    public static final String NAVBAR_ID = "navbar";
    HBox dashboardBox;
    Label dashboardLabel;
    HBox workorderHBox;
    Separator workorderSeparator;
    Separator dashboardSeparator;

    List<NavigationButton> navigationButtonList = new ArrayList<>();

    private double navBarWidth = 200;
    private double navBarHeight = 200;

    public NavBar(){

    }

    public NavBar(ApplicationWindow applicationWindow){
        this.applicationWindow = applicationWindow;
        createView();
    }

    private void createView() {
        setPrefSize(navBarWidth, navBarHeight);

        dashboardBox = new HBox(10);
        dashboardLabel = new Label("Dashboard");
        dashboardLabel.setFont(new Font(FormulaRouteTelemetryMain.class.getResource("/assets/fonts/Inter/Inter-Light.otf").toString(), 12));

        dashboardSeparator = new Separator();
        dashboardSeparator.setPrefWidth(100);
        dashboardBox.getChildren().addAll(dashboardLabel, dashboardSeparator);
        dashboardBox.setPadding(LayoutSizeManager.getResizedInsert(10, 0, 0, 10));

        HBox.setHgrow(dashboardLabel, Priority.ALWAYS);
        HBox.setHgrow(dashboardSeparator, Priority.ALWAYS);

        workorderHBox = new HBox(10);
        Label workorderLabel = new Label("Ordes de Serviço");
        workorderLabel.setFont(new Font(FormulaRouteTelemetryMain.class.getResource("/assets/fonts/Inter/Inter-Light.otf").toString(), 12));

        workorderSeparator = new Separator();
        workorderSeparator.setPrefWidth(65);
        workorderHBox.getChildren().addAll(workorderLabel, workorderSeparator);
        workorderHBox.setPadding(LayoutSizeManager.getResizedInsert(10, 0, 0, 10));

        HBox.setHgrow(workorderLabel, Priority.ALWAYS);
        HBox.setHgrow(workorderSeparator, Priority.ALWAYS);

        HBox userHBox = new HBox(10);
        Label userLabel = new Label("Gerenciar Usuários");
        userLabel.setFont(new Font(MSPM.class.getResource("/assets/fonts/Inter/Inter-Light.otf").toString(), 12));

        Separator userSeparator = new Separator();
        userSeparator.setPrefWidth(65);
        userHBox.getChildren().addAll(userLabel, userSeparator);
        userHBox.setPadding(LayoutSizeManager.getResizedInsert(10, 0, 0, 10));

        HBox.setHgrow(workorderLabel, Priority.ALWAYS);
        HBox.setHgrow(userSeparator, Priority.ALWAYS);

        getChildren().addAll(
                dashboardBox,
                createNavigationButton("Geral", Config.DASHBOARD_ICON, applicationWindow.getViewManager().getDashboardView()),
                createNavigationButton("Ativos", Config.ASSETS_ICON, applicationWindow.getViewManager().getAssetsView()),
                workorderHBox,
                createNavigationButton("Criar", Config.NEW_SERVICE_ORDER_ICON, applicationWindow.getViewManager().getNewWorkorderView()),
                createNavigationButton("Historico", Config.SERVICES_ORDER_ICON, applicationWindow.getViewManager().getWorkordersView()),
                userHBox,
                createNavigationButton("Usuários", Config.USER_NAVBAR_ICON, applicationWindow.getViewManager().getDashboardView())
        );
    }

    NavigationButton createNavigationButton(String title, String icon, Page page){
        NavigationButton main = new NavigationButton(page, applicationWindow);

        main.setPrefSize(200,30);
        main.setMaxSize(main.getPrefWidth(), main.getPrefHeight());

        HBox mainHBox = new HBox();

        Image iconImage = Config.getIcon(icon);

        ImageView iconView = new ImageView(iconImage);
        iconView.setFitWidth(15);
        iconView.setFitHeight(15);

        Label titleLabel = new Label();
        titleLabel.setText(title);
        titleLabel.setFont(new Font(FormulaRouteTelemetryMain.class.getResource("/assets/fonts/Inter/Inter-Light.otf").toString(), 12));
        titleLabel.setPrefSize(63, 17);
        titleLabel.setMaxSize(titleLabel.getPrefWidth(), titleLabel.getPrefHeight());

        titleLabel.setTextFill(Color.web(Config.getColorPalleteProperties("primary-button-color")));

        mainHBox.getChildren().addAll(iconView, titleLabel);

        // Configurar margens para os botões
        HBox.setMargin(iconView, LayoutSizeManager.getResizedInsert(5, 0, 0, 15));
        HBox.setMargin(titleLabel, LayoutSizeManager.getResizedInsert(3, 0, 0, 10));

        main.setCursor(Cursor.HAND);

        main.setOnMouseEntered(event -> {
            final Animation animation = new Transition() {
                {
                    setCycleDuration(Duration.millis(100));
                    setInterpolator(Interpolator.EASE_OUT);
                }
                @Override
                protected void interpolate(double frac) {
                    Color backgroundColor = Interpolation.lerpColorFX(
                            Color.web(Config.getColorPalleteProperties("background-default-color")),
                            Color.web("#dfe7f0"),
                            (float) frac
                    );

                    main.setStyle("-fx-background-color: "+ "rgba("
                            + (int) (backgroundColor.getRed() * 255) + ","
                            + (int) (backgroundColor.getGreen() * 255) + ","
                            + (int) (backgroundColor.getBlue() * 255) + ","
                            + (frac) + "); -fx-background-radius: 6px;-fx-border-radius: 6px;");
                }
            };
            animation.play();
        });

        main.setOnMouseExited(event -> {
            final Animation animation = new Transition() {
                {
                    setCycleDuration(Duration.millis(100));
                    setInterpolator(Interpolator.EASE_OUT);
                }
                @Override
                protected void interpolate(double frac) {
                    Color backgroundColor = Interpolation.lerpColorFX(
                            Color.web("#dfe7f0"),
                            Color.web(Config.getColorPalleteProperties("background-default-color")),
                            (float) frac
                    );
                    main.setStyle("-fx-background-color: "+ "rgba("
                            + (int) (backgroundColor.getRed() * 255) + ","
                            + (int) (backgroundColor.getGreen() * 255) + ","
                            + (int) (backgroundColor.getBlue() * 255) + ","
                            + (1 - frac) + "); -fx-background-radius: 6px;-fx-border-radius: 6px;");
                }
            };
            animation.play();
        });

        main.getChildren().addAll(mainHBox);

        navigationButtonList.add(main);
        return main;
    }

    public void minimizeNavBar(boolean isOpen){

    }

}
