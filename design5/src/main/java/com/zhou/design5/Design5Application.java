package com.zhou.design5;

import com.zhou.design5.view.LoginView;
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
@MapperScan("com.zhou.design5.dao")
public class Design5Application extends AbstractJavaFxApplicationSupport {
    public static void main(String[] args) {
        launch(Design5Application.class, LoginView.class, args);
    }

    @Override
    public void start(Stage stage) throws Exception {
        super.start(stage);
        stage.setTitle("登录");
    }
}
