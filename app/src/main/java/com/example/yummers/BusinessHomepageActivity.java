package com.example.yummers;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.example.yummers.models.Business;

public class BusinessHomepageActivity extends AppCompatActivity {
    Business business;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_business_homepage);
        TextView title = findViewById(R.id.textView4);
        business = (Business) getIntent().getSerializableExtra("business");
        Log.e("business", business.toString());
        title.setText(business.getName());
    }
    public void pastOrders(View view){
        Intent intent = new Intent(this, PastOrdersActivity.class);
        intent.putExtra("restaurantID", business.getID());
    }
    public void currentOrders(View view){

    }
    public void updateMenu(View view){
        Intent intent =  new Intent(this, CreateMenuActivities.class);
        startActivity(intent);
    }
}