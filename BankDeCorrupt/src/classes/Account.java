package classes;

import java.util.Scanner;

import enums.AccountStatus;
import enums.AccountType;

public class Account {
	private int balance, accNumber;
	private int goldCounter, platinumCounter, allow;
	private AccountType type;
	private AccountStatus status;

	public AccountStatus getStatus() {
		return status;
	}

	@Override
	public String toString() {
		return "---------------------------------\nAccount number " + accNumber + "\nBalance: " + balance + "\nType: "
				+ type + "\nStatus: " + status + "\n--------------------------------------";

	}

	public Account(int accNumber, AccountType type, int balance, AccountStatus status) {
		this.status = AccountStatus.ALLOWED;
		this.accNumber = accNumber;
		this.type = type;
		this.balance = balance;

	}

	public int getAccountNumber() {
		return this.accNumber;
	}

	public void checkMoney() {
		// TODO Auto-generated method stub

	}

	public void deposit(int amount) {
		switch (type) {
		case REGULAR:
			break;
		case GOLD:
			if (amount > 100000)
				goldCounter++;
			if (goldCounter > 10) {
				this.balance += 500;
				goldCounter = 0;
				Bank.totalMoney += 500;

			}
			break;
		case PLATINUM:
			if (amount > 100000) {
				this.balance += 0.01 * amount;
				int temp = (int)(0.01 * amount);
				Bank.totalMoney += temp;

			}
			if (balance < 0)
				platinumCounter++;
			if (platinumCounter >= 5) {
				this.status = AccountStatus.BLOCKED;
				System.out
						.println("Your account is now blocked: \nPlease call your Account Manager as soon as possible");
			}
			break;

		}
		this.balance += amount;
		Bank.totalMoney += balance;
		System.out.println("Deposited " + amount + "$ INTO account");
	}

	public void withdraw() {

		Scanner input = new Scanner(System.in);
		System.out.println("Enter amount to withdraw");
		int amount = input.nextInt();

		switch (status) {
		case WARNED:
			System.out.println("Note!!!\n Your account is warned!!!");
			break;
		case BLOCKED:
			System.out.println("Account is blocked, cannot withdraw:\n Contact Account manager!");
			this.allow = 0;
			break;
		case ALLOWED:
			this.allow = 1;
			break;
		}
		switch (type) {
		case REGULAR:
			if (amount > 5000) {
				System.out.println("Cannot withdraw more than 5k");
				withdraw();
			}

			break;
		case GOLD:
			if (amount > 50000) {
				System.out.println("Cannot withdraw more than 50k");
			} else {
				withdraw();
			}

			break;
		case PLATINUM:
			if (balance - amount < 1000000) {
				System.out.println("Account balance cannot be below 1 MIL");
			} else {
				withdraw();
			}

			break;
		}
		if (allow == 1)

		{
			this.balance -= amount;
			Bank.totalMoney -= balance;
			System.out.println("Withdrewed " + amount + "$ OUT OF account");
		}
		switch (type) {
		case REGULAR:
			if (this.balance < -7000) {
				this.status = AccountStatus.BLOCKED;
				System.out.println("Your account is now blocked, contact bank ASAP");
				this.allow = 0;
			}
			break;
		case GOLD:
			if ((this.balance <= -50000) && (this.balance >= -100000)) {
				this.status = AccountStatus.WARNED;
				System.out.println("Your account is warned, please deposit money");
			}
			if (this.balance < -100000) {
				this.status = AccountStatus.BLOCKED;
				System.out.println("Your account is now blocked, contact bank ASAP");
				this.allow = 0;
			}
			break;
		case PLATINUM:
			break;
		}

	}

}
