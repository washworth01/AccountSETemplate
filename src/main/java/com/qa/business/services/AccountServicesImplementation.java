package com.qa.business.services;

import javax.inject.Inject;

import com.qa.persistence.domain.Account;
import com.qa.persistence.repository.AccountRepository;
import com.qa.persistence.util.JSONUtil;

public class AccountServicesImplementation implements AccountServices
{
	@Inject
	JSONUtil util;
	
	@Inject
	public AccountRepository repository;

	@Override
	public String getAllAccounts() 
	{
		return repository.getAllAccounts();
	}

	@Override
	public String createAccount(String account)
	{
		Account newAccount = util.getObjectForJSON(account, Account.class);
		if(newAccount.getAccountNumber() == 9)
		{
			return "{\"message\": \"This account is blocked\"}";
		}
		else
		{
			return "{\"message\": \"Account has been successfully created\"}";
		}
	}

	@Override
	public String deleteAccount(Long id)
	{
		return repository.deleteAccount(id);
	}

	@Override
	public String updateAccount(Long id, String account)
	{
		return repository.updateAccount(id, account);
	}
	
}
