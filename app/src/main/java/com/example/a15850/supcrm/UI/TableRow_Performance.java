package com.example.a15850.supcrm.UI;

import android.content.Context;
import android.util.AttributeSet;
import android.view.Gravity;
import android.widget.TableRow;
import android.widget.TextView;

/**
 * Created by 15850 on 2016/6/13.
 */
public class TableRow_Performance extends TableRow
{
    private TextView staff_name;
    private TextView customer_num;
    private TextView contract_num;
    private TextView tracks_num;
    private TextView performance_level;
    private Context context;
    public TableRow_Performance(Context context) {
        super(context);
        this.context=context;
        init();
    }
    public TableRow_Performance(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.context=context;
        init();
    }

    private void init(){
        TableRow.LayoutParams layoutParams= new TableRow.LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT, 1.0f);
        layoutParams.setMargins(10,10,10,10);

        staff_name =new TextView(context);
        staff_name.setGravity(Gravity.CENTER);
        staff_name.setLayoutParams(layoutParams);
        staff_name.getPaint().setFakeBoldText(true);

        customer_num =new TextView(context);
        customer_num.setGravity(Gravity.CENTER);
        customer_num.setLayoutParams(layoutParams);
        customer_num.getPaint().setFakeBoldText(true);

        contract_num =new TextView(context);
        contract_num.setGravity(Gravity.CENTER);
        contract_num.setLayoutParams(layoutParams);
        contract_num.getPaint().setFakeBoldText(true);

        tracks_num =new TextView(context);
        tracks_num.setGravity(Gravity.CENTER);
        tracks_num.setLayoutParams(layoutParams);
        tracks_num.getPaint().setFakeBoldText(true);

        performance_level =new TextView(context);
        performance_level.setGravity(Gravity.CENTER);
        performance_level.setLayoutParams(layoutParams);
        performance_level.getPaint().setFakeBoldText(true);

        this.addView(staff_name);
        this.addView(customer_num);
        this.addView(contract_num);
        this.addView(tracks_num);
        this.addView(performance_level);
    }

    public void setRowData(String[] rowData){
        staff_name.setText(rowData[0]);
        customer_num.setText(rowData[1]);
        contract_num.setText(rowData[2]);
        tracks_num.setText(rowData[3]);
        performance_level.setText(rowData[4]);
    }
}
