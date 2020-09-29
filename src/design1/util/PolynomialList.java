package design1.util;

/**
 * @title PolynomialList
 * @description 一元多项式，得出的为指数从大到小排列
 * @date 2019/11/26 19:59
 * @author Minghua Zhou 周明华
 * @version 1.0
 * @modified by:
 */
public class PolynomialList {
    private PolynomialNode head;

    public PolynomialList() {
        head = new PolynomialNode();
    }

    public PolynomialNode getHead() {
        return head;
    }

    /**
     * 增加多项式的子项,将新增的子项插入到正确位置
     *
     * @param node 子项
     */
    public void addNode(PolynomialNode node) {
        if (head.getNext() == null) {
            head.setNext(node);
            return;
        }
        PolynomialNode priorNode = head;
        PolynomialNode p = head.getNext();
        PolynomialNode nextNode;
        while (p != null) {
            nextNode = p.getNext();
            // 当指数相等时，系数相加
            if (node.getExponent() == p.getExponent()) {
                double coefficient = node.getCoefficient() + p.getCoefficient();
                // 系数相加后为0就舍弃当前子项
                if ((int) coefficient == 0) {
                    priorNode.setNext(nextNode);
                } else {
                    p.setCoefficient(coefficient);
                }
                break;
            } else {
                if (node.getExponent() < p.getExponent()) {
                    /* 当新增子项的指数小于匹配到的指数，
                     * 若链表后面还有结点，priorNode指针向后移动，继续比较，
                     * 若无后继结点，则直接插入到末尾
                     */
                    if (p.getNext() != null) {
                        priorNode = priorNode.getNext();
                        p = p.getNext();
                    } else {
                        p.setNext(node);
                        break;
                    }
                } else {
                    // 当新增子项的指数大于匹配到的指数,直接插入到当前结点前面
                    priorNode.setNext(node);
                    node.setNext(p);
                    break;
                }
            }
        }
    }

    /**
     * 一元多项式加法、减法运算
     *
     * @param list  一元多项式链表
     * @param isSub 是否是减法运算
     * @return 一元多项式链表
     */
    public PolynomialList addAndSubPolynomialList(PolynomialList list, boolean isSub) {
        PolynomialList polynomialList = new PolynomialList();
        PolynomialNode p = this.head.getNext();
        PolynomialNode q = list.getHead().getNext();

        while (p != null) {
            polynomialList.addNode(new PolynomialNode(p.getCoefficient(), p.getExponent()));
            p = p.getNext();
        }

        while (q != null) {
            // 减法运算，将系数变为相反数
            if (isSub) {
                q.setCoefficient(-q.getCoefficient());
            }
            polynomialList.addNode(new PolynomialNode(q.getCoefficient(), q.getExponent()));
            q = q.getNext();
        }

        return polynomialList;
    }

    /**
     * 一元多项式求导运算
     *
     * @return 一元多项式链表
     */
    public PolynomialList derPolynomialList() {
        PolynomialList polynomialList = new PolynomialList();
        PolynomialNode p = this.head.getNext();

        while (p != null) {
            double coefficient = p.getCoefficient() * p.getExponent();
            if ((int) coefficient != 0) {
                polynomialList.addNode(new PolynomialNode(coefficient, p.getExponent() - 1));
            }
            p = p.getNext();
        }
        return polynomialList;
    }

    /**
     * 打印这条一元多项式链
     */
    public void display() {
        PolynomialNode p = this.head.getNext();
        System.out.print("\n得到的一元多项式：");
        while (p != null) {
            System.out.print(p.getCoefficient() + "x" + p.getExponent());
            if (p.getNext() != null && p.getNext().getCoefficient() > 0) {
                System.out.print(" + ");
            } else {
                System.out.print(" ");
            }
            p = p.getNext();
        }
    }

}