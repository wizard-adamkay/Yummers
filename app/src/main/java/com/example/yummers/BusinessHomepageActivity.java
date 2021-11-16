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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_business_homepage);
        TextView title = findViewById(R.id.textView4);
        Business business = (Business) getIntent().getSerializableExtra("business");
        Log.e("business", business.toString());
        title.setText(business.getName());
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