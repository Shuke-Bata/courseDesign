package design2.draw;

import design2.huffman.Data;
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Line;
import javafx.scene.text.Font;

/**
 * @author Minghua Zhou 周明华
 * @version 1.0
 * @title TreeNode
 * @description
 * @date 2019/12/23 16:24
 * @modified by:
 */
public class TreeNode {
    /**
     * 数据标签
     */
    private Label label;
    /**
     * 圆
     */
    private Circle circle;

    private Line line;
    private Label ch;

    public void addTreeNode(Data data, double centerX, double centerY, double radius, Pane pane) {
        this.circle = new Circle(centerX, centerY, radius, Color.WHITE);
        this.label = new Label(String.valueOf(data.getFrequency()));
        this.label.setLayoutX(centerX - 5);
        this.label.setLayoutY(centerY - 10);
        this.label.setFont(new Font(20));
        this.ch = new Label(String.valueOf(data.getCh()));
        this.ch.setLayoutX(centerX);
        this.ch.setLayoutY(centerY + radius);
        this.ch.setFont(new Font(20));
        pane.getChildren().addAll(circle, label, ch);
    }


    public void addLine(String str, double startX, double startY, double endX, double endY, Pane pane) {
        this.line = new Line(startX, startY, endX, endY);
        this.label = new Label(str);
        if (startX > endX) {
            this.label.setLayoutX((startX + endX) / 2 - 20);
        } else {
            this.label.setLayoutX((startX + endX) / 2 + 20);
        }
        this.label.setLayoutY((startY + endY) / 2 - 20);
        this.label.setFont(new Font(20));
        pane.getChildren().addAll(line, label);
    }

}