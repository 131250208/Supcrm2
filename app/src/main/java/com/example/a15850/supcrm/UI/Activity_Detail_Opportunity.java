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

public class Activity_Detail_Opportunity extends AppCompatActivity implements View.OnClickListener{

    private Opportunity opportunity;
    private ArrayList<Customer> customers;
    private EditText opportunity_title;
    private EditText opportunity_estimateamount;
    private EditText opportunity_successrate;
    private EditText opportunity_expecteddate;
    private EditText opportunity_channel;
    private EditText opportunity_acquisitiondate;
    private EditText opportunity_source;
    private EditText opportunity_remark;
    private Spinner opportunity_level;
    private Spinner opportunity_status;
    private Spinner opportunity_customer;
    private Button opportunity_save;

    private TimePickerView pvTime;
    public static void actionStart(Context context, Opportunity opportunity){
        Intent intent=new Intent(context,Activity_Detail_Opportunity.class);
        intent.putExtra("opportunity", Kit.objectToString(opportunity));

        context.startActivity(intent);
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_activity__detail__opportunity);
        Intent intent=getIntent();
        final String opportunity_string=intent.getStringExtra("opportunity");
        opportunity=(Opportunity) Kit.stringToObject(opportunity_string);

        opportunity_title=(EditText)this.findViewById(R.id.opportunity_detail_title);
        opportunity_estimateamount=(EditText)this.findViewById(R.id.opportunity_detail_estimateamount);
        opportunity_successrate=(EditText)this.findViewById(R.id.opportunity_detail_successrate);
        opportunity_expecteddate=(EditText)this.findViewById(R.id.opportunity_detail_expecteddate);
        opportunity_channel=(EditText)this.findViewById(R.id.opportunity_detail_channel);
        opportunity_acquisitiondate=(EditText)this.findViewById(R.id.opportunity_detail_acquisitiondate);
        opportunity_source=(EditText)this.findViewById(R.id.opportunity_detail_source);
        opportunity_remark=(EditText)this.findViewById(R.id.opportunity_detail_remark);
        opportunity_level=(Spinner)this.findViewById(R.id.opportunity_detail_level);
        opportunity_status=(Spinner)this.findViewById(R.id.opportunity_detail_status);
        opportunity_customer=(Spinner)this.findViewById(R.id.opportunity_detail_customer);
        opportunity_save=(Button) this.findViewById(R.id.opportunity_detail_save);

        //时间选择器
        pvTime = new TimePickerView(this, TimePickerView.Type.YEAR_MONTH_DAY);
        //控制时间范围
//        Calendar calendar = Calendar.getInstance();
//        pvTime.setRange(calendar.get(Calendar.YEAR) - 20, calendar.get(Calendar.YEAR));
        pvTime.setTime(new Date());
        pvTime.setCyclic(false);
        pvTime.setCancelable(true);

        DB_API_Service.findAllObjects(handler,"common_customer_json");
        /*设置按钮监听*/
        FloatingActionButton fab_home = (FloatingActionButton) this.findViewById(R.id.fab_home);
        fab_home.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(Activity_Detail_Opportunity.this,MainActivity.class));
            }
        });
        TextView navigation=(TextView)this.findViewById(R.id.position_now);
        //判断是修改还是创建
        if(opportunity.getOpportunityid().equals("")){
            navigation.setText("创建商机");
        }else {
            navigation.setText("修改商机");
        }
        //保存按钮监听
        opportunity_save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String title=opportunity_title.getText().toString();
                if(title.equals("")){
                    Toast.makeText(Activity_Detail_Opportunity.this,"商机标题为必填项！",Toast.LENGTH_SHORT).show();
                }else {
                    opportunity.setOpportunitytitle(title);
                    opportunity.setEstimatedamount(opportunity_estimateamount.getText().toString());
                    opportunity.setBusinesstype(opportunity_level.getSelectedItem().toString());
                    opportunity.setOpportunitystatus(opportunity_status.getSelectedItem().toString());
                    int position_customer=opportunity_customer.getSelectedItemPosition();
                    opportunity.setCustomerid(customers.get(position_customer).getCustomerid());
                    opportunity.setSuccessrate(opportunity_successrate.getText().toString());
                    opportunity.setExpecteddate(opportunity_expecteddate.getText().toString());
                    opportunity.setChannel(opportunity_channel.getText().toString());
                    opportunity.setAcquisitiondate(opportunity_acquisitiondate.getText().toString());
                    opportunity.setOpportunitiessource(opportunity_source.getText().toString());
                    opportunity.setOpportunityremarks(opportunity_remark.getText().toString());
                    MyApp myApp=(MyApp)getApplication();
                    opportunity.setStaffid(myApp.getStaffID());

                    if(opportunity.getOpportunityid().equals("")){
                        DB_API_Service.addObject(handler,opportunity);
                    }else {
                        DB_API_Service.modifyObject(handler,opportunity);
                    }
                }
            }
        });
    }

    private Handler handler=new Handler(){
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what){
                case MessageType.RESULT_MODIFY_OBJECT:
                    String res_mod=(String)msg.obj;
                    Toast.makeText(Activity_Detail_Opportunity.this,res_mod, Toast.LENGTH_SHORT).show();
                    if(res_mod.equals("商机信息修改成功")){
                        setEnabled_edit(false);
                    }
                    break;
                case MessageType.RESULT_ADD_OBJECT:
                    String res_add=(String)msg.obj;
                    Toast.makeText(Activity_Detail_Opportunity.this,res_add, Toast.LENGTH_SHORT).show();
                    if(res_add.equals("商机添加成功")){
                        Activity_Detail_Opportunity.this.finish();
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
                    ArrayAdapter<String> adapter=new ArrayAdapter<String>(Activity_Detail_Opportunity.this,android.R.layout.simple_spinner_item,mItems);
                    adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                    //绑定 Adapter到控件
                    opportunity_customer .setAdapter(adapter);

                    //初始化信息
                    if(opportunity.getOpportunityid().equals("")){
                        setEnabled_edit(true);
                    }else {
                        setEnabled_edit(true);
                        opportunity_title.setText(opportunity.getOpportunitytitle());
                        opportunity_estimateamount.setText(opportunity.getEstimatedamount());

                        //显示合同等级
                        SpinnerAdapter apsAdapter= opportunity_level.getAdapter(); //得到SpinnerAdapter对象
                        int k= apsAdapter.getCount();
                        String opportunityBusinesstype=opportunity.getBusinesstype_chinese();
                        for(int i=0;i<k;i++){
                            if(opportunityBusinesstype.equals(apsAdapter.getItem(i).toString())){
                                opportunity_level.setSelection(i,true);// 默认选中项
                                break;
                            }
                        }
                        //显示合同状态
                        SpinnerAdapter apsAdapter_status= opportunity_status.getAdapter(); //得到SpinnerAdapter对象
                        k= apsAdapter_status.getCount();
                        String opportunitystatus=opportunity.getOpportunitystatus_chinese();
                        for(int i=0;i<k;i++){
                            if(opportunitystatus.equals(apsAdapter_status.getItem(i).toString())){
                                opportunity_status.setSelection(i,true);// 默认选中项
                                break;
                            }
                        }
                        //显示对应上级客户
                        SpinnerAdapter apsAdapter_customer= opportunity_customer.getAdapter(); //得到SpinnerAdapter对象
                        k= apsAdapter_customer.getCount();
                        String opportunityCustomerid=opportunity.getCustomerid();
                        String opportunity_customer_name="无";
                        for(int i=0;i<customers.size();i++){
                            if(customers.get(i).getCustomerid().equals(opportunityCustomerid)){
                                opportunity_customer_name=customers.get(i).getCustomername();
                            }
                        }
                        for(int i=0;i<k;i++){
                            if(opportunity_customer_name.equals(apsAdapter_customer.getItem(i).toString())){
                                opportunity_customer.setSelection(i,true);// 默认选中项
                                break;
                            }
                        }
                        opportunity_successrate.setText(opportunity.getSuccessrate());
                        opportunity_expecteddate.setText(opportunity.getExpecteddate());
                        opportunity_channel.setText(opportunity.getChannel());
                        opportunity_acquisitiondate.setText(opportunity.getAcquisitiondate());
                        opportunity_source.setText(opportunity.getOpportunitiessource());
                        opportunity_remark.setText(opportunity.getOpportunityremarks());
                    }
                    break;
            }
        }
    };
    private  void setEnabled_edit(boolean bool){
        opportunity_title.setEnabled(bool);
        opportunity_estimateamount.setEnabled(bool);
        opportunity_successrate.setEnabled(bool);
        opportunity_expecteddate.setEnabled(bool);

        opportunity_channel.setEnabled(bool);
        opportunity_acquisitiondate.setEnabled(bool);
        opportunity_source.setEnabled(bool);
        opportunity_remark.setEnabled(bool);
        opportunity_save.setEnabled(bool);
        if(bool){
            opportunity_save.setBackgroundColor(Color.rgb(60,141,188));
        }else {
            opportunity_save.setBackgroundColor(Color.rgb(212,212,212));
        }

        if(bool){
            opportunity_expecteddate.setOnClickListener(this);
            opportunity_acquisitiondate.setOnClickListener(this);
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
    public static String getTime(Date date) {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        return format.format(date);
    }
}
