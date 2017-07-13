package com.example.a15850.supcrm.UI;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.SpinnerAdapter;
import android.widget.TextView;
import android.widget.Toast;

import com.example.a15850.supcrm.Data.DB_API_Service;
import com.example.a15850.supcrm.Enum.MessageType;
import com.example.a15850.supcrm.Kit;
import com.example.a15850.supcrm.Product;
import com.example.a15850.supcrm.R;

import java.util.ArrayList;

public class Activity_Detail_Product extends AppCompatActivity {

    private Product product;

    private EditText product_name;
    private EditText product_id;
    private EditText product_price;
    private EditText product_unit;

    private Spinner product_categories;
    private EditText product_introduction;
    private EditText product_remark;

    private Button saveProduct;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_activity__detail__product);

        //初始化信息
        product_name=(EditText)this.findViewById(R.id.product_detail_name);
        product_id=(EditText)this.findViewById(R.id.product_detail_sn);
        product_price=(EditText)this.findViewById(R.id.product_detail_price);
        product_unit=(EditText)this.findViewById(R.id.product_detail_price_unit);
        product_categories=(Spinner)this.findViewById(R.id.product_detail_categories);
        product_introduction=(EditText)this.findViewById(R.id.product_detail_introduction);
        product_remark=(EditText)this.findViewById(R.id.product_detail_remark);
        saveProduct=(Button)this.findViewById(R.id.save_product);

        //查找所有商品获取所有分类
        DB_API_Service.findAllObjects(handler,"common_product_json");

        Intent intent=getIntent();
        final String product_string=intent.getStringExtra("product");
        product=(Product) Kit.stringToObject(product_string);


        /*按钮监听*/
        FloatingActionButton fab_home = (FloatingActionButton) this.findViewById(R.id.fab_home);
        fab_home.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(Activity_Detail_Product.this,MainActivity.class));
            }
        });
        FloatingActionButton fab_edit = (FloatingActionButton) this.findViewById(R.id.fab_edit_product);
        fab_edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                setEnabled_edit(true);
            }
        });
        final FloatingActionButton fab_del = (FloatingActionButton) this.findViewById(R.id.fab_del_product);
        fab_del.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DB_API_Service.delObject(handler,"product_delete_json",product.getProductid());
            }
        });
        fab_del.setVisibility(View.INVISIBLE);

        TextView navigation=(TextView)this.findViewById(R.id.position_now);
        //判断是修改还是创建
        if(product.getProductid().equals("")){
            navigation.setText("创建产品");
            fab_edit.setVisibility(View.INVISIBLE);
        }else {
            navigation.setText("产品详情");
        }

        //保存信息
        saveProduct.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                product.setProductname(product_name.getText().toString());
                product.setStandardprice(product_price.getText().toString());
//                product.setProductsn(product_id.getText().toString());
                product.setSalesunit(product_unit.getText().toString());
                product.setIntroduction(product_introduction.getText().toString());
                product.setProductremarks(product_remark.getText().toString());

                if(product.getProductid().equals("")){
                    product.setClassification(product_categories.getSelectedItem().toString());
                    DB_API_Service.addObject(handler,product);

                }else {
                    DB_API_Service.modifyObject(handler,product);
                }
            }
        });
    }

    private void setEnabled_edit(boolean bool){
        saveProduct.setEnabled(bool);
        if(bool){
            saveProduct.setBackgroundColor(Color.rgb(60,141,188));
        }else {
            saveProduct.setBackgroundColor(Color.rgb(212,212,212));
        }
//        product_id.setEnabled(bool);
        product_name.setEnabled(bool);
        product_price.setEnabled(bool);
        product_unit.setEnabled(bool);
        product_introduction.setEnabled(bool);
        product_remark.setEnabled(bool);
    }

    private Handler handler=new Handler(){
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what){
                case MessageType.RESULT_MODIFY_OBJECT:
                    String res_mod=(String)msg.obj;
                    Toast.makeText(Activity_Detail_Product.this,res_mod, Toast.LENGTH_SHORT).show();
                    if(res_mod.equals("商品信息修改成功")){
                        setEnabled_edit(false);
                    }
                case MessageType.RESULT_ADD_OBJECT:
                    String res_add=(String)msg.obj;
                    Toast.makeText(Activity_Detail_Product.this,res_add, Toast.LENGTH_SHORT).show();
                    if(res_add.equals("商品添加成功")){
                        Activity_Detail_Product.this.finish();
                    }
                    break;
                case MessageType.RESULT_DEL_OBJECT:
                    String res_del=(String)msg.obj;
                    Toast.makeText(Activity_Detail_Product.this,res_del, Toast.LENGTH_SHORT).show();
                    if(res_del.equals("删除成功")){
                        Activity_Detail_Product.this.finish();
                    }
                    break;

                case MessageType.API_COMMON_PRODUCT_JSON://接收所有商品的数组，从中提取分类
                    Object object= Kit.stringToObject((String) msg.obj);
                    ArrayList<Product> products=(ArrayList<Product>)object ;
                    ArrayList<String> categories=new ArrayList<String>();
                    for(int i=0;i<products.size();i++){
                        String newCategory=products.get(i).getClassification();
                        if(!categories.contains(newCategory)){
                            categories.add(newCategory);
                        }
                    }
//                     建立数据源
                    String[] mItems = new String[categories.size()];
                    for(int i=0;i<categories.size();i++){
                        mItems[i]=categories.get(i);
                    }
                    // 建立Adapter并且绑定数据源
                    ArrayAdapter<String> adapter=new ArrayAdapter<String>(Activity_Detail_Product.this,android.R.layout.simple_spinner_item,mItems);
                    adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                    //绑定 Adapter到控件
                    product_categories .setAdapter(adapter);

                    //初始化信息
                    if(product.getProductid().equals("")){
                        setEnabled_edit(true);
                    }else {
                        product_name.setText(product.getProductname());
                        product_id.setText(product.getProductsn());
                        product_price.setText(product.getStandardprice());
                        product_unit.setText(product.getSalesunit());
                        SpinnerAdapter apsAdapter= product_categories.getAdapter(); //得到SpinnerAdapter对象
                        int k= apsAdapter.getCount();
                        String category=product.getClassification();
                        for(int i=0;i<k;i++){
                            if(category.equals(apsAdapter.getItem(i).toString())){
                                product_categories.setSelection(i,true);// 默认选中项
                                break;
                            }
                        }
                        product_introduction.setText(product.getIntroduction());
                        product_remark.setText(product.getProductremarks());
                    }

                    break;
            }
        }
    };
    public static void  actionStart(Context context, Product product){
        Intent intent=new Intent(context,Activity_Detail_Product.class);
        intent.putExtra("product", Kit.objectToString(product));

        context.startActivity(intent);
    }
}
