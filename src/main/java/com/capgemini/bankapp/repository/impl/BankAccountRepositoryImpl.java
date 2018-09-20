package com.capgemini.bankapp.repository.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashSet;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.capgemini.bankapp.entity.BankAccount;
import com.capgemini.bankapp.repository.BankAccountRepository;
import com.capgemini.bankapp.util.DatabaseUtil;

@Repository
public class BankAccountRepositoryImpl implements BankAccountRepository {

	@Autowired
	private DatabaseUtil databaseUtil;
	
	private HashSet<BankAccount> bankAccounts;

	public BankAccountRepositoryImpl() {
		super();
		
		bankAccounts = new HashSet<>();
		bankAccounts.add(new BankAccount(123, "Sudarshan", "CURRENT", 100));
		bankAccounts.add(new BankAccount(1234, "Sharath", "SAVINGS", 1200));
		bankAccounts.add(new BankAccount(1235, "Sandeep", "CURRENT", 1693));
		bankAccounts.add(new BankAccount(1236, "Preeti", "SAVINGS", 1305));
	}

	@Override
	public double getBalance(long accountId) {
		String query = "select balance from accounts where account_id=?";
		Connection connection = databaseUtil.getConnection();
		
		try(PreparedStatement statement = connection.prepareStatement(query)) {
			statement.setLong(1, accountId);
			ResultSet result = statement.executeQuery();
			while (result.next()) {
				return result.getDouble(1);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return -1;
	}

	@Override
	public boolean updateBalance(long accountId, double newBalance) {
		String query = "update accounts set balance=? where account_id=?";
		Connection connection = databaseUtil.getConnection();
		
		try(PreparedStatement statement = connection.prepareStatement(query)) {
			statement.setDouble(1, newBalance);
			statement.setLong(2, accountId);
			int result = statement.executeUpdate();
			if(result == 1) {
				return true;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return false;
		
	}


//	@Override
//	public double getBalance(long accountId) {
//		databaseUtil.getConnection();
//		for (BankAccount bankAccount : bankAccounts) {
//			if (bankAccount.getAccountId() == accountId) {
//				return bankAccount.getAccountBalance();
//			}
//		}
//		return -1;
//	}
//
//	@Override
//	public boolean updateBalance(long accountId, double newBalance) {
//		for (BankAccount bankAccount : bankAccounts) {
//			if (bankAccount.getAccountId() == accountId) {
//				bankAccount.setAccountBalance(newBalance);
//				return true;
//			}
//		}
//		return false;
//	}

}
