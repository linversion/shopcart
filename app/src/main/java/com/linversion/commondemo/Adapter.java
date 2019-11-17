package com.linversion.commondemo;


import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class Adapter extends RecyclerView.Adapter<RecyclerView.ViewHolder>{


    private List<bean> data;

    public static final int GROUP = 1;
    public static final int CHILD = 2;

    public Adapter(List<bean> data) {
        this.data = data;
    }

    @Override
    public int getItemViewType(int position) {
        return 1;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return null;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        int count = 0;
        if (null != data) {
            count = data.size();
            for (int i = 0; i < data.size(); i++) {
                count += data.get(i).getItems().size();
            }
        }
        return count;
    }

    static class GroupViewHolder extends RecyclerView.ViewHolder {

        CheckBox gCbx;
        TextView shopName;

        public GroupViewHolder(@NonNull View itemView) {
            super(itemView);
            gCbx = itemView.findViewById(R.id.gcbx);
            shopName = itemView.findViewById(R.id.shop_name);
        }
    }

    static class ChildViewHolder extends RecyclerView.ViewHolder {
        CheckBox cCbx;
        ImageView img;
        TextView title;
        TextView content;

        public ChildViewHolder(@NonNull View itemView) {
            super(itemView);
            cCbx = itemView.findViewById(R.id.cCbx);
            img = itemView.findViewById(R.id.img);
            title = itemView.findViewById(R.id.title);
            content = itemView.findViewById(R.id.content);
        }
    }
}
