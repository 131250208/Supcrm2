package com.example.a15850.supcrm.UI;

import android.content.Context;
import android.os.Handler;
import android.os.Message;
import android.util.AttributeSet;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import android.widget.Spinner;

import com.example.a15850.supcrm.Data.DB_API_Service;
import com.example.a15850.supcrm.Department;
import com.example.a15850.supcrm.Enum.MessageType;
import com.example.a15850.supcrm.Kit;
import com.example.a15850.supcrm.Staff;

import java.util.ArrayList;
import java.util.Calendar;

/**
 * Created by 15850 on 2016/6/22.
 */
public class ReportFilter extends LinearLayout {
    private Spinner spinner_department;
    private Spinner spinner_staff;
    private Spinner spinner_year;
    private Spinner spinner_month;

    private ArrayList<Department> departments;
    private ArrayList<Staff> staffs;

    private String department_id="";
    private Context context;


    public ArrayList<Staff> getStaffs() {
        return staffs;
    }

    public ArrayList<Department> getDepartments() {
        return departments;
    }

    public Spinner getSpinner_department() {
        return spinner_department;
    }

    public Spinner getSpinner_staff() {
        return spinner_staff;
    }

    public Spinner getSpinner_year() {
        return spinner_year;
    }

    public Spinner getSpinner_month() {
        return spinner_month;
    }
    public ReportFilter(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        this.context=context;
        initial();
    }
    public ReportFilter(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.context=context;
        initial();
    }
    public ReportFilter(Context context) {
        super(context);
        this.context=context;
        initial();
    }

    private void initial(){
        spinner_department=new Spinner(context);
        spinner_staff=new Spinner(context);
        spinner_year=new Spinner(context);
        spinner_month=new Spinner(context);

        LinearLayout.LayoutParams layoutParams= new LinearLayout.LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.MATCH_PARENT, 1.0f);
        spinner_department.setLayoutParams(layoutParams);
        spinner_staff.setLayoutParams(layoutParams);
        spinner_year.setLayoutParams(layoutParams);
        spinner_month.setLayoutParams(layoutParams);

        //获取部门和员工的数据源
        DB_API_Service.findAllObjects(handler,"common_department_json");
        DB_API_Service.findAllObjects(handler,"common_staff_json");

        //初始化年的数据
        Calendar calendar= Calendar.getInstance();
        int year_now=calendar.get(Calendar.YEAR);
        String[] mItems_year=new String[10];
        for(int i=0;i<10;i++){
            mItems_year[i]=String.valueOf(year_now-i);
        }
        // 建立Adapter并且绑定数据源
        ArrayAdapter<String> adapter_year=new ArrayAdapter<String>(context,android.R.layout.simple_spinner_item,mItems_year);
        adapter_year.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        //绑定 Adapter到控件
        spinner_year .setAdapter(adapter_year);

        //初始化月的数据
        String[] mItems_month={"01","02","03","04","05","06","07","08","09","10","11","12"};
        // 建立Adapter并且绑定数据源
        ArrayAdapter<String> adapter_month=new ArrayAdapter<String>(context,android.R.layout.simple_spinner_item,mItems_month);
        adapter_month.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        //绑定 Adapter到控件
        spinner_month .setAdapter(adapter_month);


        this.addView(spinner_department);
        this.addView(spinner_staff);
        this.addView(spinner_year);
        this.addView(spinner_month);

    }

    private Handler handler=new Handler(){
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what){
                case MessageType.API_COMMON_DEPARTMENT_JSON:
                    departments=(ArrayList<Department>)Kit.stringToObject((String)msg.obj);
                    ArrayList<String> departments_name=new ArrayList<String>();
                    for(int i=0;i<departments.size();i++){
                        departments_name.add(departments.get(i).getDepartmentname());
                    }

                    String[] mItems_department=(String[])departments_name.toArray(new String[departments_name.size()]);
                    // 建立Adapter并且绑定数据源
                    ArrayAdapter<String> adapter_department=new ArrayAdapter<String>(context,android.R.layout.simple_spinner_item,mItems_department);
                    adapter_department.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                    //绑定 Adapter到控件
                    spinner_department .setAdapter(adapter_department);

                    spinner_department.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                        @Override
                        public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                            department_id=departments.get(position).getDepartmentid();
                            DB_API_Service.findAllObjects(handler,"common_staff_json");
                        }

                        @Override
                        public void onNothingSelected(AdapterView<?> parent) {

                        }
                    });
                    break;

                case MessageType.API_COMMON_STAFF_JSON:
                    staffs=(ArrayList<Staff>)Kit.stringToObject((String)msg.obj);

                    if(!department_id.equals("")){
                        for(int i=0;i<staffs.size();i++){
                            if(!staffs.get(i).getDepartmentid().equals(department_id)){
                                staffs.remove(i);
                                i--;
                            }
                        }
                    }
                    ArrayList<String> staffs_name=new ArrayList<String>();
                    staffs_name.add("全部员工");

                    for(int i=0;i<staffs.size();i++){
                        staffs_name.add(staffs.get(i).getName());
                    }

                    String[] mItems_staff=(String[])staffs_name.toArray(new String[staffs_name.size()]);
                    // 建立Adapter并且绑定数据源
                    ArrayAdapter<String> adapter_staff=new ArrayAdapter<String>(context,android.R.layout.simple_spinner_item,mItems_staff);
                    adapter_staff.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                    //绑定 Adapter到控件
                    spinner_staff .setAdapter(adapter_staff);
                    break;
            }
        }
    };
}
