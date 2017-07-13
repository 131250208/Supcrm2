package com.example.a15850.supcrm.UI;

import android.content.Intent;
import android.graphics.Color;
import android.os.Handler;
import android.os.Message;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TableLayout;
import android.widget.TextView;

import com.example.a15850.supcrm.Customer;
import com.example.a15850.supcrm.Data.DB_API_Service;
import com.example.a15850.supcrm.Enum.MessageType;
import com.example.a15850.supcrm.FollowUpRecord;
import com.example.a15850.supcrm.Kit;
import com.example.a15850.supcrm.Opportunity;
import com.example.a15850.supcrm.R;
import com.example.a15850.supcrm.Staff;
import com.timqi.sectorprogressview.ColorfulRingProgressView;

import java.util.ArrayList;
import java.util.StringTokenizer;

public class Activity_Report_Track extends AppCompatActivity implements AdapterView.OnItemSelectedListener{

    private TableLayout table;
    private ColorfulRingProgressView cpv_customer;
    private ColorfulRingProgressView cpv_opportunity;
    private TextView percent_customer;
    private TextView percent_opportuniy;

    private TextView track_customer;
    private TextView track_opportunity;
    private TextView total_customer;
    private TextView total_opportunity;

    private int count_track_customer=-1;
    private int count_track_opportunity=-1;
    private int count_total_customer=-1;
    private int count_total_opportunity=-1;

    private ReportFilter report_filter;

    private Spinner filter_department;
    private Spinner filter_staff;
    private Spinner filter_year;
    private Spinner filter_month;

    private String date="";
    private String staff_id="";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_activity__report__track);

        table=(TableLayout)this.findViewById(R.id.report_track_table);
        track_customer=(TextView)this.findViewById(R.id.tv_track_customer);
        track_opportunity=(TextView)this.findViewById(R.id.tv_track_opportunity);
        total_customer=(TextView)this.findViewById(R.id.tv_total_customer);
        total_opportunity=(TextView)this.findViewById(R.id.tv_total_opportunity);
        cpv_customer=(ColorfulRingProgressView)this.findViewById(R.id.crpv_TrackRec_customer);
        cpv_opportunity=(ColorfulRingProgressView)this.findViewById(R.id.crpv_TrackRec_opportunity);
        percent_customer=(TextView)this.findViewById(R.id.tvPercent_track_customer);
        percent_opportuniy=(TextView)this.findViewById(R.id.tvPercent_track_opportunity);

        report_filter=(ReportFilter)this.findViewById(R.id.report_filter);

        filter_department=report_filter.getSpinner_department();
        filter_staff=report_filter.getSpinner_staff();
        filter_year=report_filter.getSpinner_year();
        filter_month=report_filter.getSpinner_month();

        filter_department.setOnItemSelectedListener(this);
        filter_staff.setOnItemSelectedListener(this);
        filter_year.setOnItemSelectedListener(this);
        filter_month.setOnItemSelectedListener(this);

        //导航标题
        TextView position=(TextView)this.findViewById(R.id.position_now);
        position.setText("跟进记录");
        //悬浮按钮
        FloatingActionButton fab_home = (FloatingActionButton) this.findViewById(R.id.fab_home);
        fab_home.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(Activity_Report_Track.this,MainActivity.class));
                Snackbar.make(view, "home！", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

//        DB_API_Service.findAllObjects(handler,"common_followup_json");

    }
    private Handler handler =new Handler(){
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what){
                case MessageType.API_COMMON_FOLLOWUP_JSON:
                    ArrayList<FollowUpRecord> followUpRecords=(ArrayList<FollowUpRecord>) Kit.stringToObject((String)msg.obj);
                    ArrayList<Staff> staffs=report_filter.getStaffs();
                    String year=filter_year.getItemAtPosition(0).toString();
                    String month=filter_month.getItemAtPosition(0).toString();
                    if(!date.equals("")){
                        refreshTable(getTracks(staffs,followUpRecords,date));
                    }else {
                        refreshTable(getTracks(staffs,followUpRecords,year+"-"+month));
                    }
                    break;

                case MessageType.API_COMMON_CUSTOMER_JSON:
                    ArrayList<Customer> customers=(ArrayList<Customer>) Kit.stringToObject((String)msg.obj);
                    int count_customer=0;
                    if(!staff_id.equals("")){
                        for(int i=0;i<customers.size();i++){
                            if(customers.get(i).getStaffid().equals(staff_id)){
                                count_customer++;
                            }
                        }
                    }
                    count_total_customer=count_customer;
                    total_customer.setText(String.valueOf(count_customer));
                    if(count_total_customer!=-1&&count_track_customer!=-1){
                        int percent=0;
                        if(count_total_customer==0||count_track_customer==0){
                            percent=0;
                        }else {
                            percent=count_track_customer/count_total_customer;
                        }
                        percent_customer.setText(String.valueOf(percent));
                        cpv_customer.setPercent(percent);
                    }
                    break;
                case MessageType.API_COMMON_OPPORTUNITY_JSON:
                    ArrayList<Opportunity> opportunities=(ArrayList<Opportunity>) Kit.stringToObject((String)msg.obj);
                    int count_opportunity=0;
                    if(!staff_id.equals("")){
                        for(int i=0;i<opportunities.size();i++){
                            if(opportunities.get(i).getStaffid().equals(staff_id)){
                                count_opportunity++;
                            }
                        }
                    }
                    count_total_opportunity=count_opportunity;
                    total_opportunity.setText(String.valueOf(count_opportunity));
                    if(count_total_opportunity!=-1&&count_track_opportunity!=-1){
                        int percent=0;
                        if(count_total_opportunity==0||count_track_opportunity==0){
                            percent=0;
                        }else {
                            percent=count_track_opportunity/count_total_opportunity;
                        }
                        percent_opportuniy.setText(String.valueOf(percent));
                        cpv_opportunity.setPercent(percent);
                    }
                    break;
            }
        }
    };
    /*获取跟进表格的数据源数组*/
    private String[][] getTracks(ArrayList<Staff> staffs, ArrayList<FollowUpRecord> followUpRecords,String date){
        String[][] res=new String[staffs.size()][4];
        for(int i=0;i<staffs.size();i++){
            res[i][0]=staffs.get(i).getName();
            String staff_id=staffs.get(i).getStaffid();
            int count_customer=0;
            int count_opportunity=0;
            for(int j=0;j<followUpRecords.size();j++){
                String time_thisfollowup=followUpRecords.get(j).getCreatetime();
                String date_thisfollowup=time_thisfollowup.split(" ")[0];
                String[] temp=date_thisfollowup.split("-");
                date_thisfollowup=temp[0]+"-"+temp[1];

                if(followUpRecords.get(j).getStaffid().equals(staff_id)
                        &&followUpRecords.get(j).getSourcetype().equals("1")
                        &&date_thisfollowup.equals(date)){
                    count_customer++;
                }else if(followUpRecords.get(j).getStaffid().equals(staff_id)
                        &&followUpRecords.get(j).getSourcetype().equals("2")
                        &&date_thisfollowup.equals(date)){
                    count_opportunity++;
                }
            }
            res[i][1]=String.valueOf(count_customer+count_opportunity);
            res[i][2]=String.valueOf(count_customer);
            res[i][3]=String.valueOf(count_opportunity);

            if(!this.staff_id.equals("")){
                if(staff_id.equals(this.staff_id)){
                    count_track_customer=count_customer;
                    count_track_opportunity=count_opportunity;
                    track_customer.setText(String.valueOf(count_customer));
                    track_opportunity.setText(String.valueOf(count_opportunity));

                    if(count_total_opportunity!=-1&&count_track_opportunity!=-1){
                        int percent=0;
                        if(count_total_opportunity==0||count_track_opportunity==0){
                            percent=0;
                        }else {
                            percent=count_track_opportunity/count_total_opportunity;
                        }
                        percent_opportuniy.setText(String.valueOf(percent));
                        cpv_opportunity.setPercent(percent);
                    }

                    if(count_total_customer!=-1&&count_track_customer!=-1){
                        int percent=0;
                        if(count_total_customer==0||count_track_customer==0){
                            percent=0;
                        }else {
                            percent=count_track_customer/count_total_customer;
                        }
                        percent_customer.setText(String.valueOf(percent));
                        cpv_customer.setPercent(percent);
                    }
                }
            }
        }
        return res;
    }
    private void refreshTable(String[][] track){
        table.removeAllViews();

        for (int i = 0; i < track.length; i++) {
            MyTableRow row=new MyTableRow(this);
            row.setDate(track[i]);
            if(i%2==0){
                row.setBackgroundColor(new Color().rgb(212,212,212));
            }
            table.addView(row);
        }
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        int staff_position=filter_staff.getSelectedItemPosition();
        if(staff_position!=0&&staff_position!=-1) {//如果选择的不是“全部员工”
            Staff staff = report_filter.getStaffs().get(staff_position - 1);
            staff_id = staff.getStaffid();

            String year = filter_year.getSelectedItem().toString();
            String month = filter_month.getSelectedItem().toString();
            date = year + "-" + month;

            DB_API_Service.findAllObjects(handler,"common_followup_json");
            DB_API_Service.findAllObjects(handler,"common_customer_json");
            DB_API_Service.findAllObjects(handler,"common_opportunity_json");
        }
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }
}
