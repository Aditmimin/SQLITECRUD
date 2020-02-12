package com.ngoding.sqlitecrud;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;


import com.activeandroid.query.Select;

import java.util.ArrayList;
import java.util.List;


public class MainActivity extends AppCompatActivity implements OnClickListener {

    private EditText editTextInventoryName, editTextInventoryStock;
    private Button buttonSaveInventory;
    private ListView listViewInventories;

    private ArrayList<String> inventoryItems;

    private ArrayAdapter inventoryItemsAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        editTextInventoryName = findViewById(R.id.editTextInventoryName);
//        editTextInventoryStock = findViewById(R.id.editTextInventoryStock);
        buttonSaveInventory = findViewById(R.id.buttonSaveInventory);
        listViewInventories = findViewById(R.id.listViewInventories);

        //Adding listener to button
        buttonSaveInventory.setOnClickListener(this);

        inventoryItems = new ArrayList<>();
        //calling method to display the inventory list
        showInventoryList();

    }

    private void saveInventory() {
        //Getting name from editText
        String name = editTextInventoryName.getText().toString().trim();
//        String stock = editTextInventoryStock.getText().toString().trim();

        //Checking if name is blank
        if (name.equalsIgnoreCase("")) {
            Toast.makeText(this, "Please enter a name", Toast.LENGTH_LONG).show();
            return;
        } else {
//            if (stock.equalsIgnoreCase("")) {
//                Toast.makeText(this, "Please enter stock", Toast.LENGTH_LONG).show();
//                return;
//            } else {
                Inventory inventory = new Inventory();
                inventory.name = name;
//                inventory.stock = Integer.parseInt(stock);
                inventory.save();
                inventoryItems.add(name);
//                inventoryItems.add(stock);

                Toast.makeText(this, "Inventory Saved Successfully", Toast.LENGTH_LONG).show();
//                showInventoryList();
            }

//        }
    }

    private List<Inventory> getAll() {
        //Getting all items stored in Inventory table
        return new Select()
                .from(Inventory.class)
                .orderBy("name ASC")
                .execute();
    }


    private void showInventoryList() {
        //Creating a list and getting all inventories from the method
        List<Inventory> inventories = getAll();

        //Adding all the items of the inventories to arraylist
        for (int i = 0; i < inventories.size(); i++) {
            Inventory inventory = inventories.get(i);
            inventoryItems.add(inventory.name);
        }

        //Creating our adapter
        inventoryItemsAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, inventoryItems);

        //Adding adapter to listview
        listViewInventories.setAdapter(inventoryItemsAdapter);

        //Updating the inventory list
//        updateInventoryList();
    }


    @Override
    public void onClick(View v) {
        saveInventory();
    }
}
