package com.spock.practices.Shared;

/**
 * Created by cweir on 17/07/2017.
 */
public class CreditCardProcessor {
    private int salesToday;

    public CreditCardProcessor()
    {
		System.out.println("Expensive Credit processor is starting");
        salesToday = 0;
    }

    public void newDayStarted()
    {
        salesToday =0;
    }
    public void charge(int price)
    {
        salesToday = salesToday +price;
    }

    public int getCurrentRevenue()
    {
        return salesToday;
    }

    public void shutDown()
    {
		System.out.println("Expensive Credit processor is stopping");
    }
}
