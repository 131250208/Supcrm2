package com.example.a15850.supcrm.UI;


import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.example.a15850.supcrm.Contract;
import com.example.a15850.supcrm.ContractAdapter;
import com.example.a15850.supcrm.Data.DB_API_Service;
import com.example.a15850.supcrm.Enum.MessageType;
import com.example.a15850.supcrm.FollowUpAdapter;
import com.example.a15850.supcrm.FollowUpRecord;
import com.example.a15850.supcrm.Kit;
import com.example.a15850.supcrm.R;

import java.io.Serializable;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;


/**
 * A simple {@link Fragment} subclass.
 */
public class FollowUpRecordListFragment extends Fragment implements Serializable {

    private View root;
    private List<FollowUpRecord> followUpRecords =new ArrayList<FollowUpRecord>();
    private String customer_id="";
    private String opportunity_id="";
    public FollowUpRecordListFragment() {
        // Required empty public constructor
    }

    public static FollowUpRecordListFragment getInstance_customerid(String customer_id){
        FollowUpRecordListFragment followUpRecordListFragment=new FollowUpRecordListFragment();
        Bundle bundle=new Bundle();
        bundle.putString("customer_id",customer_id);
        followUpRecordListFragment.setArguments(bundle);
        return followUpRecordListFragment;
    }

    public static FollowUpRecordListFragment getInstance_opportunityid(String opportunity_id){
        FollowUpRecordListFragment followUpRecordListFragment=new FollowUpRecordListFragment();
        Bundle bundle=new Bundle();
        bundle.putString("opportunity_id",opportunity_id);
        followUpRecordListFragment.setArguments(bundle);
        return followUpRecordListFragment;
    }


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Bundle bundle=getArguments();
        if(bundle!=null){
            String customer_id=bundle.getString("customer_id");
            String opportunity_id=bundle.getString("opportunity_id");
            if(customer_id!=null){
                this.customer_id=customer_id;
            }else if(opportunity_id!=null){
                this.opportunity_id=opportunity_id;
            }
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
         root=inflater.inflate(R.layout.fragment_list_followup, container, false);

        DB_API_Service.findAllObjects(handler,"common_followup_json");
        return root;
    }
    private Handler handler=new Handler(){
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what)
            {
                case MessageType.API_COMMON_FOLLOWUP_JSON:
                    Object object= Kit.stringToObject((String) msg.obj);
                    ArrayList<FollowUpRecord> followUpRecords=(ArrayList<FollowUpRecord>)object ;

                    if(!customer_id.equals("")){
                        for(int i=0;i<followUpRecords.size();i++){
                            if(!followUpRecords.get(i).getCustomerid().equals(customer_id)){
                                followUpRecords.remove(i);
                                i--;
                            }
                        }
                    }else if(!opportunity_id.equals("")){
                        for(int i=0;i<followUpRecords.size();i++){
                            if(!followUpRecords.get(i).getSourceid().equals(opportunity_id)||!followUpRecords.get(i).getSourcetype().equals("2")){
                                followUpRecords.remove(i);
                                i--;
                            }
                        }
                    }
                    FollowUpRecordListFragment.this.followUpRecords =followUpRecords;

                    showFollowUps();
                    break;
            }
        }
    };
    private void showFollowUps()
    {
        FollowUpAdapter adapter=new FollowUpAdapter(getActivity(),R.layout.followup_item, followUpRecords);
        final ListView listView=(ListView)root.findViewById(R.id.list_view_followup);
        listView.setAdapter(adapter);
        /*设置子项监听*/
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            int yourChose;
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                FollowUpRecord followUpRecord= followUpRecords.get(position);
                //拨号
                String number_tel=followUpRecord.getTel();
                String number_mobile=followUpRecord.getMobile();
                Uri uri;
                if(!number_mobile.equals("")&&!number_tel.equals("")){
                    final String[] mList={number_mobile,number_tel};
                    yourChose=-1;
                    AlertDialog.Builder sinChosDia=new AlertDialog.Builder(getContext());
                    sinChosDia.setTitle("请选择要拨打的号码");
                    sinChosDia.setSingleChoiceItems(mList, 0, new DialogInterface.OnClickListener() {

                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            // TODO Auto-generated method stub
                            yourChose=which;

                        }
                    });
                    sinChosDia.setPositiveButton("拨号", new DialogInterface.OnClickListener() {

                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            // TODO Auto-generated method stub
                            if(yourChose!=-1)
                            {
                                String dial_number=mList[yourChose];
                                dial(dial_number);
                            }
                        }
                    });
                    sinChosDia.create().show();
                }else if(!number_mobile.equals("")&&number_tel.equals("")){
                    dial(number_mobile);
                }else if(!number_tel.equals("")&&number_mobile.equals("")){
                    dial(number_tel);
                }else {
                    Toast.makeText(FollowUpRecordListFragment.this.getContext(),"该员工没有完善联系方式", Toast.LENGTH_SHORT).show();
                }

            }
        });


    }

    private void dial(String number){
        Uri uri= Uri.parse("tel:"+number);
        Intent intent = new Intent(Intent.ACTION_DIAL,uri);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        getActivity().startActivity(intent);
    }

}
