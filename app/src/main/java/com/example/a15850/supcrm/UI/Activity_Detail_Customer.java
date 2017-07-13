package com.example.a15850.supcrm.UI;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.SpinnerAdapter;
import android.widget.TextView;
import android.widget.Toast;

import com.example.a15850.supcrm.Customer;
import com.example.a15850.supcrm.Data.DB_API_Service;
import com.example.a15850.supcrm.Enum.MessageType;
import com.example.a15850.supcrm.Kit;
import com.example.a15850.supcrm.MyApp;
import com.example.a15850.supcrm.R;

import java.util.ArrayList;

public class Activity_Detail_Customer extends AppCompatActivity {

    private ArrayList<Customer> customers;
    private Customer customer;
    private EditText customer_name;
    private EditText customer_profile;
    private EditText customer_source;
    private Spinner customer_level;
    private Spinner customer_status;
    private Spinner customer_parentscustomer;
    private EditText customer_size;
    private EditText customer_phonenumber;
    private EditText customer_email;
    private EditText customer_website;
    private EditText customer_address;
    private EditText customer_zipcode;
    private EditText customer_remark;
    private Button customer_save;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_activity__detail__customer);

        Intent intent=getIntent();
        final String customer_string=intent.getStringExtra("customer");
        customer=(Customer)Kit.stringToObject(customer_string);

        customer_name=(EditText)this.findViewById(R.id.customer_detail_name);
        customer_profile=(EditText)this.findViewById(R.id.customer_detail_profile);
        customer_source=(EditText)this.findViewById(R.id.customer_detail_resource);
        customer_size=(EditText)this.findViewById(R.id.customer_detail_size);
        customer_phonenumber=(EditText)this.findViewById(R.id.customer_detail_telephone);
        customer_email=(EditText)this.findViewById(R.id.customer_detail_email);
        customer_website=(EditText)this.findViewById(R.id.customer_detail_website);
        customer_address=(EditText)this.findViewById(R.id.customer_detail_address);
        customer_zipcode=(EditText)this.findViewById(R.id.customer_detail_zipcode);
        customer_remark=(EditText)this.findViewById(R.id.customer_detail_remark);
        customer_save=(Button) this.findViewById(R.id.customer_detail_save);
        customer_level=(Spinner)this.findViewById(R.id.customer_detail_level);
        customer_status=(Spinner)this.findViewById(R.id.customer_detail_status);
        customer_parentscustomer=(Spinner)this.findViewById(R.id.customer_detail_parentcustomer);

        DB_API_Service.findAllObjects(handler,"common_customer_json");

        /*设置按钮监听*/
        FloatingActionButton fab_home = (FloatingActionButton) this.findViewById(R.id.fab_home);
        fab_home.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(Activity_Detail_Customer.this,MainActivity.class));
            }
        });

        TextView navigation=(TextView)this.findViewById(R.id.position_now);
        //判断是修改还是创建
        if(customer.getCustomerid().equals("")){
            navigation.setText("创建客户");
        }else {
            navigation.setText("修改客户");
        }

        customer_save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String customername=customer_name.getText().toString();
                if(customername.equals("")){
                    Toast.makeText(Activity_Detail_Customer.this,"客户姓名为必填项！",Toast.LENGTH_SHORT).show();
                }else {
                    customer.setCustomername(customername);
                    customer.setProfile(customer_profile.getText().toString());
                    customer.setCustomertype(customer_level.getSelectedItem().toString());
                    customer.setCustomerstatus(customer_status.getSelectedItem().toString());
                    if(!customer_parentscustomer.getSelectedItem().toString().equals("无")){
                        int position_parentcustomer=customer_parentscustomer.getSelectedItemPosition();
                        customer.setParentcustomerid(customers.get(position_parentcustomer).getCustomerid());
                    }
                    customer.setCustomersource(customer_source.getText().toString());
                    customer.setSize(customer_size.getText().toString());
                    customer.setTelephone(customer_phonenumber.getText().toString());
                    customer.setEmail(customer_email.getText().toString());
                    customer.setWebsite(customer_website.getText().toString());
                    customer.setAddress(customer_address.getText().toString());
                    customer.setZipcode(customer_zipcode.getText().toString());
                    customer.setCustomerremarks(customer_remark.getText().toString());
                    MyApp myApp=(MyApp)getApplication();
                    customer.setStaffid(myApp.getStaffID());

                    if(customer.getCustomerid().equals("")){
                        DB_API_Service.addObject(handler,customer);
                    }else {
                        DB_API_Service.modifyObject(handler,customer);
                    }
                }
            }
        });
    }
    private  void setEnabled_edit(boolean bool){
        customer_name.setEnabled(bool);
        customer_profile.setEnabled(bool);
        customer_source.setEnabled(bool);
        customer_size.setEnabled(bool);

        customer_phonenumber.setEnabled(bool);
        customer_email.setEnabled(bool);
        customer_website.setEnabled(bool);
        customer_address.setEnabled(bool);
        customer_zipcode.setEnabled(bool);
        customer_save.setEnabled(bool);
        customer_remark.setEnabled(bool);
        if(bool){
            customer_save.setBackgroundColor(Color.rgb(60,141,188));
        }else {
            customer_save.setBackgroundColor(Color.rgb(212,212,212));
        }

    }
    private Handler handler=new Handler(){
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what){
                case MessageType.RESULT_MODIFY_OBJECT:
                    String res_mod=(String)msg.obj;
                    Toast.makeText(Activity_Detail_Customer.this,res_mod, Toast.LENGTH_SHORT).show();
                    if(res_mod.equals("客户信息修改成功")){
                        setEnabled_edit(false);
                    }
                    break;
                case MessageType.RESULT_ADD_OBJECT:
                    String res_add=(String)msg.obj;
                    Toast.makeText(Activity_Detail_Customer.this,res_add, Toast.LENGTH_SHORT).show();
                    if(res_add.equals("客户添加成功")){
                        Activity_Detail_Customer.this.finish();
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
                    String[] mItems = new String[customers_name.size()+1];
                    mItems[customers_name.size()]="无";
                    for(int i=0;i<customers_name.size();i++){
                        mItems[i]=customers_name.get(i);
                    }

                    // 建立Adapter并且绑定数据源
                    ArrayAdapter<String> adapter=new ArrayAdapter<String>(Activity_Detail_Customer.this,android.R.layout.simple_spinner_item,mItems);
                    adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                    //绑定 Adapter到控件
                    customer_parentscustomer .setAdapter(adapter);

                    //初始化信息
                    if(customer.getCustomerid().equals("")){
                        setEnabled_edit(true);
                    }else {
                        setEnabled_edit(true);
                        customer_name.setText(customer.getCustomername());
                        customer_profile.setText(customer.getProfile());

                        //显示合同等级
                        SpinnerAdapter apsAdapter= customer_level.getAdapter(); //得到SpinnerAdapter对象
                        int k= apsAdapter.getCount();
                        String customertype=customer.getCustomertype_chinese();
                        for(int i=0;i<k;i++){
                            if(customertype.equals(apsAdapter.getItem(i).toString())){
                                customer_level.setSelection(i,true);// 默认选中项
                                break;
                            }
                        }
                        //显示合同状态
                        SpinnerAdapter apsAdapter_status= customer_status.getAdapter(); //得到SpinnerAdapter对象
                        k= apsAdapter_status.getCount();
                        String customerstatus=customer.getCustomerstatus_chinese();
                        for(int i=0;i<k;i++){
                            if(customerstatus.equals(apsAdapter_status.getItem(i).toString())){
                                customer_status.setSelection(i,true);// 默认选中项
                                break;
                            }
                        }
                        //显示对应上级客户
                        SpinnerAdapter apsAdapter_customer= customer_parentscustomer.getAdapter(); //得到SpinnerAdapter对象
                        k= apsAdapter_customer.getCount();
                        String parentscustomer_id=customer.getParentcustomerid();
                        String customer_parent="无";
                        for(int i=0;i<customers.size();i++){
                            if(customers.get(i).getCustomerid().equals(parentscustomer_id)){
                                customer_parent=customers.get(i).getCustomername();
                            }
                        }
                        for(int i=0;i<k;i++){
                            if(customer_parent.equals(apsAdapter_customer.getItem(i).toString())){
                                customer_parentscustomer.setSelection(i,true);// 默认选中项
                                break;
                            }
                        }
                        customer_source.setText(customer.getCustomersource());
                        customer_size.setText(customer.getSize());
                        customer_phonenumber.setText(customer.getTelephone());
                        customer_email.setText(customer.getEmail());
                        customer_website.setText(customer.getWebsite());
                        customer_address.setText(customer.getAddress());
                        customer_zipcode.setText(customer.getZipcode());
                        customer_remark.setText(customer.getCustomerremarks());
                    }
                    break;
            }
        }
    };
    public static void actionStart(Context context, Customer customer){
        Intent intent=new Intent(context,Activity_Detail_Customer.class);
        intent.putExtra("customer", Kit.objectToString(customer));

        context.startActivity(intent);
    }
}
