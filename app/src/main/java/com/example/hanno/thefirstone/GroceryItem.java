package com.example.hanno.thefirstone;

import com.orm.SugarRecord;

/**
 * Created by hanno on 2017/12/20.
 */

public class GroceryItem extends SugarRecord<GroceryItem>
{
    String name;
    Integer quantity;
    Integer minimumQuantity;
    boolean isLow;

    // TODO: 2018/01/26 add the expiry date and do daily checks to see what will be expiring soon
    // TODO: 2018/01/26 add an arraylist of barcodes which can all correspond to this product
    // TODO: 2018/01/26 Need to have a search in add which checks for all current and previously added items
    // TODO: 2018/01/26 An item can be removed from the list or completely deleted
    // TODO: 2018/01/26 Need to have different lists. Stock items, out of stock items

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

    public Integer getQuantity()
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
