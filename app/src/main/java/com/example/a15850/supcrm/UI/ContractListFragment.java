package com.example.a15850.supcrm.UI;


import android.content.Intent;
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

import com.example.a15850.supcrm.Contact;
import com.example.a15850.supcrm.Contract;
import com.example.a15850.supcrm.ContractAdapter;
import com.example.a15850.supcrm.Customer;
import com.example.a15850.supcrm.CustomerAdapter;
import com.example.a15850.supcrm.Data.DB_API_Service;
import com.example.a15850.supcrm.Enum.MessageType;
import com.example.a15850.supcrm.Kit;
import com.example.a15850.supcrm.Opportunity;
import com.example.a15850.supcrm.R;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;


/**
 * A simple {@link Fragment} subclass.
 */
public class ContractListFragment extends Fragment implements Serializable {

    private View root;
    private List<Contract> contracts=new ArrayList<Contract>();
    private String customer_id="";
    private String opportunity_id="";
    private String staff_id="";

    public ContractListFragment() {
        // Required empty public constructor
    }
    public static ContractListFragment getInstance_staffid(String staff_id){
        ContractListFragment contractListFragment=new ContractListFragment();
        Bundle bundle=new Bundle();
        bundle.putString("staff_id",staff_id);
        contractListFragment.setArguments(bundle);
        return contractListFragment;
    }
    public static ContractListFragment getInstance_customerid(String customer_id){
        ContractListFragment contractListFragment=new ContractListFragment();
        Bundle bundle=new Bundle();
        bundle.putString("customer_id",customer_id);
        contractListFragment.setArguments(bundle);
        return contractListFragment;
    }
    public static ContractListFragment getInstance_opportunityid(String opportunity_id){
        ContractListFragment contractListFragment=new ContractListFragment();
        Bundle bundle=new Bundle();
        bundle.putString("opportunity_id",opportunity_id);
        contractListFragment.setArguments(bundle);
        return contractListFragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Bundle bundle=getArguments();
        if(bundle!=null){
            String customer_id=bundle.getString("customer_id");
            String opportunity_id=bundle.getString("opportunity_id");
            String staff_id=bundle.getString("staff_id");
            if(customer_id!=null){
                this.customer_id=customer_id;
            }else if(opportunity_id!=null){
                this.opportunity_id=opportunity_id;
            }else if(staff_id!=null){
                this.staff_id=staff_id;
            }

        }

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
         root=inflater.inflate(R.layout.fragment_list_contract, container, false);

        DB_API_Service.findAllObjects(handler,"common_contract_json");
        return root;
    }

    @Override
    public void onResume() {
        super.onResume();
        DB_API_Service.findAllObjects(handler,"common_contract_json");
    }

    private Handler handler=new Handler(){
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what)
            {
                case MessageType.API_COMMON_CONTRACTS_JSON:
                    Object object= Kit.stringToObject((String) msg.obj);
                    ArrayList<Contract> contracts=(ArrayList<Contract>)object ;

                    if(!customer_id.equals("")){
                        for(int i=0;i<contracts.size();i++){
                            if(!contracts.get(i).getCustomerid().equals(customer_id)){
                                contracts.remove(i);
                                i--;
                            }
                        }
                    }else if(!opportunity_id.equals("")){
                        for(int i=0;i<contracts.size();i++){
                            if(!contracts.get(i).getOpportunityid().equals(opportunity_id)){
                                contracts.remove(i);
                                i--;
                            }
                        }
                    }else if(!staff_id.equals("")){
                        for(int i=0;i<contracts.size();i++){
                            if(!contracts.get(i).getStaffid().equals(staff_id)){
                                contracts.remove(i);
                                i--;
                            }
                        }
                    }
                    ContractListFragment.this.contracts=contracts;

                    showContract();
                    break;
            }
        }
    };
    private void showContract()
    {
        ContractAdapter adapter=new ContractAdapter(getActivity(),R.layout.contract_item,contracts);
        final ListView listView=(ListView)root.findViewById(R.id.list_view_contract);
        listView.setAdapter(adapter);
        /*设置子项监听*/
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Contract contract=contracts.get(position);
                    //打开合同详情页
                Activity_Detail_Contract.actionStart(getContext(),contract);
            }
        });


    }

}
