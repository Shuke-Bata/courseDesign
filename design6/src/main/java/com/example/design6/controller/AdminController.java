package com.example.design6.controller;

import de.felixroske.jfxsupport.FXMLController;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.stage.Stage;
import org.springframework.beans.factory.annotation.Autowired;

import java.net.URL;
import java.util.ResourceBundle;

/**
 * @author Minghua Zhou 周明华
 * @version 1.0
 * @title AdminController
 * @description
 * @date 2019/12/17 15:08
 * @modified by:
 */
@FXMLController
public class AdminController implements Initializable {
    @FXML
    private Button exit;

    @Autowired
    private LoginController loginController;

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }

    public void pressExit() {
        Stage stage = (Stage) exit.getScene().getWindow();
        stage.hide();
        loginController.backLogin();
    }
}
