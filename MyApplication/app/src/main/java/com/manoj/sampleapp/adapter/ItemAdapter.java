package com.manoj.sampleapp.adapter;

/**
 * Created by manoj on 1/11/17.
 */


import android.support.v7.widget.AppCompatTextView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.manoj.sampleapp.R;
import com.manoj.sampleapp.model.ItemModel;

import java.util.List;

public class ItemAdapter extends RecyclerView.Adapter<ItemAdapter.MyViewHolder> {

    public interface OnItemClickListener {
        void onUserItemClicked(ItemModel itemModel);
    }

    private List<ItemModel> itemModelList;
    private OnItemClickListener onItemClickListener;


    public ItemAdapter(List<ItemModel> itemModelList) {
        this.itemModelList = itemModelList;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.recylerview_row, parent, false);

        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        final ItemModel itemModel = itemModelList.get(position);
        holder.title.setText(itemModel.getTitle());
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (ItemAdapter.this.onItemClickListener != null) {
                    ItemAdapter.this.onItemClickListener.onUserItemClicked(itemModel);
                }
            }
        });
    }
    public void setOnItemClickListener (OnItemClickListener onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
    }

    @Override
    public int getItemCount() {
        return itemModelList.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        public AppCompatTextView title;

        public MyViewHolder(View view) {
            super(view);
            title = (AppCompatTextView) view.findViewById(R.id.title);
        }
    }
}
