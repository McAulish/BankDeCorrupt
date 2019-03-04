package classes;

import java.util.Scanner;

import enums.AccountStatus;

public class Main {

	public static void main (String[] args) {
		 Scanner input = new Scanner(System.in);
		
		Bank BankDeCorrupt = new Bank();
		
		BankDeCorrupt.newClient();
		BankDeCorrupt.newClient();
		BankDeCorrupt.newClient();
		BankDeCorrupt.clientList();
		

		
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
