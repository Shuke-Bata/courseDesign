package design2.draw;

import design2.huffman.Node;
import javafx.scene.layout.Pane;

/**
 * @author Minghua Zhou 周明华
 * @version 1.0
 * @title DrawHuffmanTree
 * @description 画哈夫曼树
 * @date 2019/12/23 15:54
 * @modified by:
 */
public class DrawHuffmanTree {
    /**
     * 半径
     */
    private static final int RADIUS = 20;
    /**
     * 偏移量
     */
    private int offsetX = 200;
    private int offsetY = 30;
    private int num = 30;

    public void createTree(Node rootNode, Pane pane, int centerX, int centerY, int count) {
        if (rootNode != null) {
            int addCount = ++count;
            TreeNode node = new TreeNode();
            if (rootNode.getLeftChild() != null) {
                node.addLine("0", centerX, centerY, centerX - offsetX + count * num, centerY + offsetY + count * num, pane);
            }
            if (rootNode.getRightChild() != null) {
                node.addLine("1", centerX, centerY, centerX + offsetX - count * num, centerY + offsetY + count * num, pane);
            }
            node.addTreeNode(rootNode.getData(), centerX, centerY, RADIUS, pane);
            createTree(rootNode.getLeftChild(), pane, centerX - offsetX + count * num, centerY + offsetY + count * num, addCount);
            createTree(rootNode.getRightChild(), pane, centerX + offsetX - count * num, centerY + offsetY + count * num, addCount);
        }
    }
}