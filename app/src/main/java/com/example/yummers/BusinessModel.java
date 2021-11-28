package com.example.yummers;

import android.util.Log;

import com.example.yummers.models.Business;
import com.example.yummers.models.Item;
import com.example.yummers.models.Menu;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.ArrayList;

public class BusinessModel implements BusinessMVPContract.Model{

    FirebaseAuth fireAuth;
    FirebaseFirestore firestore;
    FirebaseUser user;
    String docId;
    Menu menu;
    private static BusinessModel singleton = null;

    private BusinessModel() {
        fireAuth = FirebaseAuth.getInstance();
        firestore = FirebaseFirestore.getInstance();
        user = fireAuth.getCurrentUser();

    }
    public static BusinessModel getInstance (){
        if (singleton == null) {
            singleton = new BusinessModel();
        }
        return singleton;
    }

    public void init () {
        retrieveData();
    }
    public void reset() {
        singleton = null;
    }

    public void retrieveData() {

        firestore.collection("restaurants").whereEqualTo("owner", user.getUid())
                .get().addOnSuccessListener(queryDocumentSnapshots -> {
            if (!queryDocumentSnapshots.isEmpty()) {
                for (int i = 0; i < queryDocumentSnapshots.size(); i++) {
                    docId = queryDocumentSnapshots.getDocuments().get(0).getId();
                    Log.e("docId: ", docId);
                    Business b = queryDocumentSnapshots.getDocuments().get(i).toObject(Business.class);
                    menu = b.getMenu() == null ? new Menu(new ArrayList<Item>()) : b.getMenu();
                    Log.e("B-data(" + i + "):", b.toString());
                    Log.e("menu-data: ", menu != null ? menu.toString() : "no data");
                    BusinessPresenter.singleton.init();
                }
            } else {
                Log.e("menu-data: ", "retrieve data failed");
            }
        });
    }

    @Override
    public Menu getMenuData() {
        return menu;
    }

    @Override
    public void updateFirebaseMenu() {
        firestore.collection("restaurants").document(docId).update("menu", menu)
                .addOnSuccessListener(new OnSuccessListener<Void>() {
            @Override
            public void onSuccess(Void unused) {
                Log.e("update cloud menu: ", "trying to update to " + docId);
            }
        });
    }
    public int addItem(Item item) {
        menu.addItem(item);
        return menu.getItems().size();
    }
}
