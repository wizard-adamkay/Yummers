package com.example.yummers;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.example.yummers.models.Business;
import com.example.yummers.models.Menu;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;

import java.io.Serializable;
import java.util.ArrayList;

public class UserHomepageActivity extends AppCompatActivity {
    FirebaseFirestore db;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        db = FirebaseFirestore.getInstance();
        setContentView(R.layout.activity_user_homepage);
    }

    public void signOut(View view) {
        FirebaseAuth.getInstance().signOut();
        Intent intent =  new Intent(this, MainActivity.class);
        startActivity(intent);
    }

    public void search(View view) {
        EditText searchET = findViewById(R.id.searchEditText);
        String search = searchET.getText().toString();
        db.collection("restaurants").whereEqualTo("name", search).get().addOnSuccessListener(queryDocumentSnapshots -> {
            //note this is jank solution that needs to get fixed
            ArrayList<Business> businesses = new ArrayList<>();
            if(!queryDocumentSnapshots.getDocuments().isEmpty()) {
                for(int i = 0; i < queryDocumentSnapshots.size(); i++) {
                    Business b = queryDocumentSnapshots.getDocuments().get(i).toObject(Business.class);
                    Log.e("business retrieved:", b.toString());
                    businesses.add(b);
                }
                Intent intent =  new Intent(this, SearchActivity.class);
                //Bundle bundle = new Bundle();
                //bundle.putSerializable("businesses", (Serializable) businesses);
                intent.putExtra("b", businesses);
                startActivity(intent);
            } else {
                Toast.makeText(getApplicationContext(), "Search Yielded no Results", Toast.LENGTH_LONG).show();
            }
        });
    }

    public void allRestaurant(View view) {
        db.collection("restaurants").get().addOnSuccessListener(queryDocumentSnapshots -> {
           ArrayList<Business> allBusiness = new ArrayList<>();

           if (!queryDocumentSnapshots.getDocuments().isEmpty()) {
               for (int i = 0; i < queryDocumentSnapshots.size(); i++) {
                   Business b = queryDocumentSnapshots.getDocuments().get(i).toObject(Business.class);
                   allBusiness.add(b);
               }
               Intent intent = new Intent(this, SearchActivity.class);
               intent.putExtra("b", allBusiness);
               startActivity(intent);
           }
        });
    }

    public void viewMenu(View view) {
        Intent intent = new Intent(this, MenuActivity.class);
        startActivity(intent);
    }
}