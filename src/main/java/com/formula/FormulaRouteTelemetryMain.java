package com.formula;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import com.formula.frames.controller.*;

import java.io.IOException;

import static javafx.application.Application.launch;

public class FormulaRouteTelemetryMain extends Application {
    static Scene scene;

    @Override
    public void start(Stage stage) throws IOException, ClassNotFoundException {

        FXMLLoader fxmlMain = new FXMLLoader(Main.class.getResource(
                "/com/formula/MainFrame.fxml"));

        Parent root = fxmlMain.load();

        Scene scene = new Scene(root);

        MainFrameController controller = fxmlMain.getController();

        stage.setTitle("Dynamic Torque Transducer");

        scene.getStylesheets().add("/com/formula/MainFrameCSS.css");

        stage.setScene(scene);
        stage.setMaximized(true);
        stage.show();
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }

    public static Scene getScene() {
        return scene;
    }
}
