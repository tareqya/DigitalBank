package Chat;

import java.text.SimpleDateFormat;
import java.util.Date;

import p2p.banking.BankingInterchange;

public class ChatSession implements AutoCloseable{
	private String email;
	private SimpleDateFormat formatter;
	
	private BankingInterchange<ChatMessage> interchange;

	public ChatSession(String email, String... config) {
		super();
		this.email = email;
		this.formatter = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
		this.interchange = new BankingInterchange<ChatMessage>(this.email, config, this::handleMessage, new ChatMessage());
	}
	
	public void sendMessage(String content) {
		ChatMessage message = new ChatMessage();
		message.setSender(this.email);
		message.setTimestamp(new Date());
		message.setContnet(content);
		
		this.interchange.sendBankingTransaction(message);
	}

	@Override
	public void close() throws Exception {
		this.interchange.close();		
	}
	
	public void handleMessage (ChatMessage message) {
		if (this.email.equals(message.getSender())) {
			System.err.println("(" + message.getContnet() + ")");
		}else {
			System.err.println("" + message.getSender() + ": " + message.getContnet() + " (" + this.formatter.format(message.getTimestamp()) + ")");
		}
	}
}
