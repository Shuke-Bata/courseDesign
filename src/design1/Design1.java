package design1;

import design1.util.PolynomialList;
import design1.util.PolynomialNode;

import java.util.Scanner;

/**
 * @author Minghua Zhou 周明华
 * @version 1.0
 * @title Design1
 * @description 一元多项式计算主程序
 * @date 2019/11/25 21:41
 * @modified by:
 */
public class Design1 {
    /**
     * 根据输入创建一元多项式链表
     *
     * @param a 该多项式的名称
     * @return 一元多项式
     */
    public static PolynomialList creatPolynomialList(char a) {
        Scanner input = new Scanner(System.in);
        PolynomialList list = new PolynomialList();

        System.out.print("\n请输入一元多项式" + a + "的项数：");
        int countA = input.nextInt();

        for (int i = 0; i < countA; i++) {
            System.out.print("请输入一元多项式" + a + "第" + (i + 1) + "项的系数：");
            double coefficient = input.nextDouble();
            System.out.print("请输入一元多项式" + a + "第" + (i + 1) + "项的指数：");
            int exponent = input.nextInt();
            list.addNode(new PolynomialNode(coefficient, exponent));
        }
        return list;
    }

    /**
     * 菜单选择
     *
     * @param choose 选择的操作
     */
    public static void menu(int choose) {
        PolynomialList list1 = new PolynomialList();
        PolynomialList list2 = new PolynomialList();
        PolynomialList result = new PolynomialList();

        switch (choose) {
            case 1:
                list1 = creatPolynomialList('A');
                list1.display();
                list2 = creatPolynomialList('B');
                list2.display();
                result = list1.addAndSubPolynomialList(list2, false);
                result.display();
                break;
            case 2:
                list1 = creatPolynomialList('A');
                list1.display();
                list2 = creatPolynomialList('B');
                list2.display();
                result = list1.addAndSubPolynomialList(list2, true);
                result.display();
                break;
            case 3:
                PolynomialList list = creatPolynomialList('C');
                list.display();
                result = list.derPolynomialList();
                result.display();
                break;
            default:
                break;
        }

    }

    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        System.out.println("1、A+B\t   2、A-B");
        System.out.println("3、对C求导\t   0、退出");
        System.out.print("请选择一元多项式操作：");
        int choose = input.nextInt();
        while (choose < 0 || choose > 3) {
            System.out.print("非法输入!\n请输入正确的选择：");
            choose = input.nextInt();
        }
        menu(choose);
    }
}
