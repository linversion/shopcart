package com.linversion.commondemo;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class RAdapter extends RecyclerView.Adapter<RAdapter.ViewHolder> {

    private List<Item> mData;
    private Context mContext;
    private LayoutInflater mLayoutIft;
    private CheckInterface checkInterface;

    public RAdapter(Context context) {
        mContext = context;
        mLayoutIft = LayoutInflater.from(mContext);
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(mLayoutIft.inflate(R.layout.recycler_item, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull final ViewHolder holder, final int position) {
        final Item item = mData.get(position);

        if (position > 0 && mData.get(position - 1).getGroupPosition() == mData.get(position).getGroupPosition()) {
            holder.shopName.setText(item.getShopName());
            holder.title.setText(item.getTitle());
            holder.content.setText(item.getContent());
            holder.groupLayout.setVisibility(View.GONE);
            holder.divider.setVisibility(View.GONE);
            holder.cCbx.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    item.setChoosed(((CheckBox) v).isChecked());
                    holder.cCbx.setChecked(((CheckBox) v).isChecked());
                    checkInterface.checkChild(item.getGroupPosition(), position, ((CheckBox) v).isChecked());
                }
            });
            holder.cCbx.setChecked(item.isChoosed());
        } else {
            if (position == 0) {
                holder.divider.setVisibility(View.GONE);
            }

            holder.shopName.setText(item.getShopName());
            holder.title.setText(item.getTitle());
            holder.content.setText(item.getContent());

            holder.gCbx.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Log.i("List", "onClick: group" + ((CheckBox) v).isChecked());
                    checkInterface.checkGroup(item.getGroupPosition(), position, ((CheckBox) v).isChecked());
                }
            });
            holder.gCbx.setChecked(item.isGroupChoosed());
            holder.cCbx.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    item.setChoosed(((CheckBox) v).isChecked());
                    holder.cCbx.setChecked(((CheckBox) v).isChecked());
                    checkInterface.checkChild(item.getGroupPosition(), position, ((CheckBox) v).isChecked());
                }
            });
            holder.cCbx.setChecked(item.isChoosed());
        }
    }

    @Override
    public int getItemCount() {
        return mData != null ? mData.size() : 0;
    }

    public void setData(List<Item> items) {
        mData = items;
        notifyDataSetChanged();
    }

    /**
     * 店铺的复选框
     */
    public interface CheckInterface {
        /**
         * 组选框状态改变触发的事件
         *
         * @param groupPosition 组元素的位置
         * @param isChecked     组元素的选中与否
         */
        void checkGroup(int groupPosition, int position, boolean isChecked);

        /**
         * 子选框状态改变触发的事件
         *
         * @param groupPosition 组元素的位置
         * @param childPosition 子元素的位置
         * @param isChecked     子元素的选中与否
         */
        void checkChild(int groupPosition, int position, boolean isChecked);
    }

    public CheckInterface getCheckInterface() {
        return checkInterface;
    }

    public void setCheckInterface(CheckInterface checkInterface) {
        this.checkInterface = checkInterface;
    }

    static class ViewHolder extends RecyclerView.ViewHolder {
        CheckBox gCbx;
        CheckBox cCbx;
        TextView shopName;
        TextView title;
        TextView content;
        LinearLayout groupLayout;
        View divider;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            gCbx = itemView.findViewById(R.id.gcbx);
            shopName = itemView.findViewById(R.id.shop_name);
            title = itemView.findViewById(R.id.title);
            content = itemView.findViewById(R.id.content);
            divider = itemView.findViewById(R.id.divider);
            groupLayout = itemView.findViewById(R.id.group_layout);
            cCbx = itemView.findViewById(R.id.cCbx);
        }
    }
}
