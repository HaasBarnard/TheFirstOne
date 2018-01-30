package com.example.hanno.thefirstone;

import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.TextInputEditText;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity
{
    ArrayList<GroceryItem> m_groceryItems;
    ListView m_listView;
    GroceryItemAdapter m_groceryItemAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


        // TODO: 2018/01/26 add onesignal notifications to inform people when the stock is getting low or is about to expire so that they can order again
        // TODO: 2018/01/26 Need to have an easy way of adding/removing stock and also adjusting volumes/level
        // TODO: 2018/01/26 There can be multiple items and the one item can also have a level

        m_listView = (ListView) findViewById(R.id.lstvw_GroceryItem);

//        GroceryItem one = new GroceryItem();
//
//        one.setName("The first one");

//        m_groceryItems = new ArrayList<>();
//        m_groceryItems.add(one);

        loadGroceryItemsFromDB();

        m_groceryItemAdapter = new GroceryItemAdapter(this, R.layout.row_grocery_item, m_groceryItems);

        m_listView.setAdapter(m_groceryItemAdapter);

        m_listView.setOnItemClickListener(itemClickListener);
        m_listView.setOnItemLongClickListener(itemLongClickListener);

        m_groceryItemAdapter.notifyDataSetChanged();

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(fabClickListener);
    }

    private void loadGroceryItemsFromDB()
    {
//        m_groceryItems = new ArrayList<>(GroceryItem.listAll(GroceryItem.class));
        m_groceryItems = (ArrayList<GroceryItem>)(GroceryItem.listAll(GroceryItem.class));

        if (m_groceryItems == null)
        {
            m_groceryItems = new ArrayList<>();
        }

//        m_groceryItemAdapter.setGroceryItems(m_groceryItems);
//        m_groceryItemAdapter.notifyDataSetChanged();
    }

    View.OnClickListener fabClickListener = new View.OnClickListener()
    {
        @Override
        public void onClick(View v)
        {
            AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);

            LayoutInflater layoutInflater = (LayoutInflater)MainActivity.this.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

            View inflatedView = layoutInflater.inflate(R.layout.dialog_add_grocery_item, null);

            final TextInputEditText tilTextName = (TextInputEditText) inflatedView.findViewById(R.id.groceryDialog_name);

            builder.setView(inflatedView);

            builder.setPositiveButton("Save", new DialogInterface.OnClickListener()
            {
                @Override
                public void onClick(DialogInterface dialog, int which)
                {
                    if(tilTextName.getText().toString().isEmpty())
                    {
                        Toast.makeText(MainActivity.this, "Please enter name", Toast.LENGTH_SHORT).show();
                        return;
                    }

                    GroceryItem one = new GroceryItem();
                    one.setName(tilTextName.getText().toString());

                    one.save();

                    loadGroceryItemsFromDB();
                    m_groceryItemAdapter.setGroceryItems(m_groceryItems);
                    m_groceryItemAdapter.notifyDataSetChanged();
                }
            });

            builder.create().show();
        }
    };

    AdapterView.OnItemClickListener itemClickListener = new AdapterView.OnItemClickListener()
    {
        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position, long id)
        {
            final GroceryItem selectedItem = m_groceryItems.get(position);

            AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);

            builder.setTitle("Change quantity");
            builder.setPositiveButton("Add", new DialogInterface.OnClickListener()
            {
                @Override
                public void onClick(DialogInterface dialog, int which)
                {
                    selectedItem.changeQuantity(true);
                    m_groceryItemAdapter.notifyDataSetChanged();
                }
            });

            builder.setNegativeButton("Remove", new DialogInterface.OnClickListener()
            {
                @Override
                public void onClick(DialogInterface dialog, int which)
                {
                    selectedItem.changeQuantity(false);
                    m_groceryItemAdapter.notifyDataSetChanged();
                }
            });

            builder.create().show();
        }
    };

    AdapterView.OnItemLongClickListener itemLongClickListener = new AdapterView.OnItemLongClickListener()
    {
        @Override
        public boolean onItemLongClick(AdapterView<?> parent, View view, final int position, long id)
        {
            AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);

            builder.setTitle("Delete?");
            builder.setPositiveButton("Delete", new DialogInterface.OnClickListener()
            {
                @Override
                public void onClick(DialogInterface dialog, int which)
                {

                    m_groceryItems.get(position).delete();

                    loadGroceryItemsFromDB();
                    m_groceryItemAdapter.setGroceryItems(m_groceryItems);
                    m_groceryItemAdapter.notifyDataSetChanged();
                }
            });

            builder.setNegativeButton("Cancel", null);

            builder.create().show();

            return true;
        }
    };



    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
