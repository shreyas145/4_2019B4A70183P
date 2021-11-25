import java.util.Scanner;

public class ATM {
	private double atmBalance = 50000;
	private Account acc;
	
	public ATM(Account acc){
		this.acc = acc;
	}
	
	public void updateAtmBalance(double amount,int mode) {
		if(mode == -1) {
			atmBalance -= amount;
		}
		else if(mode == 1) {
			atmBalance += amount;
		}
		else {
			System.err.println("Invalid mode of updation");
		}
	}
	
	public boolean verifyPIN(int cardNo, int enteredPin) {
		if(acc.getPIN(cardNo) == enteredPin) {
			return true;
		}
		else
			return false;
	}
	
	public static void displayTransactionOptions(FileReader file, Account acc, ATM atm){
		Scanner scan = new Scanner(System.in);
		
		while(true) {
		System.out.println("Choose an option:\n1)Withdraw Cash\n2)Deposit Cash\n3)View Balance\n4)Cancel Transaction");
		int choice = scan.nextInt();
		switch(choice) {
			case 1:
				while(true) {	
					System.out.println("Enter amount to be withdrawn:");
					double withdrawn = scan.nextDouble();
					if(withdrawn <= 0) {
						System.err.println("Invalid withdraw amount");
						continue;
					}
					Withdraw w = new Withdraw(atm,acc,withdrawn);
					if(w.multipleChecker() == false){
						System.err.println("Enter amount in multiples of 100 and 500 only");
						break;
					}
					else if(w.verifyAccountBalance() == false) {
						System.err.println("Insufficient account balance");
						break;
					}
					else if(w.verifyAtmBalance()==false) {
						System.err.println("Insufficient machine balance");
						System.out.println("Starting balance: "+atm.getAtmBalance());
						break;
					}
					else {
						w.updateBalance();
						file.writeDetailsToFile(false, acc);
						System.out.println("Transaction was successful\n");
						System.out.println("Press enter to continue");
						try {
							System.in.read();
						}
						catch(Exception e) {}
						break;
					}
				}
				break;
			
			case 2:
				while(true) {
					System.out.println("Enter amount to be deposited:");
					double deposit = scan.nextDouble();
					if(deposit > 0) {
							new Deposit(atm,acc,deposit).updateBalance();
							file.writeDetailsToFile(false, acc);
							System.out.println("Transaction was successful");
							System.out.println("Press enter to continue");
							try {
								System.in.read();
							}
							catch(Exception e) {}
							break;
						}
					else {
						System.err.println("Invalid deposit amount");
					}
				}
				break;
			
			case 3:
				double accountbalance = new ViewBalance(acc).displayBalance();
				System.out.println("Name: "+acc.getName(acc.getCardNo())+"\nBalance: "+accountbalance+"\nTransaction was successful\n");
				System.out.println("Press enter to continue");
				try {
					System.in.read();
				}
				catch(Exception e) {}
				break;
			case 4:
				scan.close();
				System.exit(0);
				break;
			default: System.err.println("Invalid option");
			}
		}
	}
	
	public double getAtmBalance() {
		return atmBalance;
	}
	
	public boolean verifySufficientBalance(double amount) {
		if(atmBalance >= amount)
			return true;
		else
			return false;
	}	
}
