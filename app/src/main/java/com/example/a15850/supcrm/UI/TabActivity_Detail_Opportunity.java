package com.example.a15850.supcrm.UI;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.example.a15850.supcrm.Customer;
import com.example.a15850.supcrm.Data.DB_API_Service;
import com.example.a15850.supcrm.Enum.MessageType;
import com.example.a15850.supcrm.Kit;
import com.example.a15850.supcrm.Opportunity;
import com.example.a15850.supcrm.R;

public class TabActivity_Detail_Opportunity extends AppCompatActivity {

    /**
     * The {@link android.support.v4.view.PagerAdapter} that will provide
     * fragments for each of the sections. We use a
     * {@link FragmentPagerAdapter} derivative, which will keep every
     * loaded fragment in memory. If this becomes too memory intensive, it
     * may be best to switch to a
     * {@link android.support.v4.app.FragmentStatePagerAdapter}.
     */
    private SectionsPagerAdapter mSectionsPagerAdapter;

    /**
     * The {@link ViewPager} that will host the section contents.
     */
    private ViewPager mViewPager;
    private Opportunity opportunity;

    private  TextView tv_customer;

    public static void actionStart(Context context,Opportunity opportunity){
        Intent intent=new Intent(context,TabActivity_Detail_Opportunity.class);

        String opportunity_string= Kit.objectToString(opportunity);
        intent.putExtra("opportunity",opportunity_string);

        context.startActivity(intent);

    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tab_detail_opportunity);

        opportunity=(Opportunity)Kit.stringToObject(getIntent().getStringExtra("opportunity")) ;

        /*设置题头位置信息*/
        TextView head=(TextView)findViewById(R.id.position_now) ;
        head.setText("商机详情");

        /*根据传过来的customer对象进行数据初始化*/
        TextView tv_name=(TextView)findViewById(R.id.detail_head_opportunity_name);
        TextView tv_type=(TextView)findViewById(R.id.detail_head_opportunity_type);
        TextView tv_status=(TextView)findViewById(R.id.detail_head_opportunity_status);
        tv_customer=(TextView)findViewById(R.id.detail_head_opportunity_customer);
        TextView tv_amount=(TextView)findViewById(R.id.detail_head_opportunity_amount_sale);

        tv_name.setText(opportunity.getOpportunitytitle());
        tv_status.setText(opportunity.getOpportunitystatus_chinese());
        tv_type.setText(opportunity.getBusinesstype_chinese());
        tv_amount.setText("预计销售金额["+opportunity.getEstimatedamount()+"元]");
        DB_API_Service.findObject(handler,"customer_query_json",opportunity.getCustomerid());

        // Create the adapter that will return a fragment for each of the three
        // primary sections of the activity.
        mSectionsPagerAdapter = new SectionsPagerAdapter(getSupportFragmentManager());

        // Set up the ViewPager with the sections adapter.
        mViewPager = (ViewPager) findViewById(R.id.container);
        mViewPager.setAdapter(mSectionsPagerAdapter);

        TabLayout tabLayout = (TabLayout) findViewById(R.id.tabs);
        tabLayout.setupWithViewPager(mViewPager);

        FloatingActionButton fab_del = (FloatingActionButton) this.findViewById(R.id.fab_del);
        fab_del.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DB_API_Service.delObject(handler,"opportunity_delete_json",opportunity.getOpportunityid());
            }
        });
        fab_del.setVisibility(View.INVISIBLE);

        FloatingActionButton fab_editor = (FloatingActionButton) this.findViewById(R.id.fab_editor);
        fab_editor.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Activity_Detail_Opportunity.actionStart(TabActivity_Detail_Opportunity.this,opportunity);
            }
        });

        FloatingActionButton fab_home = (FloatingActionButton) this.findViewById(R.id.fab_home);
        fab_home.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(TabActivity_Detail_Opportunity.this,MainActivity.class));
            }
        });

    }


    private Handler handler =new Handler(){
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what){
                case MessageType.API_CUSTOMER_QUERY_JSON:
                    Customer customer =(Customer)Kit.stringToObject((String)msg.obj);
                    tv_customer.setText("对应客户["+customer.getCustomername()+"]");
                    break;
                case MessageType.RESULT_DEL_OBJECT:
                    String res_del=(String)msg.obj;
                    Toast.makeText(TabActivity_Detail_Opportunity.this,res_del, Toast.LENGTH_SHORT).show();
                    if(res_del.equals("删除成功")){
                        TabActivity_Detail_Opportunity.this.finish();
                    }
                    break;
            }
        }
    };
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_tab_activity__another, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
    public static class PlaceholderFragment extends Fragment {
        /**
         * The fragment argument representing the section number for this
         * fragment.
         */
        private static final String ARG_SECTION_NUMBER = "section_number";

        public PlaceholderFragment() {
        }

        /**
         * Returns a new instance of this fragment for the given section
         * number.
         */
        public static PlaceholderFragment newInstance(int sectionNumber) {
            PlaceholderFragment fragment = new PlaceholderFragment();
            Bundle args = new Bundle();
            args.putInt(ARG_SECTION_NUMBER, sectionNumber);
            fragment.setArguments(args);
            return fragment;
        }

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                                 Bundle savedInstanceState) {
            View rootView = inflater.inflate(R.layout.fragment_tab_activity__another, container, false);
            TextView textView = (TextView) rootView.findViewById(R.id.section_label);
            textView.setText(getString(R.string.section_format, getArguments().getInt(ARG_SECTION_NUMBER)));
            return rootView;
        }
    }

    /**
     * A {@link FragmentPagerAdapter} that returns a fragment corresponding to
     * one of the sections/tabs/pages.
     */
    public class SectionsPagerAdapter extends FragmentPagerAdapter {

        public SectionsPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            switch (position){
                case 0:
                    return FollowUpRecordListFragment.getInstance_opportunityid(opportunity.getOpportunityid());
                case 1:
                    return PlaceholderFragment.newInstance(position + 1);
                case 2:
                    return ContractListFragment.getInstance_opportunityid(opportunity.getOpportunityid());
            }
            return PlaceholderFragment.newInstance(position + 1);
        }

        @Override
        public int getCount() {
            return 3;
        }

        @Override
        public CharSequence getPageTitle(int position) {
            switch (position) {
                case 0:
                    return "跟进记录";
                case 1:
                    return "产品";
                case 2:
                    return "合同";
            }
            return null;
        }
    }
}
