package Transaction;

import p2p.banking.BankingInterchange;

import java.text.SimpleDateFormat;
import java.util.Date;

public class TransactionSession implements AutoCloseable{
	private String email;
	private SimpleDateFormat formatter;
	
	private BankingInterchange<TransactionMessage> interchange;

	public TransactionSession(String email, String... config) {
		super();
		this.email = email;
		this.formatter = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
		this.interchange = new BankingInterchange<TransactionMessage>(this.email, config, this::handleMessage, new TransactionMessage());
	}
	
	public void sendMessage(String content) {
		TransactionMessage message = new TransactionMessage();
		message.setSender(this.email);
		message.setTimestamp(new Date());
		message.setContnet(content);
		
		this.interchange.sendBankingTransaction(message);
	}

	@Override
	public void close() throws Exception {
		this.interchange.close();		
	}
	
	public void handleMessage (TransactionMessage message) {
		if (this.email.equals(message.getSender())) {
			System.err.println("(" + message.getContnet() + ")");
		}else {
			System.err.println("" + message.getSender() + ": " + message.getContnet() + " (" + this.formatter.format(message.getTimestamp()) + ")");
		}
	}
}
