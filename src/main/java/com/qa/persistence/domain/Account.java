package com.qa.persistence.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Account 
{
	@Id @GeneratedValue(strategy=GenerationType.AUTO)
	private Long id;
	@Column(length = 8)
	private Long accountNumber;
	@Column(length = 20)
	private String firstName;
	@Column(length = 20)
	private String lastName;
	
	public Account()
	{
		
	}
	
	public Account(String firstName, String lastName, Long accountNumber)
	{
		this.firstName = firstName;
		this.lastName = lastName;
		this.accountNumber = accountNumber;
	}

	public String getFirstName() 
	{
		return firstName;
	}

	public void setFirstName(String firstName) 
	{
		this.firstName = firstName;
	}

	public String getLastName() 
	{
		return lastName;
	}

	public void setLastName(String lastName) 
	{
		this.lastName = lastName;
	}

	public Long getAccountNumber() 
	{
		return accountNumber;
	}

	public void setAccountNumber(Long accountNumber) 
	{
		this.accountNumber = accountNumber;
	}
	
	
}

