package user_interface;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public class MenuScreen extends Application {

    private Pane pane = new Pane();


    public MenuScreen(Stage primaryStage) {


        Scene mainScene = new Scene(pane);
        primaryStage.setScene(mainScene);
        primaryStage.centerOnScreen();
        primaryStage.setResizable(false);
        primaryStage.alwaysOnTopProperty();
        primaryStage.setTitle("DisplayMovingMessage");
        primaryStage.show();

    }

    @Override
    public void start(Stage stage) throws Exception {

    }
}
