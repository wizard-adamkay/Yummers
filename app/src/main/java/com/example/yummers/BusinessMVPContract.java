package com.example.yummers;

import com.example.yummers.models.Item;
import com.example.yummers.models.Menu;

public interface BusinessMVPContract {
    interface View {
        void displayItemsText(String text);
        void setUpRecyclerView(RecyclerItemAdapter adapter);
    }

    interface Model {
        void updateFirebaseMenu();
        void retrieveData();
        Menu getMenuData();
        int addItem(Item item);
    }
    interface Presenter {
        void init();
        int addItem(Item item);
        void updateMenuDisplay();
        void updateFirebaseMenu();
    }
}
