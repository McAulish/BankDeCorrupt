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
	private int balance, accNumber;
	private AccountType type;
	private AccountStatus status;
	private boolean validInput = false;
	static int totalMoney;

	static HashMap<String, Integer> clients = new HashMap<>();

	static ArrayList<Account> accounts = new ArrayList<Account>();

	public void createAccount(String owner) {

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

		System.out.println("Welcome to BancDeCorrupt!\nWe hope you enjoy your limited use,");
		System.out.println("And may you owe us your life! :-)");
		System.out.println("------------------------------\n------------------------------");
		validInput = true;
	}

	@Override
	public void newClient() {
		System.out.println("Enter new client's name:");
		String owner = inputString();
		createAccount(owner);

	}

	@Override
	public void checkMoney() {
		// TODO Auto-generated method stub

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
	public void newAccount() {
		System.out.println("Please enter client's mame");
		String scan = input.nextLine();
		if (clients.containsKey(scan)) {
			createAccount(scan);
		} else {
			System.out.println("Wrong clients entered, would you like to try again?\n-------------------\n(Yes/No)");
			scan = input.nextLine();
			switch (scan) {
			case "yes":
				newAccount();
				break;
			case "Yes":
				newAccount();
				break;
			case "No":
				break;
			case "no":
				break;
			}
		}

	}

	@Override
	public void accountActions() {

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
}
