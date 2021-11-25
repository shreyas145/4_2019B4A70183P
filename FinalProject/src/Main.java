import java.util.*;
import java.io.*;

public class Main {
	public static void main(String args[]) throws IOException {
		FileReader file = new FileReader();
		Account acc;
		ATM atm;
		
		file.readDetailsFromFile();
		Scanner accountChoiceScanner = new Scanner(System.in);
		
		while(true) {
			try {
				System.out.println("Choose an option:");
				System.out.println("1)Create new account\n2)Use existing account\n3)Cancel");
			
				int accountChoice = Integer.parseInt(accountChoiceScanner.next());
				switch(accountChoice) {
				case 1: 
					System.out.println("Enter first name");
					String name = accountChoiceScanner.next();
					System.out.println("Enter PIN");
					int PIN = Integer.parseInt(accountChoiceScanner.next());
					acc = new Account(file.getNewCardNo(),name,0,PIN);
					System.out.println("Your card number is "+file.getNewCardNo());
					System.out.println("Press enter to quit");
					try {
						System.in.read();
					}
					catch(Exception e) {}
					file.writeDetailsToFile(true, acc);
					System.exit(0);
					break;
				
				case 2:
					System.out.println("Enter card number");			
					int cardno = accountChoiceScanner.nextInt();
					while(cardno >= file.getNewCardNo() || cardno <= 0) {	
						System.err.println("Invalid card number");
						System.out.println("Enter card number");
						cardno = accountChoiceScanner.nextInt();
					}
					System.out.println("Enter PIN");
					int enteredPIN = accountChoiceScanner.nextInt();
					
					acc = new Account(cardno,file.getName(cardno),file.getBalance(cardno),file.getPIN(cardno));
					atm = new ATM(acc);
					
					while(atm.verifyPIN(cardno, enteredPIN)==false) {
						System.err.println("Wrong PIN");
						System.out.println("Enter PIN");
						enteredPIN = accountChoiceScanner.nextInt();
					}
					ATM.displayTransactionOptions(file, acc, atm);
					break;
				
				case 3:
					accountChoiceScanner.close();
					System.exit(0);
					break;
					
				default: System.err.println("Invalid option");
				}
			}
			catch(InputMismatchException e) {
				System.err.println("Invalid option");
				continue;
			}
			catch(NumberFormatException e) {
				System.err.println("Invalid option");
				continue;
			}
		}
	}
	
}
