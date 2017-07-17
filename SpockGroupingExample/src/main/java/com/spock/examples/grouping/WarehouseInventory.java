package com.spock.examples.grouping;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by cweir on 17/07/2017.
 */
public class WarehouseInventory {
    private int boxesMovedOut =0;

    protected Map<String,Integer> inventory = new HashMap<String, Integer>();

    public void preload(Product product, int times)
    {
        inventory.put(product.getName(), times);
    }

    public void subtract(String productName, Integer times) {
        int now = inventory.get(productName);
        int after = now - times;
        if(after == 0)
        {
            inventory.remove(productName);
        }
        else
        {
            inventory.put(productName, after);
        }
        boxesMovedOut +=times;
    }

    public int availableOfProduct(String productName)
    {
        if(inventory.containsKey(productName))
        {
            return inventory.get(productName);
        }
        else
        {
            return 0;
        }
    }

    public boolean isEmpty()
    {
        return inventory.isEmpty();
    }

    public int getBoxesMovedToday()
    {
        return boxesMovedOut;
    }
}