package com.qa.persistence.repository;

import java.util.List;

import javax.persistence.TypedQuery;
import javax.transaction.Transactional;
import javax.transaction.Transactional.TxType;

import com.qa.persistence.domain.Account;

public interface AccountDBRepository 
{
	public List<Account> getAllAccounts();
	
	public Account createAccount(String account);
	
	public String deleteAccount(Long id);
	
	public String updateAccount(Long id, String account);
}
