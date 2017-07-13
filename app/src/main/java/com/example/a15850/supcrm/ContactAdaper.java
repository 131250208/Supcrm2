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
public class ContactAdaper extends ArrayAdapter<Contact> {
    private int resourceID;

    public ContactAdaper(Context context,  int textViewResourceId, List<Contact> objects) {
        super(context, textViewResourceId, objects);
        resourceID=textViewResourceId;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        Contact contact=getItem(position);

        View view;
        ViewHolder viewHolder;//缓存控件实例

        if(convertView==null){//优化性能的判断，不会每次都重新加载布局
            view= LayoutInflater.from(getContext()).inflate(resourceID,null);
            viewHolder=new ViewHolder();
            viewHolder.customer_name=(TextView)view.findViewById(R.id.customer_name);
            viewHolder.contact_name=(TextView)view.findViewById(R.id.contact_name);
//            viewHolder.contact_portrait=(ImageView)view.findViewById(R.id.contact_portrait);
            view.setTag(viewHolder);
        }
        else {
            view=convertView;
            viewHolder=(ViewHolder) view.getTag();
        }


        viewHolder.customer_name.setText("来自客户["+contact.getCustomername()+"]");
        viewHolder.contact_name.setText(contact.getContactsname());
//        viewHolder.contact_portrait.setImageResource(R.drawable.portrait);//待修改，应传入对象内部对应成员变量：图片地址

        return view;
    }

    class ViewHolder{
        TextView customer_name;
        TextView contact_name;
//        ImageView contact_portrait;
    }


}
