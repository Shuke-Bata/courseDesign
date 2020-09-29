package com.example.design6.controller;

import com.example.design6.dao.AccountDao;
import com.example.design6.pojo.Account;
import com.example.design6.view.AdminView;
import com.example.design6.view.MainView;
import com.example.design6.view.RegisteredView;
import de.felixroske.jfxsupport.AbstractFxmlView;
import de.felixroske.jfxsupport.FXMLController;
import de.felixroske.jfxsupport.GUIState;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;
import org.springframework.beans.factory.annotation.Autowired;

import java.net.URL;
import java.util.ResourceBundle;

/**
 * @author Minghua Zhou 周明华
 * @version 1.0
 * @title LoginController
 * @description 登录的Controller类，负责登录验证
 * @date 2019/12/15 10:52
 * @modified by:
 */
@FXMLController
public class LoginController implements Initializable {
    @FXML
    private TextField number;
    @FXML
    private PasswordField password;
    @FXML
    private Label errorNumber;
    @FXML
    private Label errorPassword;

    @Autowired
    private MainView mainView;
    @Autowired
    private RegisteredView registeredView;
    @Autowired
    private AdminView adminView;

    @Autowired
    private AccountDao accountDao;

    /**
     * 注册窗口舞台
     */
    private Stage registeredStage;
    /**
     * 主界面舞台
     */
    private Stage mainStage;
    /**
     * 管理员界面舞台
     */
    private Stage adminStage;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
    }

    /**
     * 初始化界面
     */
    private void init() {
        if (registeredStage == null) {
            registeredStage = new Stage();
            registeredStage.setScene(new Scene(registeredView.getView()));
            registeredStage.setOnCloseRequest(new EventHandler<WindowEvent>() {
                @Override
                public void handle(WindowEvent event) {
                    backLogin();
                }
            });
        } else if (adminStage == null) {
            adminStage = new Stage();
            adminStage.setScene(new Scene(adminView.getView()));
            adminStage.setOnCloseRequest(new EventHandler<WindowEvent>() {
                @Override
                public void handle(WindowEvent event) {
                    GUIState.getStage().close();
                }
            });
        } else if (mainStage == null) {
            mainStage = new Stage();
            mainStage.setScene(new Scene(mainView.getView()));
            mainStage.setOnCloseRequest(new EventHandler<WindowEvent>() {
                @Override
                public void handle(WindowEvent event) {
                    GUIState.getStage().close();
                }
            });
        }
    }


    /**
     * 点击注册按钮，进入注册界面
     */
    public void pressRegistered() {
        init();
        openOtherStage(this.registeredStage, registeredView, "全国交通咨询系统-注册");
    }

    /**
     * 点击登录按钮
     */
    public void pressLogin(ActionEvent event) {
        String accountNumber = this.number.getText().trim();
        String accountPassword = this.password.getText().trim();
        if ("".equals(accountNumber)) {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setHeaderText("账号不能为空");
            alert.show();
            return;
        } else if ("".equals(accountPassword)) {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setHeaderText("密码不能为空");
            alert.show();
            return;
        }
        switch (checkAccount(accountNumber, accountPassword)) {
            case -1:
                errorNumber.setVisible(true);
                break;
            case 0:
                errorPassword.setVisible(true);
                break;
            case 1:
                init();
                openOtherStage(this.adminStage, adminView, "全国交通咨询系统-管理员");
                break;
            case 2:
                init();
                openOtherStage(this.mainStage, mainView, "全国交通咨询系统");
                break;
            default:
                break;
        }
    }

    /**
     * 根据给定的账号查询数据库，检查账户信息
     *
     * @param accountNumber   账号
     * @param accountPassword 密码
     * @return -1 账号不存在 0 密码错误 1 验证通过,管理员 2 验证通过,普通用户
     */
    private int checkAccount(String accountNumber, String accountPassword) {
        Account account = accountDao.getAccount(accountNumber);
        if (account == null) {
            return -1;
        } else if (accountPassword.equals(account.getAccountPassword())) {
            if (account.getLevel() == 0) {
                return 1;
            } else {
                return 2;
            }
        } else {
            return 0;
        }
    }

    /**
     * 打开其他舞台
     *
     * @param openStage  要展示的舞台
     * @param stageTitle 标题
     */
    private void openOtherStage(Stage openStage, AbstractFxmlView view, String stageTitle) {
        clearInput();
        GUIState.getStage().hide();
        if (openStage == null) {
            openStage = new Stage();
            openStage.setScene(new Scene(view.getView()));
        }
        openStage.setTitle(stageTitle);
        openStage.show();
    }

    /**
     * 清除输入
     */
    private void clearInput() {
        this.number.setText("");
        this.password.setText("");
    }

    /**
     * 清除错误信息
     */
    private void clearErrorMessage() {
        this.errorPassword.setVisible(false);
        this.errorNumber.setVisible(false);
    }

    /**
     * 返回登录界面
     */
    public void backLogin() {
        GUIState.getStage().centerOnScreen();
        GUIState.getStage().show();
        clearInput();
        clearErrorMessage();
    }

}
