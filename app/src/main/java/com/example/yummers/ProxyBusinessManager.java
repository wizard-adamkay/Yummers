package com.example.yummers;

import android.app.Activity;
import android.view.View;

import com.google.firebase.firestore.FirebaseFirestore;

/** Proxy - Proxy BusinessManager */
public class ProxyBusinessManager implements IBusinessManager {
    IBusinessManager businessManager;

    ProxyBusinessManager() {
        businessManager = new BusinessManager();
    }

    public void getRestaurants(FirebaseFirestore db, Activity view) {
        businessManager.getRestaurants(db, view);
    }
}
