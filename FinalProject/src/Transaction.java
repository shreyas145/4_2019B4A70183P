
public interface Transaction {
	default double getBalance(int cardNo) {return -1;}
	default void updateBalance(int cardNo, double balance) {};
	default String getName(int cardNo) {return "-1";}
	default int getPIN(int cardNo) {return -1;}
	default void updatePIN(int cardNo, int PIN) {};
}
