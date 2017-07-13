package com.example.a15850.supcrm.UI;

import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.a15850.supcrm.Data.MyDbHelper;
import com.example.a15850.supcrm.R;
import com.example.a15850.supcrm.UI.HomeFragment;

public class MainActivity extends AppCompatActivity {

    private MyDbHelper myDbHelper;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if (savedInstanceState==null) {
            getSupportFragmentManager().beginTransaction()
                    .add(R.id.container,new HomeFragment())
                    .commit();
        }

//        //建表
//        myDbHelper=new MyDbHelper(MainActivity.this,"supcrm",null,5);
//        SQLiteDatabase db=myDbHelper.getWritableDatabase();
//        initDb_test(db);
    }

    private void initDb_test(SQLiteDatabase db){
        //清空所有表
        db.delete("crm_admingroup",null,null);
        db.delete("crm_adminuser",null,null);
        db.delete("crm_contacts",null,null);
        db.delete("crm_contract",null,null);
        db.delete("crm_customer",null,null);
        db.delete("crm_department",null,null);
        db.delete("crm_followup",null,null);
        db.delete("crm_log",null,null);
        db.delete("crm_opportunity",null,null);
        db.delete("crm_oprelatedproduct",null,null);
        db.delete("crm_product",null,null);
        db.delete("crm_region",null,null);
        db.delete("crm_remind",null,null);
        db.delete("crm_saletarget",null,null);
        db.delete("crm_sign",null,null);
        db.delete("crm_staff",null,null);
        db.delete("crm_workreport",null,null);

        //初始化测试数据
        ContentValues contentValues=new ContentValues();
        for(int i=0;i<20;i++){
            contentValues.put("customername","客户名称"+i);
            contentValues.put("customertype","一般客户");
            contentValues.put("customerstatus","初访");
            db.insert("crm_customer",null,contentValues);
        }



    }
}
