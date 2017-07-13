package com.example.a15850.supcrm;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

/**
 * Created by 15850 on 2016/5/10.
 */
public class ProductAdapter extends ArrayAdapter<Product> {

    private int resourceID;
    public ProductAdapter(Context context, int textViewResourceId, List<Product> objects) {
        super(context, textViewResourceId, objects);
        resourceID=textViewResourceId;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        Product product=getItem(position);

        View view;
        ViewHolder viewHolder;//缓存控件实例

        if(convertView==null){//优化性能的判断，不会每次都重新加载布局
            view= LayoutInflater.from(getContext()).inflate(resourceID,null);
            viewHolder=new ViewHolder();
            viewHolder.product_image=(ImageView) view.findViewById(R.id.product_image);
            viewHolder.product_classification=(TextView)view.findViewById(R.id.product_classification);
            viewHolder.product_name=(TextView)view.findViewById(R.id.product_name);
            viewHolder.product_price_unit=(TextView)view.findViewById(R.id.product_price_unit);
            view.setTag(viewHolder);
        }
        else {
            view=convertView;
            viewHolder=(ViewHolder) view.getTag();
        }


        viewHolder.product_image.setImageResource(R.drawable.product_list);
        viewHolder.product_classification.setText(product.getClassification());
        viewHolder.product_name.setText(product.getProductname());
        viewHolder.product_price_unit.setText(product.getStandardprice()+"/"+product.getSalesunit());

        return view;
    }

    class ViewHolder{
        ImageView product_image;
        TextView product_classification;
        TextView product_name;
        TextView product_price_unit;
    }
}
