package com.example.yummers;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.yummers.models.Business;
import com.example.yummers.models.Item;
import com.example.yummers.models.Order;

import java.util.ArrayList;

public class CustomerMenu extends AppCompatActivity {
    private Business business;
    private ArrayList<Item> items;
    private ArrayList<Item> orderList;
    private CustMenuAdapter.RestaurantClickListener listener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_customer_menu);

        TextView tvName = findViewById(R.id.tvRestName);

        business = (Business) getIntent().getSerializableExtra("business");
        items = business.getMenu().getItems();
        orderList = new ArrayList<Item>();

        tvName.setText(business.getName());

        setOnClickListener();
        RecyclerView rvCustMenu = (RecyclerView) findViewById(R.id.rvCustMenu);
        CustMenuAdapter adapter = new CustMenuAdapter(this, items, listener);
        DividerItemDecoration div = new DividerItemDecoration(rvCustMenu.getContext(), LinearLayoutManager.VERTICAL);
        rvCustMenu.addItemDecoration(div);
        rvCustMenu.setAdapter(adapter);
        rvCustMenu.setLayoutManager(new LinearLayoutManager(this));

        Button btn = (Button) findViewById(R.id.btnOrder);
        btn.setOnClickListener(new Button.OnClickListener() {
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), CustOrderActivity.class);
                intent.putExtra("business", business);
                intent.putExtra("orderList", new Order(orderList));
                startActivityForResult(intent, 1);
            }
        });

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode,resultCode,data);

        if(requestCode == 1 && resultCode == Activity.RESULT_OK) {
            orderList = (ArrayList<Item>) data.getSerializableExtra("changedList");
        }
    }


    private void setOnClickListener() {
        listener = new CustMenuAdapter.RestaurantClickListener() {
            @Override
            public void onClick(View v, int position) {
                orderList.add(items.get(position));
                Log.e("!!!!!!!!!!!!!!!!!", "added" + items.get(position).getName());
                Toast.makeText(getApplicationContext(), "Added " + items.get(position).getName()
                        + " to your order", Toast.LENGTH_SHORT).show();
            }
        };
    }
}