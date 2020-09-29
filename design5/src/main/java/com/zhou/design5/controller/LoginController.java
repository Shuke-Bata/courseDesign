package com.zhou.design5.controller;

import com.zhou.design5.dao.AccountDao;
import com.zhou.design5.dao.ContactDao;
import com.zhou.design5.pojo.Account;
import com.zhou.design5.pojo.ContactPerson;
import com.zhou.design5.view.MainView;
import de.felixroske.jfxsupport.FXMLController;
import de.felixroske.jfxsupport.GUIState;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import org.springframework.beans.factory.annotation.Autowired;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

/**
 * @author Minghua Zhou 周明华
 * @version 1.0
 * @title LoginController
 * @description
 * @date 2019/12/29 9:39
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
    public ContactDao contactDao;
    @Autowired
    private AccountDao accountDao;
    @Autowired
    private MainView mainView;

    public ObservableList<ContactPerson> items;
    public Stage mainStage;

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }

    public void pressLogin() {
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
                List<ContactPerson> contactPeoples = contactDao.getContacts();
                items = FXCollections.observableList(contactPeoples);
                GUIState.getStage().hide();
                mainStage = new Stage();
                mainStage.setScene(new Scene(mainView.getView()));
                mainStage.setTitle("手机通讯录管理系统");
                mainStage.show();
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
     * @return -1 账号不存在 0 密码错误 1 验证通过
     */
    private int checkAccount(String accountNumber, String accountPassword) {
        Account account = accountDao.getAccount(accountNumber);
        if (account == null) {
            return -1;
        } else if (accountPassword.equals(account.getAccountPassword())) {
            return 1;
        } else {
            return 0;
        }
    }
}
