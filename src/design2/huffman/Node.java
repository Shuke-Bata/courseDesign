package design2.huffman;

/**
 * @author Minghua Zhou 周明华
 * @version 1.0
 * @title Node
 * @description 哈夫曼树的结点
 * @date 2019/12/23 10:32
 * @modified by:
 */
public class Node implements Comparable<Node> {
    /**
     * 结点数据
     */
    private Data data;
    /**
     * 结点的左孩子
     */
    private Node leftChild;
    /**
     * 结点的右孩子
     */
    private Node rightChild;

    public Node() {
        this(null, null, null);
    }

    public Node(Data data) {
        this(data, null, null);
    }

    public Node(Data data, Node leftChild, Node rightChild) {
        this.data = data;
        this.leftChild = leftChild;
        this.rightChild = rightChild;
    }

    public Data getData() {
        return data;
    }

    public void setData(Data data) {
        this.data = data;
    }

    public Node getLeftChild() {
        return leftChild;
    }

    public void setLeftChild(Node leftChild) {
        this.leftChild = leftChild;
    }

    public Node getRightChild() {
        return rightChild;
    }

    public void setRightChild(Node rightChild) {
        this.rightChild = rightChild;
    }

    @Override
    public int compareTo(Node o) {
        return this.data.compareTo(o.getData());
    }

    @Override
    public String toString() {
        return "Node{" +
                "data=" + data +
                ", leftChild=" + leftChild +
                ", rightChild=" + rightChild +
                '}';
    }
}
