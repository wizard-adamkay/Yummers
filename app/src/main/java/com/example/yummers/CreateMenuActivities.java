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

public class CreateMenuActivities extends AppCompatActivity implements BusinessMVPContract.View{

    static final int REQUEST_ITEM_ADD = 1;

    Button add;
    Button done;
    TextView itemsText;
    RecyclerView recyclerView;

    BusinessPresenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu_business);
        add = findViewById(R.id.add_menu_item);
        itemsText = findViewById(R.id.item_list);
        recyclerView = findViewById(R.id.items_list);
        presenter = BusinessPresenter.getInstance(this, BusinessModel.getInstance());
        Log.d("create", "onCreate: 1");
    }

    public void addItem(View v) {
        Intent intent = new Intent(this, AddItemActivity.class);
        startActivityForResult(intent, REQUEST_ITEM_ADD);
    }

    public void displayItemsText(String text) {
        itemsText.setText(text);
    }

    @Override
    public void setUpRecyclerView(RecyclerItemAdapter adapter) {
        Log.d("adapter: ", "adding " + adapter.toString());
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
    }

    public void done(View v){
        presenter.updateFirebaseMenu();
        presenter.reset();
        finish();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == REQUEST_ITEM_ADD && resultCode == RESULT_OK) {
            String itemName = data.getStringExtra("ITEMNAME");
            double itemPrice = data.getDoubleExtra("ITEMPRICE", -1);
            ArrayList<String> tags = new ArrayList<String>();
            Item item = new Item(itemName, itemPrice, tags);

            recyclerView.scrollToPosition(presenter.addItem(item));
            presenter.updateMenuDisplay();
        }
    }
}
