package com.example.yummers;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.yummers.models.Business;

import java.util.ArrayList;

public class SearchAdapter extends RecyclerView.Adapter<SearchAdapter.ViewHolder> {
    private ArrayList<Business> bList;
    private RestaurantClickListener listener;
    Context context;

    public SearchAdapter(Context c, ArrayList<Business> businesses, RestaurantClickListener l) {
        bList = businesses;
        context = c;
        listener = l;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View searchView = inflater.inflate(R.layout.rest_row, parent, false);
        ViewHolder vh = new ViewHolder(searchView);
        return vh;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Business b = bList.get(position);

        TextView tvN = holder.tvN;
        TextView tvA = holder.tvA;
        tvN.setText(b.getName());
        tvA.setText(b.getAddress());
    }

    @Override
    public int getItemCount() {
        return bList.size();
    }

    public interface RestaurantClickListener {
        void onClick(View v, int position);
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        public TextView tvN;
        public TextView tvA;


        public ViewHolder(View itemView) {
            super(itemView);

            tvN = (TextView) itemView.findViewById(R.id.tvName);
            tvA = (TextView) itemView.findViewById(R.id.tvAddress);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            listener.onClick(v, getAdapterPosition());
        }
    }
}
