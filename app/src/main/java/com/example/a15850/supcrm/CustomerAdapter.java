package com.example.a15850.supcrm;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;

/**
 * Created by 15850 on 2016/5/8.
 */
public class CustomerAdapter extends ArrayAdapter<Customer>{

    private int resourceID;
    public CustomerAdapter(Context context, int textViewResourceId, List<Customer> objects) {
        super(context, textViewResourceId, objects);
        resourceID=textViewResourceId;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        Customer customer=getItem(position);

        View view;
        ViewHolder viewHolder;//缓存控件实例

        if(convertView==null){//优化性能的判断，不会每次都重新加载布局
            view= LayoutInflater.from(getContext()).inflate(resourceID,null);
            viewHolder=new ViewHolder();
            viewHolder.customer_name=(TextView)view.findViewById(R.id.customer_name);
            viewHolder.customer_type=(TextView)view.findViewById(R.id.customer_type);
            viewHolder.customer_state=(TextView)view.findViewById(R.id.customer_state);
            view.setTag(viewHolder);
        }
        else {
            view=convertView;
            viewHolder=(ViewHolder) view.getTag();
        }


        viewHolder.customer_name.setText(customer.getCustomername());
        viewHolder.customer_state.setText(customer.getCustomerstatus_chinese());
        viewHolder.customer_type.setText(customer.getCustomertype_chinese());

        return view;
    }

    class ViewHolder{
        TextView customer_name;
        TextView customer_type;
        TextView customer_state;
    }
}
