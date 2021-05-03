import javafx.application.Application;
import javafx.stage.Stage;
import user_interface.MenuScreen;

public class Start extends Application {

    public static void main(String[] args){
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        MenuScreen menuScreen = new MenuScreen(primaryStage);
    }
}
