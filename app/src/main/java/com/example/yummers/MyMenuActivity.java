package com.example.yummers;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.util.Log;

import com.example.yummers.models.Menus;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentChange;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.Query;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;

public class MyMenuActivity extends AppCompatActivity {

    RecyclerView recyclerView;
    ArrayList<Menus> menusArrayList;
    MenuAdapter menuAdapter;
    FirebaseAuth fireAuth;
    FirebaseFirestore db;
    FirebaseUser user;
    ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_menu);

        fireAuth = FirebaseAuth.getInstance();
        user = fireAuth.getCurrentUser();

        progressDialog = new ProgressDialog(this);
        progressDialog.setCancelable(false);
        progressDialog.setMessage("Fetching Data...");
        progressDialog.show();

        recyclerView = findViewById(R.id.recyclerview);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        db = FirebaseFirestore.getInstance();
        menusArrayList = new ArrayList<Menus>();
        menuAdapter = new MenuAdapter(MyMenuActivity.this, menusArrayList);

        recyclerView.setAdapter(menuAdapter);

        EventChangeListener();
    }

    private void EventChangeListener() {
        db.collection("menus").orderBy("name", Query.Direction.ASCENDING)
            .addSnapshotListener(new EventListener<QuerySnapshot>() {
                @Override
                public void onEvent(@Nullable QuerySnapshot value, @Nullable FirebaseFirestoreException error) {
                    if (error != null) {
                        if (progressDialog.isShowing()) {
                            progressDialog.dismiss();
                        }

                        Log.e("Firestore error", error.getMessage());
                    }

                    for (DocumentChange dc : value.getDocumentChanges()) {
                        if (dc.getType() == DocumentChange.Type.ADDED) {
                            if (dc.getDocument().toObject(Menus.class).getOwner().equals(user.getUid())) {
                                menusArrayList.add(dc.getDocument().toObject(Menus.class));
                            }
                        }
                        menuAdapter.notifyDataSetChanged();

                        if (progressDialog.isShowing()) {
                            progressDialog.dismiss();
                        }
                    }
                }
            });
    }
}