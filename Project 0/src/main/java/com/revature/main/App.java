package com.revature.main;

import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

import com.revature.dao.AccountDao;
import com.revature.dao.AccountTypeDao;
import com.revature.dao.Dao;
import com.revature.dao.UserDao;
import com.revature.exception.BalanceException;
import com.revature.pojo.Account;
import com.revature.pojo.AccountType;
import com.revature.pojo.User;
import com.revature.service.AccountService;
import com.revature.service.UserService;



public class App {
	
	static UserService uService = new UserService();
	//static UserDao ud = new UserDao();
	static AccountTypeDao atd = new AccountTypeDao();
	//static AccountDao ad = new AccountDao();
	static AccountService aService = new AccountService();
	static AccountType at = new AccountType();

	public static void main(String[] args) throws BalanceException {
		run();
	}
	
	static void run() throws BalanceException {
		System.out.println("Hello welcome to Bank of Bock!"
				+ " Please select an option?"
				+ "\n 1. Log in"
				+ "\n 2. Sign up"
				+ "\n 3. Exit");
		Scanner scan = new Scanner(System.in);
		int option = 0;
		while(option !=3) {
		try {
			option = scan.nextInt();
			switch(option) {
				case 1: log();
				case 2: createUser();
				case 3: System.out.println("Exit");break;
				default:
					System.out.println("Please enter a number on ur menu");
					//run(); 
					break;
				 
			}
		}catch(InputMismatchException e) {
				System.out.println("Sorry! You must enter a number from our menu!");
				run();
		}	
		}
	}
	
	static void createAccount(User user) throws BalanceException {
		Scanner scan = new Scanner(System.in);
		int option = 0;
		System.out.println("Please select an account to create"
				+ "\n 1. Credit"
				+ "\n 2. Savings"
				+ "\n 3. Checkings"
				+ "\n 4. Go back");
		try {
			 option = scan.nextInt();
			 switch(option) {
			 case 1: 
				 credit(user, 0); 
				 accountOptions(user);//error
			 case 2: 
				 savings(user, 0);
				 accountOptions(user);
			 case 3: 
				 checkings(user, 0); 
				 accountOptions(user);
			 case 4: 
				 accountOptions(user);
			 default:
					 System.out.println("Please enter a number on ur menu");
					 createAccount(user); break;				 
			 }
			}catch(InputMismatchException e) {
					System.out.println("Sorry! You must enter a number from our menu!");
					createAccount(user);
			}	
	}
	
		
	static void createUser() throws BalanceException {
		UserDao ud = new UserDao();
		Scanner scan = new Scanner(System.in);
		System.out.println("Please enter a username");
		String username = scan.nextLine();
		if (!isUnique(username)) {
			System.out.println("Username already exists!");
			run();
		} else {
			System.out.println("Please enter a password");
			String password = scan.nextLine();
			System.out.println("Please enter your first name");
			String firstname = scan.nextLine();
			System.out.println("Please enter your last name");
			String lastname = scan.nextLine();
			User user = new User(firstname, lastname, username, password);
			ud.save(user);
			System.out.println("User account created!");
			accountOptions(user);
		}
	}
	
	
	public static void credit(User user, int balance) {
		Account a = new Account(1, user.getId(), balance);
		AccountDao ad = new AccountDao();
		ad.save(a); //error
	}
	
	
	public static void savings(User user, int balance) {
		Account a = new Account(2, user.getId(), balance);
		AccountDao ad = new AccountDao();
		ad.save(a); //error
	}
	
	
	public static void checkings(User user, int balance) {
		Account a = new Account(3, user.getId(), balance);
		AccountDao ad = new AccountDao();
		ad.save(a); //error
	}
	
	
	static void createBankAccount(User user, int type, int balance) {
		Account a = new Account(type, user.getId(), balance);
		System.out.println(a);
		AccountDao ad = new AccountDao();
		ad.save(a); //error
	}

	
	static boolean isUnique(String username) {
		List<User> users = uService.getAllUsers();
		if(users == null) {
			return true;
		}
		for(User u : users) {
			if(username.equals(u.getUserName())) {
				return false;
			}
		}
		return true;
	}
	
	
	static void accountOptions(User user) throws BalanceException {
		int ownerId = user.getId();
		Scanner scan = new Scanner(System.in);
		System.out.println("Please select an option"
				+ "\n 1. Create bank account"
				+ "\n 2. Deposit"
				+ "\n 3. Withdraw"
				+ "\n 4. Log out");
		int option = 0;
		try {
		 option = scan.nextInt();
		 switch(option) {
		 case 1: createAccount(user); 
		 case 2: 
			 deposit(ownerId);
			 accountOptions(user);
		 case 3: withdraw(ownerId);
		 accountOptions(user);
		 case 4: run();
		 default:
				 System.out.println("Please enter a number on ur menu");
				 accountOptions(user); break;
				 
		 }
		}catch(InputMismatchException e) {
				System.out.println("Sorry! You must enter a number from our menu!");
				accountOptions(user);
		}
		
	}
	
	
	static void deposit(int id) {
		AccountDao ad = new AccountDao();
		Account a  = new Account();
		Scanner in = new Scanner(System.in);
		System.out.println("Select the account you wish to deposit to");

		for(Account ac : ad.findAll()) {
			if(ac.getUser() == id) {
				System.out.println(ac);
			}
		}
		//System.out.println(ad.findByIdUser(a.getUser()));
		int acc = in.nextInt();
		a = ad.findById(acc);
		System.out.println("Enter the amount of money you wish to deposit");
		int amt = in.nextInt();
		int balance = a.getBalance() + amt;
		a.setBalance(balance);
		ad.update(a);
	}
	
	
	static void withdraw(int id) throws BalanceException {
		AccountDao ad = new AccountDao();
		Account a  = new Account();
		Scanner in = new Scanner(System.in);
		System.out.println("Select the account you wish to deposit to");

		for(Account ac : ad.findAll()) {
			if(ac.getUser() == id) {
				System.out.println(ac);
			}
		}
		int acc = in.nextInt();
		a = ad.findById(acc);
		int amt = 0;
		
		System.out.println("Enter the amount of money you wish to withdraw");
		try {
			amt = in.nextInt();
			int newBalance = a.getBalance() - amt;
			if(newBalance >= 0) {
				a.setBalance(newBalance);
				ad.update(a);
				
			} else {
				throw new BalanceException();
			}
		} catch(BalanceException e) {
			e.printStackTrace();
		}	
		
	}
	
	
	static void log() throws BalanceException {
		UserDao ud = new UserDao();
		Scanner in = new Scanner(System.in);
		System.out.println("Please enter your username");
		String username = in.nextLine();
		System.out.println("Enter your password");
		String password = in.nextLine();
		if(ud.findByUsername(username, password) == null) {
			System.out.println("Username or password incorrect!");
			run();
		} else {
			User user = new User();
			user = ud.findByUsername(username, password);
			accountOptions(user);
		}
	}
}
	



