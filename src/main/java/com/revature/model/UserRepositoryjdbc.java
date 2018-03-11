package com.revature.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import com.revature.exception.*;
import org.apache.log4j.Logger;

import com.revature.repository.ConnectionUtil;
import com.revature.service.UserInfo;

public class UserRepositoryjdbc implements User {
	private static Logger logger = Logger.getLogger(UserRepositoryjdbc.class);
	private static UserRepositoryjdbc repository = new UserRepositoryjdbc();
	public static UserRepositoryjdbc getInstance() {
		return repository;
	}

	@Override
	public UserInfo getUserName(String userName) throws BankException {
		String msg="";
		try(Connection connection = ConnectionUtil.getConnection()){
			int parameterIndex = 0;
			String sql = "SELECT * FROM BANK_ACCOUNTS WHERE USER_ID = ?";
			logger.trace("Getting statement object in get all users");
			PreparedStatement statement = connection.prepareStatement(sql);
			statement.setString(++parameterIndex, userName);
			ResultSet result= statement.executeQuery();
			if(result.next()) {
				return(new UserInfo(
						result.getInt("B_ID"),
						result.getString("USER_ID"),
						result.getString("PASS"),
						result.getDouble("BALANCE")
						));
			}
			else {
				throw new BankException(msg);
			}
		}catch (SQLException e) {
			logger.error("Error while selecting all users",e);
		}
		return null;
	}
	@Override
	public boolean withdraw(String userName, double withdrawal) throws BankWithdrawException {
		String msg="";
		try(Connection connection = ConnectionUtil.getConnection()){
			int parameterIndex = 0;
			String sql = "UPDATE BANK_ACCOUNTS SET BALANCE = ? WHERE USER_ID = ?";
			logger.trace("Getting statement object in get all users");
			PreparedStatement statement = connection.prepareStatement(sql);
			statement.setDouble(++parameterIndex, withdrawal);
			statement.setString(++parameterIndex, userName);
			if(statement.executeUpdate() != 0) {
				return true; 
			}
			else {
				throw new BankWithdrawException(msg);
			}
		}catch (SQLException e) {
			logger.error("Error while updating balance",e);
		}
		return false;
	}
	@Override
	public boolean deposit(String userName, double Deposit) {
		try(Connection connection = ConnectionUtil.getConnection()){
			int parameterIndex = 0;
			String sql = "UPDATE BANK_ACCOUNTS SET BALANCE = ? WHERE USER_ID = ?";
			logger.trace("Getting statement object in get all users");
			PreparedStatement statement = connection.prepareStatement(sql);
			statement.setDouble(++parameterIndex, Deposit);
			statement.setString(++parameterIndex, userName);
			if(statement.executeUpdate() != 0) {
				return true; 
			}
			else {
				logger.error("error while updating balance");
				return false;
			}
		}catch (SQLException e) {
			logger.error("Error while updating balance",e);
		}
		return false;
	}

}
