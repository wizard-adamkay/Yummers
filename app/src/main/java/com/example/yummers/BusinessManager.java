package com.example.yummers;

import android.app.Activity;
import android.content.Intent;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.example.yummers.models.Business;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.ArrayList;

/** Proxy - Service BusinessManager */
public class BusinessManager implements IBusinessManager {
    public void getRestaurants(FirebaseFirestore db, Activity view) {
        db.collection("restaurants").get().addOnSuccessListener(queryDocumentSnapshots -> {
           ArrayList<Business> allBusiness = new ArrayList<>();

           if (!queryDocumentSnapshots.getDocuments().isEmpty()) {
               for (int i = 0; i < queryDocumentSnapshots.size(); i++) {
                   Business b = queryDocumentSnapshots.getDocuments().get(i).toObject(Business.class);
                   allBusiness.add(b);
               }
               Intent intent = new Intent(view, SearchActivity.class);
               intent.putExtra("b", allBusiness);
               view.startActivity(intent);
           }
        });
    }
}