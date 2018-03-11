package com.revature.model;

import com.revature.exception.BankException;
import com.revature.exception.BankWithdrawException;
import com.revature.service.UserInfo;

public interface User {
	public UserInfo getUserName(String userName) throws BankException;
	public boolean withdraw(String userName, double withdrawal) throws BankWithdrawException;
	public boolean deposit(String userName,double Deposit);

}
