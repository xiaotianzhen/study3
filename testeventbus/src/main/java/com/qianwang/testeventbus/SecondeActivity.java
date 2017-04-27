package com.qianwang.testeventbus;


import android.os.Bundle;
import android.util.Log;
import android.view.View;

import de.greenrobot.event.EventBus;


public class SecondeActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_seconde);
    }

    public void onClose(View view){

        EventBus.getDefault().post("");

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.i("520it", "" + "*********** 第二个页面销毁***************");
    }
}
