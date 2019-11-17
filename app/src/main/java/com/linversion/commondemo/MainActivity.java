package com.linversion.commondemo;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

//    private ExpandableListView listView;
    private RecyclerView rv;
    private List<Item> mData;
    private RAdapter adapter;
    private CheckBox mCbxCheckAll;
    private Button mDeleteSelected;
    private boolean checkAll = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
        initData();
    }

    private void initData() {
        mData = new ArrayList<>();
        mSelectedList = new ArrayList<>();

        Item a1 = new Item();
        a1.setTitle("aaaa1");
        a1.setContent("aaaa");
        a1.setShopName("shop1");
        a1.setGroupPosition(0);
        mData.add(a1);

        Item a2 = new Item();
        a2.setTitle("bbbb1");
        a2.setContent("bbbb");
        a2.setGroupPosition(1);
        a2.setShopName("shop2");
        mData.add(a2);

        Item a3 = new Item();
        a3.setTitle("cccc1");
        a3.setContent("cccc");
        a3.setGroupPosition(1);
        a3.setShopName("shop2");
        mData.add(a3);

//        for (int i = 0; i < 3; i++) {
//            Item a = new Item();
//            a.setShopName("shop3");
//            a.setTitle("ddd");
//            a.setGroupPosition(2);
//            a.setContent("dddddd");
//            mData.add(a);
//        }
        Item a4 = new Item();
        a4.setGroupPosition(2);
        a4.setShopName("shop3");
        a4.setTitle("dddd2");
        a4.setContent("ddddd");
        mData.add(a4);

        adapter = new RAdapter(this);
        rv.setAdapter(adapter);
        adapter.setData(mData);


        adapter.setCheckInterface(new RAdapter.CheckInterface() {
            @Override
            public void checkGroup(int groupPosition, int position, boolean isChecked) {
                mData.get(position).setGroupChoosed(isChecked);
                for (int i = position; i < mData.size(); i++) {
                    if (mData.get(i).getGroupPosition() != groupPosition) {
                        break;
                    }
                    mData.get(i).setChoosed(isChecked);
                }
                adapter.notifyDataSetChanged();
            }

            @Override
            public void checkChild(int groupPosition, int position, boolean isChecked) {
                Log.i("Main", "checkChild: g" + groupPosition + "p:" + position);
                boolean allChildSameState = true;
                int firstOne = 0;
                boolean isFirstOne = true;
                Item item = mData.get(position);
                item.setChoosed(isChecked);

                for (int i = 0; i < mData.size(); i++) {
                    if (mData.get(i).getGroupPosition() == groupPosition) {
                        if (isFirstOne) {
                            firstOne = i;
                            isFirstOne = false;
                        }
                        if (mData.get(i).isChoosed() != isChecked) {
                            allChildSameState = false;
                            break;
                        }
                    }
                }
                if (allChildSameState) {
                    mData.get(firstOne).setGroupChoosed(isChecked);
                } else {
                    mData.get(firstOne).setGroupChoosed(false);
                }
                adapter.notifyDataSetChanged();
            }
        });
    }

    private void initView() {
        rv = findViewById(R.id.rv);
        rv.setLayoutManager(new LinearLayoutManager(this));
        mCbxCheckAll = findViewById(R.id.cbx_check_all);
        mCbxCheckAll.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                checkAll = isChecked;
                doCheckAll();
            }
        });
        mDeleteSelected = findViewById(R.id.btn_delete_selected);
        mDeleteSelected.setOnClickListener(this);
    }

    private void doCheckAll() {
        for (int i = 0; i < mData.size(); i++) {
            Item item = mData.get(i);
            item.setGroupChoosed(checkAll);
            item.setChoosed(checkAll);
        }
        adapter.notifyDataSetChanged();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_delete_selected:
                if (checkAll) {
                    mData.clear();
                    adapter.notifyDataSetChanged();
                    break;
                }

                for (int i = 0; i < mData.size(); i++) {
                    Item item = mData.get(i);
                    if (item.isChoosed()) {
                        mData.remove(item);
                    }
                }
                adapter.notifyDataSetChanged();
                break;
        }
    }
}
