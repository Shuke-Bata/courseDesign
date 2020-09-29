package design4;

/**
 * @title
 * @description
 * @author Minghua Zhou 周明华
 * @updateTime 2019/12/29 20:14
 */
public class CourseNode {
    /**
     * 当前节点入度
     */
    public int inDegree = 0;
    /**
     * 学分
     */
    public int credit = 0;
    /**
     * 课程名称
     */
    public String courseName;
    /**
     * 指向下一条边（此顶点作为起始点）
     */
    public ArcNode arcs = null;
    /**
     * 是否排课
     */
    public boolean flag = true;

    public CourseNode() {
    }

    @Override
    public String toString() {
        return "学分：" + this.credit + "  " + "课程名称：" + this.courseName;
    }
}