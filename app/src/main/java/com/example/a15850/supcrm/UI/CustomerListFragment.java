package com.example.a15850.supcrm.UI;


import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.example.a15850.supcrm.Customer;
import com.example.a15850.supcrm.CustomerAdapter;
import com.example.a15850.supcrm.Data.DB_API_Service;
import com.example.a15850.supcrm.Data.MyDbHelper;
import com.example.a15850.supcrm.Enum.MessageType;
import com.example.a15850.supcrm.Kit;
import com.example.a15850.supcrm.R;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;


/**
 * A simple {@link Fragment} subclass.
 */
public class CustomerListFragment extends Fragment implements Serializable {


//    private String[] customers={"林一","大天二","张三","李四","王五"};
    private List<Customer> customers=new ArrayList<Customer>();
    private String staff_id="";
    public CustomerListFragment() {
        // Required empty public constructor
    }


    private  View root;
    public static CustomerListFragment getInstance_staffid(String staff_id){
        CustomerListFragment customerListFragment=new CustomerListFragment();
        Bundle bundle=new Bundle();
        bundle.putString("staff_id",staff_id);
        customerListFragment.setArguments(bundle);
        return customerListFragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Bundle bundle=getArguments();
        if(bundle!=null){
            String staff_id=bundle.getString("staff_id");
            if(staff_id!=null){
                this.staff_id=staff_id;
            }

        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        root =inflater.inflate(R.layout.fragment_list_customer, container, false);

        DB_API_Service.findAllObjects(handler,"common_customer_json");//请求所有客户数据
        return root;
    }

    @Override
    public void onResume() {
        super.onResume();
        DB_API_Service.findAllObjects(handler,"common_customer_json");//请求所有客户数据
    }

    private Handler handler=new Handler(){
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what)
            {
                case MessageType.API_COMMON_CUSTOMER_JSON:
                    Object object=Kit.stringToObject((String) msg.obj);
                    ArrayList<Customer> customers=(ArrayList<Customer>)object ;
                    CustomerListFragment.this.customers=customers;

                    if(!staff_id.equals("")){
                        for(int i=0;i<customers.size();i++){
                            if(!customers.get(i).getStaffid().equals(staff_id)){
                                customers.remove(i);
                                i--;
                            }
                        }
                    }
                    showCustomers();
                break;
            }
        }
    };

    private void showCustomers(){
        CustomerAdapter adapter=new CustomerAdapter(getActivity(),R.layout.customer_item,CustomerListFragment.this.customers);
        final ListView customerLV=(ListView)root.findViewById(R.id.list_view_customer);
        customerLV.setAdapter(adapter);
                    /*设置子项监听*/
        customerLV.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Customer customer=CustomerListFragment.this.customers.get(position);

                TabActivity_Detail_Customer.actionStart(getContext(),customer);
            }
        });
    }
}
