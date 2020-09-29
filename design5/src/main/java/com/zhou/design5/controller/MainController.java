package com.zhou.design5.controller;

import com.zhou.design5.dao.ContactDao;
import com.zhou.design5.pojo.ContactPerson;
import com.zhou.design5.util.PinYin4jUtils;
import com.zhou.design5.view.AddContactView;
import de.felixroske.jfxsupport.FXMLController;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;
import net.sourceforge.pinyin4j.format.exception.BadHanyuPinyinOutputFormatCombination;
import org.springframework.beans.factory.annotation.Autowired;

import java.net.URL;
import java.util.*;

/**
 * @author Minghua Zhou 周明华
 * @version 1.0
 * @title Controller
 * @description
 * @date 2019/12/28 21:27
 * @modified by:
 */
@FXMLController
public class MainController implements Initializable {
    @FXML
    private TextField searchInput;
    @FXML
    public ListView<String> listView;
    @FXML
    public ListView<String> navigation;

    @Autowired
    public LoginController loginController;
    @Autowired
    public AddController addController;
    @Autowired
    private AddContactView addContactView;
    @Autowired
    private ContactDao contactDao;

    /**
     * 存放初始数据
     */
    public ObservableList<ContactPerson> data;
    /**
     * 存放名字和开始字母
     */
    public ObservableList<String> items;
    /**
     * 主界面
     */
    public Stage mainStage;
    /**
     * 增加界面
     */
    public Stage addContactStage;

    /**
     * 新增还是编辑标志，默认新增
     */
    public boolean addFlag = true;
    /**
     * 当前编辑的数据
     */
    public ContactPerson editData;
    /**
     * 被编辑的下标
     */
    public int editNum;

    /**
     * 姓名，2~4个汉字
     */
    private static String namePattern = "[\\u4e00-\\u9fa5]";
    private static String phonePattern = "^((13[0-9])|(14[0-9])|(15[0-9])|(17[0-9])|(18[0-9]))\\d{0,8}$";
    private static String emailPattern = "^[a-zA-Z0-9][\\w\\.-]*[a-zA-Z0-9]@[a-zA-Z0-9][\\w\\.-]*$";

    /**
     * 存放字母和位置的Map
     */
    private Map<String, Integer> anchors = new HashMap<>();

    @Override

    public void initialize(URL location, ResourceBundle resources) {
        mainStage = loginController.mainStage;
        // 初始化面板展示数据
        try {
            initData();
        } catch (BadHanyuPinyinOutputFormatCombination badHanyuPinyinOutputFormatCombination) {
            badHanyuPinyinOutputFormatCombination.printStackTrace();
        }

        // 为ListView加上点击事件
        listView.getSelectionModel().selectedIndexProperty().addListener(
                (observable, oldValue, newValue) -> {
                    openEditStage(newValue.intValue());
                }
        );
        // 为ListView加上点击事件
        navigation.getSelectionModel().selectedItemProperty().addListener(
                (observable, oldValue, newValue) -> {
                    int count = anchors.get(newValue);
                    if (count >= 0) {
                        listView.scrollTo(count);
                    }
                }
        );

        // 搜索加上监听事件
        searchInput.setOnKeyReleased(event -> {
            String str = searchInput.getText().trim();
            if (event.getCode() == KeyCode.BACK_SPACE && str.equals("")) {
                List<ContactPerson> contactPeoples = contactDao.getContacts();
                data = FXCollections.observableList(contactPeoples);
                refresh();
            } else if (event.getCode() == KeyCode.ENTER) {
                if (str.matches(namePattern)) {
                    str = "%" + str + "%";
                    List<ContactPerson> contactPeoples = contactDao.findByName(str);
                    data = FXCollections.observableList(contactPeoples);
                    refresh();
                } else if (str.matches(phonePattern)) {
                    str = "%" + str + "%";
                    List<ContactPerson> contactPeoples = contactDao.findByPhone(str);
                    data = FXCollections.observableList(contactPeoples);
                    refresh();
                } else if (str.matches(emailPattern)) {
                    str = "%" + str + "%";
                    List<ContactPerson> contactPeoples = contactDao.findByEmail(str);
                    data = FXCollections.observableList(contactPeoples);
                    refresh();
                }
            }
        });
    }

    /**
     * 按下新增联系人按钮
     */
    public void pressAddContact() {
        mainStage.hide();
        initAddStage();
        addContactStage.show();
    }

    /**
     * 初始化数据
     */
    private void initData() throws BadHanyuPinyinOutputFormatCombination {
        List<String> navigationList = new ArrayList<String>();
        String[] strings = {"A", "B", "C", "D", "E", "F", "G", "H", "I", "J", "K", "L", "M", "N", "O", "P", "Q", "R",
                "S", "T", "U", "V", "W", "X", "Y", "Z", "#"};
        for (int i = 0; i < strings.length; i++) {
            navigationList.add(strings[i]);
            anchors.put(strings[i], -1);
        }
        ObservableList<String> navigationData = FXCollections.observableList(navigationList);
        this.navigation.setItems(navigationData);

        data = loginController.items;
        refresh();
    }

    /**
     * 对联系人重新排序
     *
     * @param data
     * @return 返回排序后的字符串
     */
    public List<String> sortData(ObservableList<ContactPerson> data) throws BadHanyuPinyinOutputFormatCombination {
        // 对汉字排序
        Collections.sort(data);
        PinYin4jUtils pinYin4jUtils = new PinYin4jUtils();
        List<String> list = new ArrayList<>();
        for (int i = 0; i < data.size(); i++) {
            if (i == 0) {
                String name = data.get(0).getName();
                char ch = pinYin4jUtils.toPinYinUppercase(name).charAt(0);
                list.add(String.valueOf(ch));
                anchors.put(String.valueOf(ch), list.size() - 1);
            } else {
                String name1 = data.get(i - 1).getName();
                String name2 = data.get(i).getName();
                char ch1 = pinYin4jUtils.toPinYinUppercase(name1).charAt(0);
                char ch2 = pinYin4jUtils.toPinYinUppercase(name2).charAt(0);
                if (ch1 != ch2) {
                    list.add(String.valueOf(ch2));
                    anchors.put(String.valueOf(ch2), list.size() - 1);
                }
            }
            list.add(data.get(i).getName());
        }
        return list;
    }

    /**
     * 刷新展示界面
     */
    public void refresh() {
        try {
            items = FXCollections.observableList(sortData(data));
        } catch (BadHanyuPinyinOutputFormatCombination badHanyuPinyinOutputFormatCombination) {
            badHanyuPinyinOutputFormatCombination.printStackTrace();
        }
        listView.setItems(items);
    }


    /**
     * 初始化增加界面
     */
    private void initAddStage() {
        if (addContactStage == null) {
            addContactStage = new Stage();
            addContactStage.setScene(new Scene(addContactView.getView()));
            addContactStage.setOnCloseRequest(new EventHandler<WindowEvent>() {
                @Override
                public void handle(WindowEvent event) {
                    addController.clear();
                    mainStage.show();
                }
            });
        }
        if (addFlag) {
            addContactStage.setTitle("新建联系人");
        } else {
            addContactStage.setTitle("编辑");
        }
    }

    /**
     * 打开编辑界面
     *
     * @param i
     */
    private void openEditStage(int i) {
        String name = items.get(i);
        if (name.charAt(0) >= 'A' && name.charAt(0) <= 'Z') {
            return;
        }
        if (name.equals("")) {
            return;
        }
        int count = i;
        for (int j = 0; j < i; j++) {
            name = items.get(j);
            if (name.charAt(0) >= 'A' && name.charAt(0) <= 'Z') {
                count--;
            }
        }
        addFlag = false;
        editData = data.get(count);
        editNum = count;
        initAddStage();
        mainStage.hide();
        addController.initEdit();
        addContactStage.show();
    }

}
