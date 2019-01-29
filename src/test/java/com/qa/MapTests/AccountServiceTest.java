package com.qa.MapTests;

import static org.junit.Assert.*;

import java.util.HashMap;
import java.util.Map;

import javax.inject.Inject;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import com.qa.persistence.domain.Account;
import com.qa.persistence.repository.AccountMapRepository;
import com.qa.persistence.util.JSONUtil;

public class AccountServiceTest
 {
	AccountMapRepository repository = new AccountMapRepository();
	JSONUtil util = new JSONUtil();
	
	@Before
	public void setup() 
	{
		Account account1 = new Account("John", "Cleese", (long) 1);
		Account account2 = new Account("Frank", "West", (long) 2);
		Account account3 = new Account("George", "Bush2", (long) 3);
		
		repository.accountMap.put((long) 1, account1);
		repository.accountMap.put((long) 2, account2);
		repository.accountMap.put((long) 3, account3);
	}
	
	
	@Test
	public void addAccountTest() 
	{
		int mapSize = repository.accountMap.size();
		Account newAccount = new Account("John", "Smith", (long) 4);
		repository.createAccount(util.getJSONForObject(newAccount));
		assertEquals("The Map size has not increased", mapSize + 1, repository.accountMap.size());
	}
	
	@Test
	public void add2AccountTest()
	{
		int mapSize = repository.accountMap.size();
		Account newAccount1 = new Account("Frank", "Boyo", (long) 5);
		Account newAccount2 = new Account("Steven", "Stamcoast", (long) 6);
		repository.createAccount(util.getJSONForObject(newAccount1)); 
		repository.createAccount(util.getJSONForObject(newAccount2));
		assertEquals("The Map size has not increased", mapSize + 2, repository.accountMap.size());
	}
 
	@Test
	public void removeAccountTest() 
	{
		int mapSize = repository.accountMap.size();
		repository.deleteAccount((long)1);
		assertEquals("The Map size has not decreased by 1", mapSize - 1, repository.accountMap.size());
	}
	
	@Test 
	public void remove2AccountTest()
	{
		int mapSize = repository.accountMap.size();
		repository.deleteAccount((long)1);
		repository.deleteAccount((long)2);
		assertEquals("The Map size has not decreased by 2", mapSize - 2, repository.accountMap.size());
	}
	
	@Test
	public void remove2AccountTestAnd1ThatDoesntExist() 
	{
		int mapSize = repository.accountMap.size();
		repository.deleteAccount((long)1);
		repository.deleteAccount((long)2);
		repository.deleteAccount((long)31);
		assertEquals("The Map should have decreased by only 2", mapSize - 2, repository.accountMap.size());
	}
	
	@Test
	public void accountConversionToJSONTestWithEmptyMap() 
	{
		String json = util.getJSONForObject(repository.accountMap.get((long)22));
		assertEquals("JSON Strings did not match", "null", json);
	} 
	
	@Test
	public void accountConversionToJSONTestEmptyMapWithConversion()
	{
		
	} 

	@Test
	public void accountConversionToJSONTest()
	{
		String json = util.getJSONForObject(repository.accountMap.get((long)1));
		assertEquals("Json Strings did not match", "{\"firstName\":\"John\",\"lastName\":\"Cleese\",\"accountNumber\":1}", json);
	}

	@Test 
	public void getCountForFirstNamesInAccountWhenZeroOccurances() 
	{
		
	}
	
	@Test
	public void getCountForFirstNamesInAccountWhenOne() 
	{
		
	}

	@Test
	public void getCountForFirstNamesInAccountWhenMult()
	{
		
	}

}
