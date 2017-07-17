package com.spock.examples.grouping;

import java.util.*;
import java.util.Map.Entry;
/**
 * Created by cweir on 14/07/2017.
 */
public class Basket {

    protected Map<Product, Integer> contents = new HashMap();

    public void addProduct(Product product){
        addProduct(product,1);
    }

    public void addProduct(Product product, int times) {

        if(contents.containsKey(product)){
            int existing = contents.get(product);
            contents.put(product,existing+times);
        }
        else {
            contents.put(product,times);
        }
    }

    public int getCurrentWeight(){
        int total = 0;
        for(Entry<Product,Integer> entry:contents.entrySet())
        {
            total = total + (entry.getKey().getWeight() * entry.getValue());
        }

        return total;
    }

    public int getProductTypesCount()
    {
        return contents.size();
    }

    public void clearAllProducts(){
        contents.clear();
    }
}