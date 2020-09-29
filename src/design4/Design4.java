package design4;

import java.util.Scanner;
/**
 * @title
 * @description
 * @author Minghua Zhou 周明华
 * @updateTime 2019/12/29 20:14
 */
public class Design4 {
	public static void main(String[] args) {
		TestCourseAOV V = new TestCourseAOV();
		V.creatEdgeList();
		System.out.println("请选择需要的排序类型:  1、课程尽量靠前    2、课程尽量分布平均");
		int a;
		int flag = 0;
		do {
			if (flag != 0) {
				System.out.println("输入错误，请重新输入！");
			}
			a = new Scanner(System.in).nextInt();
			flag++;
		} while (a != 2 && a != 1);
		if (a == 1){
			V.sortRank();
		}

		else{
			V.sortRankAve();
		}

	}
}