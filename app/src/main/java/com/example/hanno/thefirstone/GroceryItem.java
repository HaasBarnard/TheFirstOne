package com.example.hanno.thefirstone;

import com.orm.SugarRecord;

/**
 * Created by hanno on 2017/12/20.
 */

public class GroceryItem extends SugarRecord<GroceryItem>
{
    String name;
    int quantity;
    int minimumQuantity;
    boolean isLow;

    public GroceryItem(){}

    public GroceryItem(String name, int quantity, int minimumQuantity)
    {
        this.name = name;
        this.quantity = quantity;
        this.minimumQuantity = minimumQuantity;
    }

    public String getName()
    {
        return name;
    }

    public void setName(String name)
    {
        this.name = name;
    }

    public int getQuantity()
    {
        return quantity;
    }

    public void setQuantity(int quantity)
    {
        this.quantity = quantity;
    }

    public int getMinimumQuantity()
    {
        return minimumQuantity;
    }

    public void setMinimumQuantity(int minimumQuantity)
    {
        this.minimumQuantity = minimumQuantity;
    }

    public boolean isLow()
    {
        return isLow;
    }

    public void setLow(boolean low)
    {
        isLow = low;
    }
}
