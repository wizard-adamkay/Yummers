package com.example.yummers;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.example.yummers.models.Business;
import com.google.firebase.auth.FirebaseAuth;

public class BusinessHomepageActivity extends AppCompatActivity {
    Business business;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_business_homepage);
        TextView title = findViewById(R.id.textView5);
        business = (Business) getIntent().getSerializableExtra("business");
        Log.e("business", business.toString());
        title.setText(business.getName());
    }
    public void pastOrders(View view){
        Intent intent = new Intent(this, PastOrdersActivity.class);
        String restaurantID = business.getID();
        intent.putExtra("restaurantID", restaurantID);
        startActivity(intent);
    }

    public void currentOrders(View view){
        Intent intent = new Intent(this, OrderViewActivity.class);
        String restaurantID = business.getID();
        intent.putExtra("restaurantID", restaurantID);
        startActivity(intent);
    }
    public void signOut(View view) {
        FirebaseAuth.getInstance().signOut();
        Intent intent =  new Intent(this, MainActivity.class);
        startActivity(intent);
    }

    public void updateMenu(View view){
        Intent intent =  new Intent(this, CreateMenuActivities.class);
        startActivity(intent);
    }

    public void viewMenu(View view) {
        Intent intent = new Intent(this,MenuActivity.class);
        startActivity(intent);
    }

    public void myMenu(View view) {
        Intent intent = new Intent(this, MyMenuActivity.class);
        String restaurantID = business.getID();
        intent.putExtra("restaurantID", restaurantID);
        startActivity(intent);
    }
}