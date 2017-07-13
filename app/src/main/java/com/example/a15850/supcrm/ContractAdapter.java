package com.example.a15850.supcrm;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;

/**
 * Created by 15850 on 2016/5/9.
 */
public class ContractAdapter extends ArrayAdapter<Contract> {

    private int resourceID;

    public ContractAdapter(Context context,  int textViewResourceId, List<Contract> objects) {
        super(context,  textViewResourceId, objects);

        resourceID=textViewResourceId;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        Contract contract=getItem(position);

        View view;
        ViewHolder viewHolder;//缓存控件实例

        if(convertView==null){//优化性能的判断，不会每次都重新加载布局
            view= LayoutInflater.from(getContext()).inflate(resourceID,null);
            viewHolder=new ViewHolder();
            viewHolder.contract_title=(TextView)view.findViewById(R.id.contract_title);
            viewHolder.contract_status=(TextView)view.findViewById(R.id.contract_status);
            viewHolder.contract_type=(TextView)view.findViewById(R.id.contract_type);
            viewHolder.contract_totalamount=(TextView)view.findViewById(R.id.contract_totalamount);
            view.setTag(viewHolder);
        }
        else {
            view=convertView;
            viewHolder=(ViewHolder) view.getTag();
        }


        viewHolder.contract_title.setText(contract.getContracttitle());
        viewHolder.contract_status.setText(contract.getContractstatus_chinese());
        viewHolder.contract_type.setText(contract.getContracttype_chinese());
        viewHolder.contract_totalamount.setText(contract.getTotalamount());

        return view;
    }

    class ViewHolder{
        TextView contract_title;
        TextView contract_status;
        TextView contract_type;
        TextView contract_totalamount;
    }
}
