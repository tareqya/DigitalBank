package Transaction;

import Database.DatabaseManager;
import Database.tables.Account;
import p2p.banking.BankingInterchange;

public class TransactionControl implements AutoCloseable{
    public static final String bankID = "afeka02";
    private BankingInterchange<BankingTransactionBoundary> interchange;
    private boolean success;

    public TransactionControl(String bankID, String... config){
        success = true;
        this.interchange = new BankingInterchange<BankingTransactionBoundary>(bankID, config, this::handleTransaction, new BankingTransactionBoundary());

    }

    private void handleTransaction(BankingTransactionBoundary bankingTransactionBoundary) {
        if(bankingTransactionBoundary.getDestination().getBankId().equals(bankID)){
            DatabaseManager db = new DatabaseManager();
            Account account = db.getAccount(Integer.parseInt(bankingTransactionBoundary.getDestination().getAccountNumber()));
            float amount = (float) bankingTransactionBoundary.getAmount();
            account.setBalance(account.getBalance() + amount);
            if(!db.updateAccount(account))
                this.success = false;
        }
    }

    public void sendTransaction(BankingTransactionBoundary BankingTransaction) {
        this.interchange.sendBankingTransaction(BankingTransaction);
    }

    @Override
    public void close() throws Exception {
        this.interchange.close();
    }

    public boolean isSuccess() {
        return success;
    }
}
