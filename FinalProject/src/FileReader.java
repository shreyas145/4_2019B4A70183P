import java.util.*;
import java.io.*;

public class FileReader implements Transaction{
	private HashMap<Integer,Integer> PINstorer = new HashMap<Integer,Integer>();
	private HashMap<Integer,Double> balanceStorer = new HashMap<Integer,Double>();
	private HashMap<Integer,String> nameStorer = new HashMap<Integer,String>();
	private int newCardNo = 1;
	
	Scanner fileReader = null;
	PrintWriter pw = null;
	
	void readDetailsFromFile(){
		try {
			fileReader = new Scanner(new FileInputStream("./data/data.txt"));
			while(fileReader.hasNextLine()) {
				int cardNo = fileReader.nextInt();
				String name = fileReader.next();
				double balance = fileReader.nextDouble();
				int PIN = fileReader.nextInt();
				
				PINstorer.put(cardNo, PIN);
				balanceStorer.put(cardNo,balance);
				nameStorer.put(cardNo,name);
				
				newCardNo++;
			}
		}
		catch(FileNotFoundException e) {
			System.err.println("Data file not found");
		}
		finally {
			fileReader.close();
		}
	}
	
	void writeDetailsToFile(boolean append, Account acc) {
		if(append == true) {
			try {
				pw = new PrintWriter(new FileOutputStream("./data/data.txt",true));
				pw.print("\n");
				pw.print(newCardNo+" "+acc.getName(newCardNo)+" "+acc.getBalance(newCardNo)+" "+acc.getPIN(newCardNo));
			}
			catch(FileNotFoundException e) {
				System.err.println("Data file not found");
			}
			finally {
				pw.close();
			}
		}
		else {
			nameStorer.replace(acc.getCardNo(),acc.getName(acc.getCardNo()));
			balanceStorer.replace(acc.getCardNo(),acc.getBalance(acc.getCardNo()));
			PINstorer.replace(acc.getCardNo(),acc.getPIN(acc.getCardNo()));
			
			try {
				pw = new PrintWriter(new FileOutputStream("./data/data.txt"));
				int i;
				for(i = 1; i < newCardNo-1; i++) {
					pw.println(i+" "+nameStorer.get(i)+" "+balanceStorer.get(i)+" "+PINstorer.get(i));
				}
				pw.print(i+" "+nameStorer.get(i)+" "+balanceStorer.get(i)+" "+PINstorer.get(i));
			}
			catch(FileNotFoundException e) {
				System.err.println("Data could not be updated");
			}
			finally {
				pw.close();
			}
		}
	}
	
	public double getBalance(int cardNo) {
		return balanceStorer.get(cardNo);
	}
	
	public String getName(int cardNo) {
		return nameStorer.get(cardNo);
	}
	
	public int getPIN(int cardNo) {
		return PINstorer.get(cardNo);
	}
	
	public int getNewCardNo() {
		return newCardNo;
	}
}
