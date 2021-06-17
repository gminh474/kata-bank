package com.oxiane.kata.bank.models;

import java.math.BigDecimal;

public class Account {
	String accountid;
	BigDecimal balance;

	public Account(String accountid, double balance) {
		this.accountid = accountid;
		this.balance = BigDecimal.valueOf(balance);
	}

	public String getAccountid() {
		return accountid;
	}

	public BigDecimal getBalance() {
		return balance;
	}

	public boolean increment(double amount) {
		balance = balance.add(new BigDecimal(amount));
		return true;
	}

}
