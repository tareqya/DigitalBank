package Transaction;

import java.util.Scanner;

public class TransactionApplication {
	public static void main(String[] args) {
		try (Scanner scanner = new Scanner(System.in)){
			System.err.println("type your email:");
			String bankID = scanner.nextLine().trim();
			String bankBranch = scanner.nextLine().trim();
			
			try (TransactionSession u1 = new TransactionSession(bankID, args)){
				System.err.println("type a message:");
				String message = scanner.nextLine().trim();
				u1.sendMessage(message);
				System.err.println("type any key to quit");
				scanner.nextLine();
			}catch (Exception e) {
				e.printStackTrace();
			}
			
		}		
		
	}
}
