package com.revature.model;


import com.revature.service.UserInfo;

public interface User {
	public UserInfo getUserName(String userName);
	public boolean withdraw(String userName, double withdrawal);
	public boolean deposit(String userName,double Deposit);

}
