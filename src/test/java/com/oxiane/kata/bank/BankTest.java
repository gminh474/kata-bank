package com.oxiane.kata.bank;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.oxiane.kata.bank.exceptions.AccountNonExistentException;

public class BankTest {

	Bank bank;
	static String DEFAULT_ACCOUNTID = "abcd-12345-efgh";
	static double DEFAULT_BALANCE = 1000;

	@BeforeEach
	void setup() {
		bank = new Bank();
		bank.createAccount(DEFAULT_ACCOUNTID, 1000);
	}

	@Test
	void should_return_balance() throws AccountNonExistentException {
		// given
		
		// when
		double balance = bank.getBalance(DEFAULT_ACCOUNTID);
		
		// then
        assertThat(balance).isEqualTo(DEFAULT_BALANCE);
	}

	@Test
	void should_deposit_money() throws AccountNonExistentException {
		//given
		String accountid = DEFAULT_ACCOUNTID;
		double amount = 50;
		
		// when
		boolean isComplete = bank.depositMoney(accountid, amount);
		
		// then
		assertThat(isComplete).isEqualTo(true);
		assertThat(bank.getBalance(accountid)).isEqualTo(amount + DEFAULT_BALANCE);
		
	}
}
