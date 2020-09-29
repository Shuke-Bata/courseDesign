package com.example.design6.controller;

import de.felixroske.jfxsupport.FXMLController;
import javafx.fxml.FXML;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

import java.io.Serializable;

/**
 * @author Minghua Zhou 周明华
 * @version 1.0
 * @title RegisteredController
 * @description
 * @date 2019/12/15 14:17
 * @modified by:
 */
@FXMLController
public class RegisteredController implements Serializable {
    @FXML
    TextField accountNumber;
    @FXML
    PasswordField password, confirmPassword;

    /**
     * 按下注册键，进行账号注册
     */
    public void pressRegistered() {
        String account = this.accountNumber.getText().trim();
    }
}
