//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package user_interface;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class MenuScreen extends Application {
    int numberOfMenuOptions = 10;
    private VBox pane = new VBox(20.0D);
    private VBox btnPane = new VBox(10.0D);
    private Label title = new Label("WELCOME TO THE DIGITAL BANK");
    private String buttonsStyle = "-fx-background-color: \n        #000000,\n        linear-gradient(#7ebcea, #2f4b8f),\n        linear-gradient(#426ab7, #263e75),\n        linear-gradient(#395cab, #223768);\n    -fx-background-insets: 0,1,2,3;\n    -fx-background-radius: 3,2,2,2;\n    -fx-padding: 12 30 12 30;\n    -fx-text-fill: white;\n    -fx-font-size: 12px;";
    private Button[] btnMenu = new Button[10];

    public MenuScreen(Stage primaryStage) {
        this.designButtons();
        this.pane.getChildren().addAll(new Node[]{this.title, this.btnPane});
        this.pane.setPadding(new Insets(20.0D));
        this.pane.setAlignment(Pos.CENTER);
        Scene mainScene = new Scene(this.pane, 500.0D, 500.0D);
        primaryStage.setScene(mainScene);
        primaryStage.centerOnScreen();
        primaryStage.setResizable(false);
        primaryStage.alwaysOnTopProperty();
        primaryStage.setTitle("DisplayMovingMessage");
        primaryStage.show();
    }

    private void designButtons() {
        for(int i = 0; i < 10; ++i) {
        }

    }

    public void start(Stage stage) throws Exception {
    }
}