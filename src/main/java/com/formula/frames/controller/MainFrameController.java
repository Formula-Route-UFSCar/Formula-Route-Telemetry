package com.formula.frames.controller;

import com.formula.frames.controller.popup.ConnectPopUpController;
import com.formula.serialport.SerialReadder;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.chart.LineChart;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import lombok.*;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

@Getter @Setter @NoArgsConstructor @AllArgsConstructor @EqualsAndHashCode
public class MainFrameController{
    private SerialReadder serialReadder;

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private AnchorPane canvas_wheel_acc;

    @FXML
    private MenuItem connect_menu_button;

    @FXML
    private MenuItem debug_menu_button;

    @FXML
    private MenuBar menu_bar;

    @FXML
    private ImageView pitch_car_image;

    @FXML
    private ImageView roll_car_image;

    @FXML
    private ImageView yaw_car_image;

    @FXML
    void onClickInConnectButton(ActionEvent event) {
        openConnectPopUp();
    }

    @FXML
    void onClickInDebugButton(ActionEvent event) {

    }

    @FXML
    void initialize() {
        assert canvas_wheel_acc != null : "fx:id=\"canvas_wheel_acc\" was not injected: check your FXML file 'MainFrame.fxml'.";
        assert connect_menu_button != null : "fx:id=\"connect_menu_button\" was not injected: check your FXML file 'MainFrame.fxml'.";
        assert debug_menu_button != null : "fx:id=\"debug_menu_button\" was not injected: check your FXML file 'MainFrame.fxml'.";
        assert menu_bar != null : "fx:id=\"menu_bar\" was not injected: check your FXML file 'MainFrame.fxml'.";
        assert pitch_car_image != null : "fx:id=\"pitch_car_image\" was not injected: check your FXML file 'MainFrame.fxml'.";
        assert roll_car_image != null : "fx:id=\"roll_car_image\" was not injected: check your FXML file 'MainFrame.fxml'.";
        assert yaw_car_image != null : "fx:id=\"yaw_car_image\" was not injected: check your FXML file 'MainFrame.fxml'.";

        //setImageToImageView("/com/formula/images/yawaxis.jpg", yaw_car_image);
        //setImageToImageView("/com/formula/images/rollaxis.jpg", roll_car_image);
        //setImageToImageView("/com/formula/images/pitchaxis.jpg", pitch_car_image

        serialReadder = new SerialReadder();
        ScheduledExecutorService scheduledExecutorService;
        scheduledExecutorService = Executors.newSingleThreadScheduledExecutor();

        scheduledExecutorService.scheduleAtFixedRate(() -> {
            Platform.runLater(() -> {

            });
        }, 0, 100, TimeUnit.MILLISECONDS);
    }

    void setImageToImageView(String path, ImageView imageView){
        File file = new File(path);
        Image image = new Image(file.toURI().toString());
        imageView.setImage(image);
    }

    void openConnectPopUp(){
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource(
                    "/com/formula/ConnectPopUp.fxml"));
            Parent root = fxmlLoader.load();

            ConnectPopUpController connectPopUpController = (ConnectPopUpController)fxmlLoader.getController();

            connectPopUpController.setController(this);
            connectPopUpController.setSerialReadder(this.serialReadder);

            Stage stage = new Stage();
            stage.setTitle("Conectar");
            stage.setScene(new Scene(root));

            stage.show();
        } catch (IOException ignored) {
            System.err.println(ignored);
        }
    }

}
