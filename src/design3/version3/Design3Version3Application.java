package design3.version3;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 * @author Minghua Zhou 周明华
 * @version 1.0
 * @title Design3Version3Application
 * @description
 * @date 2019/12/28 19:24
 * @modified by:
 */
public class Design3Version3Application extends Application {
    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("version3Main.fxml"));
        primaryStage.setTitle("24点扑克牌游戏");
        primaryStage.setScene(new Scene(root));
        primaryStage.show();
    }
}
