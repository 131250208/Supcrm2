package com.example.a15850.supcrm.UI;


import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.os.PersistableBundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.a15850.supcrm.Customer;
import com.example.a15850.supcrm.Data.DB_API_Service;
import com.example.a15850.supcrm.Enum.MessageType;
import com.example.a15850.supcrm.Kit;
import com.example.a15850.supcrm.Opportunity;
import com.example.a15850.supcrm.Product;
import com.example.a15850.supcrm.ProductAdapter;
import com.example.a15850.supcrm.R;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class Activity_List_Product extends AppCompatActivity {

    private List<Product> products=new ArrayList<Product>();

    public Activity_List_Product() {
        // Required empty public constructor
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // Inflate the layout for this fragment
        setContentView(R.layout.fragment_list_product);
        TextView head=(TextView)this.findViewById(R.id.position_now) ;
        head.setText("产品列表");//导航题头
        //悬浮按钮
        FloatingActionButton fab_home = (FloatingActionButton) this.findViewById(R.id.fab_home);
        fab_home.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(Activity_List_Product.this,MainActivity.class));
            }
        });
        FloatingActionButton fab_add = (FloatingActionButton) this.findViewById(R.id.fab_add);
        fab_add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Activity_Detail_Product.actionStart(Activity_List_Product.this,new Product());
            }
        });

        //初始化listview 产品列表
        DB_API_Service.findAllObjects(handler,"common_product_json");
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        //重新初始化listview 产品列表
        DB_API_Service.findAllObjects(handler,"common_product_json");
    }

    private Handler handler=new Handler(){
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what)
            {
                case MessageType.API_COMMON_PRODUCT_JSON:
                    Object object= Kit.stringToObject((String) msg.obj);
                    ArrayList<Product> products=(ArrayList<Product>)object ;
                    Activity_List_Product.this.products=products;

                    showProduct();
                    break;
            }
        }
    };

    private void showProduct(){
        ProductAdapter adapter=new ProductAdapter(this,R.layout.product_item,products);
        final ListView listView=(ListView)this.findViewById(R.id.list_view_product);
        listView.setAdapter(adapter);
        /*设置子项监听*/
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Product product=products.get(position);
                Activity_Detail_Product.actionStart(Activity_List_Product.this,product);
            }
        });
    }

}
