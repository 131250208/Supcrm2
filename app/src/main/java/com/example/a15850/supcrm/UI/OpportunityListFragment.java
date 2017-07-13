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

import com.example.a15850.supcrm.Data.DB_API_Service;
import com.example.a15850.supcrm.Enum.MessageType;
import com.example.a15850.supcrm.Kit;
import com.example.a15850.supcrm.Opportunity;
import com.example.a15850.supcrm.OpportunityAdapter;
import com.example.a15850.supcrm.R;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;


/**
 * A simple {@link Fragment} subclass.
 */
public class OpportunityListFragment extends Fragment implements Serializable {


    private List<Opportunity> opportunities=new ArrayList<Opportunity>();
    private View root;
    private String customer_id="";
    private String staff_id="";
    public OpportunityListFragment() {
        // Required empty public constructor
    }

    public static OpportunityListFragment getInstance_customerid(String customer_id){
        OpportunityListFragment opportunityListFragment=new OpportunityListFragment();
        Bundle bundle=new Bundle();
        bundle.putString("customer_id",customer_id);
        opportunityListFragment.setArguments(bundle);
        return  opportunityListFragment;
    }
    public static OpportunityListFragment getInstance_staffid(String staff_id){
        OpportunityListFragment opportunityListFragment=new OpportunityListFragment();
        Bundle bundle=new Bundle();
        bundle.putString("staff_id",staff_id);
        opportunityListFragment.setArguments(bundle);
        return opportunityListFragment;
    }
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Bundle bundle=getArguments();
        if(bundle!=null){
            String customer_id=bundle.getString("customer_id");
            String staff_id=bundle.getString("staff_id");
            if(staff_id!=null){
                this.staff_id=staff_id;
            }else if(customer_id!=null){
                this.customer_id=customer_id;
            }

        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        root =inflater.inflate(R.layout.fragment_list_opportunity, container, false);

        DB_API_Service.findAllObjects(handler,"common_opportunity_json");

        return root;
    }

    @Override
    public void onResume() {
        super.onResume();
        DB_API_Service.findAllObjects(handler,"common_opportunity_json");
    }

    private Handler handler=new Handler(){
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what)
            {
                case MessageType.API_COMMON_OPPORTUNITY_JSON:
                    Object object= Kit.stringToObject((String) msg.obj);
                    ArrayList<Opportunity> opportunities=(ArrayList<Opportunity>)object ;
                    //如果有客户id限定，把不对应的商机从数组中移除。
                    if(!customer_id.equals("")){
                        for (int i=0;i<opportunities.size();i++){
                            if(!opportunities.get(i).getCustomerid().equals(customer_id)){
                                opportunities.remove(i);
                                i--;
                            }
                        }
                    }else if(!staff_id.equals("")){
                        for (int i=0;i<opportunities.size();i++){
                            if(!opportunities.get(i).getStaffid().equals(staff_id)){
                                opportunities.remove(i);
                                i--;
                            }
                        }
                    }

                    OpportunityListFragment.this.opportunities=opportunities;

                    showOpportunity();
                    break;
            }
        }
    };

    private void showOpportunity(){
        OpportunityAdapter adapter=new OpportunityAdapter(getActivity(),R.layout.opportunity_item,opportunities);
        final ListView listView=(ListView)root.findViewById(R.id.list_view_opportunity);
        listView.setAdapter(adapter);
        /*设置子项监听*/
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Opportunity opportunity=opportunities.get(position);

                TabActivity_Detail_Opportunity.actionStart(getContext(),opportunity);
            }
        });
    }

}
