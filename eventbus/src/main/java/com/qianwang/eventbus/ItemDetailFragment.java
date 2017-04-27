package com.qianwang.eventbus;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.qianwang.eventbus.bean.Item;

import de.greenrobot.event.EventBus;
import de.greenrobot.event.Subscribe;


/**
 * Created by sky on 2017/4/26.
 */

public class ItemDetailFragment extends Fragment {

    private TextView mTextView;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EventBus.getDefault().register(this);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.detail_item, container,false);
        mTextView = (TextView) view.findViewById(R.id.tv_show);
        return view;
    }



    //list被点击触发的事件
    @Subscribe
    public void onEventMainThread(Item item) {

        mTextView.setText(item.content.toString());
    }
}
