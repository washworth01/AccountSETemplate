package com.qa.persistence.repository;

public interface AccountRepository {

	String getAllAccounts();
	String getAnAccount(Long id);
	String createAccount(String account);
	String deleteAccount(Long id);
	String updateAccount(Long id, String account);
	int countFirstNames(String name);
}
