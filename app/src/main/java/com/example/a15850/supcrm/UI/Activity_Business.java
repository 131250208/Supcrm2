package com.example.a15850.supcrm.UI;

import android.content.Context;
import android.content.Intent;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.a15850.supcrm.R;

public class Activity_Business extends AppCompatActivity {

    private LinearLayout report;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_activity__business);

        report=(LinearLayout)this.findViewById(R.id.report);
        report.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Activity_Business.this,Activity_Report.class));
            }

        });

        FloatingActionButton fab_home = (FloatingActionButton) this.findViewById(R.id.fab_home);
        fab_home.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(Activity_Business.this,MainActivity.class));
                Snackbar.make(view, "home！", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        TextView position=(TextView)this.findViewById(R.id.position_now);
        position.setText("业务管理");
    }

}
