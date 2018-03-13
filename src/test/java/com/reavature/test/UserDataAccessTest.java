package com.reavature.test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.apache.log4j.Logger;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.revature.exception.BankException;
import com.revature.exception.BankWithdrawException;
import com.revature.model.User;
import com.revature.model.UserRepositoryjdbc;
import com.revature.service.UserInfo;

public class UserDataAccessTest {
	private static Logger logger = Logger.getLogger(UserDataAccessTest.class);
	private User repository;
	private UserInfo userTest;
	
	@Before
	public void setUp() {
		repository = UserRepositoryjdbc.getInstance();
		userTest = new UserInfo(1,"JoelD95","ABC123",50);

	}
	@Test
	public void getUserNameTest(){
		logger.trace("Testing to see if user exists in database");
		assertEquals(repository.getUserName("JoelD95"),userTest);
		assertEquals(repository.getUserName("bill123"),null);
	}
	@Test
	public void withdrawalTest() {
		logger.trace("Testing withdrawing");
		assertTrue(repository.withdraw("JoelD95", userTest.getbalance()-10));
		assertFalse(repository.withdraw("Bill123", userTest.getbalance()-10));
	}
//	@Test
//	public void depositTest() {
//		logger.trace("Testing deposit");
//		assertTrue(repository.deposit("JoelD95", userTest.getbalance()+10));
//		assertFalse(repository.deposit("Bill123", userTest.getbalance()+10));
//	}

}
