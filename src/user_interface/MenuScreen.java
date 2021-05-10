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
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
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
    private Label titleLbl = new Label ("- WELCOME TO THE DIGITAL BANK -");
    private Text helloTxt = new Text("Hello, ");

    //Login scene
    private GridPane loginGridPane = new GridPane();
    private TextField tfUserName = new TextField();
    private TextField tfPassword = new TextField();
    private Button loginBtn = new Button("Log in");
    private ToggleGroup group = new ToggleGroup();
    private RadioButton clientRbtn = new RadioButton("Client");
    private RadioButton employeeRbtn = new RadioButton("Employee");
    private Text signInTxt = new Text("new customer? Sign in here");

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
    private GridPane transferDetailsGridPane = new GridPane();
    private TextField tfMoneyAmount = new TextField();
    private TextField tfDestinationBankID = new TextField();
    private TextField tfDestinationAccountNumber = new TextField();
   // private Text txtTransactionID = new Text();

    //Client UI : view account status tab
    private GridPane accountDetailsGridPane = new GridPane();

    //Client UI : open request status tab
    private GridPane requestDetailsGridPane = new GridPane();
    private Button sendRequestOkBtn = new Button("SEND");
    private TextField tfRequestSubject = new TextField();
    private TextField tfRequestDescription = new TextField();



    //Employee UI - menu screen
    private VBox employeeUIPane = new VBox(20);
    private final ScrollPane requestsScrollPane = new ScrollPane();
    private TextField tfRequestNumber = new TextField("enter request number...    ");
    private Button updateRequestOkBtn = new Button("OK");


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

    public MenuScreen(Stage primaryStage) {

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
        transferTabPane.setMinHeight(550);
        transferTabPane.setPadding(new Insets(30));

        //set client UI - transfer tab
        transferDetailsGridPane.add(new Label("Transfer details: "), 0, 0); // column=1 row=0
        //   transferDetailsGridPane.add(new Label("Transaction type: "), 0, 1);    //transacction lbl
        //   transferDetailsGridPane.add(new Label("Transaction type: "), 1, 1);     //transacction radio button
        transferDetailsGridPane.add(new Label("Transaction amount: "), 0, 1);     //transacction lbl
        transferDetailsGridPane.add(tfMoneyAmount, 1, 1);     //transacction amount tf
        transferDetailsGridPane.add(new Label("Destination bank ID: "), 0, 2);
        transferDetailsGridPane.add(tfDestinationBankID, 1, 2);
        transferDetailsGridPane.add(new Label("Destination account number: "), 0, 3);
        transferDetailsGridPane.add(tfDestinationAccountNumber, 1, 3);
        transferDetailsGridPane.add(transferOkBtn, 0, 4);
        transferTabPane.getChildren().addAll(helloTxt,transferDetailsGridPane);

        //set client UI - view account tab
        accountDetailsGridPane.add(new Label("Bank ID: "), 0, 0);
        accountDetailsGridPane.add(new Label("Account number: "), 0, 1);
        accountDetailsGridPane.add(new Label("Client name: "), 0, 2);
        accountDetailsGridPane.add(new Label("Client SSN: "), 0, 3);
        accountDetailsGridPane.add(new Label("Balance: "), 0, 4);
        accountDetailsGridPane.add(new Label("Email: "), 0, 5);
        viewAccountTabPane.getChildren().addAll(helloTxt,accountDetailsGridPane);
        viewAccountTabPane.setPadding(new Insets(50));

        //set client UI - open request tab
        requestDetailsGridPane.add(new Label("Subject: "), 0, 0);
        requestDetailsGridPane.add(tfRequestSubject, 1, 0);
        requestDetailsGridPane.add(new Label("Account number: "), 0, 1);
        requestDetailsGridPane.add(tfRequestDescription, 1, 1);
        requestDetailsGridPane.add(sendRequestOkBtn, 0, 2);
        openRequestTabPane.getChildren().addAll(helloTxt,requestDetailsGridPane);
        tfRequestSubject.setMinWidth(150);
        tfRequestDescription.setMinWidth(150);
        openRequestTabPane.setPadding(new Insets(25));

        //set employee UI - employeeUIPane
        employeeUIPane.getChildren().addAll(helloTxt, new Label("List of customers requests: "), requestsScrollPane,
                new HBox(new Label("Update request status:            "), tfRequestNumber), updateRequestOkBtn);
        helloTxt.setFont(Font.font("Verdana", FontWeight.BOLD, 20));
        requestsScrollPane.setPrefSize(300, 300);

        //design GridPanes
        loginGridPane.setAlignment(Pos.CENTER);
        loginGridPane.setHgap(20); //horizontal gap in pixels => that's what you are asking for
        loginGridPane.setVgap(30); //vertical gap in pixels
        loginGridPane.setPadding(new Insets(15, 15, 15, 15)); //margins around the whole grid(top/right/bottom/left)
        transferDetailsGridPane.setAlignment(Pos.CENTER);
        transferDetailsGridPane.setHgap(35);
        transferDetailsGridPane.setVgap(30);
        accountDetailsGridPane.setHgap(35);
        accountDetailsGridPane.setVgap(30);
        requestDetailsGridPane.setHgap(35);
        requestDetailsGridPane.setVgap(30);
        requestDetailsGridPane.setPadding(new Insets(15, 15, 15, 15));
     //   transferDetailsGridPane.setPadding(new Insets(10, 15, 15, 15));

        //design buttons
        loginBtn.setMinWidth(75);
        transferOkBtn.setMinWidth(75);
        updateRequestOkBtn.setMinWidth(75);
        sendRequestOkBtn.setMinWidth(75);
        signInTxt.setFont(Font.font("Verdana", FontWeight.BOLD, 11));
        //loginBtn.setStyle(buttonsStyle);

        //design main pane
        pane.getChildren().addAll(titleLbl, loginGridPane, loginBtn, signInTxt, btnPane);
        pane.setPadding(new Insets(20));
        pane.setAlignment(Pos.CENTER);
        titleLbl.setFont(Font.font("Verdana", FontWeight.BOLD, 13));

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
                        updateAccountStatus();
                        clientMenuScreen();
                    }else{
                        employeeMenuScreen();
                    }
                }
            }

            private void updateAccountStatus() {

                //TODO
                //implement the "view account" tab
            }
        });



        transferOkBtn.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                //get properties from UI
                float amount;
                Date create_date;
                Account destinationAccount;
                int transactionID;

                transferMoneyFromAccount();
            }
        });

        updateRequestOkBtn.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                updateRequestStatus(tfRequestNumber);
            }

            private void updateRequestStatus(TextField tfRequestNumber) {
                //TODO
            }
        });

        signInTxt.addEventFilter(
                MouseEvent.MOUSE_PRESSED,
                new EventHandler<MouseEvent>() {
                    @Override
                    public void handle(MouseEvent event) {
                        signIn();
                        System.out.println("PRESSED");
                    }
                }
        );
}

    private void transferMoneyFromAccount() {

        //TODO implement the OPEN NEW TRANSFER MONEY REQUEST


    }

    private void signIn() {
        pane.getChildren().clear();

        //get properties from UI
        String ssn;
        String fullName;
        String username;
        String password;
        String email;

        //TODO implement the SIGN IN

    }

    private void employeeMenuScreen() {
        pane.getChildren().clear();
        pane.getChildren().add(employeeUIPane);
    }

    private void clientMenuScreen() {
        pane.getChildren().clear();
        pane.getChildren().add(tabPane);
    }

    @Override
    public void start(Stage stage) throws Exception {

    }
}