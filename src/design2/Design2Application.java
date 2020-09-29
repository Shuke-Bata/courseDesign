package design2;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

/**
 * @author Minghua Zhou 周明华
 * @version 1.0
 * @title Design2Application
 * @description
 * @date 2019/12/10 19:46
 * @modified by:
 */
public class Design2Application extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("fxml/main.fxml"));
        primaryStage.setTitle("哈夫曼法数据压缩和解压缩");
        primaryStage.setScene(new Scene(root));
        primaryStage.show();
    }
}
