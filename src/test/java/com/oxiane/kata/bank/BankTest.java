package com.oxiane.kata.bank;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.math.BigDecimal;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.oxiane.kata.bank.exceptions.AccountNonExistentException;
import com.oxiane.kata.bank.models.Operation;

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
	void should_getBalance_throw_AccountNonExistentException_when_account_not_exists()  {
		// given
		String accountid = "autreId";
		
		// when
		
		// then
        assertThrows(AccountNonExistentException.class, () -> bank.getBalance(accountid)); 
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
		assertThat(bank.getBalance(accountid)).isEqualTo(amount - DEFAULT_BALANCE);
		
	}

	@Test
	void should_deposit_throw_AccountNonExistentException_when_account_not_exists()  {
		// given
		String accountid = "autreId";
		double amount = 50;
		
		// when
		
		// then
        assertThrows(AccountNonExistentException.class, () -> bank.depositMoney(accountid, amount)); 
	}

	@Test
	void should_withdraw_money() throws AccountNonExistentException {
		//given
		String accountid = DEFAULT_ACCOUNTID;
		double amount = 50;
		
		// when
		boolean isComplete = bank.withdrawMoney(accountid, amount);
		
		// then
		assertThat(isComplete).isEqualTo(true);
		assertThat(bank.getBalance(accountid)).isEqualTo(DEFAULT_BALANCE - amount);
		
	}

	@Test
	void should_withdraw_throw_AccountNonExistentException_when_account_not_exists()  {
		// given
		String accountid = "autreId";
		double amount = 50;
		
		// when
		
		// then
        assertThrows(AccountNonExistentException.class, () -> bank.withdrawMoney(accountid, amount)); 
	}
	
	@Test
	void should_list_statement() throws AccountNonExistentException {
		// given
		String accountid = DEFAULT_ACCOUNTID;
		double depositAmount1 =50;
		double withdrawAmount1 =100;
		double depositAmount2 =20;
		
		// when
		bank.depositMoney(accountid, depositAmount1);
		bank.withdrawMoney(accountid, withdrawAmount1);
		bank.depositMoney(accountid, depositAmount2);
		List<Operation> operations = bank.getStatements(accountid);
		
		// then
		assertThat(operations.size()).isEqualTo(3);
		Operation operation1 = operations.get(0); 
		assertThat(operation1.getAccountId()).isEqualTo(DEFAULT_ACCOUNTID);
		assertThat(operation1.getType()).isEqualTo(Operation.TYPES.DEPOSIT);
		assertThat(operation1.getAmount().compareTo(BigDecimal.valueOf(depositAmount1))).isEqualTo(0);

		Operation operation2 = operations.get(1); 
		assertThat(operation2.getAccountId()).isEqualTo(DEFAULT_ACCOUNTID);
		assertThat(operation2.getType()).isEqualTo(Operation.TYPES.WITHDRAW);
		assertThat(operation2.getAmount().compareTo(BigDecimal.valueOf(withdrawAmount1))).isEqualTo(0);

		Operation operation3 = operations.get(2); 
		assertThat(operation3.getAccountId()).isEqualTo(DEFAULT_ACCOUNTID);
		assertThat(operation3.getType()).isEqualTo(Operation.TYPES.DEPOSIT);
		assertThat(operation3.getAmount().compareTo(BigDecimal.valueOf(depositAmount2))).isEqualTo(0);

	}
}
