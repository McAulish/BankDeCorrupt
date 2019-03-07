package classes;

import java.util.Scanner;

import enums.AccountStatus;
import enums.AccountType;

public class Main {

	public static void main (String[] args) {
		// Scanner input = new Scanner(System.in);
		
		Bank BankDeCorrupt = new Bank();
		
		//BankDeCorrupt.menu();
		
		Bank.accounts.add(new Account(12345, AccountType.GOLD, 5000, AccountStatus.ALLOWED));
		System.out.println(Bank.accounts);
		BankDeCorrupt.menu();
		
		for (int i =0; i<Bank.accounts.size(); i++) {
			//System.out.println(Bank.accounts.get(i));
			
			/*Bank.accounts.get(i).withdraw();
			System.out.println(Bank.accounts.get(i).getStatus());
			System.out.println(Bank.accounts.get(i));
			
			Bank.accounts.get(i).withdraw();
			System.out.println(Bank.accounts.get(i).getStatus());
			System.out.println(Bank.accounts.get(i));*/
		}

	}

}
