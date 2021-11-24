package com.example.yummers;

import static com.google.firebase.firestore.SetOptions.merge;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;


import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.yummers.models.Business;
import com.example.yummers.models.Item;
import com.example.yummers.models.Menu;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class CreateMenuActivities extends AppCompatActivity {

    static final int REQUEST_ITEM_ADD = 1;

    Menu menu;
    ArrayList<Item> items;
    FirebaseAuth fireAuth;
    FirebaseFirestore firestore;
    FirebaseUser user;
    String docId;
    Button add;
    Button done;
    TextView itemsText;
    RecyclerView recyclerView;
    RecyclerItemAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu_business);
        add = findViewById(R.id.add_menu_item);
        itemsText = findViewById(R.id.item_list);
        fireAuth = FirebaseAuth.getInstance();
        firestore = FirebaseFirestore.getInstance();
        user = fireAuth.getCurrentUser();
        items = new ArrayList<>();
        recyclerView = findViewById(R.id.items_list);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        retrieveData();
    }

    public void addItem(View v) {
        Intent intent = new Intent(this, AddItemActivity.class);
        startActivityForResult(intent, REQUEST_ITEM_ADD);
    }

    public void deleteItem() {

    }

    public void done(View v){

//        Log.e("menu", menu.toString());

        updateFirebaseMenu();

        finish();
    }
    public void updateFirebaseMenu() {
        firestore.collection("restaurants").document(docId).update("menu", menu).addOnSuccessListener(new OnSuccessListener<Void>() {
            @Override
            public void onSuccess(Void unused) {
                Log.e("update cloud menu: ", "trying to update to " + docId);
            }
        });
    }
    public void retrieveData() {

        firestore.collection("restaurants").whereEqualTo("owner", user.getUid())
                .get().addOnSuccessListener(queryDocumentSnapshots -> {
            if (!queryDocumentSnapshots.isEmpty()){
                for (int i = 0; i<queryDocumentSnapshots.size(); i++) {
                    docId = queryDocumentSnapshots.getDocuments().get(0).getId();
                    Log.e("docId: ", docId);
                    Business b = queryDocumentSnapshots.getDocuments().get(i).toObject(Business.class);
                    menu = b.getMenu() == null ? new Menu(items) : b.getMenu();
                    Log.e("B-data(" + i +"):", b.toString());
                    Log.e("menu-data: ", menu != null ? menu.toString() : "no data");

                    adapter = new RecyclerItemAdapter(this, menu.getItems());
                    recyclerView.setAdapter(adapter);
                }
            } else {
                Log.e("menu-data: ", "retrieve data failed");
            }
        });
//        firestore.collection("menus").whereEqualTo("owner", user.getUid()).get().addOnSuccessListener(queryDocumentSnapshots -> {
//                 if (!queryDocumentSnapshots.isEmpty()){
//                     menu = queryDocumentSnapshots.getDocuments().get(0).toObject(Menu.class);
//                     updateMenu();
//                     Log.e("menu-data: ", menu.toString());
//
//                 } else {
//                     Log.e("menu-data: ", "retrieve data failed");
//                 }
//
//         });
    }

    public void updateMenu() {
        if (menu.getItems().size() == 0) {
            itemsText.setText("No item");
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == REQUEST_ITEM_ADD && resultCode == RESULT_OK) {
            String itemName = data.getStringExtra("ITEMNAME");
            double itemPrice = data.getDoubleExtra("ITEMPRICE", -1);
            ArrayList<String> tags = new ArrayList<String>();
            Item item = new Item(itemName, itemPrice, tags);
            menu.addItem(item);
            adapter.notifyItemInserted(menu.getItems().size() - 1);
            recyclerView.scrollToPosition(menu.getItems().size() - 1);
            updateMenu();
        }
    }
}
