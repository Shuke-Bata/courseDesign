package design1.controller;


import design1.util.PolynomialList;
import design1.util.PolynomialNode;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;

import java.util.HashMap;
import java.util.Map;

/**
 * @author Minghua Zhou 周明华
 * @version 1.0
 * @title MainController
 * @description Controller类
 * @date 2019/12/10 20:39
 * @modified by:
 */
public class Controller {
    @FXML
    private TextArea show;

    /**
     * 符号枚举
     */
    private enum Symbol {
        /**
         * 左括号(()
         */
        LEFT_BRACKET,
        /**
         * 右括号())
         */
        RIGHT_BRACKET,
        /**
         * 小数点(.)
         */
        POINT,
        /**
         * 加号(+)
         */
        ADD,
        /**
         * 减号(-)
         */
        SUB,
        /**
         * 等号(=)
         */
        EQUAL,
        /**
         * 未知数y
         */
        UNKNOWN
    }

    ;

    /**
     * 包括的符号Map
     */
    private Map<Symbol, String> symbols = new HashMap<Symbol, String>() {
        {
            put(Symbol.LEFT_BRACKET, "(");
            put(Symbol.RIGHT_BRACKET, ")");
            put(Symbol.POINT, ".");
            put(Symbol.ADD, "+");
            put(Symbol.SUB, "-");
            put(Symbol.EQUAL, "=");
            put(Symbol.UNKNOWN, "y");
        }
    };


    /**
     * 获取按钮的值
     *
     * @param event 事件来源
     * @return String 按钮的值
     */
    private String getButtonValue(ActionEvent event) {
        Button button = (Button) event.getSource();
        return button.getText();
    }

    /**
     * 当按下数字键事做出的反应
     *
     * @param event 事件
     */
    public void pressNumberButton(ActionEvent event) {
        String number = getButtonValue(event);
        String inputString = show.getText();
        // 判断字符串中是否已经存在小数点
        boolean contains = inputString.contains(".");

        if (symbols.get(Symbol.POINT).equals(number)) {
            /*
             * 如果已经存在或者非法输入，则退出
             */
            if (contains || inputString.length() == 0) {
                return;
            }
        }

        show.setText(show.getText() + number);
    }

    /**
     * 按下操作键
     *
     * @param event
     */
    public void pressOperator(ActionEvent event) {
        String operator = getButtonValue(event);
        show.setText(show.getText() + operator);
    }

    /**
     * 按下未知数键
     *
     * @param event
     */
    public void pressUnknownButton(ActionEvent event) {
        show.setText(show.getText() + "y");
    }

    /**
     * 按下clear键
     *
     * @param event
     */
    public void pressClear(ActionEvent event) {
        show.setText("");
    }

    /**
     * 按下删除键
     *
     * @param event
     */
    public void pressDeleteOne(ActionEvent event) {
        String text = show.getText();
        if (text != null && text != "" && text.length() > 0) {
            text = text.substring(0, text.length() - 1);
            show.setText(text);
        }
    }

    /**
     * 按下等于键
     *
     * @param event
     */
    public void pressEqual(ActionEvent event) {
        String str = show.getText();
        PolynomialList list = creatPolynomialList(str);
        String result = changePolynomialListToString(list);
        show.setText(str + "=" + result);
    }

    /**
     * 求导
     */
    public void pressDerivative() {
        String str = show.getText();
        PolynomialList list = creatPolynomialList(str).derPolynomialList();
        String result = changePolynomialListToString(list);
        show.setText(str + "=" + result);
    }

    /**
     * 根据字符串创建一元多项式链表
     *
     * @param text 一元多项式表达式
     * @return 一元多项式链表
     */
    private PolynomialList creatPolynomialList(String text) {
        PolynomialList list = new PolynomialList();
        // 系数
        double coefficient = 0;
        // 指数
        int exponent = 0;

        // 当前正在读取中的数值字符追加器
        StringBuilder curNumBuilder = new StringBuilder(10);

        // 标志是否是指数
        boolean flag = false;
        for (int i = 0; i < text.length(); i++) {
            // 持续读取一个数值的各个字符
            while (isNumber(text.charAt(i)) || isUnknown(text.charAt(i))) {
                if (symbols.get(Symbol.UNKNOWN).equals(String.valueOf(text.charAt(i)))) {
                    coefficient = Double.parseDouble(curNumBuilder.toString());
                    curNumBuilder.delete(0, curNumBuilder.length());
                    flag = true;
                } else {
                    /* 如果遇到未知数，说明之前读取的字符是系数
                     * 将该数值给系数，并将数值字符追加器清空
                     */
                    curNumBuilder.append(text.charAt(i));
                }

                if (++i == text.length()) {
                    break;
                }
            }

            if (flag) {
                exponent = new Integer(curNumBuilder.toString());
                curNumBuilder.delete(0, curNumBuilder.length());
                list.addNode(new PolynomialNode(coefficient, exponent));
                flag = false;
            }

        }
        return list;
    }

    /**
     * 将一元多项式转换成字符串
     *
     * @param list 一元多项式链表
     * @return 字符串
     */
    private String changePolynomialListToString(PolynomialList list) {
        StringBuffer str = new StringBuffer();
        PolynomialNode p = list.getHead().getNext();
        while (p != null) {
            str.append(p.getCoefficient() + "y^" + p.getExponent());
            p = p.getNext();
            if (p != null) {
                str.append("+");
            }
        }
        return str.toString();
    }

    /**
     * 判断字符是否为数字
     *
     * @param x 字符
     * @return true 数字，false 其他
     */
    private boolean isNumber(char x) {
        return x >= '0' && x <= '9';
    }

    /**
     * 判断字符是否为未知数
     *
     * @param ch 字符
     * @return true 是未知数
     */
    private boolean isUnknown(char ch) {
        return symbols.get(Symbol.UNKNOWN).equals(String.valueOf(ch));
    }

}
