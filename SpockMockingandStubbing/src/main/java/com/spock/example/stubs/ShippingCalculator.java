package com.spock.example.stubs;

import com.spock.example.*;

public interface ShippingCalculator {
	int findShippingCostFor(Product product, int times);
}
