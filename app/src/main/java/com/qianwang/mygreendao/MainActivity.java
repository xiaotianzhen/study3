package com.qianwang.mygreendao;

import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.qianwang.mygreendao.bean.User;
import com.qianwang.mygreendao.greendao.gen.DaoMaster;
import com.qianwang.mygreendao.greendao.gen.DaoSession;
import com.qianwang.mygreendao.greendao.gen.UserDao;

import org.greenrobot.greendao.query.QueryBuilder;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    UserDao userDao;
    private EditText etId;
    private EditText etName;
    private Button btnAdd;
    private Button btnDelete;
    private Button btnQuery;
    private TextView tvQuery;

    public void initView() {

        etId = (EditText) findViewById(R.id.etId);
        etName = (EditText) findViewById(R.id.etName);
        btnAdd = (Button) findViewById(R.id.btnAdd);
        btnDelete = (Button) findViewById(R.id.btnDelete);
        btnQuery = (Button) findViewById(R.id.btnQuery);
        tvQuery = (TextView) findViewById(R.id.tvQuery);

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initView();
        initHelper();


        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String id = etId.getText().toString();
                String name = etName.getText().toString();

                if (!TextUtils.isEmpty(id) && !TextUtils.isEmpty(name)) {
                    QueryBuilder qb = userDao.queryBuilder();
                    ArrayList<User> list = (ArrayList<User>) qb.where(UserDao.Properties.Id.eq(id)).list();
                    if (list.size() > 0) {
                        Toast.makeText(getApplicationContext(), "主键重复", Toast.LENGTH_SHORT).show();
                    } else {
                        userDao.insert(new User(Long.valueOf(id), name));
                        Toast.makeText(getApplicationContext(), "插入数据成功", Toast.LENGTH_SHORT).show();
                    }
                } else {
                    if (TextUtils.isEmpty(id) && !TextUtils.isEmpty(name)) {
                        Toast.makeText(getApplicationContext(), "id为空", Toast.LENGTH_SHORT).show();
                    }
                    if (!TextUtils.isEmpty(id) && TextUtils.isEmpty(name)) {
                        Toast.makeText(getApplicationContext(), "name为空", Toast.LENGTH_SHORT).show();
                    }
                    if (TextUtils.isEmpty(id) && TextUtils.isEmpty(name)) {
                        Toast.makeText(getApplicationContext(), "请输入信息", Toast.LENGTH_SHORT).show();
                    }

                }
                etId.setText("");
                etName.setText("");
            }
        });
        btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String id = etId.getText().toString();
                if (!TextUtils.isEmpty(id)) {
                    userDao.deleteByKey(Long.valueOf(id));
                    QueryBuilder qb = userDao.queryBuilder();
                    ArrayList<User> list = (ArrayList<User>) qb.where(UserDao.Properties.Id.eq(id)).list();
                    if (list.size() < 1) {
                        Toast.makeText(getApplicationContext(), "删除数据成功", Toast.LENGTH_SHORT).show();
                        etId.setText("");
                        etName.setText("");
                    } else {
                        Toast.makeText(getApplicationContext(), "id为空", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });

        btnQuery.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String id = etId.getText().toString();

                if (!TextUtils.isEmpty(id)) {
                    QueryBuilder qb = userDao.queryBuilder();
                    ArrayList<User> list = (ArrayList<User>) qb.where(UserDao.Properties.Id.eq(id)).list();

                    if (list.size() > 0) {
                        String text = "";

                        for (User user : list) {
                            text = text + "\r\n" + user.getName();
                        }
                        tvQuery.setText(text);
                    } else {
                        tvQuery.setText("");
                        Toast.makeText(getApplicationContext(), "不存在该数据", Toast.LENGTH_SHORT).show();
                    }
                    etId.setText("");
                    etName.setText("");
                } else {
                    Toast.makeText(getApplicationContext(), "id为空", Toast.LENGTH_SHORT).show();
                }
            }
        });


    }

    //初始化数据库相关
    private void initHelper() {

        DaoMaster.DevOpenHelper helper = new DaoMaster.DevOpenHelper(this, "recluse-db", null);
        SQLiteDatabase db = helper.getWritableDatabase();
        DaoMaster daoMaster = new DaoMaster(db);
        DaoSession daoSession = daoMaster.newSession();
        userDao = daoSession.getUserDao();

    }
}
