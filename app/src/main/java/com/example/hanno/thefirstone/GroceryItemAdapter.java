package com.example.hanno.thefirstone;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.zip.Inflater;

/**
 * Created by hanno on 2017/12/20.
 */

public class GroceryItemAdapter extends ArrayAdapter<GroceryItem>
{
    ArrayList<GroceryItem> m_groceryItems;
    Context m_context;

    public GroceryItemAdapter(@NonNull Context context, int resource, ArrayList<GroceryItem> groceryItems)
    {
        super(context, resource);
        m_groceryItems = groceryItems;
        m_context = context;

    }

    @Nullable
    @Override
    public GroceryItem getItem(int position)
    {
        return super.getItem(position);
    }

    @Override
    public int getCount()
    {
        return m_groceryItems.size();
    }

    @Override
    public long getItemId(int position)
    {
        return m_groceryItems.get(position).getId();
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent)
    {
        GroceryItem currentItem = m_groceryItems.get(position);

        View view = convertView;

        if (convertView == null)
        {
            LayoutInflater inflater = (LayoutInflater)getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = inflater.inflate(R.layout.row_grocery_item, null);
        }

        TextView itemName = view.findViewById(R.id.txtvw_ItemName);

        itemName.setText(currentItem.getName());

        return view;
    }

    public ArrayList<GroceryItem> getGroceryItems()
    {
        return m_groceryItems;
    }

    public void setGroceryItems(ArrayList<GroceryItem> m_groceryItems)
    {
        this.m_groceryItems = m_groceryItems;
    }
}
