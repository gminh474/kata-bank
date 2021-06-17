package com.oxiane.kata.bank;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import com.oxiane.kata.bank.exceptions.AccountNonExistentException;
import com.oxiane.kata.bank.models.Account;

public class Bank {
	List<Account> accounts = new ArrayList<Account>();

	public void createAccount(String accountid, double balance) {
		Account account = new Account(accountid, balance);
		accounts.add(account);
	}

	public double getBalance(String accountid) throws AccountNonExistentException {
		Optional<Account> optionalAccount = findAccount(accountid);
		return optionalAccount.orElseThrow(() -> new AccountNonExistentException(accountid)).getBalance().doubleValue();
	}

	public boolean depositMoney(String accountid, double amount) throws AccountNonExistentException {
		Optional<Account> optionalAccount = findAccount(accountid);
		return optionalAccount.orElseThrow(() -> new AccountNonExistentException(accountid)).increment(amount);
	}
	
	public Optional<Account> findAccount(String accountid) {
		return accounts.stream().filter(account -> account.getAccountid().equals(accountid)).findFirst();
		
	}

}
