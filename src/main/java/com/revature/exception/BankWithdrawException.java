package com.revature.exception;

public class BankWithdrawException extends Exception {

	private static final long serialVersionUID = 9144721892560980821L;
	public BankWithdrawException(String msg){
		super(msg);
	}

}
