package com.example.yummers;

import android.app.Activity;
import android.view.View;

import com.google.firebase.firestore.FirebaseFirestore;

/** Proxy - Interface BusinessManager */
public interface IBusinessManager {
    void getRestaurants(FirebaseFirestore db, Activity view);
}
