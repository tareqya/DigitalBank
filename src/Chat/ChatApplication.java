package Chat;

import java.util.Scanner;

public class ChatApplication {
	public static void main(String[] args) {
		try (Scanner scanner = new Scanner(System.in)){
			System.err.println("type your email:");
			String email = scanner.nextLine().trim();
			
			try (ChatSession u1 = new ChatSession(email, args)){
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
