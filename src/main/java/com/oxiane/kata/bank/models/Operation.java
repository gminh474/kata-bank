package com.oxiane.kata.bank.models;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public class Operation {
	public enum TYPES{
		DEPOSIT,
		WITHDRAW
	}

	LocalDateTime time;
	String accountId;
	TYPES type;
	BigDecimal amount;
	
	
	
	public Operation(LocalDateTime time, String accountId, TYPES type, BigDecimal amount) {
		super();
		this.time = time;
		this.accountId = accountId;
		this.type = type;
		this.amount = amount;
	}
	
	public LocalDateTime getTime() {
		return time;
	}
	public String getAccountId() {
		return accountId;
	}
	public TYPES getType() {
		return type;
	}
	public BigDecimal getAmount() {
		return amount;
	}
	
	
}
