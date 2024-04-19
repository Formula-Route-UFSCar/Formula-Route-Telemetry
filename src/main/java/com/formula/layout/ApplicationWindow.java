/*
 * MIT License
 *
 * Copyright (c)2023 Matheus Markies
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all
 * copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
 */

package com.formula.layout;

import com.formula.FormulaRouteTelemetryMain;
import com.formula.layout.javafx.utils.NodeUtils;
import com.formula.layout.javafx.utils.Page;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import lombok.Getter;
import lombok.Setter;

/**
 * This class, ApplicationWindow, represents the main plan of a graphical interface.
 * It extends JavaFX AnchorPane class and is responsible for managing the program's sub-interfaces.
 * The sub-interfaces are represented by classes that extend the Page class, which is also an extension of AnchorPane.
 * The organization of the sub-interfaces follows the MVC (Model View Controller) pattern.
 * The classes with the View function are the sub-interfaces, which extend the Page class and control the visual presentation.
 * The classes that perform the Model function extend the Controller class and manage the business logic and data.
 * The ApplicationWindow class acts as the application's main Controller.
 * -------------------------------------------------------------------------------------------------------------------------------------
 * The code includes methods for initialization, switching pages and logging in, following the MVC logic.
 * The init() method can be customized for specific application initialization.
 * The changePage() method manages the exchange between different sub-interfaces, initializing and updating their controllers.
 * The realiseLogin() method configures the interface after login, displaying the navigation bar and the top menu.
 * The ViewManager class has the function of storing the sub-interfaces that have already been initialized, as well as the main setup with navbar and topmenu.
 *-------------------------------------------------------------------------------------------------------------------------------------
 * The class uses NetworkManager and ViewManager objects to handle network operations and manage the sub-interfaces, respectively.
 * The layout structure is based on an AnchorPane containing a BorderPane, with the sub-interfaces being displayed in the center.
 * As well as the navbar on the left side of the BorderPane and the TopMenu at the top of it.
 * The interface body is identified using the BODY_ID constant.
 *-------------------------------------------------------------------------------------------------------------------------------------
 * Note: The MVC design pattern is adopted to maintain a clear separation between presentation logic, business logic and data.
 *
 * com.brasens.javafx.utils.Page
 * com.brasens.javafx.utils.Controller
 * com.brasens.layout.components.navbar.NavBar
 * com.brasens.layout.components.TopMenu
 * com.brasens.layout.ViewManager
 *
 */

@Getter
@Setter
public class ApplicationWindow extends AnchorPane {

    // Main class
    FormulaRouteTelemetryMain formulaRouteTelemetryMain;

    // Constants for interface body identification and minimum width
    public static final String BODY_ID = "body";
    public static final int MIN_WIDTH = 1280;

    // Instâncias de gerenciadores para visualização
    private ViewManager viewManager;

    BorderPane borderPane = new BorderPane();

    // Manager instances for network and visualization
    public void init(){

    }

    // Class constructor
    public ApplicationWindow() {
        // Starting visualization
        viewManager = new ViewManager(this);

        // Interface configuration
        viewManager.setup(this);
        init(); // Calls the initialization method

        // Configuring the size, style and identification of the main window
        setPrefSize(LayoutSizeManager.getResizedWidth(1280), LayoutSizeManager.getResizedHeight(800));
        getStyleClass().add("body");
        setId(BODY_ID);

        // Setting up anchors for the layout
        NodeUtils.setAnchors(this, Insets.EMPTY);
        AnchorPane.setBottomAnchor(borderPane, 0.0);
        AnchorPane.setLeftAnchor(borderPane, 0.0);
        AnchorPane.setRightAnchor(borderPane, 0.0);
        AnchorPane.setTopAnchor(borderPane, 0.0);

        // Style settings for the body of the interface
        borderPane.getStyleClass().add("body");

        // Initially loads the login page
        changePage(viewManager.getDashboardView());
        BorderPane.setAlignment(viewManager.getDashboardView(), Pos.CENTER);

        // Adds BorderPane as a child of AnchorPane (main window)
        getChildren().addAll(borderPane);
    }

    // Variable to store the currently loaded page
    private Page currentPageLoaded;

    // Method for changing the page in the interface
    public void changePage(Page page){
        borderPane.setCenter(page); // Sets the center of the BorderPane as the new page

        page.getController().init(); // Initializes the page controller

        // Disables refresh of previous page
        if(currentPageLoaded != null) {
            currentPageLoaded.getController().close(); // Closed the page controller
            currentPageLoaded.getController().setUpdate(false);
        }

        // Activates the refresh of the new page (refresh interval defined in the subclasses of the Controller class by means of a counter variable that counts how many times a 100ms delay has occurred)
        page.getController().setUpdate(true);

        // Refreshes the currently loaded page
        currentPageLoaded = page;
    }
}
