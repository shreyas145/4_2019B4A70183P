
public class ViewBalance {
	private Account acc;
	
	public ViewBalance(Account acc){
		this.acc = acc;
	}
	
	public double displayBalance() {
		return acc.getBalance(acc.getCardNo());
	}
}
