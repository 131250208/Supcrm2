package com.example.a15850.supcrm;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;

/**
 * Created by 15850 on 2016/5/10.
 */
public class OpportunityAdapter extends ArrayAdapter<Opportunity>{

    private int resourceID;
    public OpportunityAdapter(Context context, int textViewResourceId, List<Opportunity> objects) {
        super(context, textViewResourceId, objects);
        resourceID=textViewResourceId;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        Opportunity opportunity=getItem(position);

        View view;
        ViewHolder viewHolder;//缓存控件实例

        if(convertView==null){//优化性能的判断，不会每次都重新加载布局
            view= LayoutInflater.from(getContext()).inflate(resourceID,null);
            viewHolder=new ViewHolder();
            viewHolder.opportunity_status=(TextView)view.findViewById(R.id.opportunity_status);
            viewHolder.opportunity_type=(TextView)view.findViewById(R.id.opportunity_type);
            viewHolder.opportunity_title=(TextView)view.findViewById(R.id.opportunity_title);
            viewHolder.opportunity_estimatedamount_customer=(TextView)view.findViewById(R.id.opportunity_estimatedamount_customer);
            view.setTag(viewHolder);
        }
        else {
            view=convertView;
            viewHolder=(ViewHolder) view.getTag();
        }


        viewHolder.opportunity_status.setText(opportunity.getOpportunitystatus_chinese());
        viewHolder.opportunity_type.setText(opportunity.getBusinesstype_chinese());
        viewHolder.opportunity_title.setText(opportunity.getOpportunitytitle());
        viewHolder.opportunity_estimatedamount_customer.setText(opportunity.getEstimatedamount()+"|"+opportunity.getCustomerid());

        return view;
    }

    class ViewHolder{
        TextView opportunity_title;
        TextView opportunity_type;
        TextView opportunity_status;
        TextView opportunity_estimatedamount_customer;
    }
}
