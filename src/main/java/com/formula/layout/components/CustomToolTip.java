package com.formula.layout.components;

import javafx.scene.control.Tooltip;
import javafx.scene.text.Font;
import javafx.stage.PopupWindow;

public class CustomToolTip extends Tooltip {

    private static final String SQUARE_BUBBLE =
            "M12 1h-24v16.981h4v5.019l7-5.019h13z";

    public CustomToolTip(String text){
        this.setText(text);
        this.setFont(new Font(15));
        //this.setStyle("-fx-font-size: 10px; -fx-shape: \"" + SQUARE_BUBBLE + "\";");
        this.setAnchorLocation(AnchorLocation.CONTENT_BOTTOM_LEFT);
    }

}
