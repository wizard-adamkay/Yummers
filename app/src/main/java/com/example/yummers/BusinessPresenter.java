package com.example.yummers;

import android.content.Context;
import android.util.Log;

import androidx.recyclerview.widget.RecyclerView;

import com.example.yummers.models.Business;
import com.example.yummers.models.Item;
import com.example.yummers.models.Menu;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.ArrayList;

public class BusinessPresenter implements BusinessMVPContract.Presenter {

    CreateMenuActivities view;
    BusinessModel model;
    RecyclerItemAdapter adapter;

    public static BusinessPresenter singleton;

    private BusinessPresenter(CreateMenuActivities view, BusinessModel model) {
        this.view = view;
        this.model = model;
    }
    public static BusinessPresenter getInstance (CreateMenuActivities view, BusinessModel model){
        if (singleton == null){
            singleton = new BusinessPresenter(view, model);
        }
        BusinessModel.getInstance().init();
        return singleton;
    }
    public void reset() {
        model.reset();
        singleton = null;
    }

    public void init () {
        Log.d("init:", model.getMenuData().toString());
        adapter = new RecyclerItemAdapter(view, model.getMenuData().getItems());
        view.setUpRecyclerView(adapter);
        updateMenuDisplay();
    }

    public void updateMenuDisplay() {
        if (model.getMenuData().getItems().size() == 0) {
            Log.d("menu", model.toString());
            view.displayItemsText("You have no item in your menu.");
        } else {
            view.displayItemsText("You have " + model.getMenuData().getItems().size() + " items in your menu.");
        }
    }
    public void updateFirebaseMenu() {
        model.updateFirebaseMenu();
    }

    public int addItem(Item item){
        int size = model.addItem(item);
        adapter.notifyItemInserted(size);
        updateMenuDisplay();
        return size;
    }

}
