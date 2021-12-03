package com.example.yummers;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.util.Log;
import android.widget.ListView;

import com.example.yummers.models.Business;
import com.example.yummers.models.Menu;
import com.example.yummers.models.Menus;
import com.example.yummers.models.Order;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentChange;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.Query;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;

public class MyMenuActivity extends AppCompatActivity {

    FirebaseAuth fireAuth;
    FirebaseFirestore db;
    FirebaseUser user;
    String restaurantID;
    Menu menu;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_menu);

        fireAuth = FirebaseAuth.getInstance();
        user = fireAuth.getCurrentUser();

        db = FirebaseFirestore.getInstance();
        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            restaurantID = extras.getString("restaurantID");
        }

        EventChangeListener();
    }

    private void EventChangeListener() {
        db.collection("restaurants").document(restaurantID).get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                if(task.isSuccessful()) {
                    DocumentSnapshot document = task.getResult();
                    if (document.exists()) {
                        Log.d("asdf", "DocumentSnapshot data: " + document.getData());
                        Business business = task.getResult().toObject(Business.class);
                        menu = business.getMenu();
                        ItemAdapter adapter = new ItemAdapter(MyMenuActivity.this, menu.getItems());
                        ListView listView = (ListView) findViewById(R.id.myMenuLV);
                        listView.setAdapter(adapter);
                    } else {
                        Log.d("asdf", "No such document");
                    }
                }
            }
        });
    }
}