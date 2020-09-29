package com.example.design6.controller;

import com.example.design6.dao.CityDao;
import com.example.design6.dao.RouteDao;
import com.example.design6.dao.TrainTimeDao;
import com.example.design6.pojo.City;
import com.example.design6.pojo.Route;
import com.example.design6.pojo.TrainTime;
import com.example.design6.util.Algorithm;
import com.example.design6.util.SearchType;
import com.example.design6.util.TrainRouteNode;
import de.felixroske.jfxsupport.FXMLController;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import net.sourceforge.pinyin4j.format.exception.BadHanyuPinyinOutputFormatCombination;
import org.springframework.beans.factory.annotation.Autowired;

import java.net.URL;
import java.util.*;

/**
 * @author Minghua Zhou 周明华
 * @version 1.0
 * @title MainController
 * @description
 * @date 2019/12/14 22:02
 * @modified by:
 */
@FXMLController
public class MainController implements Initializable {
    @FXML
    private Button exit;
    @FXML
    private TableView showTable;
    @FXML
    private TableColumn trainNumberCol, startCityCol, endCityCol, startTimeCol, endTimeCol, costTimeCol, priceCol;
    @FXML
    private Label totalMoney, totalTime, totalCount;
    @FXML
    private TextField startInput, endInput;
    @FXML
    private DatePicker departureTime;
    @FXML
    private ListView<String> startCityListView, endCityListView;

    @Autowired
    private CityDao cityDao;
    @Autowired
    private RouteDao routeDao;
    @Autowired
    private TrainTimeDao trainTimeDao;

    @Autowired
    private LoginController loginController;

    /**
     * 城市数据
     */
    private List<City> cities;
    private List<Route> routes;
    private List<TrainTime> trainTimes;

    /**
     * 查询到的路线数据展示
     */
    private ObservableList<TrainRouteNode> data;

    /**
     * 城市名称展示
     */
    private ObservableList<String> cityNames;
    /**
     * 存放城市名字和在表中位置的Map
     */
    private Map<String, Integer> map = new HashMap<>();

    /**
     * 查询类型，默认最省
     */
    private SearchType searchType = SearchType.LESS_MONEY;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        trainNumberCol.setCellValueFactory(new PropertyValueFactory<>("trainNumber"));
        startCityCol.setCellValueFactory(new PropertyValueFactory<>("startCity"));
        endCityCol.setCellValueFactory(new PropertyValueFactory<>("endCity"));
        startTimeCol.setCellValueFactory(new PropertyValueFactory<>("startTime"));
        endTimeCol.setCellValueFactory(new PropertyValueFactory<>("endTime"));
        costTimeCol.setCellValueFactory(new PropertyValueFactory<>("costTime"));
        priceCol.setCellValueFactory(new PropertyValueFactory<>("price"));

        initData();
        refreshListView();

        // 为两个TextField加上焦点事件
        addOnclickAndHoverListener(startInput, startCityListView);
        addOnclickAndHoverListener(endInput, endCityListView);
        // 给listView添加监听事件
        addListenerForListView(startCityListView, startInput);
        addListenerForListView(endCityListView, endInput);

    }

    /**
     * 为ListView加事件
     *
     * @param listView
     * @param textField
     */
    private void addListenerForListView(ListView listView, TextField textField) {
        listView.hoverProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue) {
                listView.setVisible(true);
            }
        });
        listView.getSelectionModel().selectedItemProperty().addListener(
                (observable, oldValue, newValue) -> {
                    textField.setText(newValue.toString());
                }
        );
    }

    /**
     * 为TextField增加焦点事件
     *
     * @param textField 文本输入框
     */
    private void addOnclickAndHoverListener(TextField textField, ListView<String> listView) {
        textField.setOnMousePressed(event -> {
            if (!listView.isVisible()) {
                listView.setVisible(true);
            }
        });
        textField.focusedProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue) {
                if (!listView.isVisible()) {
                    listView.setVisible(true);
                }
            }
            if (!newValue) {
                listView.setVisible(false);
            }
        });
        textField.hoverProperty().addListener((observable, oldValue, newValue) -> {
            if (!newValue) {
                listView.setVisible(false);
            }
        });
    }

    /**
     * 对城市名字重新排序
     *
     * @param cityData 城市数据
     * @return 返回排序后的字符串
     */
    public List<String> sortData(List<City> cityData) throws BadHanyuPinyinOutputFormatCombination {
        // 对汉字排序
        Collections.sort(cityData);

        // 将城市名字存好
        List<String> list = new ArrayList<>();
        for (int i = 0; i < cityData.size(); i++) {
            list.add(cityData.get(i).getCityName());
            map.put(cityData.get(i).getCityName(), i);
        }
        return list;
    }

    /**
     * 刷新城市名字展示界面
     */
    public void refreshListView() {
        try {
            cityNames = FXCollections.observableList(sortData(cities));
        } catch (BadHanyuPinyinOutputFormatCombination badHanyuPinyinOutputFormatCombination) {
            badHanyuPinyinOutputFormatCombination.printStackTrace();
        }
        startCityListView.setItems(cityNames);
        endCityListView.setItems(cityNames);
    }

    /**
     * 初始化数据，从数据库拿出信息
     */
    private void initData() {
        this.cities = cityDao.getAllCityList();
        this.routes = routeDao.getAllRoute();
        this.trainTimes = trainTimeDao.getAllTrainTime();
    }

    /**
     * 点击最省路线
     */
    public void pressLeastMoney() {
        searchType = SearchType.LESS_MONEY;
        pressSearch();
    }

    /**
     * 点击最省路线
     */
    public void pressFast() {
        searchType = SearchType.FAST;
        pressSearch();
    }

    /**
     * 点击最省路线
     */
    public void pressLessChange() {
        searchType = SearchType.LESS_CHANGE;
        pressSearch();
    }


    public void pressSearch() {
        // 在list中的下标
        int start = map.get(startInput.getText().trim());
        // 在list中的下标
        int end = map.get(endInput.getText().trim());
        ArrayList<TrainRouteNode> list = Algorithm.getRouteInformation(
                cities, routes, trainTimes, start, end, searchType);
        data = FXCollections.observableList(list);
        showTable.setItems(data);

        this.totalCount.setText("转乘次数：" + String.valueOf(list.size()));
        int sumMoney = 0;

        String[] str = new String[list.size()];
        for (int i = 0; i < list.size(); i++) {
            sumMoney += list.get(i).getPrice();
            str[i] = list.get(i).getCostTime();
        }
        this.totalMoney.setText("总金额：" + String.valueOf(sumMoney));
        StringBuffer sumHour = new StringBuffer();
        StringBuffer sumMin = new StringBuffer();
        int sumH = 0;
        int sumM = 0;
        boolean flag = true;
        for (int i = 0; i < str.length; i++) {
            for (int j = 0; j < str[i].length(); j++) {
                if (str[i].charAt(j) == '时') {
                    flag = false;
                    sumH += Integer.parseInt(sumHour.toString());
                    sumHour.delete(0, sumHour.length());
                    continue;
                } else if (str[i].charAt(j) == '分') {
                    flag = true;
                    sumM += Integer.parseInt(sumMin.toString());
                    sumMin.delete(0, sumMin.length());
                    continue;
                }
                if (flag) {
                    sumHour.append(str[i].charAt(j));
                } else {
                    sumMin.append(str[i].charAt(j));
                }
            }
        }
        if (sumM > 60) {
            sumH += sumM / 60;
        }
        if (sumH > 0) {
            this.totalTime.setText("总时间：" + sumH + "时" + sumM % 60 + "分");
        } else {
            this.totalTime.setText("总时间：" + sumM % 60 + "分");
        }
    }

    /**
     * 按下退出键，退回登录界面
     */
    public void pressExit() {
        Stage stage = (Stage) exit.getScene().getWindow();
        stage.hide();
        loginController.backLogin();
    }

    /**
     * 清空数据
     */
    public void pressClearTable() {
        showTable.getItems().clear();
        this.startInput.setText("");
        this.endInput.setText("");
        this.totalCount.setText("转乘次数：0");
        this.totalMoney.setText("总金额：0");
        this.totalTime.setText("总时间：0");
    }

    /**
     * 查询城市名字在城市链表中的下标
     *
     * @param name
     * @return
     */
    private int findCityIndex(String name) {
        for (int i = 0; i < cities.size(); i++) {
            if (cities.get(i).getCityName().equals(name)) {
                return i;
            }
        }
        return -1;
    }

}
