package com.qianwang.testeventbus;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;


public class MainActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


    }

    public void onBtn(View view){

        startActivity(new Intent(MainActivity.this,SecondeActivity.class));
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

        Log.i("520it", "" + "************ 第一个界面销毁 **************");
    }
}
