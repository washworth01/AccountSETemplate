package com.qa.persistence.repository;

import java.util.HashMap;
import java.util.Map;
import java.util.stream.Stream;

import javax.inject.Inject;

import com.qa.persistence.domain.Account;
import com.qa.persistence.util.*;

public class AccountMapRepository implements AccountRepository{
	
	public Map<Long, Account> accountMap = new HashMap<>();
	  
	JSONUtil util = new JSONUtil();
	
	public String getAllAccounts() 
	{
		return util.getJSONForObject(accountMap.values());		
	}
	
	public String getAnAccount(Long id)
	{
		return util.getJSONForObject(accountMap.get(id));
	}

	public String createAccount(String account)
	{
		Account newAccount = util.getObjectForJSON(account, Account.class);
		accountMap.put(newAccount.getAccountNumber(), newAccount);
		return util.getJSONForObject(accountMap.get(newAccount.getAccountNumber()));
	}

	public String deleteAccount(Long id) 
	{
		if (accountMap.get(id) != null)
		{
			accountMap.remove(id);
		}
		
		return "Account has been deleted.";
	}

	public String updateAccount(Long id, String account)
	{
		Account newAccount = util.getObjectForJSON(account, Account.class);
		accountMap.put(id, newAccount);
		return util.getJSONForObject(accountMap.get(id));
	}
	
	public int countFirstNames(String name)
	{
		int nameCounter = 0;
		for(Account account : accountMap.values())
		{
			if(account.getFirstName().contains(name))
			{
				nameCounter++;
			}
		}
		
		return nameCounter;
	}

}
