package com.example.design6;

import com.example.design6.view.LoginView;
import de.felixroske.jfxsupport.AbstractJavaFxApplicationSupport;
import javafx.stage.Stage;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @author Minghua Zhou 周明华
 * @title
 * @description
 * @updateTime 2019/12/16 16:22
 */
@SpringBootApplication
@MapperScan("com.example.design6.dao")
public class Design6Application extends AbstractJavaFxApplicationSupport {
    public static void main(String[] args) {
        launch(Design6Application.class, LoginView.class, args);
    }

    @Override
    public void start(Stage stage) throws Exception {
        super.start(stage);
        stage.setTitle("全国交通咨询系统-登录");
    }
}
