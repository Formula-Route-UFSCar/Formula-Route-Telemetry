package com.formula.layout.components.navbar;

import com.formula.layout.ApplicationWindow;
import com.formula.layout.javafx.utils.Page;
import javafx.event.EventHandler;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class NavigationButton extends AnchorPane {
    private final Page page;
    private final ApplicationWindow applicationWindow;

    public NavigationButton(Page page, ApplicationWindow applicationWindow) {
        this.page = page;
        this.applicationWindow = applicationWindow;
        this.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                onClick();
            }
        });
    }

    private void onClick() {
        applicationWindow.changePage(this.getPage());
    }
}
