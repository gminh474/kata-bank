package com.oxiane.kata.bank.exceptions;

public class AccountNonExistentException extends Exception {
	String accountId;

	public AccountNonExistentException(String accountId) {
		super();
		this.accountId = accountId;
	}
	
	@Override
	public String getMessage() {
		return "Account " + accountId + "doesn't exist";
	}

}
