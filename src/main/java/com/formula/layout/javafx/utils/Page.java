package com.formula.layout.javafx.utils;

import com.formula.layout.ApplicationWindow;
import javafx.scene.layout.AnchorPane;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter @Setter
public abstract class Page extends AnchorPane {

    private ApplicationWindow applicationWindow;

    protected Controller controller;
    protected String CSS = getClass().getResource("/mspm/pages/DashboardCSS.css").toString();

    public Page(ApplicationWindow applicationWindow, String CSS){
        this.applicationWindow = applicationWindow;
        this.CSS = getClass().getResource(CSS).toString();

        setPrefSize(1080, 725);
        getStyleClass().add("body");
        getStylesheets().add(this.CSS);
    }
}
