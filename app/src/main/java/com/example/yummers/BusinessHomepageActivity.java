package com.example.yummers;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class BusinessHomepageActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_business_homepage);
    }
    public void pastOrders(View view){

    }
    public void currentOrders(View view){

    }
    public void updateMenu(View view){
        Intent intent =  new Intent(this, CreateMenuActivities.class);
        startActivity(intent);
    }
}