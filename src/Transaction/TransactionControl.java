package Transaction;

import Database.DatabaseManager;
import Database.tables.Account;
import p2p.banking.BankingInterchange;

public class TransactionControl implements AutoCloseable{
    public static final String BANK_ID = "afeka02";
    private BankingInterchange<BankingTransactionBoundary> interchange;
    private boolean success;
    private boolean complete;
    public TransactionControl(String bankID, String... config){
        success = false;
        complete = false;
        this.interchange = new BankingInterchange<BankingTransactionBoundary>(bankID, config, this::handleTransaction, new BankingTransactionBoundary());

    }

    private void handleTransaction(BankingTransactionBoundary bankingTransactionBoundary) {
        try {
            if (bankingTransactionBoundary.getDestination().getBankId().equals(BANK_ID)) {
                DatabaseManager db = new DatabaseManager();
                Account account = db.getAccount(Integer.parseInt(bankingTransactionBoundary.getDestination().getAccountNumber()));
                float amount = (float) bankingTransactionBoundary.getAmount();
                account.setBalance(account.getBalance() + amount);
                this.success = db.updateAccount(account);
            }
        }catch (Exception e){
            this.success = false;
        }
        complete = true;
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

    public boolean isComplete() {
        return complete;
    }
}
