package com.example.a15850.supcrm.UI;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.SpinnerAdapter;
import android.widget.TextView;
import android.widget.Toast;

import com.example.a15850.supcrm.Contact;
import com.example.a15850.supcrm.Customer;
import com.example.a15850.supcrm.Data.DB_API_Service;
import com.example.a15850.supcrm.Enum.MessageType;
import com.example.a15850.supcrm.Kit;
import com.example.a15850.supcrm.MyApp;
import com.example.a15850.supcrm.R;

import java.util.ArrayList;

public class Activity_Detail_Contact extends AppCompatActivity {

    private Contact contact;
    private ArrayList<Customer>  customers;

    private EditText contact_detail_name;
    private EditText contact_detail_mobilephonenumber;
    private EditText contact_detail_fixphonenumber;
    private Spinner contact_detail_customer;

    private EditText contact_detail_age;
    private EditText contact_detail_gender;
    private EditText contact_detail_email;
    private EditText contact_detail_address;
    private EditText contact_detail_qq;
    private EditText contact_detail_postcode;
    private EditText contact_detail_wechat;
    private EditText contact_detail_wangwang;
    private EditText contact_detail_remark;
    
    private Button contact_detail_save;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_activity__detail__contact);

        //初始化信息
        contact_detail_name=(EditText)this.findViewById(R.id.contact_detail_name);
        contact_detail_mobilephonenumber=(EditText)this.findViewById(R.id.contact_detail_mobilephonenumber);
        contact_detail_fixphonenumber=(EditText)this.findViewById(R.id.contact_detail_fixphonenumber);
        contact_detail_customer=(Spinner)this.findViewById(R.id.contact_detail_customer);

        contact_detail_age=(EditText)this.findViewById(R.id.contact_detail_age);
        contact_detail_gender=(EditText)this.findViewById(R.id.contact_detail_gender);
        contact_detail_email=(EditText)this.findViewById(R.id.contact_detail_email);
        contact_detail_address=(EditText)this.findViewById(R.id.contact_detail_address);
        contact_detail_qq=(EditText)this.findViewById(R.id.contact_detail_qq);
        contact_detail_postcode=(EditText)this.findViewById(R.id.contact_detail_postcode);
        contact_detail_wechat=(EditText)this.findViewById(R.id.contact_detail_wechat);
        contact_detail_wangwang=(EditText)this.findViewById(R.id.contact_detail_wangwang);
        contact_detail_remark=(EditText)this.findViewById(R.id.contact_detail_remark);
        
        contact_detail_save=(Button) this.findViewById(R.id.contact_detail_save);

        Intent intent=getIntent();
        final String contact_string=intent.getStringExtra("contact");
        contact=(Contact) Kit.stringToObject(contact_string);

        DB_API_Service.findAllObjects(handler,"common_customer_json");


        /*设置按钮监听*/
        FloatingActionButton fab_home = (FloatingActionButton) this.findViewById(R.id.fab_home);
        fab_home.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(Activity_Detail_Contact.this,MainActivity.class));
            }
        });

        FloatingActionButton fab_edit= (FloatingActionButton) this.findViewById(R.id.fab_edit_contact);
        fab_edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                setEnabled_edit(true);
            }
        });

        FloatingActionButton fab_dial= (FloatingActionButton) this.findViewById(R.id.fab_dial_contact);
        fab_dial.setOnClickListener(new View.OnClickListener() {
            int yourChose;
            @Override
            public void onClick(View view) {
                String mobile=contact.getContactsmobile();
                String tel=contact.getContactstelephone();
                if(!mobile.equals("")&&!tel.equals("")){
                    final String[] mList={mobile,tel};
                    yourChose=-1;
                    AlertDialog.Builder sinChosDia=new AlertDialog.Builder(Activity_Detail_Contact.this);
                    sinChosDia.setTitle("请选择要拨打的号码");
                    sinChosDia.setSingleChoiceItems(mList, 0, new DialogInterface.OnClickListener() {

                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            // TODO Auto-generated method stub
                            yourChose=which;

                        }
                    });
                    sinChosDia.setPositiveButton("拨号", new DialogInterface.OnClickListener() {

                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            // TODO Auto-generated method stub
                            if(yourChose!=-1)
                            {
                                String dial_number=mList[yourChose];
                                dial(dial_number);
                            }
                        }
                    });
                    sinChosDia.create().show();
                }else if(!mobile.equals("")&&tel.equals("")){
                    dial(mobile);
                }
                else if(mobile.equals("")&&!tel.equals("")){
                    dial(tel);
                }else {
                    Toast.makeText(Activity_Detail_Contact.this,"没有联系方式的记录", Toast.LENGTH_SHORT).show();
                }

            }
        });

        final FloatingActionButton fab_del = (FloatingActionButton) this.findViewById(R.id.fab_del_contact);
        fab_del.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DB_API_Service.delObject(handler,"contact_delete_json",contact.getContactsid());
            }
        });
        fab_del.setVisibility(View.INVISIBLE);

        TextView navigation=(TextView)this.findViewById(R.id.position_now);
        //判断是修改还是创建
        if(contact.getContactsid().equals("")){
            navigation.setText("创建联系人");
            fab_edit.setVisibility(View.INVISIBLE);
            fab_dial.setVisibility(View.INVISIBLE);
        }else {
            navigation.setText("联系人详情");
        }

        contact_detail_save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String contact_name=contact_detail_name.getText().toString();
                if(contact_name.equals("")){
                    Toast.makeText(Activity_Detail_Contact.this,"联系人姓名为必填项！",Toast.LENGTH_SHORT);
                }else {
                    contact.setContactsname(contact_name);
                    contact.setContactsmobile(contact_detail_mobilephonenumber.getText().toString());
                    contact.setContactstelephone(contact_detail_fixphonenumber.getText().toString());
                    contact.setCustomername(contact_detail_customer.getSelectedItem().toString());

                    int position=contact_detail_customer.getSelectedItemPosition();
                    contact.setCustomerid(customers.get(position).getCustomerid());

                    contact.setContactsage(contact_detail_age.getText().toString());
                    contact.setContactsgender(contact_detail_gender.getText().toString());
                    contact.setContactsemail(contact_detail_email.getText().toString());
                    contact.setContactsaddress(contact_detail_address.getText().toString());
                    contact.setContactsqq(contact_detail_qq.getText().toString());
                    contact.setContactszipcode(contact_detail_postcode.getText().toString());
                    contact.setContactswechat(contact_detail_wechat.getText().toString());
                    contact.setContactswangwang(contact_detail_wangwang.getText().toString());
                    contact.setContactsremarks(contact_detail_remark.getText().toString());
                    MyApp myApp=(MyApp)getApplication();
                    contact.setStaffid(myApp.getStaffID());

                    if(contact.getContactsid().equals("")){
                        DB_API_Service.addObject(handler,contact);
                    }else {
                        DB_API_Service.modifyObject(handler,contact);
                    }
                }
            }
        });

    }

    private void dial(String dial_number){
        Uri uri= Uri.parse("tel:"+dial_number);
        Intent intent = new Intent(Intent.ACTION_DIAL,uri);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);
    }
    private Handler handler=new Handler(){
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what){
                case MessageType.RESULT_MODIFY_OBJECT:
                    String res_mod=(String)msg.obj;
                    Toast.makeText(Activity_Detail_Contact.this,res_mod, Toast.LENGTH_SHORT).show();
                    if(res_mod.equals("联系人信息修改成功")){
                        setEnabled_edit(false);
                    }
                case MessageType.RESULT_ADD_OBJECT:
                    String res_add=(String)msg.obj;
                    Toast.makeText(Activity_Detail_Contact.this,res_add, Toast.LENGTH_SHORT).show();
                    if(res_add.equals("联系人添加成功")){
                        Activity_Detail_Contact.this.finish();
                    }
                    break;

                case MessageType.RESULT_DEL_OBJECT:
                    String res_del=(String)msg.obj;
                    Toast.makeText(Activity_Detail_Contact.this,res_del, Toast.LENGTH_SHORT).show();
                    if(res_del.equals("删除成功")){
                        Activity_Detail_Contact.this.finish();
                    }
                    break;

                case MessageType.API_COMMON_CUSTOMER_JSON:
                    Object object= Kit.stringToObject((String) msg.obj);
                    customers=(ArrayList<Customer>)object ;
                    ArrayList<String> customers_name=new ArrayList<String>();
                    for(int i=0;i<customers.size();i++){
                        String newCustomer=customers.get(i).getCustomername();
                        customers_name.add(newCustomer);
                    }
//                     建立数据源
                    String[] mItems = new String[customers_name.size()];
                    for(int i=0;i<customers_name.size();i++){
                        mItems[i]=customers_name.get(i);
                    }
                    // 建立Adapter并且绑定数据源
                    ArrayAdapter<String> adapter=new ArrayAdapter<String>(Activity_Detail_Contact.this,android.R.layout.simple_spinner_item,mItems);
                    adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                    //绑定 Adapter到控件
                    contact_detail_customer .setAdapter(adapter);

                    //初始化信息
                    if(contact.getCustomerid().equals("")){
                        setEnabled_edit(true);
                    }else {
                        contact_detail_name.setText(contact.getContactsname());
                        contact_detail_mobilephonenumber.setText(contact.getContactsmobile());
                        contact_detail_fixphonenumber.setText(contact.getContactstelephone());
                        SpinnerAdapter apsAdapter= contact_detail_customer.getAdapter(); //得到SpinnerAdapter对象
                        int k= apsAdapter.getCount();
                        String customername=contact.getCustomername();
                        for(int i=0;i<k;i++){
                            if(customername.equals(apsAdapter.getItem(i).toString())){
                                contact_detail_customer.setSelection(i,true);// 默认选中项
                                break;
                            }
                        }
                        contact_detail_age.setText(contact.getContactsage());
                        contact_detail_gender.setText(contact.getContactsgender());
                        contact_detail_email.setText(contact.getContactsemail());
                        contact_detail_address.setText(contact.getContactsaddress());
                        contact_detail_qq.setText(contact.getContactsqq());
                        contact_detail_postcode.setText(contact.getContactszipcode());
                        contact_detail_wechat.setText(contact.getContactswechat());
                        contact_detail_wangwang.setText(contact.getContactswangwang());
                        contact_detail_remark.setText(contact.getContactsremarks());

                    }
                    break;
            }
        }
    };
    private  void setEnabled_edit(boolean bool){
        contact_detail_name.setEnabled(bool);
        contact_detail_mobilephonenumber.setEnabled(bool);
        contact_detail_fixphonenumber.setEnabled(bool);
        contact_detail_customer.setEnabled(bool);

        contact_detail_age.setEnabled(bool);
        contact_detail_gender.setEnabled(bool);
        contact_detail_email.setEnabled(bool);
        contact_detail_address.setEnabled(bool);
        contact_detail_qq.setEnabled(bool);
        contact_detail_postcode.setEnabled(bool);
        contact_detail_wechat.setEnabled(bool);
        contact_detail_wangwang.setEnabled(bool);
        contact_detail_remark.setEnabled(bool);

        contact_detail_save.setEnabled(bool);
        if(bool){
            contact_detail_save.setBackgroundColor(Color.rgb(60,141,188));
        }else {
            contact_detail_save.setBackgroundColor(Color.rgb(212,212,212));
        }

    }
    public static void  actionStart(Context context, Contact contact){
        Intent intent=new Intent(context,Activity_Detail_Contact.class);
        intent.putExtra("contact", Kit.objectToString(contact));

        context.startActivity(intent);
    }
}
