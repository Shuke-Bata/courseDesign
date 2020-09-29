package design4;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

/**
 * @title
 * @description
 * @author Minghua Zhou 周明华
 * @updateTime 2019/12/29 20:14
 */
public class TestCourseAOV {
    private CourseNode[] courseList = new CourseNode[12];


    /**
     *  根据题目的AOV网图，创建邻接表
     */
    void creatEdgeList() {
        for (int i = 0; i < 12; i++) {
            courseList[i] = new CourseNode();
        }
        courseList[0].courseName = "C01";
        courseList[0].credit = 2;
        courseList[0].arcs = new ArcNode(1, new ArcNode(3, new ArcNode(2, new ArcNode(11, null))));

        courseList[1].courseName = "C02";
        courseList[1].credit = 3;
        courseList[1].arcs = new ArcNode(2, null);

        courseList[2].courseName = "C03";
        courseList[2].credit = 4;
        courseList[2].arcs = new ArcNode(4, new ArcNode(6, new ArcNode(7, null)));

        courseList[3].courseName = "C04";
        courseList[3].credit = 3;
        courseList[3].arcs = new ArcNode(4, null);

        courseList[4].courseName = "C05";
        courseList[4].credit = 2;
        courseList[4].arcs = new ArcNode(6, null);

        courseList[5].courseName = "C06";
        courseList[5].credit = 3;
        courseList[5].arcs = new ArcNode(7, null);

        courseList[6].courseName = "C07";
        courseList[6].credit = 4;
        courseList[6].arcs = new ArcNode();

        courseList[7].courseName = "C08";
        courseList[7].credit = 4;
        courseList[7].arcs = new ArcNode();

        courseList[8].courseName = "C09";
        courseList[8].credit = 7;
        courseList[8].arcs = new ArcNode(9, new ArcNode(10, new ArcNode(11, null)));

        courseList[9].courseName = "C10";
        courseList[9].credit = 5;
        courseList[9].arcs = new ArcNode(11, null);

        courseList[10].courseName = "C11";
        courseList[10].credit = 2;
        courseList[10].arcs = new ArcNode(5, null);

        courseList[11].courseName = "C12";
        courseList[11].credit = 3;
        courseList[11].arcs = new ArcNode();

        for (int i = 0; i < courseList.length; i++) {
            courseList[i].inDegree = setInDegrees()[i];
        }
    }


    /**
     * 求入度
     * @return
     */
    public int[] setInDegrees() {
        int[] inDegree = new int[courseList.length];
        for (int i = 0; i < inDegree.length; i++) {
            for (ArcNode arcNode = courseList[i].arcs; arcNode != null; arcNode = arcNode.edgArcs) {
                if (arcNode.pointCourse != -1) {
                    ++inDegree[arcNode.pointCourse];
                }

            }
        }
        return inDegree;
    }


    /**
     * 刷新邻接表
     * @param p
     */
    public void upDate(ArcNode p) {
        while (p != null) {
            if (p.pointCourse != -1) {
                courseList[p.pointCourse].inDegree--;
            }
            p = p.edgArcs;
        }
    }


    /**
     * 进行排序（尽量往前）
     */
    public void sortRank() {
        // 将入度重新赋值
        for (int i = 0; i < courseList.length; i++) {
            courseList[i].inDegree = setInDegrees()[i];
        }
        int creditLimit;
        int count = 0;
        System.out.println("请输入学期数:");
        Scanner input = new Scanner(System.in);
        int n = input.nextInt();
        System.out.println("请输入每个学期限制的学分:");
        creditLimit = input.nextInt();
        LinkedList<CourseNode> queue = new LinkedList<>();
        for (int k = 0; k < n; k++) {
            int credit = 0;
            for (int i = 0; i < courseList.length; i++) {
                //如果未被排课且入度为0
                if (courseList[i].flag && courseList[i].inDegree == 0) {
                    credit = credit + courseList[i].credit;
                    //入队列
                    if (credit <= creditLimit) {
                        queue.offer(courseList[i]);
                        courseList[i].flag = false;
                        count++;
                        System.out.println(courseList[i].courseName + "学分为:" + courseList[i].credit + "          在第"
                                + (k + 1) + "学期");
                    }
                }
            }
            while (!queue.isEmpty()) {
                upDate(queue.poll().arcs);
            }
            System.out.println();

        }
        if (count < courseList.length) {
            for (CourseNode aVAdjacencyList : courseList) {
                if (aVAdjacencyList.flag) {
                    System.out.println("课程" + aVAdjacencyList.courseName + "未安排！");
                }
            }
        }
    }

    /**
     * 进行排序（平均）
     */
    public void sortRankAve() {
        // 将入度重新赋值
        for (int i = 0; i < courseList.length; i++) {
            courseList[i].inDegree = setInDegrees()[i];
        }
        int creditLimit;
        int count = 0;
        System.out.println("请输入学期数：");
        Scanner in = new Scanner(System.in);
        int n = in.nextInt();
        System.out.println("请输入每个学期限制的学分:");
        creditLimit = in.nextInt();
        Queue<CourseNode> queue = new LinkedList<>();
        // 每个学期最多排的课程
        int ave = courseList.length / n + 1;
        for (int k = 0; k < n; k++) {
            // 学分监测
            int credit = 0;
            for (int i = 0; i < courseList.length; i++) {

                if (courseList[i].flag && courseList[i].inDegree == 0) {
                    credit = credit + courseList[i].credit;
                    if (credit <= creditLimit && queue.size() < ave) {
                        queue.offer(courseList[i]);
                        courseList[i].flag = false;
                        count++;
                        System.out.println(courseList[i].courseName + "学分为:" + courseList[i].credit + "          在第"
                                + (k + 1) + "学期");
                    }
                }
            }
            while (!queue.isEmpty()) {
                upDate(queue.poll().arcs);
            }
            System.out.println();
        }
        if (count < courseList.length) {
            for (CourseNode aVAdjacencyList : courseList) {
                if (aVAdjacencyList.flag) {
                    System.out.println("课程" + aVAdjacencyList.courseName + "未安排！");
                }
            }
        }
    }
}