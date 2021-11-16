package com.example.yummers;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.example.yummers.models.Business;
import com.example.yummers.models.Item;
import com.example.yummers.models.Order;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    FirebaseFirestore db;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        db = FirebaseFirestore.getInstance();
        setContentView(R.layout.activity_main);
    }


    public void signUp(View view) {
        Intent intent = new Intent(this, SignUpActivity.class);
        startActivity(intent);
    }
    public void signIn(View view) {
        Intent intent = new Intent(this, LoginActivity.class);
        startActivity(intent);
    }
    public void register(View view) {
        Intent intent = new Intent(this, RegisterActivity.class);
        startActivity(intent);
    }

    public void addTest(View view) {
        Business business = new Business("Jims Bar","1333 a street","604-345-6346", "UserCodeGoesHere");
        Item item1 = new Item("burger", 23.22, null);
        Item item2 = new Item("fries", 12.22, null);
        List<Item> items = new ArrayList<Item>();
        items.add(item1);
        items.add(item2);
        Order order1 = new Order(items);
        List<Order> orders = new ArrayList<>();
        orders.add(order1);
        business.setCurrentOrders(orders);
        db.collection("restaurants").document().set(business)
                .addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void aVoid) {
                        Log.d("asdf", "DocumentSnapshot successfully written!");
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Log.w("asdf", "Error writing document", e);
                    }
                });
    }

    public void testOrders(View view) {
        Intent intent = new Intent(MainActivity.this, OrderViewActivity.class);
        intent.putExtra("restaurantID","ympdW5AMNMCj9usPJj8v");
        startActivity(intent);
    }
}