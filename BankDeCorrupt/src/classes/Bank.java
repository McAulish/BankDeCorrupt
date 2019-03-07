package classes;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.InputMismatchException;
import java.util.Random;
import java.util.Scanner;

import enums.AccountStatus;
import enums.AccountType;
import interfaces.GeneralActions;
import interfaces.MainInterface;
import interfaces.MenuInterface;

public class Bank implements MainInterface, MenuInterface, GeneralActions {
	static Scanner input = new Scanner(System.in);
	Random rand = new Random();
	private int command, balance, accNumber;
	private AccountType type;
	private AccountStatus status;
	private boolean validInput = false;
	static int totalMoney;
	static boolean quit = false;
	private String method;
	private static int number;

	static HashMap<String, Integer> clients = new HashMap<>();

	static ArrayList<Account> accounts = new ArrayList<Account>();

	public void createAccount(String owner) { // create account
		validInput = false;
		while (!validInput) {
			try {
				System.out.println("Please enter account type");
				this.type = inputType();
				validInput = true;
			} catch (InputMismatchException e) {
				inputMismatch();
			}
		}
		validInput = false;
		while (!validInput) {
			try {
				System.out.println("Enter starting balance");
				this.balance = inputInt();
				validInput = true;

			} catch (InputMismatchException e) {
				inputMismatch();
				this.balance = inputInt();
			}
		}
		this.accNumber = accountNumber();
		System.out.println("New account number is: " + accNumber);

		clients.put(owner, this.accNumber); // CREATES NEW CLIENT
		Bank.accounts.add(new Account(this.accNumber, this.type, this.balance, this.status)); // CREATES NEW ACCOUNT

		System.out.println("Welcome to your new account!\nWe hope you enjoy your limited use,");
		System.out.println("And may you owe us your life! :-)");
		System.out.println("------------------------------\n------------------------------");
		validInput = true;
	}

	@Override
	public void newClient() { // new client
		System.out.println("Enter new client's name:");
		String owner = inputString();
		createAccount(owner);

	}

	@Override
	public void checkMoney() {
		System.out.println("Acount balance: " + this.balance + "\n----------------------");
	}

	@SuppressWarnings("unlikely-arg-type")
	public int accountNumber() {

		int temp = rand.nextInt(100000) + 1;

		if (accounts.contains(temp)) {
			accountNumber();
		}

		return temp;
	}

	public String inputString() {
		String scan = input.nextLine();
		validInput = false;
		return scan;
	}

	public AccountType inputType() {

		String scan = input.nextLine();
		switch (scan) {
		case "regular":
			type = AccountType.REGULAR;
			return type;
		case "gold":
			type = AccountType.GOLD;
			return type;
		case "platinum":
			type = AccountType.PLATINUM;
			return type;
		default:
			System.out.println("Enter type again please");
			inputType();

		}
		validInput = false;
		return type;
	}

	public int inputInt() {
		Boolean isFinished = false;
		while (!isFinished) {
			try {
				String scan = input.nextLine();
				this.balance = Integer.parseInt(scan);
				isFinished = true;
			} catch (NumberFormatException e) {
				System.out.println("Please enter again");
				isFinished = false;

			}
		}

		return balance;
	}

	public void inputMismatch() {
		System.out.println("Please enter again\n----------------------------");
		validInput = false;
	}

	@Override
	public void clientList() {
		System.out.println(Bank.clients.keySet());

	}

	@Override
	public void accountList() {
		for (int i = 0; i < Bank.accounts.size(); i++) {
			System.out.println(Bank.accounts.get(i));
		}

	}

	@Override
	public void TotalBankMoney() {
		System.out.println("Total Bank Capital:\n\n" + totalMoney + "\n------------------");
	}

	@Override
	public void newAccount() { // new account
		System.out.println("Please enter client's mame");
		String scan = input.nextLine();
		if (clients.containsKey(scan)) {
			createAccount(scan);
		} else {
			System.out.println(
					"Wrong client entered, would you like to try again?\n////(Yes/No)////\n(You must create a client before creating an account)\n---------------");
			yesNo("newaccount");
		}

	}

	@SuppressWarnings("unlikely-arg-type")
	@Override
	public void accountActions() {

		System.out.println("Enter number of account for more info");
		Boolean isFinished = false;
		while (!isFinished) {
			try {

				String scan = input.nextLine();
				Bank.number = Integer.parseInt(scan);
				isFinished = true;
			} catch (NumberFormatException e) {
				System.out.println("Please enter again\n------------------------------");
				isFinished = false;
			} catch (InputMismatchException e) {
				System.out.println("Please enter again\n------------------------------");
				isFinished = false;
			}
		}
		if (checkAccountNumber(number) == true) {
			System.out.println("=====================================");
			System.out.println("=                                   =");
			System.out.println("=   Enter command number            =");
			System.out.println("=                                   =");
			System.out.println("=   1. Withdraw money               =");
			System.out.println("=   2. Deposit                      =");
			System.out.println("=   3. Total money in all accounts  =");
			System.out.println("=                                   =");
			System.out.println("=====================================");

		} else {
			System.out.println(
					"No account was found by that number, would you like to try again?\n(((Yes/No)))\n---------------");
			yesNo("accountActions");
		}
	}

	public void yesNo(String method) {
		String scan = input.nextLine();
		switch (scan) {
		case "yes":
			if (method == "newaccount") {
				newAccount();
			} else {
				accountActions();
			}

			break;
		case "Yes":
			if (method == "newaccount") {
				newAccount();
			} else {
				accountActions();
			}
			break;
		case "No":
			break;
		case "no":
			break;
		}
	}

	@Override
	public void generalActions() {
		// TODO Auto-generated method stub

	}

	@Override
	public void removeAccount() {
		// TODO Auto-generated method stub

	}

	@Override
	public void removeClient() {
		// TODO Auto-generated method stub

	}

	public boolean menu() {

		System.out.println("===========================================================");
		System.out.println("=    Welcome to BancDeCorrupt Interface!                  =");
		System.out.println("=    Please choose your action with the command number    =");
		System.out.println("=													      =");
		System.out.println("=    1. Create new account                                =");
		System.out.println("=    2. Add a new sucker (client) to the bank             =");
		System.out.println("=    3. Acount actions                                    =");
		System.out.println("=    4. General actions                                   =");
		System.out.println("=    5. Remove account                                    =");
		System.out.println("=    6. Remove client                                     =");
		System.out.println("=    7.Exit                                               =");
		System.out.println("=                                                         =");
		System.out.println("===========================================================");
		stringToInt();
		while (!quit) {
			switch (command) {
			case 1:
				newAccount();
				menu();
				break;
			case 2:
				newClient();
				menu();
				break;
			case 3:
				accountActions();
				menu();
				break;
			case 4:
				generalActions();
				menu();
				break;
			case 5:
				removeAccount();
				menu();
				break;
			case 6:
				removeClient();
				menu();
				break;
			case 7:
				System.out.println("Thank you and goodbye");
				return quit = true;
			default:
				menu();
			}
		}
		return quit = false;
	}

	public int stringToInt() {

		Boolean isFinished = false;
		while (!isFinished) {
			try {
				String scan = input.nextLine();
				command = Integer.parseInt(scan);
				isFinished = true;
			} catch (NumberFormatException e) {
				System.out.println("Please enter again\n------------------------------");
				isFinished = false;
			}
		}
		return command;
	}

	public boolean checkAccountNumber(int a) {
		boolean check = false;
		if (Bank.accounts.contains(a)) {
			check = true;
		}

		return check;

	}

}
