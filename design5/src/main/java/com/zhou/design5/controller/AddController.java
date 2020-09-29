package com.zhou.design5.controller;

import com.zhou.design5.dao.ContactDao;
import com.zhou.design5.pojo.ContactPerson;
import de.felixroske.jfxsupport.FXMLController;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import org.springframework.beans.factory.annotation.Autowired;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

/**
 * @author Minghua Zhou 周明华
 * @version 1.0
 * @title AddController
 * @description
 * @date 2019/12/29 10:18
 * @modified by:
 */
@FXMLController
public class AddController implements Initializable {
    @FXML
    private TextField name;
    @FXML
    private TextField phoneNumber;
    @FXML
    private TextField email;
    @FXML
    private Button addPhone;
    @FXML
    private Label errorName;
    @FXML
    private Label errorPhone;
    @FXML
    private Label errorEmail;


    @Autowired
    public ContactDao contactDao;
    @Autowired
    public MainController mainController;

    /**
     * 存放新增电话链表
     */
    private List<TextField> phoneList;
    /**
     * 提示信息框
     */
    public Alert alert;

    /**
     * 新增还是编辑标志
     */
    public boolean addFlag;
    /**
     * 当前编辑的数据
     */
    public ContactPerson editData;
    /**
     * 被编辑的下标
     */
    public int editNum;

    /**
     * 删除按钮
     */
    private Button deleteButton;

    /**
     * 姓名，2~4个汉字
     */
    private static String namePattern = "^[\\u4E00-\\u9FA5]{2,4}$";
    private static String phonePattern = "^((13[0-9])|(14[0-9])|(15[0-9])|(17[0-9])|(18[0-9]))\\d{8}$";
    private static String emailPattern = "^[a-zA-Z0-9][\\w\\.-]*[a-zA-Z0-9]@[a-zA-Z0-9][\\w\\.-]*[a-zA-Z0-9]\\.[a-zA-Z][a-zA-Z\\.]*[a-zA-Z]$";

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        phoneList = new ArrayList<>();
        addFlag = mainController.addFlag;
        addFocusListener(name, namePattern, errorName);
        addFocusListener(phoneNumber, phonePattern, errorPhone);
        addFocusListener(email, emailPattern, errorEmail);
    }

    /**
     * 为TextField增加失去焦点事件
     *
     * @param textField
     * @param pattern
     * @param errorLabel
     */
    private void addFocusListener(TextField textField, String pattern, Label errorLabel) {
        textField.focusedProperty().addListener((observable, oldValue, newValue) -> {
            if (!newValue) {
                boolean isTrue = textField.getText().trim().matches(pattern);
                if (!isTrue) {
                    errorLabel.setVisible(true);
                }
            } else {
                if (errorLabel.isVisible()) {
                    errorLabel.setVisible(false);
                }
            }
        });
    }

    /**
     * 初始化编辑界面，就是将新增界面填充数据，加上一个删除按钮
     */
    public void initEdit() {
        this.editData = mainController.editData;
        this.editNum = mainController.editNum;
        this.name.setText(editData.getName());
        this.email.setText(editData.getEmail());
        String[] phoneNumbers = editData.getPhoneNumber().split(",");
        this.phoneNumber.setText(phoneNumbers[0]);
        for (int i = 1; i < phoneNumbers.length; i++) {
            pressAddPhone();
            phoneList.get(i - 1).setText(phoneNumbers[i]);
        }
        addDelete();
        deleteButton.setOnAction(event -> {
            contactDao.deleteContact(editData.getEid());
            pressCancel();
        });
    }

    /**
     * 增加删除按钮
     */
    private void addDelete() {
        HBox emailBox = (HBox) email.getParent();
        deleteButton = new Button("删除");
        deleteButton.setPrefHeight(40);
        deleteButton.setPrefWidth(80);
        deleteButton.setStyle("-fx-background-color: red");
        deleteButton.setLayoutX(emailBox.getLayoutX() + 150);
        deleteButton.setLayoutY(emailBox.getLayoutY() + 60);

        AnchorPane anchorPane = (AnchorPane) addPhone.getParent();
        anchorPane.getChildren().add(deleteButton);

        mainController.addContactStage.setHeight(mainController.addContactStage.getHeight() + 40);
    }

    /**
     * 按下取消按钮
     */
    public void pressCancel() {
        mainController.addContactStage.hide();
        mainController.mainStage.show();
        clear();
    }

    /**
     * 点击增加电话号码键
     */
    public void pressAddPhone() {
        if (phoneNumber.getText().trim().equals("") ||
                (phoneList.size() > 0 && phoneList.get(phoneList.size()).getText().trim().equals(""))) {
            alertPrompt("警告", "请先输入电话号码");
            return;
        }
        TextField newPhone = new TextField();
        newPhone.setPrefWidth(phoneNumber.getPrefWidth());
        newPhone.setPrefHeight(phoneNumber.getPrefHeight());

        HBox emailBox = (HBox) email.getParent();
        newPhone.setLayoutX(175);
        newPhone.setLayoutY(emailBox.getLayoutY());
        emailBox.setLayoutY(emailBox.getLayoutY() + 70);

        AnchorPane anchorPane = (AnchorPane) addPhone.getParent();
        anchorPane.getChildren().add(newPhone);

        mainController.addContactStage.setHeight(mainController.addContactStage.getHeight() + 70);

        phoneList.add(newPhone);
    }

    /**
     * 按下保存键
     */
    public void pressSave() {
        if (errorName.isVisible() || errorPhone.isVisible()) {
            alertPrompt("错误！", "请填写正确信息");
            return;
        }
        String nameStr = this.name.getText().trim();
        String phoneStr = this.phoneNumber.getText().trim();
        if (nameStr.equals("") || phoneStr.equals("")) {
            alertPrompt("错误！", "请填写必填项");
            return;
        }
        for (int i = 0; i < this.phoneList.size(); i++) {
            phoneStr += "," + phoneList.get(i).getText().trim();
        }

        String emailStr = this.email.getText().trim();
        if (errorEmail.isVisible()) {
            emailStr = "";
        }
        if (addFlag) {
            try {
                contactDao.addContact(nameStr, phoneStr, emailStr);
                this.mainController.addContactStage.hide();

                ContactPerson person = new ContactPerson(nameStr, phoneStr, emailStr);
                mainController.data.add(person);
                // 刷新数据
                mainController.refresh();

                this.mainController.mainStage.show();
                alertPrompt("成功", "添加成功");

            } catch (Exception e) {
                alertPrompt("失败", "添加失败");
            }

        } else {
            if (nameStr.equals(editData.getName()) && phoneStr.equals(editData.getPhoneNumber()) &&
                    emailStr.equals(editData.getEmail())) {
            } else {
                try {
                    contactDao.updateContact(nameStr, phoneStr, emailStr, editData.getEid());
                    this.mainController.addContactStage.hide();

                    ContactPerson person = new ContactPerson(nameStr, phoneStr, emailStr);
                    mainController.data.set(editNum, person);
                    mainController.refresh();

                    this.mainController.mainStage.show();
                    alertPrompt("成功", "修改成功");
                } catch (Exception e) {
                    alertPrompt("失败", "修改失败");
                }
            }
        }

        clear();
    }

    /**
     * 弹出提示框
     *
     * @param title   标题
     * @param message 提示信息
     */
    protected void alertPrompt(String title, String message) {
        alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(title);
        alert.setHeaderText(message);
        alert.show();
    }

    /**
     * 清空重置操作
     */
    public void clear() {
        this.errorName.setVisible(false);
        this.errorPhone.setVisible(false);
        this.errorEmail.setVisible(false);
        this.name.setText("");
        this.email.setText("");
        this.phoneNumber.setText("");
        AnchorPane anchorPane = (AnchorPane) addPhone.getParent();
        for (int i = phoneList.size() - 1; i >= 0; i--) {
            anchorPane.getChildren().remove(phoneList.get(i));
            phoneList.remove(i);
            HBox emailBox = (HBox) email.getParent();
            emailBox.setLayoutY(emailBox.getLayoutY() - 70);
            mainController.addContactStage.setHeight(mainController.addContactStage.getHeight() - 70);
        }

        if (deleteButton != null) {
            anchorPane.getChildren().remove(deleteButton);
            deleteButton = null;
            mainController.addContactStage.setHeight(mainController.addContactStage.getHeight() - 40);
        }
    }
}
