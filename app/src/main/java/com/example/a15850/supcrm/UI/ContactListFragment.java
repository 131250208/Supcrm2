package com.example.a15850.supcrm.UI;


import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.example.a15850.supcrm.Contact;
import com.example.a15850.supcrm.ContactAdaper;
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
public class ContactListFragment extends Fragment implements Serializable {

    private List<Contact> contacts=new ArrayList<Contact>();
    private View root;
    private String customer_id="";
    public ContactListFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Bundle bundle=getArguments();
        if(bundle!=null){
            String customer_id=bundle.getString("customer_id");
            this.customer_id=customer_id;
        }

    }

    public static ContactListFragment getInstance(String customer_id){
        ContactListFragment contactListFragment = new ContactListFragment();
        Bundle b = new Bundle();
        b.putString("customer_id", customer_id);
        contactListFragment.setArguments(b);
        return contactListFragment;
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        root=inflater.inflate(R.layout.fragment_list_contact, container, false);

        DB_API_Service.findAllObjects(handler,"common_contacts_json");
        return root;
    }

    @Override
    public void onResume() {
        super.onResume();
        DB_API_Service.findAllObjects(handler,"common_contacts_json");
    }

    private Handler handler=new Handler(){
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what)
            {
                case MessageType.API_COMMON_CONTACTS_JSON:
                    Object object= Kit.stringToObject((String) msg.obj);
                    ArrayList<Contact> contacts=(ArrayList<Contact>)object ;


                    if(!customer_id.equals("")){
                        for(int i=0;i<contacts.size();i++){
                            if(!contacts.get(i).getCustomerid().equals(customer_id)){
                                contacts.remove(i);
                                i--;
                            }
                        }
                    }

                    ContactListFragment.this.contacts=contacts;
                    showContact();
                    break;
            }
        }
    };
    private void showContact(){

        ContactAdaper contactAdaper=new ContactAdaper(getActivity(),R.layout.contact_item,contacts);
        ListView listView=(ListView)root.findViewById(R.id.list_view_contact);
        listView.setAdapter(contactAdaper);

         /*设置子项监听*/
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Contact contact=contacts.get(position);

                Activity_Detail_Contact.actionStart(getContext(),contact);

            }
        });
    }


}
