package com.example.a15850.supcrm.UI;

import android.content.Context;
import android.util.AttributeSet;
import android.view.Gravity;
import android.widget.LinearLayout;
import android.widget.TableRow;
import android.widget.TextView;

/**
 * Created by 15850 on 2016/5/25.
 */
public class MyTableRow extends TableRow {
    private TextView date;
    private TextView opportunities;
    private TextView customers;
    private TextView tracks;
    private Context context;
    public MyTableRow(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.context=context;
        init();
    }
    public MyTableRow(Context context) {
        super(context);
        this.context=context;
        init();
    }

    private void init(){
//        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(
//                LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);
//        layoutParams.weight=1;
        TableRow.LayoutParams layoutParams= new TableRow.LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT, 1.0f);
        layoutParams.setMargins(10,10,10,10);

        date=new TextView(context);
        date.setGravity(Gravity.CENTER);
        date.setLayoutParams(layoutParams);
        date.getPaint().setFakeBoldText(true);

        opportunities=new TextView(context);
        opportunities.setGravity(Gravity.CENTER);
        opportunities.setLayoutParams(layoutParams);
        opportunities.getPaint().setFakeBoldText(true);

        customers=new TextView(context);
        customers.setGravity(Gravity.CENTER);
        customers.setLayoutParams(layoutParams);
        customers.getPaint().setFakeBoldText(true);

        tracks=new TextView(context);
        tracks.setGravity(Gravity.CENTER);
        tracks.setLayoutParams(layoutParams);
        tracks.getPaint().setFakeBoldText(true);

        this.addView(date);
        this.addView(opportunities);
        this.addView(customers);
        this.addView(tracks);
    }

    public void setDate(String[] rowData){
        date.setText(rowData[0]);
        opportunities.setText(rowData[1]);
        customers.setText(rowData[2]);
        tracks.setText(rowData[3]);
    }
}
