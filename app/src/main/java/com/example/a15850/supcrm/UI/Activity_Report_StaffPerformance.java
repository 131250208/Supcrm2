package com.example.a15850.supcrm.UI;

import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TableLayout;
import android.widget.TextView;

import com.example.a15850.supcrm.R;

public class Activity_Report_StaffPerformance extends AppCompatActivity {

    private TableLayout table;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_activity__report__staff_performance);

        table=(TableLayout)this.findViewById(R.id.report_staffperformance_table);

        TextView position=(TextView)this.findViewById(R.id.position_now);
        position.setText("员工绩效考评");

        String[][] performance={
                {"李淑英","15","11","43%","C"},
                {"于广洋","33","21","80%","A"},
                {"陈珑升","14","18","65%","B"}
        };

        refreshTable(performance);
    }

    private void refreshTable(String[][] performance){

        for (int i = 0; i < performance.length; i++) {
            TableRow_Performance row=new TableRow_Performance(this);
            row.setRowData(performance[i]);
            if(i%2==0){
                row.setBackgroundColor(new Color().rgb(212,212,212));
            }
            table.addView(row);
        }
    }
}
