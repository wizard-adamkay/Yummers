package com.example.yummers;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.example.yummers.models.Order;

import java.util.ArrayList;

public class OrderArrayAdapter extends ArrayAdapter<Order> {
    private final Context context;
    private final ArrayList<Order> orders;

    public OrderArrayAdapter(Context context, ArrayList<Order> orders){
        super(context, 0, orders);
        this.context = context;
        this.orders = orders;
    }
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // Get the data item for this position
        Order order = getItem(position);
        // Check if an existing view is being reused, otherwise inflate the view
        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.order_row, parent, false);
        }
        // Lookup view for data population
        TextView tvName = (TextView) convertView.findViewById(R.id.tvOrderNumber);
        TextView tvHome = (TextView) convertView.findViewById(R.id.tvOrderQuantity);
        // Populate the data into the template view using the data object
        tvName.setText(order.getItems().toString());
        tvHome.setText("$" + order.getTotalCost());
        // Return the completed view to render on screen
        return convertView;
    }
}
