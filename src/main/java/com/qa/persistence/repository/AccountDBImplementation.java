package com.qa.persistence.repository;

import javax.enterprise.inject.Default;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.transaction.Transactional;
import static javax.transaction.Transactional.TxType.*;

import com.qa.persistence.domain.Account;
import com.qa.persistence.util.*;

@Default
@Transactional(SUPPORTS)
public class AccountDBImplementation implements AccountRepository
{	
	@Inject
	private JSONUtil util;
		
	@PersistenceContext (unitName = "primary")
	private EntityManager manager;
	
	@Override
	public String getAllAccounts()
	{
		TypedQuery<Account> query = manager.createQuery("SELECT a FROM Account a ORDER BY a.lastName DESC", Account.class);
		
		return util.getJSONForObject(query.getResultList());
	}
	
	@Transactional(REQUIRED)
	@Override
	public String createAccount(String account)
	{
		Account newAccount = util.getObjectForJSON(account, Account.class);
		manager.persist(newAccount);
		
		return util.getJSONForObject(newAccount);
	}
	
	@Transactional(REQUIRED)
	@Override
	public String deleteAccount(Long id)
	{
		TypedQuery<Account> query = manager.createQuery("SELECT a FROM Account a WHERE a.id" + id, Account.class);
		manager.remove(query.getSingleResult());
		
		return util.getJSONForObject(query.getSingleResult()) + "{\"message\": \"account sucessfully deleted\"}";
	}
	
	@Transactional(REQUIRED)
	@Override
	public String updateAccount(Long id, String account)
	{
		Account updateAccount = util.getObjectForJSON(account, Account.class);
		TypedQuery<Account> query = manager.createQuery("SELECT a FROM Account a WHERE a.id" + id, Account.class);
		manager.remove(query.getSingleResult());
		manager.persist(updateAccount);
		
		return util.getJSONForObject(updateAccount) + "{\"message\": \"account sucessfully updated\"}";
	}
}
