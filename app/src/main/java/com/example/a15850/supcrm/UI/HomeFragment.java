package com.example.a15850.supcrm.UI;


import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.a15850.supcrm.Contact;
import com.example.a15850.supcrm.Contract;
import com.example.a15850.supcrm.Customer;
import com.example.a15850.supcrm.Data.DB_API_Service;
import com.example.a15850.supcrm.Enum.MessageType;
import com.example.a15850.supcrm.Kit;
import com.example.a15850.supcrm.MyApp;
import com.example.a15850.supcrm.Opportunity;
import com.example.a15850.supcrm.Product;
import com.example.a15850.supcrm.R;

import java.math.BigDecimal;
import java.util.ArrayList;


/**
 * A simple {@link Fragment} subclass.
 */
public class HomeFragment extends Fragment implements View.OnTouchListener{


    public HomeFragment() {
        // Required empty public constructor
    }

    private LinearLayout customer;
    private LinearLayout opportunity;
    private LinearLayout contract;
    private LinearLayout contact;
    private LinearLayout product;
    private LinearLayout businessManager;

    private TextView count_customer_total;
    private TextView count_customer_mine;
    private TextView count_opportunity_total;
    private TextView count_opportunity_mine;
    private TextView count_contract_total;
    private TextView count_contract_mine;
    private TextView count_contact_total;
    private TextView count_contact_mine;
    private TextView count_product_total;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View root =inflater.inflate(R.layout.fragment_home, container, false);

        customer=(LinearLayout) root.findViewById(R.id.bt_customer);
        opportunity=(LinearLayout) root.findViewById(R.id.bt_opportunity);
        contract=(LinearLayout) root.findViewById(R.id.bt_contract);
        contact=(LinearLayout) root.findViewById(R.id.bt_contact);
        product=(LinearLayout) root.findViewById(R.id.bt_product);
        businessManager=(LinearLayout) root.findViewById(R.id.bt_businessManager);

        count_customer_total=(TextView)root.findViewById(R.id.count_customer_total);
        count_customer_mine=(TextView)root.findViewById(R.id.count_customer_mine);
        count_opportunity_total=(TextView)root.findViewById(R.id.count_opportunity_total);
        count_opportunity_mine=(TextView)root.findViewById(R.id.count_opportunity_mine);
        count_contract_total=(TextView)root.findViewById(R.id.count_contract_total);
        count_contract_mine=(TextView)root.findViewById(R.id.count_contract_mine);
        count_contact_total=(TextView)root.findViewById(R.id.count_contact_total);
        count_contact_mine=(TextView)root.findViewById(R.id.count_contact_mine);
        count_product_total=(TextView)root.findViewById(R.id.count_product_total);

        DB_API_Service.findAllObjects(handler,"common_customer_json");
        DB_API_Service.findAllObjects(handler,"common_opportunity_json");
        DB_API_Service.findAllObjects(handler,"common_contract_json");
        DB_API_Service.findAllObjects(handler,"common_contacts_json");
        DB_API_Service.findAllObjects(handler,"common_product_json");

        customer.setOnTouchListener(this);
        opportunity.setOnTouchListener(this);
        contract.setOnTouchListener(this);
        contact.setOnTouchListener(this);
        product.setOnTouchListener(this);
        businessManager.setOnTouchListener(this);

        return root;
    }

    private Handler handler=new Handler(){
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            MyApp myApp=(MyApp)getActivity().getApplication();
            String staffid=myApp.getStaffID();
            switch (msg.what){
                case MessageType.API_COMMON_CUSTOMER_JSON:
                    String customers_string=(String)msg.obj;
                    ArrayList<Customer> customers=(ArrayList<Customer>) Kit.stringToObject(customers_string);
                    int customer_total=customers.size();
                    count_customer_total.setText(customer_total+"位");
                    int customer_mine=0;
                    for(int i=0;i<customers.size();i++){
                        if(customers.get(i).getStaffid().equals(staffid)){
                            customer_mine++;
                        }
                    }
                    float percent_customer=0;
                    if(customer_total==0||customer_mine==0){
                        percent_customer=0;
                    }else {
                        percent_customer=((float)customer_mine/(float)customer_total)*100;
                        BigDecimal b   =   new   BigDecimal(percent_customer);
                        percent_customer  =   b.setScale(1,BigDecimal.ROUND_HALF_UP).floatValue();
                    }
                    count_customer_mine.setText("您有"+customer_mine+"位客户，占比"+percent_customer+"%");
                    break;
                case MessageType.API_COMMON_OPPORTUNITY_JSON:
                    String opportunitys_string=(String)msg.obj;
                    ArrayList<Opportunity> opportunitys=(ArrayList<Opportunity>) Kit.stringToObject(opportunitys_string);
                    int opportunity_total=opportunitys.size();
                    count_opportunity_total.setText(opportunity_total+"个");
                    int opportunity_mine=0;
                    for(int i=0;i<opportunitys.size();i++){
                        if(opportunitys.get(i).getStaffid().equals(staffid)){
                            opportunity_mine++;
                        }
                    }
                    float percent_opportunity=0;
                    if(opportunity_total==0||opportunity_mine==0){
                        percent_opportunity=0;
                    }else {
                        percent_opportunity=((float)opportunity_mine/(float)opportunity_total)*100;
                        BigDecimal b   =   new   BigDecimal(percent_opportunity);
                        percent_opportunity  =   b.setScale(1,BigDecimal.ROUND_HALF_UP).floatValue();
                    }
                    count_opportunity_mine.setText("您有"+opportunity_mine+"个商机，占比"+percent_opportunity+"%");
                    break;
                case MessageType.API_COMMON_CONTRACTS_JSON:
                    String contracts_string=(String)msg.obj;
                    ArrayList<Contract> contracts=(ArrayList<Contract>) Kit.stringToObject(contracts_string);
                    int contract_total=contracts.size();
                    count_contract_total.setText(contract_total+"份");
                    int contract_mine=0;
                    for(int i=0;i<contracts.size();i++){
                        if(contracts.get(i).getStaffid().equals(staffid)){
                            contract_mine++;
                        }
                    }
                    float percent_contract=0;
                    if(contract_total==0||contract_mine==0){
                        percent_contract=0;
                    }else {
                        percent_contract=((float)contract_mine/(float)contract_total)*100;
                        BigDecimal b   =   new   BigDecimal(percent_contract);
                        percent_contract  =   b.setScale(1,BigDecimal.ROUND_HALF_UP).floatValue();
                    }
                    count_contract_mine.setText("您有"+contract_mine+"份合同，占比"+percent_contract+"%");
                    break;
                case MessageType.API_COMMON_CONTACTS_JSON:
                    String contacts_string=(String)msg.obj;
                    ArrayList<Contact> contacts=(ArrayList<Contact>) Kit.stringToObject(contacts_string);
                    int contact_total=contacts.size();
                    count_contact_total.setText(contact_total+"位");
                    int contact_mine=0;
                    for(int i=0;i<contacts.size();i++){
                        if(contacts.get(i).getStaffid().equals(staffid)){
                            contact_mine++;
                        }
                    }
                    float percent_contact=0;
                    if(contact_total==0||contact_mine==0){
                        percent_contact=0;
                    }else {
                        percent_contact=((float)contact_mine/(float)contact_total)*100;
                        BigDecimal b   =   new   BigDecimal(percent_contact);
                        percent_contact  =   b.setScale(1,BigDecimal.ROUND_HALF_UP).floatValue();
                    }
                    count_contact_mine.setText("您有"+contact_mine+"位联系人，占比"+percent_contact+"%");
                    break;
                case MessageType.API_COMMON_PRODUCT_JSON:
                    String products_string=(String)msg.obj;
                    ArrayList<Product> products=(ArrayList<Product>) Kit.stringToObject(products_string);
                    int product_total=products.size();
                    count_product_total.setText(product_total+"种");
                    break;
            }
        }
    };
    @Override
    public boolean onTouch(View v, MotionEvent event) {
        if (event.getAction()==MotionEvent.ACTION_UP){
            switch (v.getId()){
                case R.id.bt_customer:
                    startActivity(new Intent(getActivity(),TabActivity_List_Customer.class));
                    break;
                case R.id.bt_opportunity:
                    startActivity(new Intent(getActivity(),TabActivity_List_Opportunity.class));
                    break;
                case R.id.bt_contract:
                    startActivity(new Intent(getActivity(),TabActivity_List_Contract.class));
                    break;
                case R.id.bt_contact:
                    startActivity(new Intent(getActivity(),TabActivity_List_Contact.class));
                    break;
                case R.id.bt_product:
                    startActivity(new Intent(getActivity(),Activity_List_Product.class));
                    break;
                case R.id.bt_businessManager:
                    startActivity(new Intent(getActivity(),Activity_Business.class));
                    break;
            }

        }
        return true;
    }
}
