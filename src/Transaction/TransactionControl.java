package Transaction;

import p2p.banking.BankingInterchange;

public class TransactionControl implements AutoCloseable{
    public static final String bankID = "afeka02";
    private BankingInterchange<BankingTransactionBoundary> interchange;

    public TransactionControl(String bankID, String... config){

        this.interchange = new BankingInterchange<BankingTransactionBoundary>(bankID, config, this::handleTransaction, new BankingTransactionBoundary());

    }

    private void handleTransaction(BankingTransactionBoundary bankingTransactionBoundary) {
        if(bankingTransactionBoundary.getDestination().getBankId().equals(bankID)){
            //TODO: update destination account balance
        }
    }

    public void sendTransaction(BankingTransactionBoundary BankingTransaction) {
        this.interchange.sendBankingTransaction(BankingTransaction);
    }

    @Override
    public void close() throws Exception {
        this.interchange.close();
    }

}
