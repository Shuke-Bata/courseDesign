package design4;

/**
 * @author Minghua Zhou 周明华
 * @title
 * @description
 * @updateTime 2019/12/29 20:14
 */
public class ArcNode {
    /**
     * 指向的顶点的位置
     */
    public int pointCourse;
    /**
     * 相邻的边
     */
    public ArcNode edgArcs;

    public ArcNode() {
        this.pointCourse = -1;
        this.edgArcs = null;
    }

    public ArcNode(int next, ArcNode nextArc) {
        this.pointCourse = next;
        this.edgArcs = nextArc;
    }
}
