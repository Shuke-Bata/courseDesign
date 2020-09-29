package design3.version1;

import design3.util.Calculator;
import design3.util.ErrorCheck;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.net.URL;
import java.util.Random;
import java.util.ResourceBundle;

/**
 * @author Minghua Zhou 周明华
 * @version 1.0
 * @date 2019/12/12 11:06
 * @modified by:
 */
public class Version1Controller implements Initializable {
    @FXML
    protected TextField input;
    @FXML
    protected ImageView picture1, picture2, picture3, picture4;

    /**
     * 存放当前扑克牌的数字
     */
    protected int[] cardNumbers = new int[4];

    /**
     * 生成n个随机数，1~52，且与之前生成的随机数不同
     *
     * @return 整形数组
     */
    protected int[] getRandomCard(int n) {
        Random random = new Random();
        int[] numbers = new int[n];
        int number = 0;
        int count = 0;
        while (count < n) {
            // 生成1~52的随机数
            number = random.nextInt(52) + 1;
            // 当前数字未被使用
            boolean isUsed = false;
            for (int i = 0; i < count; i++) {
                if (number == numbers[i]) {
                    isUsed = true;
                    break;
                }
            }
            // 数字未被使用，就添加到数组中
            if (!isUsed) {
                numbers[count++] = number;
            }
        }
        return numbers;
    }

    /**
     * 弹出提示框
     *
     * @param title   标题
     * @param message 提示信息
     */
    protected void alertPrompt(String title, String message) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(title);
        alert.setHeaderText(message);
        alert.show();
    }

    /**
     * 按下Refresh键，刷新扑克牌卡片
     */
    public void pressRefresh() {
        this.cardNumbers = getRandomCard(4);
        Image[] images = new Image[4];
        for (int i = 0; i < cardNumbers.length; i++) {
            images[i] = new Image("design3/images/" + cardNumbers[i] + ".jpg");
        }
        picture1.setImage(images[0]);
        picture2.setImage(images[1]);
        picture3.setImage(images[2]);
        picture4.setImage(images[3]);
    }

    /**
     * 按下Verify键，验证
     */
    public void pressVerify() {
        String exp = input.getText().trim() + "=";
        if (ErrorCheck.isNull(exp)) {
            alertPrompt("警告", "请输入相应的表达式");
        } else if (!ErrorCheck.isLegal(exp)) {
            alertPrompt("错误", "输入正确的算数表达式");
        } else if (ErrorCheck.isDifferent(cardNumbers, exp)) {
            alertPrompt("错误", "输入数据与纸牌数据不同");
        } else {
            try {
                if (Calculator.executeExpression(exp) == 24) {
                    alertPrompt("消息", "正确");
                } else {
                    alertPrompt("消息", "错误");
                }
            }catch (Exception e){
                alertPrompt("错误", "输入正确的算数表达式");
            }

        }
      //  input.setText("");
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        this.input.textProperty().addListener((observable, oldValue, newValue) -> {
          /*  newValue = checkValue(newValue, oldValue);
            input.setText(newValue);*/
        });
        pressRefresh();
    }

    private String checkValue(String newValue, String oldValue) {
        if (newValue.length() < 0) {
            return "";
        }
        if ('.' == newValue.charAt(newValue.length() - 1)) {
            return oldValue;
        }
        if (isOperator(newValue.charAt(newValue.length() - 1)) && isOperator(oldValue.charAt(oldValue.length() - 1))) {
            return oldValue;
        }
        return newValue;
    }

    private boolean isOperator(char ch) {
        return '+' == ch || '-' == ch || '*' == ch || '/' == ch;
    }

}
