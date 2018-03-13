package com.revature;

import org.apache.log4j.Logger;
import com.revature.service.*;
import com.revature.model.*;
import com.revature.repository.InFile;
import com.revature.exception.*;

public class Main {
	private static Logger logger = Logger.getLogger(Main.class);
	public static void main(String[] args) throws BankWithdrawException, BankException{
		UserInfo user = new UserInfo();
		User repository = new UserRepositoryjdbc();
		InFile infile = new InFile();
		String username="";
		String input="";
		String passinput= "";
		String action="";
		logger.info("Enter your User ID: ");
		username = infile.getFile();
		if (repository.getUserName(username)==null) {
			throw new BankException("User Not Found");
		}
		user=repository.getUserName(username);
		logger.info("Enter your password: ");
		passinput= infile.getFile();
		if(passinput.equals(user.getPass())) {
			do {
				logger.info("Welcome "+ user.getUserName()+"! What would you like to do? \n"+"----------------------------------\n"+
						"1) View current balance\n"+
						"2) Withdraw money\n" +
						"3) Deposit money\n"+
						"4) Log out");
				action=infile.getFile();

				switch(action) {

				case "1":
					logger.info("Your balance is: $"+user.getbalance());
					break;

				case "2":
					logger.info("Enter the amount you wish to withdraw.");

					input = infile.getFile();

					if(Double.parseDouble(input) <= 0) {
						throw new BankWithdrawException("Invalid withdraw amount!");
					}

					if(Double.parseDouble(input) > user.getbalance()) {

						logger.info("Withdraw requested exceeds balance.");
					}
					else {
						repository.withdraw(user.getUserName(), user.getbalance() - Double.parseDouble(input));
						user=repository.getUserName(username);
						logger.info("Your money has been dispensed. Remaining balance is: $"+ user.getbalance());
					}
					break;

				case "3":

					logger.info("Enter the amount you wish to deposit.");

					input = infile.getFile();

					repository.deposit(user.getUserName(), user.getbalance() + Double.parseDouble(input));
					user=repository.getUserName(username);
					logger.info("Your account has been credited. Current Balance is $"+ user.getbalance());

					break;

				case "4":

					logger.info("Thank you and have a great day!");
					break;

				default:
					logger.info("Invalid Choice User has been logged out.");
					break;
				}
			}while(Integer.parseInt(action)<4);
		}else {
			logger.info("Invalid password");
		}

	}
}
