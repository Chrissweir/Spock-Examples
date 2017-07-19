package com.spock.example.mocks;

public interface CreditCardProcessor {

	CreditCardResult sale(int amount, Customer customer);
	
	CreditCardResult authorize(int amount, Customer customer);
	
	CreditCardResult capture(String token, Customer customer);
	
	void shutdown();
}
