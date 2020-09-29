package design3.version1;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

/**
 * @title: Design3Version1Application
 * @description:
 * @date: 2019/12/12 11:01
 * @author: Minghua Zhou 周明华
 * @version: 1.0
 * @modified by:
 */
public class Design3Version1Application extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("version1Main.fxml"));
        primaryStage.setTitle("24点扑克牌游戏");
        primaryStage.setScene(new Scene(root));
        primaryStage.show();
    }
}
