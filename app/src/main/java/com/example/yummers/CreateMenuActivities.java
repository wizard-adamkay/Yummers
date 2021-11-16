package com.example.yummers;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;


import androidx.appcompat.app.AppCompatActivity;

import com.example.yummers.models.Item;
import com.example.yummers.models.Menu;

import java.util.ArrayList;
import java.util.List;

public class CreateMenuActivities extends AppCompatActivity {

    static final int REQUEST_ITEM_ADD = 1;

    ArrayList<Item> menu;

    Button add;
    Button done;
    TextView itemsText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu_business);
        add = findViewById(R.id.add_menu_item);
        menu = new ArrayList<>();
        itemsText = findViewById(R.id.item_list);
        updateMenu();
    }

    public void addItem(View v) {
        Intent intent = new Intent(this, AddItemActivity.class);
        startActivityForResult(intent, REQUEST_ITEM_ADD);
    }

    public void done(View v){
        finish();
    }

    public void updateMenu() {
        if (menu.size() == 0){
            itemsText.setText("No item");
        } else {
            itemsText.setText("");
            for (int i = 0; i<menu.size(); i++){
                itemsText.append(menu.get(i).toString() + "\n");
            }
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == REQUEST_ITEM_ADD && resultCode == RESULT_OK){

            String itemName = data.getStringExtra("ITEMNAME");
            double price = data.getDoubleExtra("ITEMPRICE", -1);
            ArrayList<String> tags = new ArrayList<String>();
            Item item = new Item(itemName, price, new String[]{});
            menu.add(item);
            updateMenu();
        }
    }

}
