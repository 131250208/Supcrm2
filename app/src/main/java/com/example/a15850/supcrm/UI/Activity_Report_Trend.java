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
import android.widget.LinearLayout;
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
import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;
import com.github.mikephil.charting.interfaces.datasets.ILineDataSet;

import java.text.DateFormat;
import java.text.Format;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;

public class Activity_Report_Trend extends AppCompatActivity implements AdapterView.OnItemSelectedListener{

    private LineChart chart;
    private TableLayout table;

    private ReportFilter report_filter;

    private Spinner filter_department;
    private Spinner filter_staff;
    private Spinner filter_year;
    private Spinner filter_month;

    private String staff_id="";
    private String year;
    private String date_head="";

    String[][] data;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_activity__report__trend);

        chart = (LineChart) this.findViewById(R.id.chart);
        table=(TableLayout)this.findViewById(R.id.report_trend_table);
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
        position.setText("新增趋势");

        //悬浮按钮
        FloatingActionButton fab_home = (FloatingActionButton) this.findViewById(R.id.fab_home);
        fab_home.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(Activity_Report_Trend.this,MainActivity.class));
                Snackbar.make(view, "home！", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });


        String[][] trend={
                {"01-01","1","2","3"},
                {"01-02","2","3","4"},
                {"01-03","4","2","3"},
                {"01-04","8","3","0"},
                {"01-05","9","6","9"},
                {"01-06","1","1","1"},
                {"01-07","1","1","1"},
                {"01-08","1","1","1"},
                {"01-09","1","1","1"},
                {"01-10","1","1","1"},
                {"01-11","1","1","1"},
                {"01-12","1","1","1"},
                {"01-13","1","1","1"},
                {"01-14","1","1","1"},
                {"01-15","1","1","1"},
                {"01-16","1","1","1"},
                {"01-17","1","1","1"},
                {"01-18","1","1","1"},
                {"01-19","1","1","1"},
                {"01-20","1","1","1"},
                {"01-21","1","1","1"},
                {"01-22","1","1","1"},
                {"01-23","1","1","1"},
                {"01-24","1","1","1"},
                {"01-25","1","1","1"},
                {"01-26","1","1","1"},
                {"01-27","1","1","1"},
                {"01-28","1","1","1"},
                {"01-29","1","1","1"},
                {"01-30","1","1","1"},
                {"01-31","1","1","1"},
        };

        refreshLineChart(trend);
        refreshTable(trend);
    }

    private void refreshTable(String[][] trend){
        table.removeAllViews();
        for (int i = 0; i < trend.length; i++) {
            MyTableRow row=new MyTableRow(this);
            row.setDate(trend[i]);
            if(i%2==0){
                row.setBackgroundColor(new Color().rgb(212,212,212));
            }
            table.addView(row);
        }
    }
    private void refreshLineChart(String[][] trend) {
        //横坐标-日期
        ArrayList<String> xVals = new ArrayList<String>();

        //三个指标的新增趋势数组
        ArrayList<Entry> valsOpportunity = new ArrayList<Entry>();
        ArrayList<Entry> valsCustomer = new ArrayList<Entry>();
        ArrayList<Entry> valsTrack = new ArrayList<Entry>();

        float cal_opp=0;float cal_cus=0;float cal_tra=0;

        for (int i = 0; i < trend.length; i++) {
            //添加日期
            String date = trend[i][0];
            xVals.add(date);

            //添加三个新增数值
            float f_opp=Float.parseFloat(trend[i][1]);
            cal_opp+=f_opp;
            Entry c1e_opp = new Entry(cal_opp, i);
            valsOpportunity.add(c1e_opp);

            float f_cus=Float.parseFloat(trend[i][2]);
            cal_cus+=f_cus;
            Entry c1e_cus = new Entry(cal_cus, i);
            valsCustomer.add(c1e_cus);

            float f_tra=Float.parseFloat(trend[i][3]);
            cal_tra+=f_tra;
            Entry c1e_tra = new Entry(cal_tra, i);
            valsTrack.add(c1e_tra);
        }

        LineDataSet setOpportunity = new LineDataSet(valsOpportunity, "商机");
        setOpportunity.setAxisDependency(YAxis.AxisDependency.LEFT);
        setOpportunity.setCircleRadius(0);
        setOpportunity.setValueTextSize(0);
        setOpportunity.setColor(Color.rgb(235,189,6));
        setOpportunity.setLineWidth(2.5f);

        LineDataSet setCustomer = new LineDataSet(valsCustomer, "客户");
        setCustomer.setAxisDependency(YAxis.AxisDependency.LEFT);
        setCustomer.setCircleRadius(0);
        setCustomer.setValueTextSize(0);
        setCustomer.setColor(Color.rgb(0,132,153));
        setCustomer.setLineWidth(2.5f);

        LineDataSet setTrack = new LineDataSet(valsTrack, "拜访");
        setTrack.setAxisDependency(YAxis.AxisDependency.LEFT);
        setTrack.setCircleRadius(0);
        setTrack.setValueTextSize(0);
        setTrack.setColor(Color.rgb(16,184,63));
        setTrack.setLineWidth(2.5f);

        // use the interface ILineDataSet
        ArrayList<ILineDataSet> dataSets = new ArrayList<ILineDataSet>();
        dataSets.add(setOpportunity);
        dataSets.add(setCustomer);
        dataSets.add(setTrack);

        LineData data = new LineData(xVals, dataSets);
        chart.setData(data);

        chart.setDrawGridBackground(false);
        chart.invalidate(); // refresh



    }

    private Handler handler=new Handler(){
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what){
                case MessageType.API_COMMON_CUSTOMER_JSON:
                    ArrayList<Customer> customers=(ArrayList<Customer>) Kit.stringToObject((String)msg.obj);

                    if(!staff_id.equals("")){
                        for(int i =0;i<customers.size();i++){
                            if(!customers.get(i).getStaffid().equals(staff_id)){
                                customers.remove(i);
                                i--;
                            }
                        }
                    }

                    for(int i=0;i<data.length;i++){
                        String date=year+"-"+data[i][0];
                        int count=0;
                        for(int j=0;j<customers.size();j++){
                            String[] splitgroup=customers.get(j).getCreatedate().split(" ");
                            String createdate=splitgroup[0];
                            Log.i("wyc", "handleMessage: customer"+date+"=="+createdate);
                            if(createdate.equals(date)){
                                count++;
                            }
                        }
                        data[i][2]=String.valueOf(count);
                    }
                    refreshLineChart(data);
                    refreshTable(data);
                    break;
                case MessageType.API_COMMON_OPPORTUNITY_JSON:
                    ArrayList<Opportunity> opportunities=(ArrayList<Opportunity>) Kit.stringToObject((String)msg.obj);

                    if(!staff_id.equals("")){
                        for(int i =0;i<opportunities.size();i++){
                            if(!opportunities.get(i).getStaffid().equals(staff_id)){
                                opportunities.remove(i);
                                i--;
                            }
                        }
                    }

                    for(int i=0;i<data.length;i++){
                        String date=year+"-"+data[i][0];
                        int count=0;
                        for(int j=0;j<opportunities.size();j++){
                            String[] splitgroup=opportunities.get(j).getAcquisitiondate().split(" ");
                            String createdate=splitgroup[0];
                            Log.i("wyc", "handleMessage: opportunity"+date+"=="+createdate);
                            if(createdate.equals(date)){
                                count++;
                            }
                        }
                        data[i][1]=String.valueOf(count);
                    }
                    refreshLineChart(data);
                    refreshTable(data);
                    break;
                case MessageType.API_COMMON_FOLLOWUP_JSON:
                    ArrayList<FollowUpRecord> follows=(ArrayList<FollowUpRecord>) Kit.stringToObject((String)msg.obj);

                    if(!staff_id.equals("")){
                        for(int i =0;i<follows.size();i++){
                            if(!follows.get(i).getStaffid().equals(staff_id)){
                                follows.remove(i);
                                i--;
                            }
                        }
                    }

                    for(int i=0;i<data.length;i++){
                        String date=year+"-"+data[i][0];
                        int count=0;
                        for(int j=0;j<follows.size();j++){
                            String[] splitgroup=follows.get(j).getCreatetime().split(" ");
                            String createdate=splitgroup[0];
                            Log.i("wyc", "handleMessage: follows"+date+"=="+createdate);
                            if(createdate.equals(date)){
                                count++;
                            }
                        }
                        data[i][3]=String.valueOf(count);
                    }
                    refreshLineChart(data);
                    refreshTable(data);
                    break;
            }

        }
    };

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        int staff_position=filter_staff.getSelectedItemPosition();
        if(staff_position!=0&&staff_position!=-1){//如果选择的不是“全部员工”
            Staff staff=report_filter.getStaffs().get(staff_position-1);
            staff_id=staff.getStaffid();

            year=filter_year.getSelectedItem().toString();
            String month=filter_month.getSelectedItem().toString();
            date_head=year+"-"+month;

            Calendar canlender=Calendar.getInstance();
            canlender.set(Calendar.MONTH, Integer.parseInt(month)- 1);
            canlender.set(Calendar.DATE, 1);//把日期设置为当月第一天
            canlender.roll(Calendar.DATE, -1);//日期回滚一天，也就是最后一天
            int maxDate = canlender.get(Calendar.DATE);

            data=new String[maxDate][4];
            for(int i=0;i<maxDate;i++){
                String tempdate="";
                if(i<9){
                    tempdate=month+"-0"+String.valueOf(i+1);
                }else {
                    tempdate=month+"-"+String.valueOf(i+1);
                }

//                DateFormat dataFormat=new SimpleDateFormat("MM-dd");
//                try {
//                    data[i][0]=dataFormat.format(dataFormat.parse(tempdate));
//                } catch (ParseException e) {
//                    e.printStackTrace();
//                }

                data[i][0]=tempdate;
                data[i][1]="0";
                data[i][2]="0";
                data[i][3]="0";
            }
            DB_API_Service.findAllObjects(handler,"common_customer_json");
            DB_API_Service.findAllObjects(handler,"common_opportunity_json");
            DB_API_Service.findAllObjects(handler,"common_followup_json");
        }else if(staff_position==0){
            //如果选择的是全部员工
        }

    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }
}
