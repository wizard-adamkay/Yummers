package com.example.yummers;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.example.yummers.models.Business;

import java.util.ArrayList;

public class SearchActivity extends AppCompatActivity {
    private ArrayList<Business> businesses;
    private SearchAdapter.RestaurantClickListener listener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);

        businesses = (ArrayList<Business>) getIntent().getSerializableExtra("b");

        setOnClickListener();
        RecyclerView rvSearch = (RecyclerView) findViewById(R.id.rvSearch);
        SearchAdapter adapter= new SearchAdapter(this, businesses, listener);
        DividerItemDecoration div = new DividerItemDecoration(rvSearch.getContext(), LinearLayoutManager.VERTICAL);
        rvSearch.addItemDecoration(div);
        rvSearch.setAdapter(adapter);
        rvSearch.setLayoutManager(new LinearLayoutManager(this));
    }

    private void setOnClickListener() {
        listener = new SearchAdapter.RestaurantClickListener() {
            @Override
            public void onClick(View v, int position) {
                Intent intent =  new Intent(getApplicationContext(), CustomerMenu.class);
                Business b = businesses.get(position);
                intent.putExtra("business", b);
                startActivity(intent);
            }
        };
    }
}

