package user_interface;

import Database.tables.Account;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.geometry.Side;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.time.Clock;
import java.util.Date;
import java.util.Vector;

public class MenuScreen extends Application {

    int numberOfMenuOptions = 10;

    private VBox pane = new VBox(20);
    private VBox btnPane = new VBox(10);
    private Label titleLbl = new Label ("WELCOME TO THE DIGITAL BANK");
    private Text helloTxt = new Text("Hello, ");

    //Login scene
    private GridPane loginGridPane = new GridPane();
    private TextField tfUserName = new TextField();
    private TextField tfPassword = new TextField();
    private Button loginBtn = new Button("Log in");
    private ToggleGroup group = new ToggleGroup();
    private RadioButton clientRbtn = new RadioButton("Client");
    private RadioButton employeeRbtn = new RadioButton("Employee");

    //Client UI - menu screen
    private VBox transferTabPane = new VBox(20);
    private VBox viewAccountTabPane = new VBox(20);
    private VBox openRequestTabPane = new VBox(20);
    private TabPane tabPane = new TabPane();
    private Tab tab1 = new Tab("Transfer money", transferTabPane);
    private Tab tab2 = new Tab("View account status"  , viewAccountTabPane);
    private Tab tab3 = new Tab("Open request"  , openRequestTabPane);

    //Client UI : transfer tab
    private Button transferOkBtn = new Button("OK");
    /*private int transactionID;
    private String type;
    private float amount;
    private Date create_date;
    private Account destinationAccount;*/

    //Client UI : transfer tab


    //Employee UI - menu screen
    private VBox employeeUIPane = new VBox(20);



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
        loginGridPane.add(clientRbtn, 0, 3);
        loginGridPane.add(employeeRbtn, 1, 3);

        //set radio buttons (employee \ client) for sign in
        clientRbtn.setToggleGroup(group);
        employeeRbtn.setToggleGroup(group);

        //set client UI - tabs pane
        tabPane.getTabs().addAll(tab1, tab2, tab3);
        transferTabPane.setMinSize(550, 550);
        transferTabPane.setPadding(new Insets(20));

        //set client UI - transfer tab
        transferTabPane.getChildren().addAll(helloTxt, transferOkBtn);


        //set employee UI - employeeUIPane
        employeeUIPane.getChildren().add(helloTxt);
        helloTxt.setFont(Font.font("Verdana", FontWeight.BOLD, 20));

        //design GridPane
        loginGridPane.setAlignment(Pos.CENTER);
        loginGridPane.setHgap(20); //horizontal gap in pixels => that's what you are asking for
        loginGridPane.setVgap(30); //vertical gap in pixels
        loginGridPane.setPadding(new Insets(15, 15, 15, 15)); //margins around the whole grid(top/right/bottom/left)

        //design buttons
        loginBtn.setMinWidth(75);
        transferOkBtn.setMinWidth(75);
        //loginBtn.setStyle(buttonsStyle);

        //design main pane
        pane.getChildren().addAll(titleLbl, loginGridPane, loginBtn, btnPane);
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


        loginBtn.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {

                Boolean isClient = clientRbtn.isSelected();
                String userName = tfUserName.getText();
                String password = tfPassword.getText();

                //TODO
                Boolean isAssign = true;


                if(isAssign){
                    if(isClient){
                        clientMenuScreen();
                    }else{
                        employeeMenuScreen();
                    }
                }
            }
    });



        transferOkBtn.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {

                //get properties from UI
                int transactionID = 1;
                String type = "1";
                float amount = 1;
                Date create_date = new Date();
                Account destinationAccount = new Account();

                //TODO
                Boolean isAssign = true;

            }
        });



}

    private void employeeMenuScreen() {
        pane.getChildren().clear();
        pane.getChildren().add(employeeUIPane);
    }

    private void clientMenuScreen() {
        pane.getChildren().clear();
        pane.getChildren().add(tabPane);
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