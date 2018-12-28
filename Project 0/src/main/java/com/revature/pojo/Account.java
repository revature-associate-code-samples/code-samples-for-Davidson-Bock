package com.revature.pojo;

public class Account {
	private int id;
	private int type;
	private int user;
	private int balance;
	
	public Account() {}
	
	

	public Account( int type, int user, int balance) {
		super();
		this.type = type;
		this.user = user;
		this.balance = balance;
	}



	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getType() {
		return type;
	}

	public void setType(int type) {
		this.type = type;
	}

	public int getUser() {
		return user;
	}

	public void setUser(int user) {
		this.user = user;
	}

	public int getBalance() {
		return balance;
	}

	public void setBalance(int balance) {
		this.balance = balance;
	}

	@Override
	public String toString() {
		return "Account [id=" + id + ", type=" + type + ", user=" + user + ", balance=" + balance + "]";
	}
	
	
	

}
