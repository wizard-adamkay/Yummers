package com.example.yummers;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import com.example.yummers.models.Business;
import com.example.yummers.models.Item;
import com.example.yummers.models.Order;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FieldValue;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.ArrayList;

public class OrderStatusActivity extends AppCompatActivity {
    FirebaseFirestore db;
    String restaurantID;
    int orderNum;
    Order order;
    Business business;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order_status);
        db = FirebaseFirestore.getInstance();
        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            restaurantID = extras.getString("restaurantID");
            orderNum = extras.getInt("order number");
        }
        db.collection("restaurants").document(restaurantID).get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                if(task.isSuccessful()) {
                    DocumentSnapshot document = task.getResult();
                    Log.d("asd123f", "DocumentSnapshot data: " + document.getData());
                    business = document.toObject(Business.class);
                    ArrayList<Order> orders = (ArrayList<Order>) business.getCurrentOrders();
                    order = orders.get(orderNum);
                    ArrayList<Item> items = new ArrayList<Item>();
                    items = (ArrayList<Item>) order.getItems();
                    Log.d("asd123f", "order data: " + order);
                    ItemAdapter adapter = new ItemAdapter(OrderStatusActivity.this, items);
                    ListView listView = (ListView) findViewById(R.id.itemLV);
                    listView.setAdapter(adapter);
                }
            }
        });

        Button btn = findViewById(R.id.confirmBtn);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                business.getOrderHistory().add(order);
                db.collection("restaurants").document(restaurantID).update("orderHistory", business.getOrderHistory()).addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void unused) {
                        Log.d("test", "order archived!");
                        Toast.makeText(getApplicationContext(), "Order archived successfully!", Toast.LENGTH_SHORT).show();
                        business.removeOrder(orderNum);
                        ArrayList<Order> changedOrders = (ArrayList<Order>) business.getCurrentOrders();
                        db.collection("restaurants").document(restaurantID).update("currentOrders",changedOrders);
                        Intent intent = new Intent(getApplicationContext(), BusinessHomepageActivity.class);
                        intent.putExtra("business", business);
                        startActivity(intent);
                    }
                });
            }
        });
    }

}