package user_interface;

import Chat.ChatSession;
import Database.DatabaseManager;
import Database.tables.*;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import org.apache.kafka.common.protocol.types.Field;

import java.util.Calendar;
import java.util.Date;
import Transaction.AccountBoundary;
import Transaction.TransactionControl;
import Transaction.BankingTransactionBoundary;

public class MenuScreen extends Application {

    int numberOfMenuOptions = 10;
    private boolean isClient = false;
    private boolean isEmployee = false;
    private final VBox pane = new VBox(20);

    private final TextField tfUserName = new TextField();
    private final PasswordField tfPassword = new PasswordField();
    private final RadioButton clientRbtn = new RadioButton("Client");

    private final VBox viewAccountTabPane = new VBox(20);
    private final VBox openRequestTabPane = new VBox(20);
    private final TabPane tabPane = new TabPane();
    private final Tab tab3 = new Tab("Open request", openRequestTabPane);

    //Client UI : transfer tab
    private final Button transferOkBtn = new Button("SEND");
    private final GridPane transferDetailsGridPane = new GridPane();
    private final TextField tfMoneyAmount = new TextField();
    private final TextField tfCustomerName = new TextField();
    private final TextField tfBranchName = new TextField();
    private final TextField tfDestinationBankID = new TextField();
    private final TextField tfDestinationAccountNumber = new TextField();
    // private Text txtTransactionID = new Text();

    //Client UI : view account status tab
    private final GridPane accountDetailsGridPane = new GridPane();

    //Client UI : open request status tab
    private final GridPane requestDetailsGridPane = new GridPane();
    private final Button sendRequestOkBtn = new Button("SEND");
    private final TextField tfRequestSubject = new TextField();
    private final TextField tfRequestDescription = new TextField();

    private Boolean isAssign = false;

    //Employee UI - menu screen
    private final VBox employeeUIPane = new VBox(20);
    private final ScrollPane requestsScrollPane = new ScrollPane();
    private final TextField tfRequestNumber = new TextField("enter request number...    ");
    private final Button updateRequestOkBtn = new Button("OK");
    RadioButton employeeRbtn = new RadioButton("Employee");
    private BankClient bc;
    private Employee em;
    private Person client;

    private Label bankID = new Label("Bank ID: ");
    private Label accountNumber = new Label("Account number: ");
    private Label client_Name = new Label("Client Name: ");
    private Label client_ssn = new Label("Client SSN: ");
    private Label balance = new Label("Balance: ");
    private Label clientemail = new Label("Email: ");
    private StringBuilder accountClientsnumbers = new StringBuilder();
    private final String buttonsStyle = "-fx-background-color: \n" +
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
        //Login scene
        GridPane loginGridPane = new GridPane();
        loginGridPane.add(new Label("Login details: "), 1, 0); // column=1 row=0
        loginGridPane.add(new Label("Email: "), 0, 1);  // column=2 row=0
        loginGridPane.add(tfUserName, 1, 1);
        loginGridPane.add(new Label("Password: "), 0, 2);
        loginGridPane.add(tfPassword, 1, 2);
        loginGridPane.add(clientRbtn, 0, 3);

        loginGridPane.add(employeeRbtn, 1, 3);

        //set radio buttons (employee \ client) for sign in
        ToggleGroup group = new ToggleGroup();
        clientRbtn.setToggleGroup(group);
        employeeRbtn.setToggleGroup(group);

        //set client UI - tabs pane
        //Client UI - menu screen
        VBox transferTabPane = new VBox(20);
        Tab tab1 = new Tab("Transfer money", transferTabPane);
        Tab tab2 = new Tab("View account status", viewAccountTabPane);
        tabPane.getTabs().addAll(tab1, tab2, tab3);
        transferTabPane.setMinHeight(550);
        transferTabPane.setPadding(new Insets(30));

        //set client UI - transfer tab
        transferDetailsGridPane.add(new Label("Transfer details: "), 0, 0); // column=1 row=0
        //   transferDetailsGridPane.add(new Label("Transaction type: "), 0, 1);    //transacction lbl
        //   transferDetailsGridPane.add(new Label("Transaction type: "), 1, 1);     //transacction radio button
        transferDetailsGridPane.add(new Label("Customer Name: "), 0, 1);
        transferDetailsGridPane.add(tfCustomerName, 1, 1);
        transferDetailsGridPane.add(new Label("Branch Name: "), 0, 2);
        transferDetailsGridPane.add(tfBranchName, 1, 2);
        transferDetailsGridPane.add(new Label("Transaction amount: "), 0, 3);     //transacction lbl
        transferDetailsGridPane.add(tfMoneyAmount, 1, 3);     //transacction amount tf
        transferDetailsGridPane.add(new Label("Destination bank ID: "), 0, 4);
        transferDetailsGridPane.add(tfDestinationBankID, 1, 4);
        transferDetailsGridPane.add(new Label("Destination account number: "), 0, 5);
        transferDetailsGridPane.add(tfDestinationAccountNumber, 1, 5);
        transferDetailsGridPane.add(transferOkBtn, 0, 6);
        Text helloTxt = new Text("Hello, ");
        transferTabPane.getChildren().addAll(helloTxt, transferDetailsGridPane);

        //set client UI - view account tab
        accountDetailsGridPane.add(bankID, 0, 0);
        accountDetailsGridPane.add(accountNumber, 0, 1);
        accountDetailsGridPane.add(client_Name, 0, 2);
        accountDetailsGridPane.add(client_ssn, 0, 3);
        accountDetailsGridPane.add(balance, 0, 4);
        accountDetailsGridPane.add(clientemail, 0, 5);


        viewAccountTabPane.getChildren().addAll(helloTxt, accountDetailsGridPane);
        viewAccountTabPane.setPadding(new Insets(50));

        //set client UI - open request tab
        requestDetailsGridPane.add(new Label("Subject: "), 0, 0);
        requestDetailsGridPane.add(tfRequestSubject, 1, 0);
        requestDetailsGridPane.add(new Label("Description: "), 0, 1);
        requestDetailsGridPane.add(tfRequestDescription, 1, 1);
        requestDetailsGridPane.add(sendRequestOkBtn, 0, 2);
        openRequestTabPane.getChildren().addAll(helloTxt, requestDetailsGridPane);
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
        Button loginBtn = new Button("Log in");
        loginBtn.setMinWidth(75);
        transferOkBtn.setMinWidth(75);
        updateRequestOkBtn.setMinWidth(75);
        sendRequestOkBtn.setMinWidth(75);
        Text signInTxt = new Text("new customer? Sign in here");
        signInTxt.setFont(Font.font("Verdana", FontWeight.BOLD, 11));
        //loginBtn.setStyle(buttonsStyle);

        //design main pane
        Label titleLbl = new Label("- WELCOME TO THE DIGITAL BANK -");
        VBox btnPane = new VBox(10);
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
                Login();
                if (isAssign) {
                    if (isClient) {
                        showClientDetails(0);
                        updateAccountStatus();
                        clientMenuScreen();
                    } else {

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
        sendRequestOkBtn.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
               // String description=tfRequestDescription.getText();

                openClientRequest();
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
        transferOkBtn.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {transferMoneyFromAccount();}


        });

        signInTxt.addEventFilter(
                MouseEvent.MOUSE_PRESSED,
                new EventHandler<MouseEvent>() {
                    @Override
                    public void handle(MouseEvent event) {
                        Login();
                        System.out.println("PRESSED");
                    }
                }
        );
    }

    private void openClientRequest( ) {
        String snn=bc.getSsn();
        String description=tfRequestDescription.getText();
        String subject=tfRequestSubject.getText();
        Request request=new Request(bc.getRequests().size()+1,subject,description);
        bc.addRequest(request);
        Text sendRequestText = new Text("Request send");
        pane.getChildren().add(sendRequestText);
        tfRequestDescription.clear();
        tfRequestSubject.clear();
        DatabaseManager databaseManager=new DatabaseManager();
        databaseManager.addRequestToClient(snn,request);



    }

    private void transferMoneyFromAccount ()  {

            double amount = Double.parseDouble(tfMoneyAmount.getText());
            String destBankID=tfDestinationBankID.getText();
            String destAccountNum=tfDestinationAccountNumber.getText();
            String branchName = tfBranchName.getText();
            String customerName = tfCustomerName.getText();
            AccountBoundary destAccount = new AccountBoundary(destAccountNum, destBankID, branchName, customerName);
            Account account = bc.getAccounts().get(0);
            AccountBoundary srcAccount = new AccountBoundary(account.getAccountNumber()+"",
                    account.getBankID(), "online", bc.getFullName());
            if(account.getBalance() - amount < 0)
            {
                //TODO: show error msg no enough money
                return;
            }
            BankingTransactionBoundary bankingTransactionBoundary = new BankingTransactionBoundary("",
                    Calendar.getInstance().getTime(), amount, srcAccount, destAccount);
            try (TransactionControl transactionControl = new TransactionControl(destBankID)){
                transactionControl.sendTransaction(bankingTransactionBoundary);
                if(transactionControl.isSuccess()) // transaction success
                {
                    Account destinationAccount = new Account(destAccount.getBankId(),
                            Integer.parseInt(destAccountNum), customerName, "", 0);
                    account.addTransaction(new Transaction(account.getTransactions().size()+1,
                            "",(float) amount, destinationAccount));
                    account.setBalance(account.getBalance() - (float)amount);
                    DatabaseManager db = new DatabaseManager();
                    if(db.updateAccount(account)){
                        //TODO: show success msg
                    }else{
                        // TODO: show error msg failed to update account
                    }

                }
            }catch (Exception e) {
                e.printStackTrace();
                //TODO: show error msg e.toString()
            }

    }

    private void Login() {
        pane.getChildren().clear();
        String email = tfUserName.getText();
        String password = tfPassword.getText();
        DatabaseManager db = new DatabaseManager();

        if (clientRbtn.isSelected()) {
            bc = db.getBankClientByEmail(email);
            if (bc != null && bc.getPassword().equals(password)) {
                isAssign = true;
                isClient = true;

            } else
                isAssign = false;

        } else {
            if (employeeRbtn.isSelected()) {
                em = db.getEmployee(email);
                if (em != null && em.getPassword().equals(password)) {
                    isAssign = true;
                    isEmployee = true;

                } else
                    isAssign = false;
            }

        }


    }

    private void employeeMenuScreen() {
        pane.getChildren().clear();
        pane.getChildren().add(employeeUIPane);
    }

    private void clientMenuScreen() {
        pane.getChildren().clear();
        pane.getChildren().add(tabPane);
    }

    private void showClientDetails(int index) {
        bankID.setText(bankID.getText() +" "+bc.getAccounts().get(index).getBankID() + "");
        accountNumber.setText(accountNumber.getText()+" "+bc.getAccounts().get(index).getAccountNumber()+"");
        client_Name.setText(client_Name.getText() +" "+ bc.getFullName());
        client_ssn.setText(client_ssn.getText() +" "+bc.getSsn());
        balance.setText(balance.getText() +" "+String.format("%.2f", bc.getAccounts().get(index).getBalance()));
        clientemail.setText(clientemail.getText() +" "+bc.getEmail());

    }


    @Override
    public void start(Stage stage) throws Exception {


    }


}