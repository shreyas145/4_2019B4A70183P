
public class Account implements Transaction{
	private int cardNo;
	private String username;
	private double balance;
	private int PIN;
	
	Account(int cardNo, String username, double balance, int PIN){
		this.cardNo = cardNo;
		this.username = username;
		this.balance = balance;
		this.PIN = PIN;
	}
	
	public double getBalance(int cardNo) {
		return balance;
	}
	
	public String getName(int cardNo) {
		return username;
	}
	
	public int getPIN(int cardNo) {
		return PIN;
	}
	
	public int getCardNo() {
		return cardNo;
	}
	public void updateBalance(int cardNo, double balance) {
		if(cardNo == this.cardNo) {
			this.balance = balance;
		}
		else {
			System.out.println("Incorrect card number and balance combination");
		}
	}
	
	public void updatePIN(int cardNo, int PIN) {
		if(cardNo == this.cardNo) {
			this.PIN = PIN;
		}
		else {
			System.out.println("Incorrect card number and PIN combination");
		}
	}
}
