package com.foursixteen.kuma.layout;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;

import java.net.URL;
import java.util.ResourceBundle;

public class Main implements Initializable {

    @FXML
    Button testButton;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        testButton.setText("Test");
    }

    @FXML
    public void test() {
        System.out.println("Test!");
    }
}
