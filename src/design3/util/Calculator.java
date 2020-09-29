package design3.util;

import java.util.HashMap;
import java.util.Map;
import java.util.Stack;

/**
 * @author Minghua Zhou 周明华
 * @version 1.0
 * @title Calculator
 * @description 加减乘除算数表达式求值算法
 * @date 2019/12/14 11:06
 * @modified by:
 */
public class Calculator {
    /**
     * 运算符优先级map
     */
    private static final Map<String, Integer> OPT_PRIORITY_MAP = new HashMap<String, Integer>() {
        {
            put("(", 0);
            put("+", 2);
            put("-", 2);
            put("*", 3);
            put("/", 3);
            put(")", 7);
            put("=", 20);
        }
    };

    /**
     * 计算整数算数表达式的值
     *
     * @param exp 算数表达式
     * @return 计算得到的值
     */
    public static int executeExpression(String exp) {
        int count = 0;
        // 操作符栈
        Stack<String> optStack = new Stack<>();
        // 数值栈
        Stack<Integer> numStack = new Stack<>();
        // 当前正在读取中的数值字符追加器
        StringBuilder curNumBuilder = new StringBuilder(10);

        for (int i = 0; i < exp.length(); i++) {
            char ch = exp.charAt(i);
            // 空白字符直接丢弃
            if (ch != ' ') {
                if (ch >= '0' && ch <= '9') {
                    // 持续读取一个数值的各个字符
                    curNumBuilder.append(ch);
                } else {
                    /* 如果追加器有值，说明之前读取的字符是数值，而且此时已经完整读取完一个数值
                     * 将该数值放入数值栈，并将数值字符追加器清空
                     */
                    if (curNumBuilder.length() > 0) {
                        numStack.push(new Integer(curNumBuilder.toString()));
                        curNumBuilder.delete(0, curNumBuilder.length());
                    }

                    String curOpt = String.valueOf(ch);
                    if (optStack.empty()) {
                        // 运算符栈栈顶为空则直接入栈
                        optStack.push(curOpt);
                    } else {
                        if ("(".equals(curOpt)) {
                            // 当前运算符为左括号，直接入运算符栈
                            optStack.push(curOpt);
                        } else if (")".equals(curOpt)) {
                            // 当前运算符为右括号，触发括号内的字表达式进行计算
                            directCalc(optStack, numStack, true);
                        } else if ("=".equals(curOpt)) {
                            // 当前运算符为等号，触发整个表达式剩余计算，并返回总的计算结果
                            directCalc(optStack, numStack, false);
                            return numStack.pop();
                        } else {
                            // 当前运算符为加减乘除之一，要与栈顶运算符比较，判断是否要进行一次二元计算
                            compareAndCalc(optStack, numStack, curOpt);
                        }
                    }
                }
            }

        }
        return numStack.pop();
    }

    /**
     * 拿当前运算符和栈顶运算符对比，如果栈顶运算符优先级高于或同级于当前运算符，
     * 则执行一次二元运算（递归比较并计算），否则当前运算符入栈
     *
     * @param optStack 运算符栈
     * @param numStack 数值栈
     * @param curOpt   当前运算符
     */
    private static void compareAndCalc(Stack<String> optStack, Stack<Integer> numStack, String curOpt) {
        // 比较当前运算符和栈顶运算符的优先级
        String peekOpt = optStack.peek();
        int priority = getPriority(peekOpt, curOpt);
        // 栈顶运算符优先级大或同级，触发一次二元运算
        if (priority == -1 || priority == 0) {
            // 当前参与计算运算符
            String opt = optStack.pop();
            // 当前参与计算数值2
            Integer num2 = numStack.pop();
            // 当前参与计算数值1
            Integer num1 = numStack.pop();
            Double result = executeValue(num1, opt, num2);

            // 计算结果当做操作数入栈
            numStack.push(result.intValue());

            if (optStack.empty()) {
                optStack.push(curOpt);
            } else {
                // 运算完栈顶还有运算符，则还需要再次触发一次比较判断是否需要再次二元计算
                compareAndCalc(optStack, numStack, curOpt);
            }
        } else {
            // 当前运算符优先级高，则直接入栈
            optStack.push(curOpt);
        }
    }

    /**
     * 比较加减乘除中两个运算符的优先级
     *
     * @param opt1 运算符1
     * @param opt2 运算符2
     * @return priority = 0  表示两个运算符同级别
     * priority = 1  第二个运算符级别高，负数则相反
     */
    private static int getPriority(String opt1, String opt2) {
        return OPT_PRIORITY_MAP.get(opt2) - OPT_PRIORITY_MAP.get(opt1);
    }

    /**
     * 遇到右括号和等号执行的连续计算操作（递归计算）
     *
     * @param optStack  运算符栈
     * @param numStack  数值栈
     * @param isBracket true表示为括号类型计算
     */
    private static void directCalc(Stack<String> optStack, Stack<Integer> numStack, boolean isBracket) {
        // 当前参与计算运算符
        String opt = optStack.pop();
        // 当前参与计算数值2
        Integer num2 = numStack.pop();
        // 当前参与计算数值1
        Integer num1 = numStack.pop();
        Double result = executeValue(num1, opt, num2);

        // 计算结果当做操作数入栈
        numStack.push(result.intValue());

        if (isBracket) {
            if ("(".equals(optStack.peek())) {
                // 括号类型则遇左括号停止计算，同时将左括号从栈中移除
                optStack.pop();
            } else {
                directCalc(optStack, numStack, true);
            }
        } else {
            if (!optStack.empty()) {
                // 如果是等号类型，只要栈中还有运算符就继续计算
                directCalc(optStack, numStack, false);
            }
        }
    }

    /**
     * 计算两个整数的加减乘除运算操作
     *
     * @param num1 整数1
     * @param opt  运算符
     * @param num2 整数2
     * @return 运算结果
     */
    private static Double executeValue(Integer num1, String opt, Integer num2) {
        Double result = new Double(0);
        switch (opt) {
            case "+":
                result = num1.doubleValue() + num2.doubleValue();
                break;
            case "-":
                result = num1.doubleValue() - num2.doubleValue();
                break;
            case "*":
                result = num1.doubleValue() * num2.doubleValue();
                break;
            case "/":
                result = num1.doubleValue() / num2.doubleValue();
                break;
            default:
                break;
        }
        return result;
    }

}