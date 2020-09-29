package design1.util;

/**
 * @title PolynomialNode
 * @description 一元多项式子项结点定义类
 * @date 2019/11/26 20:02
 * @author Minghua Zhou 周明华
 * @version 1.0
 * @modified by:
 */
public class PolynomialNode {
    /**
     * 系数
     */
    private double coefficient;
    /**
     * 指数
     */
    private int exponent;
    /**
     * 下一个结点的指针域
     */
    private PolynomialNode next;

    public PolynomialNode() {
        this(0, 0);
    }

    public PolynomialNode(double coefficient, int exponent) {
        this.coefficient = coefficient;
        this.exponent = exponent;
        this.next = null;
    }

    public double getCoefficient() {
        return coefficient;
    }

    public void setCoefficient(double coefficient) {
        this.coefficient = coefficient;
    }

    public int getExponent() {
        return exponent;
    }

    public void setExponent(int exponent) {
        this.exponent = exponent;
    }

    public PolynomialNode getNext() {
        return next;
    }

    public void setNext(PolynomialNode next) {
        this.next = next;
    }
}
