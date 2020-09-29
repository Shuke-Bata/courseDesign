package design3.version3;

import design3.util.PointGameAlgorithm;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import javafx.scene.text.Font;

import java.net.URL;
import java.util.ResourceBundle;

/**
 * @author Minghua Zhou 周明华
 * @version 1.0
 * @title Version3Controller
 * @description
 * @date 2019/12/28 19:15
 * @modified by:
 */
public class Version3Controller implements Initializable {
    @FXML
    private TextField number1, number2, number3, number4, resultShow;

    private static final int MAX_CARD_Value = 13;
    private boolean refreshFlag = true;
    protected String[] result;
    protected int count = 0;

    /**
     * 初始化
     */
    private void initTextField(TextField textField) {
        textField.setFont(Font.font(60));
        textField.setAlignment(Pos.CENTER);

        textField.textProperty().addListener((observable, oldValue, newValue) -> {
            newValue = checkValue(newValue, oldValue);
            textField.setText(newValue);
        });

    }

    /**
     * 检查输入的值是否在1~13之间
     * @param newNumber
     * @param oldValue
     * @return
     */
    private String checkValue(String newNumber, String oldValue) {
        if (newNumber.length() > 0) {
            char ch = newNumber.charAt(newNumber.length() - 1);
            if ("".equals(oldValue) && ch == '0') {
                return oldValue;
            }

            if (ch < '0' || ch > '9') {
                return oldValue;
            }

            if (Integer.parseInt(newNumber) > MAX_CARD_Value) {
                return oldValue;
            }

            if (!refreshFlag) {
                refreshFlag = true;
            }
            return newNumber;
        }
        return "";

    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        initTextField(number1);
        initTextField(number2);
        initTextField(number3);
        initTextField(number4);
    }

    public void pressFindResult() {
        if (isNull()) {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("警告");
            alert.setHeaderText("不能为空，请输入数字！");
            alert.show();
            return;
        }
        if (refreshFlag) {
            result = PointGameAlgorithm.getResultStrings(getCardsNumbers());
            refreshFlag = false;
            count = 0;
        }
        if (result.length > 0) {
            count %= result.length;
            this.resultShow.setText(result[count++]);
        } else {
            this.resultShow.setText("No solution");
        }

    }

    private int[] getCardsNumbers() {
        int[] cardsNumbers = new int[4];
        cardsNumbers[0] = Integer.parseInt(number1.getText().trim());
        cardsNumbers[1] = Integer.parseInt(number2.getText().trim());
        cardsNumbers[2] = Integer.parseInt(number3.getText().trim());
        cardsNumbers[3] = Integer.parseInt(number4.getText().trim());
        return cardsNumbers;
    }

    private boolean isNull() {
        return "".equals(number1.getText().trim()) || "".equals(number2.getText().trim())
                || "".equals(number3.getText().trim()) || "".equals(number4.getText().trim());
    }
}
