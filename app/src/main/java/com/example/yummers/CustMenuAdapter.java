package com.example.yummers;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.yummers.models.Business;
import com.example.yummers.models.Item;

import java.util.ArrayList;

public class CustMenuAdapter extends RecyclerView.Adapter<CustMenuAdapter.ViewHolder>{
    private ArrayList<Item> iList;
    private CustMenuAdapter.RestaurantClickListener listener;
    Context context;

    public CustMenuAdapter(Context c, ArrayList<Item> foods, CustMenuAdapter.RestaurantClickListener l) {
        iList = foods;
        context = c;
        listener = l;
    }

    @NonNull
    @Override
    public CustMenuAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View searchView = inflater.inflate(R.layout.rest_row, parent, false);
        CustMenuAdapter.ViewHolder vh = new CustMenuAdapter.ViewHolder(searchView);
        return vh;
    }

    @Override
    public void onBindViewHolder(@NonNull CustMenuAdapter.ViewHolder holder, int position) {
        Item i = iList.get(position);

        TextView tvN = holder.tvN;
        TextView tvA = holder.tvA;
        tvN.setText(i.getName());
        tvA.setText(Double.toString(i.getPrice()));
    }

    @Override
    public int getItemCount() {
        return iList.size();
    }

    public interface RestaurantClickListener {
        void onClick(View v, int position);
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        public TextView tvN;
        public TextView tvA;


        public ViewHolder(View itemView) {
            super(itemView);

            tvN = (TextView) itemView.findViewById(R.id.tvBig);
            tvA = (TextView) itemView.findViewById(R.id.tvSmall);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            listener.onClick(v, getAdapterPosition());
        }
    }
}
