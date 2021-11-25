public class Deposit {
	private double cash;
	private Account acc;
	private ATM atm;
	
	public Deposit(ATM atm, Account acc, double cash) {
		this.cash = cash;
		this.acc = acc;
		this.atm = atm;
	}
	
	public void updateBalance() {
		atm.updateAtmBalance(cash, 1);
		double balance = acc.getBalance(acc.getCardNo())+cash;
		acc.updateBalance(acc.getCardNo(), balance);
	}
}
