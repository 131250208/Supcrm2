package com.example.a15850.supcrm.UI;

import android.content.Intent;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.GridLayout;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.a15850.supcrm.R;

public class Activity_Report extends AppCompatActivity implements View.OnClickListener{

    private GridLayout report_trend;
    private GridLayout report_filter;
    private LinearLayout report_track;
    private LinearLayout report_target;
    private LinearLayout report_performance;
    private LinearLayout report_situation;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_activity__report);

        report_trend=(GridLayout)this.findViewById(R.id.report_trend);
        report_filter=(GridLayout)this.findViewById(R.id.report_filter);
        report_track=(LinearLayout)this.findViewById(R.id.report_track);
        report_target=(LinearLayout)this.findViewById(R.id.report_target);
        report_performance=(LinearLayout)this.findViewById(R.id.report_performance);
        report_situation=(LinearLayout)this.findViewById(R.id.report_situation);

        report_trend.setOnClickListener(this);
        report_track.setOnClickListener(this);
        report_target.setOnClickListener(this);
        report_filter.setOnClickListener(this);
        report_performance.setOnClickListener(this);
        report_situation.setOnClickListener(this);

        FloatingActionButton fab_home = (FloatingActionButton) this.findViewById(R.id.fab_home);
        fab_home.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(Activity_Report.this,MainActivity.class));
                Snackbar.make(view, "home！", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        TextView position=(TextView)this.findViewById(R.id.position_now);
        position.setText("统计分析");
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.report_trend:
                startActivity(new Intent(this,Activity_Report_Trend.class));
                break;
            case R.id.report_track:
                startActivity(new Intent(this,Activity_Report_Track.class));
                break;
            case R.id.report_target:
                startActivity(new Intent(this,Activity_Report_Target.class));
                break;
            case R.id.report_filter:
                startActivity(new Intent(this,Activity_Report_Filter.class));
                break;
            case R.id.report_performance:
                startActivity(new Intent(this,Activity_Report_StaffPerformance.class));
                break;
            case R.id.report_situation:
                startActivity(new Intent(this,Activity_Report_Situation.class));
                break;
        }
    }
}
