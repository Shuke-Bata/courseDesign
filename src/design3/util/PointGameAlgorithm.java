package design3.util;

import java.util.LinkedList;

/**
 * @author Minghua Zhou 周明华
 * @version 1.0
 * @title PointGameAlgorithm
 * @description 24点游戏算法
 * @date 2019/12/28 13:51
 * @modified by:
 */
public class PointGameAlgorithm {
    private static final int resultValue = 24;
    private static final int cardsNumber = 4;

    private static LinkedList<String> resultString = new LinkedList<String>();
    private static double[] number = new double[cardsNumber];
    private static String[] exp = new String[cardsNumber];

    public static void init(int[] numbers) {
        for (int i = 0; i < numbers.length; i++) {
            number[i] = numbers[i];
            exp[i] = String.valueOf(numbers[i]);
        }
    }

    public static String[] getResultStrings(int[] cards) {
        init(cards);
        findResult(cardsNumber);
        String[] list = new String[resultString.size()];
        int j = 0;
        for (Object i : resultString.toArray()) {
            list[j++] = (String) i;
        }
        return list;
    }

    /**
     * 利用递归穷举算出可能的方案
     *
     * @param n
     */
    public static void findResult(int n) {
        if (n == 1) {
            if (number[0] == resultValue) {
                resultString.add(exp[0]);
            }
        } else {
            for (int i = 0; i < n; i++) {
                for (int j = i + 1; j < n; j++) {
                    double a = number[i];
                    double b = number[j];
                    number[j] = number[n - 1];
                    String str1 = exp[i];
                    String str2 = exp[j];
                    exp[j] = exp[n - 1];
                    //加法
                    exp[i] = "(" + str1 + "+" + str2 + ")";
                    number[i] = a + b;
                    findResult(n - 1);
                    //减法
                    exp[i] = "(" + str1 + "-" + str2 + ")";
                    number[i] = a - b;
                    findResult(n - 1);
                    //乘法
                    exp[i] = "(" + str1 + "*" + str2 + ")";
                    number[i] = a * b;
                    findResult(n - 1);
                    //除法
                    if (b != 0) {
                        exp[i] = "(" + str1 + "/" + str2 + ")";
                        number[i] = a / b;
                        findResult(n - 1);
                    }
                    //减法
                    exp[i] = "(" + str2 + "-" + str1 + ")";
                    number[i] = b - a;
                    findResult(n - 1);
                    //除法
                    if (a != 0) {
                        exp[i] = "(" + str2 + "/" + str1 + ")";
                        number[i] = b / a;
                        findResult(n - 1);
                    }
                    //状态还原
                    number[i] = (int) a;
                    number[j] = (int) b;
                    exp[i] = str1;
                    exp[j] = str2;
                }
            }
        }
    }
}
