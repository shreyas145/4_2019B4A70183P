
public class Withdraw {
	private double withdrawAmount;
	private Account acc;
	private ATM atm;
	
	public Withdraw(ATM atm, Account acc, double withdrawAmount){
		this.acc = acc;
		this.withdrawAmount = withdrawAmount;
		this.atm = atm;
	}
	
	public boolean multipleChecker() {
		if(withdrawAmount%100.0 == 0) {
			return true;
		}
		else {
			return false;
		}
	}
	
	public boolean verifyAccountBalance() {
		if(withdrawAmount <= acc.getBalance(acc.getCardNo())) {
			return true;
		}
		else {
			return false;
		}
	}
	
	public boolean verifyAtmBalance() {
		return atm.verifySufficientBalance(withdrawAmount);
	}
	
	public void updateBalance() {
		atm.updateAtmBalance(withdrawAmount, -1);
		double balance = acc.getBalance(acc.getCardNo())-withdrawAmount;
		acc.updateBalance(acc.getCardNo(), balance);
	}
}
