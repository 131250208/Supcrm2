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

import com.bigkoo.pickerview.TimePickerView;
import com.example.a15850.supcrm.Contract;
import com.example.a15850.supcrm.Customer;
import com.example.a15850.supcrm.Data.DB_API_Service;
import com.example.a15850.supcrm.Enum.MessageType;
import com.example.a15850.supcrm.Kit;
import com.example.a15850.supcrm.MyApp;
import com.example.a15850.supcrm.Opportunity;
import com.example.a15850.supcrm.R;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class Activity_Detail_Contract extends AppCompatActivity implements View.OnClickListener{

    private Contract contract;
    private ArrayList<Customer> customers;
    private ArrayList<Opportunity> opportunities;
    
    private EditText contract_number;
    private EditText contract_title;
    private EditText contract_totalamount;
    private EditText contract_startdate;
    private EditText contract_enddate;
    private EditText contract_paymethod;
    private EditText contract_clientcontractor;
    private EditText contract_ourcontractor;
    private EditText contract_signingdate;
    private EditText contract_remark;
    private Spinner contract_level;
    private Spinner contract_status;
    private Spinner contract_customer;
    private Spinner contract_opportunity;
    private Button contract_save;

    TimePickerView pvTime;
    public static void actionStart(Context context, Contract contract){
        Intent intent=new Intent(context,Activity_Detail_Contract.class);
        intent.putExtra("contract", Kit.objectToString(contract));

        context.startActivity(intent);
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_activity__detail__contract);
        Intent intent=getIntent();
        String contract_string=intent.getStringExtra("contract");
        contract=(Contract) Kit.stringToObject(contract_string);
        contract_number=(EditText)this.findViewById(R.id.contract_detail_number);
        contract_title=(EditText)this.findViewById(R.id.contract_detail_title);
        contract_totalamount =(EditText)this.findViewById(R.id.contract_detail_totalamount);
        contract_startdate =(EditText)this.findViewById(R.id.contract_detail_startdate);
        contract_enddate =(EditText)this.findViewById(R.id.contract_detail_enddate);
        contract_paymethod =(EditText)this.findViewById(R.id.contract_detail_paymethod);
        contract_clientcontractor =(EditText)this.findViewById(R.id.contract_detail_clientcontractor);
        contract_ourcontractor =(EditText)this.findViewById(R.id.contract_detail_ourcontract);
        contract_signingdate =(EditText)this.findViewById(R.id.contract_detail_signingdate);
        contract_remark=(EditText)this.findViewById(R.id.contract_detail_remark);
        contract_level=(Spinner)this.findViewById(R.id.contract_detail_level);
        contract_status=(Spinner)this.findViewById(R.id.contract_detail_status);
        contract_customer=(Spinner)this.findViewById(R.id.contract_detail_customer);
        contract_opportunity=(Spinner)this.findViewById(R.id.contract_detail_opportunity);
        contract_save=(Button) this.findViewById(R.id.contract_detail_save);

        //时间选择器
        pvTime = new TimePickerView(this, TimePickerView.Type.YEAR_MONTH_DAY);
        //控制时间范围
//        Calendar calendar = Calendar.getInstance();
//        pvTime.setRange(calendar.get(Calendar.YEAR) - 20, calendar.get(Calendar.YEAR));
        pvTime.setTime(new Date());
        pvTime.setCyclic(false);
        pvTime.setCancelable(true);


        DB_API_Service.findAllObjects(handler,"common_customer_json");
        DB_API_Service.findAllObjects(handler,"common_opportunity_json");
        /*设置按钮监听*/
        FloatingActionButton fab_home = (FloatingActionButton) this.findViewById(R.id.fab_home);
        fab_home.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(Activity_Detail_Contract.this,MainActivity.class));
            }
        });
        FloatingActionButton fab_edit= (FloatingActionButton) this.findViewById(R.id.fab_edit_contract);
        fab_edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                setEnabled_edit(true);
            }
        });

        TextView navigation=(TextView)this.findViewById(R.id.position_now);
        //判断是修改还是创建
        if(contract.getContractid().equals("")){
            navigation.setText("创建合同");
            fab_edit.setVisibility(View.INVISIBLE);
        }else {
            navigation.setText("合同详情");
        }
        //保存按钮监听
        contract_save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String title=contract_title.getText().toString();
                if(title.equals("")){
                    Toast.makeText(Activity_Detail_Contract.this,"合同标题为必填项！",Toast.LENGTH_SHORT).show();
                }else {

                    contract.setContractnumber(contract_number.getText().toString());
                    contract.setContracttitle(title);
                    contract.setTotalamount(contract_totalamount.getText().toString());
                    contract.setContracttype(contract_level.getSelectedItem().toString());
                    contract.setContractstatus(contract_status.getSelectedItem().toString());
                    int position_customer=contract_customer.getSelectedItemPosition();
                    int position_opportunity=contract_opportunity.getSelectedItemPosition();
                    contract.setCustomerid(customers.get(position_customer).getCustomerid());
                    contract.setOpportunityid(opportunities.get(position_opportunity).getOpportunityid());
                    contract.setStartdate(contract_startdate.getText().toString());
                    contract.setEnddate(contract_enddate.getText().toString());
                    contract.setPaymethod(contract_paymethod.getText().toString());
                    contract.setClientcontractor(contract_clientcontractor.getText().toString());
                    contract.setOurcontractor(contract_ourcontractor.getText().toString());
                    contract.setSigningdate(contract_signingdate.getText().toString());
                    contract.setContractremarks(contract_remark.getText().toString());
                    MyApp myApp=(MyApp)getApplication();
                    contract.setStaffid(myApp.getStaffID());

                    if(contract.getContractid().equals("")){
                        DB_API_Service.addObject(handler,contract);
                    }else {
                        DB_API_Service.modifyObject(handler,contract);
                    }
                }
            }
        });
    }

    public static String getTime(Date date) {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        return format.format(date);
    }

    private Handler handler=new Handler(){
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what){
                case MessageType.RESULT_MODIFY_OBJECT:
                    String res_mod=(String)msg.obj;
                    Toast.makeText(Activity_Detail_Contract.this,res_mod, Toast.LENGTH_SHORT).show();
                    if(res_mod.equals("合同信息修改成功")){
                        setEnabled_edit(false);
                    }
                    break;
                case MessageType.RESULT_ADD_OBJECT:
                    String res_add=(String)msg.obj;
                    Toast.makeText(Activity_Detail_Contract.this,res_add, Toast.LENGTH_SHORT).show();
                    if(res_add.equals("合同添加成功")){
                        Activity_Detail_Contract.this.finish();
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
                    ArrayAdapter<String> adapter=new ArrayAdapter<String>(Activity_Detail_Contract.this,android.R.layout.simple_spinner_item,mItems);
                    adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                    //绑定 Adapter到控件
                    contract_customer .setAdapter(adapter);

                    //初始化信息
                    if(contract.getContractid().equals("")){
                        setEnabled_edit(true);
                        SimpleDateFormat df = new SimpleDateFormat("yyyyMMddHHmmss");//设置日期格式
                        String num_contract=df.format(new Date());// new Date()为获取当前系统时间
                        contract_number.setText("CONTRACT"+num_contract);
                    }else {
                        contract_number.setText(contract.getContractnumber());
                        contract_title.setText(contract.getContracttitle());
                        contract_totalamount.setText(contract.getTotalamount());
                        //显示合同等级
                        SpinnerAdapter apsAdapter= contract_level.getAdapter(); //得到SpinnerAdapter对象
                        int k= apsAdapter.getCount();
                        String contracttype=contract.getContracttype_chinese();
                        for(int i=0;i<k;i++){
                            if(contracttype.equals(apsAdapter.getItem(i).toString())){
                                contract_level.setSelection(i,true);// 默认选中项
                                break;
                            }
                        }
                        //显示合同状态
                        SpinnerAdapter apsAdapter_status= contract_status.getAdapter(); //得到SpinnerAdapter对象
                        k= apsAdapter_status.getCount();
                        String contracttype_status=contract.getContractstatus_chinese();
                        for(int i=0;i<k;i++){
                            if(contracttype_status.equals(apsAdapter_status.getItem(i).toString())){
                                contract_status.setSelection(i,true);// 默认选中项
                                break;
                            }
                        }
                        //显示对应客户
                        SpinnerAdapter apsAdapter_customer= contract_customer.getAdapter(); //得到SpinnerAdapter对象
                        k= apsAdapter_customer.getCount();
                        String contracttype_customer=contract.getCustomername();
                        for(int i=0;i<k;i++){
                            if(contracttype_customer.equals(apsAdapter_customer.getItem(i).toString())){
                                contract_customer.setSelection(i,true);// 默认选中项
                                break;
                            }
                        }
                        contract_startdate.setText(contract.getStartdate());
                        contract_enddate.setText(contract.getEnddate());
                        contract_paymethod.setText(contract.getPaymethod());
                        contract_clientcontractor.setText(contract.getClientcontractor());
                        contract_signingdate.setText(contract.getSigningdate());
                        contract_remark.setText(contract.getContractremarks());
                    }
                    break;
                case MessageType.API_COMMON_OPPORTUNITY_JSON:
                    Object object_opportunity= Kit.stringToObject((String) msg.obj);
                    opportunities=(ArrayList<Opportunity>)object_opportunity ;
                    ArrayList<String> opportunities_title=new ArrayList<String>();
                    for(int i=0;i<opportunities.size();i++){
                        String newOpportunity=opportunities.get(i).getOpportunitytitle();
                        opportunities_title.add(newOpportunity);
                    }
//                     建立数据源
                    String[] mItems_opportunity = new String[opportunities_title.size()];
                    for(int i=0;i<opportunities_title.size();i++){
                        mItems_opportunity[i]=opportunities_title.get(i);
                    }
                    // 建立Adapter并且绑定数据源
                    ArrayAdapter<String> adapter_opportunity=new ArrayAdapter<String>(Activity_Detail_Contract.this,android.R.layout.simple_spinner_item,mItems_opportunity);
                    adapter_opportunity.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                    //绑定 Adapter到控件
                    contract_opportunity .setAdapter(adapter_opportunity);

                    //初始化信息
                    if(!contract.getContractid().equals("")){
                        //显示对应商机
                        SpinnerAdapter apsAdapter_opportunity= contract_opportunity.getAdapter(); //得到SpinnerAdapter对象
                        int k= apsAdapter_opportunity.getCount();
                        String contracttype_opportuntiy=contract.getOpportunitytitle();
                        for(int i=0;i<k;i++){
                            if(contracttype_opportuntiy.equals(apsAdapter_opportunity.getItem(i).toString())){
                                contract_opportunity.setSelection(i,true);// 默认选中项
                                break;
                            }
                        }
                    }
                    break;
            }
        }
    };
    private  void setEnabled_edit(boolean bool){
        contract_title.setEnabled(bool);
        contract_totalamount.setEnabled(bool);
        contract_startdate.setEnabled(bool);
        contract_enddate.setEnabled(bool);

        contract_paymethod.setEnabled(bool);
        contract_clientcontractor.setEnabled(bool);
        contract_ourcontractor.setEnabled(bool);
        contract_signingdate.setEnabled(bool);
        contract_remark.setEnabled(bool);
        contract_save.setEnabled(bool);
        //保存按钮
        if(bool){
            contract_save.setBackgroundColor(Color.rgb(60,141,188));
        }else {
            contract_save.setBackgroundColor(Color.rgb(212,212,212));
        }
        //给时间输入框加监听
        if(bool){
            //弹出时间选择器
            contract_startdate.setOnClickListener(this);
            contract_enddate.setOnClickListener(this);
            contract_signingdate.setOnClickListener(this);
        }
    }

    @Override
    public void onClick(View v) {
        pvTime.show();
        final EditText et_click=(EditText)this.findViewById(v.getId());
        //时间选择后回调
        pvTime.setOnTimeSelectListener(new TimePickerView.OnTimeSelectListener() {
            @Override
            public void onTimeSelect(Date date) {
                et_click.setText(getTime(date));
            }
        });
    }
}
