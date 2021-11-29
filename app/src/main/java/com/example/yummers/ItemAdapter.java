package com.example.yummers;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.example.yummers.models.Item;
import com.example.yummers.models.Order;

import java.util.ArrayList;

public class ItemAdapter extends ArrayAdapter<Item> {

    private final Context context;
    private final ArrayList<Item> items;

    public ItemAdapter(Context context, ArrayList<Item> items) {
        super(context, 0, items);
        this.context = context;
        this.items = items;
    }
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // Get the data item for this position
        Item item = getItem(position);
        Log.d("test", item.toString());
        // Check if an existing view is being reused, otherwise inflate the view
        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.order_row, parent, false);
        }
        // Lookup view for data population
        TextView tvName = (TextView) convertView.findViewById(R.id.tvOrderNumber);
        TextView tvPrice = (TextView) convertView.findViewById(R.id.tvOrderQuantity);
        // Populate the data into the template view using the data object
        tvName.setText(item.getName());
        tvPrice.setText("$" + item.getPrice());
        // Return the completed view to render on screen
        return convertView;
    }
}
