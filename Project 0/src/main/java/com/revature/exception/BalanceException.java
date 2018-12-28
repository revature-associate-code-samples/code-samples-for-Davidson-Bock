package com.revature.exception;

public class BalanceException extends Exception {


	  public BalanceException() {
	    super("You don't have that amount of money!");
	  }

	  public BalanceException(String message, Throwable cause) {
	    super(message, cause);
	  }


}
