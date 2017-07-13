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
public class FollowUpAdapter extends ArrayAdapter<FollowUpRecord> {

    private int resourceID;

    public FollowUpAdapter(Context context, int textViewResourceId, List<FollowUpRecord> objects) {
        super(context,  textViewResourceId, objects);

        resourceID=textViewResourceId;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        FollowUpRecord followUpRecord=getItem(position);

        View view;
        ViewHolder viewHolder;//缓存控件实例

        if(convertView==null){//优化性能的判断，不会每次都重新加载布局
            view= LayoutInflater.from(getContext()).inflate(resourceID,null);
            viewHolder=new ViewHolder();
            viewHolder.followup_time=(TextView)view.findViewById(R.id.followup_time);
            viewHolder.followup_staffname=(TextView)view.findViewById(R.id.followup_staffname);
            viewHolder.followup_content=(TextView)view.findViewById(R.id.followup_content);
            view.setTag(viewHolder);
        }
        else {
            view=convertView;
            viewHolder=(ViewHolder) view.getTag();
        }


        viewHolder.followup_time.setText(followUpRecord.getCreatetime());
        viewHolder.followup_staffname.setText(followUpRecord.getName());
        viewHolder.followup_content.setText(followUpRecord.getContent());

        return view;
    }

    class ViewHolder{
        TextView followup_time;
        TextView followup_staffname;
        TextView followup_content;
    }
}
