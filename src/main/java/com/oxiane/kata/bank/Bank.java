package com.oxiane.kata.bank;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import com.oxiane.kata.bank.exceptions.AccountNonExistentException;
import com.oxiane.kata.bank.models.Account;
import com.oxiane.kata.bank.models.Operation;

public class Bank {
	List<Account> accounts = new ArrayList<Account>();
	List<Operation> operations = new ArrayList<Operation>();

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
		boolean result = optionalAccount.orElseThrow(() -> new AccountNonExistentException(accountid)).increment(amount);
		operations.add(createOperation(accountid, Operation.TYPES.DEPOSIT, amount));
		return result;
	}
	
	public Optional<Account> findAccount(String accountid) {
		return accounts.stream().filter(account -> account.getAccountid().equals(accountid)).findFirst();
		
	}

	public boolean withdrawMoney(String accountid, double amount) throws AccountNonExistentException {
		Optional<Account> optionalAccount = findAccount(accountid);
		boolean result = optionalAccount.orElseThrow(() -> new AccountNonExistentException(accountid)).decrement(amount);
		operations.add(createOperation(accountid, Operation.TYPES.WITHDRAW, amount));
		return result;
	}

	public List<Operation> getStatements(String accountid) {
		return operations.stream().filter(operation -> operation.getAccountId().equals(accountid)).collect(Collectors.toList());
		
	}
	
	private Operation createOperation(String accountid, Operation.TYPES type, double amount) {
		return new Operation(LocalDateTime.now(), accountid, type, BigDecimal.valueOf(amount));
	}
}
