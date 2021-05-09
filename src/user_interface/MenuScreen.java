package user_interface;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.time.Clock;
import java.util.Vector;

public class MenuScreen extends Application {

    int numberOfMenuOptions = 10;

    private VBox pane = new VBox(20);
    private VBox btnPane = new VBox(10);
    private Label titleLbl = new Label ("WELCOME TO THE DIGITAL BANK");

    //Login scene
    private GridPane loginGridPane = new GridPane();
    private TextField tfUserName = new TextField();
    private TextField tfPassword = new TextField();
    private Button okBtn = new Button("OK");

    private String buttonsStyle = "-fx-background-color: \n" +
            "        #000000,\n" +
            "        linear-gradient(#7ebcea, #2f4b8f),\n" +
            "        linear-gradient(#426ab7, #263e75),\n" +
            "        linear-gradient(#395cab, #223768);\n" +
            "    -fx-background-insets: 0,1,2,3;\n" +
            "    -fx-background-radius: 3,2,2,2;\n" +
            "    -fx-padding: 12 30 12 30;\n" +
            "    -fx-text-fill: white;\n" +
            "    -fx-font-size: 12px;";

    //declare buttons
    private Button[] btnMenu = new Button[10];

    public MenuScreen(Stage primaryStage) {

        designButtons();

        //set login pane (GridPane)
        loginGridPane.add(new Label("Login details: "), 1, 0); // column=1 row=0
        loginGridPane.add(new Label("Username: "), 0, 1);  // column=2 row=0
        loginGridPane.add(tfUserName, 1, 1);
        loginGridPane.add(new Label("Password: "), 0, 2);
        loginGridPane.add(tfPassword, 1, 2);
        // loginGridPane.add(okBtn, 1, 3);

        //design GridPane
        loginGridPane.setAlignment(Pos.CENTER);
        loginGridPane.setHgap(10); //horizontal gap in pixels => that's what you are asking for
        loginGridPane.setVgap(10); //vertical gap in pixels
        loginGridPane.setPadding(new Insets(15, 15, 15, 15)); //margins around the whole grid(top/right/bottom/left)

        //design buttons
        okBtn.setMinWidth(75);

        //design main pane
        pane.getChildren().addAll(titleLbl, loginGridPane, okBtn, btnPane);
        pane.setPadding(new Insets(20));
        pane.setAlignment(Pos.CENTER);



        //set scene
        Scene mainScene = new Scene(pane, 500, 500);
        primaryStage.setScene(mainScene);
        primaryStage.centerOnScreen();
        primaryStage.setResizable(false);
        primaryStage.alwaysOnTopProperty();
        primaryStage.setTitle("DisplayMovingMessage");
        primaryStage.show();


        okBtn.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {

                System.out.println("one");
                //TODO


            }
    });



}

    private void designButtons() {
        for (int i=0; i<10; i++) {
            //TODO
            // this.btnMenu[i].setStyle(buttonsStyle);
            // btnPane.getChildren().addAll(btnMenu[i]);
        }

    }




    @Override
    public void start(Stage stage) throws Exception {

    }
}