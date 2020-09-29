package design3.util;

import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author Minghua Zhou 周明华
 * @version 1.0
 * @title ErrorCheck
 * @description 检查错误以及合法性
 * @date 2019/12/28 14:15
 * @modified by:
 */
public class ErrorCheck {
    /**
     * 表达式字符合法性校验正则模式，静态常量化可以降低每次使用都要编译地消耗
     */
    private static final Pattern EXPRESSION_PATTERN = Pattern.compile("[0-9\\.+-/*()= ]+");

    /**
     * 判断算数表达式是否为空
     *
     * @param expression 算数表达式
     * @return true 空
     */
    public static boolean isNull(String expression) {
        return null == expression || "".equals(expression.trim());
    }

    /**
     * 检验算数表达式是否合法
     *
     * @param expression 算数表达式
     * @return true 合法
     */
    public static boolean isLegal(String expression) {
        Matcher matcher = EXPRESSION_PATTERN.matcher(expression);
        return matcher.matches();
    }


    /**
     * 输入的算数表达式是否和纸牌相同
     *
     * @param exp 表达式
     * @return false 相同 true 不同
     */
    public static boolean isDifferent(int[] cardsNumber, String exp) {
        // 个数不同
        if (getNumberFromStr(exp) != cardsNumber.length) {
            return true;
        }
        // 将牌转换成1~13的数字
        for (int i = 0; i < cardsNumber.length; i++) {
            cardsNumber[i] %= 13;
            if (cardsNumber[i] == 0) {
                cardsNumber[i] = 13;
            }

            // 检查表达中是否存在纸牌数字
            if (exp.indexOf(String.valueOf(cardsNumber[i])) == -1) {
                return true;
            }
        }

        for (int i = 0; i < cardsNumber.length; i++) {
            int count = 0;
            for (int j = 0; j < cardsNumber.length; j++) {
                if (cardsNumber[i] == cardsNumber[j]) {
                    count++;
                }
            }

            if (count >= 1) {
                int index = exp.indexOf(String.valueOf(cardsNumber[i]));
                while (index >= 0 && index < exp.length() - 1) {
                    index = exp.indexOf(String.valueOf(cardsNumber[i]), index + 1);
                    count--;
                }

                // 表达式中缺少数字
                if (count > 0) {
                    return true;
                }
            }
        }

        return false;
    }

    public static int getNumberFromStr(String str) {
        ArrayList<String> list = new ArrayList<>();
        StringBuffer stringBuffer = new StringBuffer();
        for (int i = 0; i < str.length(); i++) {
            char ch = str.charAt(i);
            // 空白字符直接丢弃
            if (ch >= '0' && ch <= '9') {
                // 持续读取一个数值的各个字符
                stringBuffer.append(ch);
            } else {
                /* 如果追加器有值，说明之前读取的字符是数值，而且此时已经完整读取完一个数值
                 * 将该数值放入数值栈，并将数值字符追加器清空
                 */
                if (stringBuffer.length() > 0) {
                    list.add(stringBuffer.toString());
                    stringBuffer.delete(0, stringBuffer.length());
                }
            }
        }
        return list.size();
    }
}
