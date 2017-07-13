package com.example.a15850.supcrm.UI;

import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import com.example.a15850.supcrm.R;
import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.interfaces.datasets.IBarDataSet;
import com.github.mikephil.charting.interfaces.datasets.ILineDataSet;

import java.util.ArrayList;

public class Activity_Report_Situation extends AppCompatActivity {

    private LineChart lineChart;
    private BarChart barChart;
    private PieChart pieChart;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_activity__situation);

        TextView head=(TextView)findViewById(R.id.position_now);
        head.setText("产品销售情况");

        lineChart=(LineChart)findViewById(R.id.linechart);
        barChart=(BarChart)findViewById(R.id.barchart);
        pieChart=(PieChart)findViewById(R.id.piechart);

        float[] sales={48,68,56,79,80,99,109,120,111,90,78,66};
        initLineChart(sales,"红家组合衣柜","个");

        float[] importNum={200,200,300,300};
        float[] exportNum={172,258,340,234};
        initBarChart(importNum,exportNum);

        intiPieChart();
    }

    private void initLineChart(float[] salesVolumn,String productName,String unit){
        //横坐标-日期
        ArrayList<String> xVals = new ArrayList<String>();
        for(int i=0;i<12;i++){
            xVals.add(String.valueOf(i)+"月");
        }
        //趋势数组
        ArrayList<Entry> valsProduct = new ArrayList<Entry>();

        for(int i=0;i<salesVolumn.length;i++){
            Entry c1e_product = new Entry(salesVolumn[i], i);
            valsProduct.add(c1e_product);
        }


        LineDataSet setProduct = new LineDataSet(valsProduct, productName+"/"+unit);
        setProduct.setAxisDependency(YAxis.AxisDependency.LEFT);
        setProduct.setCircleRadius(0);
        setProduct.setValueTextSize(0);
        setProduct.setColor(Color.rgb(235,189,6));
        setProduct.setLineWidth(2.5f);

        // use the interface ILineDataSet
        ArrayList<ILineDataSet> dataSets = new ArrayList<ILineDataSet>();
        dataSets.add(setProduct);

        LineData data = new LineData(xVals, dataSets);
        lineChart.setData(data);

        lineChart.setDrawGridBackground(false);
        lineChart.invalidate(); // refresh
    }
    private void initBarChart(float[] importNum,float[] exportNum){

        ArrayList<BarEntry> list_entry_import=new ArrayList<BarEntry>();
        ArrayList<BarEntry> list_entry_export=new ArrayList<BarEntry>();

        for(int i=0;i<importNum.length;i++){
            BarEntry barEntry_im=new BarEntry(importNum[i],i);
            list_entry_import.add(barEntry_im);
            BarEntry barEntry_ex=new BarEntry(exportNum[i],i);
            list_entry_export.add(barEntry_ex);
        }

        String[] xvals={"春季","夏季","秋季","冬季"};
        BarDataSet set_import=new BarDataSet(list_entry_import,"库存");
        BarDataSet set_export=new BarDataSet(list_entry_export,"订单");
        set_import.setColor(Color.rgb(235,189,6));

        ArrayList<IBarDataSet> datalist=new ArrayList<IBarDataSet>();

        datalist.add(set_import);
        datalist.add(set_export);

        BarData barData=new BarData(xvals,datalist);

        barChart.setData(barData);
        barChart.invalidate();
    }
    private void intiPieChart(){

        Entry entry1=new Entry(12,0);
        Entry entry2=new Entry(20,1);
        Entry entry3=new Entry(66,2);
        Entry entry4=new Entry(2,3);
        ArrayList<Entry> list=new ArrayList<Entry>();
        list.add(entry1);
        list.add(entry2);
        list.add(entry3);
        list.add(entry4);

        PieDataSet set=new PieDataSet(list,"产品销售占比");

        int[] colors={
                Color.rgb(16,184,63),
                Color.rgb(0,154,190),
                Color.rgb(235,189,6),
                Color.rgb(238,65,65)};
        set.setColors(colors);
        String[] xvals={"席梦思床垫","白丽玻璃桌","红家组合衣柜","盼盼防盗门"};

        PieData pieData=new PieData(xvals,set);

        pieChart.setData(pieData);
        pieChart.invalidate();
    }
}
