package design2.controller;

import design2.huffman.EncodeResult;
import design2.huffman.HuffmanAlgorithm;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import javafx.scene.text.Font;
import javafx.stage.Stage;

/**
 * @author Minghua Zhou 周明华
 * @version 1.0
 * @title MainController
 * @description
 * @date 2019/12/10 19:49
 * @modified by:
 */
public class Controller {
    @FXML
    private TextField encodeInput;
    @FXML
    private TextField decodeInput;

    @FXML
    private ScrollPane scrollPane;
    /**
     * 编码结果
     */
    private EncodeResult encodeResult;

    /**
     * 编码
     */
    public void pressEncode() {
        HuffmanAlgorithm huffmanAlgorithm = new HuffmanAlgorithm();
        Pane pane = new Pane();
        String encodeStr = encodeInput.getText().trim();
        encodeResult = huffmanAlgorithm.encode(encodeStr, pane);
        scrollPane.setContent(pane);
        showMessage(encodeStr, encodeResult.getEncode());
    }

    /**
     * 解码
     */
    public void pressDecode() {
        HuffmanAlgorithm huffmanAlgorithm = new HuffmanAlgorithm();
        String decodeStr = decodeInput.getText().trim();
        encodeResult.setEncode(decodeStr);
        String decode = huffmanAlgorithm.decode(encodeResult);
        showMessage(decodeStr, decode);
    }

    public void showMessage(String str, String decode) {
        Pane pane = new Pane();
        Label label = new Label(str + "is decode to" + decode);
        pane.getChildren().add(label);
        label.setLayoutX(100);
        label.setLayoutY(30);
        label.setFont(Font.font(20));
        Stage stage = new Stage();
        stage.setScene(new Scene(pane,  400, 100));
        stage.show();
    }
}
