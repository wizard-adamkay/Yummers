package com.example.yummers;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.example.yummers.models.Business;
import com.example.yummers.models.Order;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.ArrayList;
//must pass a restaurantID through intent to this activity for it to work
public class OrderViewActivity extends AppCompatActivity {
    FirebaseFirestore db;
    String restaurantID;
    ListView listView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order_view);
        db = FirebaseFirestore.getInstance();
        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            restaurantID = extras.getString("restaurantID");
        }
        ArrayList<Order> orders= new ArrayList<Order>();
        db.collection("restaurants").document(restaurantID).get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                if (task.isSuccessful()) {
                    DocumentSnapshot document = task.getResult();
                    if (document.exists()) {
                        Log.d("asdf", "DocumentSnapshot data: " + document.getData());
                        Business business = task.getResult().toObject(Business.class);
                        for(Order order : business.getCurrentOrders()){
                            orders.add(order);
                        }
                        OrderArrayAdapter adapter = new OrderArrayAdapter(OrderViewActivity.this, orders);
                        listView = (ListView) findViewById(R.id.orderLV);
                        listView.setAdapter(adapter);
                        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                            @Override
                            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                                Order order = (Order) parent.getAdapter().getItem(position);
                                Log.d("asdf", order.toString());
                                Intent intent = new Intent(getApplicationContext(), OrderStatusActivity.class);
                                intent.putExtra("restaurantID", restaurantID);
                                intent.putExtra("order number", position);
                                startActivity(intent);
                            }
                        });

                    } else {
                        Log.d("asdf", "No such document");
                    }
                } else {
                    Log.d("asdf", "get failed with ", task.getException());
                }
            }
        });

    }




}