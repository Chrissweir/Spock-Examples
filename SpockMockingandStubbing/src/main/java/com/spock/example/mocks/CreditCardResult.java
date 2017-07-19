package com.spock.example.mocks;

public enum CreditCardResult {

	OK, INVALID_CARD, NOT_ENOUGH_FUNDS;

	private String token;

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

}
