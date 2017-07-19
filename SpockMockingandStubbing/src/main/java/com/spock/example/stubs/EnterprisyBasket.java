package com.spock.example.stubs;

import com.spock.example.*;

public class EnterprisyBasket extends Basket{

	public EnterprisyBasket(ServiceLocator serviceLocator)
	{
		setWarehouseInventory(serviceLocator.getWarehouseInventory());
	}
}
