package com.qa.persistence.repository;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.transaction.Transactional;
import javax.transaction.Transactional.TxType;

import com.qa.persistence.domain.Account;
import com.qa.persistence.util.*;

@Transactional (TxType.SUPPORTS)
public class AccountDBImplementation 
{	
	@PersistenceContext (unitName = "primary")
	private EntityManager manager;
	JSONUtil util = new JSONUtil();
	
	public List<Account> getAllAccounts()
	{
		TypedQuery<Account> query = manager.createQuery("SELECT a FROM Account a ORDER BY a.lastName DESC", Account.class);
		
		return query.getResultList();
	}
	
	public Account getAnAccount(Long id)
	{
		return manager.find(Account.class, id);
	}
	
	@Transactional (TxType.REQUIRED)
	public Account createAccount(String account)
	{
		Account newAccount = util.getObjectForJSON(account, Account.class);
		manager.persist(newAccount);
		
		return newAccount;
	}
	
	@Transactional (TxType.REQUIRED)
	public String deleteAccount(Long id)
	{
		TypedQuery<Account> query = manager.createQuery("SELECT a FROM Account a WHERE a.id" + id, Account.class);
		manager.remove(query.getSingleResult());
		
		return util.getJSONForObject(query.getSingleResult()) + "{\"message\": \"account sucessfully deleted\"}";
	}
	
	@Transactional (TxType.REQUIRED)
	public String updateAccount(Long id, String account)
	{
		Account updateAccount = util.getObjectForJSON(account, Account.class);
		TypedQuery<Account> query = manager.createQuery("SELECT a FROM Account a WHERE a.id" + id, Account.class);
		manager.remove(query.getSingleResult());
		manager.persist(updateAccount);
		
		return util.getJSONForObject(updateAccount) + "{\"message\": \"account sucessfully updated\"}";
	}
}
