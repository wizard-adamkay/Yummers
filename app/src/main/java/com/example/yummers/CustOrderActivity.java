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
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.ArrayList;

public class CustOrderActivity extends AppCompatActivity {
    private Business business;
    private ArrayList<Item> orderList;
    private Order order;
    private TextView tvTotal;
    private CustMenuAdapter.RestaurantClickListener listener;
    private CustMenuAdapter adapter;
    private FirebaseAuth fireAuth;
    private FirebaseFirestore fireStore;
    private FirebaseUser fireUser;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cust_order);

        tvTotal = findViewById(R.id.tvTotal);
        fireAuth = FirebaseAuth.getInstance();
        fireStore = FirebaseFirestore.getInstance();
        fireUser = fireAuth.getCurrentUser();

        Intent i = getIntent();

        business = (Business) i.getSerializableExtra("business");
        order = (Order) i.getSerializableExtra("orderList");
        orderList = new ArrayList<Item>(order.getItems());

        setOnClickListener();
        RecyclerView rvOrder = findViewById(R.id.rvOrder);
        adapter = new CustMenuAdapter(this, orderList, listener);
        DividerItemDecoration div = new DividerItemDecoration(rvOrder.getContext(), LinearLayoutManager.VERTICAL);
        rvOrder.addItemDecoration(div);
        rvOrder.setAdapter(adapter);
        rvOrder.setLayoutManager(new LinearLayoutManager(this));

        setTotalText();

        Button btn = findViewById(R.id.btnSubmitOrder);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                order.setItems(orderList);
                order.setCustomerID(fireUser.getUid());
                if(business.getCurrentOrders() != null) {
                    business.getCurrentOrders().add(order);
                } else {
                    ArrayList<Order> list = new ArrayList<Order>();
                    list.add(order);
                    business.setCurrentOrders(list);
                }
                fireStore.collection("restaurants").document(business.getID()).
                        update("currentOrders",business.getCurrentOrders()).addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void unused) {
                        Log.e("!!!!!!!!!!!!!!!", "Sent order successfully");
                        Toast.makeText(getApplicationContext(), "Order sent successfully!", Toast.LENGTH_SHORT).show();
                        Intent i = new Intent(getApplicationContext(), UserHomepageActivity.class);
                        startActivity(i);
                    }
                });
            }
        });

    }

    private void setTotalText() {
        order.setItems(orderList);
        tvTotal.setText("Total: $" + order.getTotalCost());
    }

    @Override
    public void onBackPressed() {
        Log.e("!!!!!!!!!!!!!!", "back pressed");
        Intent backIntent = new Intent();
        backIntent.putExtra("changedList", orderList);
        setResult(Activity.RESULT_OK,backIntent);
        finish();
    }

    private void setOnClickListener() {
        listener = new CustMenuAdapter.RestaurantClickListener() {
            @Override
            public void onClick(View v, int position) {

                Toast.makeText(getApplicationContext(), "Removed " + orderList.get(position).getName()
                        + " from your order", Toast.LENGTH_SHORT).show();
                orderList.remove(position);
                adapter.notifyItemRemoved(position);
                adapter.notifyItemRangeChanged(position, orderList.size());
                setTotalText();
            }
        };
    }
}