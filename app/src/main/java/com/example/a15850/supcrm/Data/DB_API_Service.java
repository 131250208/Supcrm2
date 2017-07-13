package com.example.a15850.supcrm.Data;

import android.os.Handler;
import android.os.Message;
import android.util.Log;

import com.example.a15850.supcrm.Contact;
import com.example.a15850.supcrm.Contract;
import com.example.a15850.supcrm.Customer;
import com.example.a15850.supcrm.Department;
import com.example.a15850.supcrm.Enum.MessageType;
import com.example.a15850.supcrm.FollowUpRecord;
import com.example.a15850.supcrm.Kit;
import com.example.a15850.supcrm.Opportunity;
import com.example.a15850.supcrm.Product;
import com.example.a15850.supcrm.Staff;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;
import java.util.ArrayList;

/**
 * Created by 15850 on 2016/6/4.
 */
public class DB_API_Service {
    public static final String api_address="http://nqiwx.mooctest.net:8090/wexin.php/Api/Index/";

    /*get 方法请求网络api*/
    public static String connect(String request) {
        HttpURLConnection connection=null;
        URL url= null;
        String result="";
        try {
            url = new URL(api_address+request);
            connection=(HttpURLConnection)url.openConnection();
            connection.setRequestMethod("GET");
            connection.setConnectTimeout(8000);
            connection.setReadTimeout(8000);
            //获得输入流
            InputStream inputStream=connection.getInputStream();
            //读取输入流
            BufferedReader reader=new BufferedReader(new InputStreamReader(inputStream));
            StringBuilder response=new StringBuilder();
            String line;
            while ((line=reader.readLine())!=null){
                response.append(line);
            }
            result=response.toString();

        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (ProtocolException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (connection!=null){
                connection.disconnect();
            }
        }
        return result;
    }
    /*post 方法请求网络api*/
    public static String connect(String request,String[][] param) {
        param=removeNullParams(param);
        HttpURLConnection connection=null;
        URL url= null;
        String result="";
        try {
            url = new URL(api_address+request);
            connection=(HttpURLConnection)url.openConnection();
            connection.setRequestMethod("POST");
            connection.setConnectTimeout(8000);
            connection.setReadTimeout(8000);
            //获取输出流输出参数
            DataOutputStream outputStream=new DataOutputStream(connection.getOutputStream());
            StringBuilder paramStirng=new StringBuilder();
            for(int i=0;i<param.length;i++){
                paramStirng.append(param[i][0]);
                paramStirng.append("=");
                paramStirng.append(param[i][1]);
                if (i!=param.length-1){
                    paramStirng.append("&");
                }
            }
            Log.i("wyc", "connect: "+paramStirng.toString());
            outputStream.writeBytes(paramStirng.toString());
            //获得输入流
            InputStream inputStream=connection.getInputStream();
            //读取输入流
            BufferedReader reader=new BufferedReader(new InputStreamReader(inputStream));
            StringBuilder response=new StringBuilder();
            String line;
            while ((line=reader.readLine())!=null){
                response.append(line);
            }
            result=response.toString();

        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (ProtocolException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (connection!=null){
                connection.disconnect();
            }
        }
        return result;
    }
    /*查询所有对象（员工、商机、客户、联系人、跟进、商品、部门）信息*/
    public static void findAllObjects(final Handler handler, final String request){
        //开启新线程来访问网络
        new Thread(new Runnable() {
            @Override
            public void run() {

                String[][] param_getpages={
                        {"currentpage","1"},
                        {"search",""}
                };
                String json_string_p1=connect(request,param_getpages);
                try {
                    JSONObject jobject_p1=new JSONObject(json_string_p1);
                    int pages=jobject_p1.getInt("pagecount");//从第一页获得页数

                    //根据请求初始化和设置标志信息
                    ArrayList objects=null;
                    Message message=null;
                    switch (request){
                        case "common_contract_json":
                            objects=new ArrayList<Contract>();
                            message=new Message();
                            message.what= MessageType.API_COMMON_CONTRACTS_JSON;
                            break;
                        case "common_staff_json":
                            objects=new ArrayList<Staff>();
                            message=new Message();
                            message.what= MessageType.API_COMMON_STAFF_JSON;
                            break;
                        case "common_contacts_json":
                            objects=new ArrayList<Contact>();
                            message=new Message();
                            message.what= MessageType.API_COMMON_CONTACTS_JSON;
                            break;
                        case "common_followup_json":
                            objects=new ArrayList<FollowUpRecord>();
                            message=new Message();
                            message.what= MessageType.API_COMMON_FOLLOWUP_JSON;
                            break;
                        case "common_opportunity_json":
                            objects=new ArrayList<Opportunity>();
                            message=new Message();
                            message.what= MessageType.API_COMMON_OPPORTUNITY_JSON;
                            break;
                        case "common_product_json":
                            objects=new ArrayList<Product>();
                            message=new Message();
                            message.what= MessageType.API_COMMON_PRODUCT_JSON;
                            break;
                        case "common_customer_json":
                            objects=new ArrayList<Customer>();
                            message=new Message();
                            message.what= MessageType.API_COMMON_CUSTOMER_JSON;
                            break;
                        case "common_department_json":
                            objects=new ArrayList<Department>();
                            message=new Message();
                            message.what= MessageType.API_COMMON_DEPARTMENT_JSON;
                            break;
                    }


                    for (int i=0;i<pages;i++){//遍历并用数组保存每一页的员工数据
                        String[][] param_getstaffs={
                                {"currentpage",String.valueOf(i+1)},
                                {"search",""}
                        };
                        String json_string_pp=connect(request,param_getstaffs);
                        JSONObject jobject_pp=new JSONObject(json_string_pp);
                        int currentcount=jobject_pp.getInt("currentcount");
                        for (int j=0;j<currentcount;j++){
                            JSONObject jobject_string=jobject_pp.getJSONObject(String.valueOf(j));
                            //根据请求实例化对象
                            switch (request){
                                case "common_contract_json":
                                    Contract contract=  new Contract();
                                    contract.Initial(jobject_string);
                                    //加入到数组
                                    objects.add(contract);
                                    break;
                                case "common_staff_json":
                                    Staff staff=  new Staff();
                                    staff.Initial(jobject_string);
                                    //加入到数组
                                    objects.add(staff);
                                    break;
                                case "common_contacts_json":
                                    Contact contact=  new Contact();
                                    contact.Initial(jobject_string);
                                    //加入到数组
                                    objects.add(contact);
                                    break;
                                case "common_followup_json":
                                    FollowUpRecord followUpRecord=  new FollowUpRecord();
                                    followUpRecord.Initial(jobject_string);
                                    //加入到数组
                                    objects.add(followUpRecord);
                                    break;
                                case "common_opportunity_json":
                                    Opportunity opportunity=  new Opportunity();
                                    opportunity.Initial(jobject_string);
                                    //加入到数组
                                    objects.add(opportunity);
                                    break;
                                case "common_product_json":
                                    Product product=  new Product();
                                    product.Initial(jobject_string);
                                    //加入到数组
                                    objects.add(product);
                                    break;
                                case "common_customer_json":
                                    Customer customer=  new Customer();
                                    customer.Initial(jobject_string);
                                    //加入到数组
                                    objects.add(customer);
                                    break;
                                case "common_department_json":
                                    Department department=  new Department();
                                    department.Initial(jobject_string);
                                    //加入到数组
                                    objects.add(department);
                                    break;
                            }

                        }
                    }

                    message.obj=Kit.objectToString(objects);
                    handler.sendMessage(message);
                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }
        }).start();
    }
    /*查找指定对象*/
    public static void findObject(final Handler handler, final String request,final String id){
        String objectid="";
        switch (request){
            case "contract_query_json":
                objectid="contractid";
                break;
            case "opportunity_query_json":
                objectid="opportunityid";
                break;
            case "staff_query_json":
                objectid="staffid";
                break;
            case "customer_query_json":
                objectid="cusotmerid";
                break;
            case "product_query_json":
                objectid="productid";
                break;
        }
        final String finalObjectid = objectid;
        new Thread(new Runnable() {
            @Override
            public void run() {
                String json_string=connect(request+"?"+ finalObjectid +"="+id);
                try {
                    JSONObject jsonObject=new JSONObject(json_string);
                    JSONObject jsonObject_object=jsonObject.getJSONObject("0");

                    String result="";
                    Message message=new Message();
                    switch (request){
                        case "staff_query_json":
                            Staff staff=new Staff();
                            staff.Initial(jsonObject_object);
                            result=Kit.objectToString(staff);

                            message.what=MessageType.API_STAFF_QUERY_JSON;
                            break;
                        case "customer_query_json":
                            Customer customer=new Customer();
                            customer.Initial(jsonObject_object);
                            result=Kit.objectToString(customer);

                            message.what=MessageType.API_CUSTOMER_QUERY_JSON;
                            break;
                        case "product_query_json":
                            Product product=new Product();
                            product.Initial(jsonObject_object);
                            result=Kit.objectToString(product);

                            message.what=MessageType.API_PRODUCT_QUERY_JSON;
                            break;
                        case "contract_query_json":
                            Contract contract=new Contract();
                            contract.Initial(jsonObject_object);
                            result=Kit.objectToString(contract);

                            message.what=MessageType.API_CONTRACT_QUERY_JSON;
                            break;
                        case "opportunity_query_json":
                            Opportunity opportunity=new Opportunity();
                            opportunity.Initial(jsonObject_object);
                            result=Kit.objectToString(opportunity);

                            message.what=MessageType.API_OPPORTUNITY_QUERY_JSON;
                            break;
                    }


                    message.obj=result;
                    handler.sendMessage(message);
                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }
        }).start();
    }
    /*删除对象*/
    public static void delObject(final Handler handler,final String request,final String id){
        new Thread(new Runnable() {
            @Override
            public void run() {
                String js_string=connect(request+"?"+request.split("_")[0]+"id="+id);
                Message message=new Message();
                message.what=MessageType.RESULT_DEL_OBJECT;
                try {
                    JSONObject jsonObject=new JSONObject(js_string);
                    String resultcode=jsonObject.getString("resultcode");
                    if (resultcode.equals("0")){
                        message.obj="删除成功";
                    }
                    else {
                        message.obj="删除失败";
                    }
                    handler.sendMessage(message);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }
    /*新增员工*/
    public static void addObject(final Handler handler,final Staff staff){
        final String[][] param_staff={
                {"userid",staff.getUserid()},
                {"openid",staff.getOpenid()},
                {"name",staff.getName()},
                {"departmentid",staff.getDepartmentid()},
                {"leaderflag",staff.getLeaderflag()},
                {"position",staff.getPosition()},
                {"order",staff.getOrder()},
                {"mobile",staff.getMobile()},
                {"tel",staff.getTel()},
                {"gender",staff.getGender()},
                {"email",staff.getEmail()},
                {"weixinid",staff.getWeixinid()},
                {"avatar",staff.getAvatar()},
                {"extattr",staff.getExtattr()},
                {"staffstatus",staff.getStaffstatus()},
                {"enable",staff.getEnable()},
                {"staffremarks",staff.getStaffremarks()},
                {"departmentname",staff.getDepartmentname()},
                {"parentid",staff.getParentid()},
                {"departmentorder",staff.getDepartmentorder()},
                {"departmentstatus",staff.getDepartmentstatus()},
                {"departmentremarks",staff.getDepartmentremarks()}
        };
        new Thread(new Runnable() {
            @Override
            public void run() {
                String js_string=connect("staff_create_json",param_staff);
                Message message=new Message();
                message.what=MessageType.RESULT_ADD_OBJECT;
                try {
                    JSONObject jsonObject=new JSONObject(js_string);
                    String resultcode=jsonObject.getString("resultcode");
                    if (resultcode.equals("0")){
                        message.obj="员工添加成功";
                    }
                    else {
                        message.obj="员工添加失败";
                    }
                    handler.sendMessage(message);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }).start();

    }
    /*修改员工信息*/
    public static void modifyObject(final Handler handler,final Staff staff){
        final String[][] param_staff={
                {"userid",staff.getStaffid()},
                {"userid",staff.getUserid()},
                {"openid",staff.getOpenid()},
                {"name",staff.getName()},
                {"departmentid",staff.getDepartmentid()},
                {"leaderflag",staff.getLeaderflag()},
                {"position",staff.getPosition()},
                {"order",staff.getOrder()},
                {"mobile",staff.getMobile()},
                {"tel",staff.getTel()},
                {"gender",staff.getGender()},
                {"email",staff.getEmail()},
                {"weixinid",staff.getWeixinid()},
                {"avatar",staff.getAvatar()},
                {"extattr",staff.getExtattr()},
                {"staffstatus",staff.getStaffstatus()},
                {"enable",staff.getEnable()},
                {"staffremarks",staff.getStaffremarks()},
                {"departmentname",staff.getDepartmentname()},
                {"parentid",staff.getParentid()},
                {"departmentorder",staff.getDepartmentorder()},
                {"departmentstatus",staff.getDepartmentstatus()},
                {"departmentremarks",staff.getDepartmentremarks()}
        };
        new Thread(new Runnable() {
            @Override
            public void run() {
                String js_string=connect("staff_modify_json",param_staff);
                Message message=new Message();
                message.what=MessageType.RESULT_MODIFY_OBJECT;
                try {
                    JSONObject jsonObject=new JSONObject(js_string);
                    String resultcode=jsonObject.getString("resultcode");
                    if (resultcode.equals("0")){
                        message.obj="员工信息修改成功";
                    }
                    else {
                        message.obj="员工信息修改失败";
                    }
                    handler.sendMessage(message);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }).start();

    }
    /*新增客户*/
    public static void addObject(final Handler handler,final Customer customer){
        final String[][] param_customer={
        {"customername",customer.getCustomername()},
        {"profile",customer.getProfile()},
        {"customertype",customer.getCustomertype()},
        {"customerstatus",customer.getCustomerstatus()},
        {"regionid",customer.getRegionid()},
        {"parentcustomerid",customer.getParentcustomerid()},
        {"customersource",customer.getCustomersource()},
        {"size",customer.getSize()},
        {"telephone",customer.getTelephone()},
        {"email",customer.getEmail()},
        {"website",customer.getWebsite()},
        {"address",customer.getAddress()},
        {"zipcode",customer.getZipcode()},
        {"staffid",customer.getStaffid()},
        {"createdate",customer.getCreatedate()},
        {"customerremarks",customer.getCustomerremarks()},
        {"userid",customer.getUserid()},
        {"openid",customer.getOpenid()},
        {"name",customer.getName()},
        {"departmentid",customer.getDepartmentid()},
        {"leaderflag",customer.getLeaderflag()},
        {"position",customer.getPosition()},
        {"order",customer.getOrder()},
        {"mobile",customer.getMobile()},
        {"tel",customer.getTel()},
        {"gender",customer.getGender()},
        {"weixinid",customer.getWeixinid()},
        {"avatar",customer.getAvatar()},
        {"extattr",customer.getExtattr()},
        {"staffstatus",customer.getStaffstatus()},
        {"enable",customer.getEnable()},
        {"staffremarks",customer.getStaffremarks()}
        };
        new Thread(new Runnable() {
            @Override
            public void run() {
                String js_string=connect("customer_create_json",param_customer);
                Message message=new Message();
                message.what=MessageType.RESULT_ADD_OBJECT;
                try {
                    JSONObject jsonObject=new JSONObject(js_string);
                    String resultcode=jsonObject.getString("resultcode");
                    if (resultcode.equals("0")){
                        message.obj="客户添加成功";
                    }else {
                        message.obj="客户添加失败";
                    }
                    handler.sendMessage(message);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }).start();

    }
    /*修改客户信息*/
    public static void modifyObject(final Handler handler,final Customer customer){
        final String[][] param_customer={
                {"customerid",customer.getCustomerid()},
                {"customername",customer.getCustomername()},
                {"profile",customer.getProfile()},
                {"customertype",customer.getCustomertype()},
                {"customerstatus",customer.getCustomerstatus()},
                {"regionid",customer.getRegionid()},
                {"parentcustomerid",customer.getParentcustomerid()},
                {"customersource",customer.getCustomersource()},
                {"size",customer.getSize()},
                {"telephone",customer.getTelephone()},
                {"email",customer.getEmail()},
                {"website",customer.getWebsite()},
                {"address",customer.getAddress()},
                {"zipcode",customer.getZipcode()},
                {"staffid",customer.getStaffid()},
                {"createdate",customer.getCreatedate()},
                {"customerremarks",customer.getCustomerremarks()},
                {"userid",customer.getUserid()},
                {"openid",customer.getOpenid()},
                {"name",customer.getName()},
                {"departmentid",customer.getDepartmentid()},
                {"leaderflag",customer.getLeaderflag()},
                {"position",customer.getPosition()},
                {"order",customer.getOrder()},
                {"mobile",customer.getMobile()},
                {"tel",customer.getTel()},
                {"gender",customer.getGender()},
                {"weixinid",customer.getWeixinid()},
                {"avatar",customer.getAvatar()},
                {"extattr",customer.getExtattr()},
                {"staffstatus",customer.getStaffstatus()},
                {"enable",customer.getEnable()},
                {"staffremarks",customer.getStaffremarks()}
        };
        new Thread(new Runnable() {
            @Override
            public void run() {
                String js_string=connect("customer_modify_json",param_customer);
                Message message=new Message();
                message.what=MessageType.RESULT_MODIFY_OBJECT;
                try {
                    JSONObject jsonObject=new JSONObject(js_string);
                    String resultcode=jsonObject.getString("resultcode");
                    if (resultcode.equals("0")){
                        message.obj="客户信息修改成功";
                    }else {
                        message.obj="客户信息修改失败";
                    }
                    handler.sendMessage(message);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }).start();

    }
    /*新增商品*/
    public static void addObject(final Handler handler,final Product product){
        final String[][] param_product={
            {"productname",product.getProductname()},
            {"productsn",product.getProductsn()},
            {"standardprice",product.getStandardprice()},
            {"salesunit",product.getSalesunit()},
            {"unitcost",product.getUnitcost()},
            {"classification",product.getClassification()},
            {"picture",product.getPicture()},
            {"introduction",product.getIntroduction()},
            {"productremarks",product.getProductremarks()}
        };
        new Thread(new Runnable() {
            @Override
            public void run() {
                String js_string=connect("product_create_json",param_product);
                Message message=new Message();
                message.what=MessageType.RESULT_ADD_OBJECT;
                try {
                    JSONObject jsonObject=new JSONObject(js_string);
                    String resultcode=jsonObject.getString("resultcode");
                    if (resultcode.equals("0")){
                        message.obj="商品添加成功";
                    }else {
                        message.obj="商品添加失败";
                    }
                    handler.sendMessage(message);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }).start();

    }
    /*修改商品信息*/
    public static void modifyObject(final Handler handler,final Product product){
        final String[][] param_product={
                {"productid",product.getProductid()},
                {"productname",product.getProductname()},
                {"productsn",product.getProductsn()},
                {"standardprice",product.getStandardprice()},
                {"salesunit",product.getSalesunit()},
                {"unitcost",product.getUnitcost()},
                {"classification",product.getClassification()},
                {"picture",product.getPicture()},
                {"introduction",product.getIntroduction()},
                {"productremarks",product.getProductremarks()}
        };
        new Thread(new Runnable() {
            @Override
            public void run() {
                String js_string=connect("product_modify_json",param_product);
                Message message=new Message();
                message.what=MessageType.RESULT_MODIFY_OBJECT;

                try {
                    JSONObject jsonObject=new JSONObject(js_string);
                    String resultcode=jsonObject.getString("resultcode");
                    if (resultcode.equals("0")){
                        message.obj="商品信息修改成功";
                    }
                    else {
                        message.obj="商品信息修改失败";
                    }
                    handler.sendMessage(message);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }).start();

    }
    /*新增商机*/
    public static void addObject(final Handler handler,final Opportunity opportunity){
        final String[][] param_opportunity={
            {"opportunitytitle",opportunity.getOpportunitytitle()},
            {"customerid",opportunity.getCustomerid()},
            {"estimatedamount",opportunity.getEstimatedamount()},
            {"successrate",opportunity.getSuccessrate()},
            {"expecteddate",opportunity.getExpecteddate()},
            {"opportunitystatus",opportunity.getOpportunitystatus()},
            {"channel",opportunity.getChannel()},
            {"businesstype",opportunity.getBusinesstype()},
            {"acquisitiondate",opportunity.getAcquisitiondate()},
            {"opportunitiessource",opportunity.getOpportunitiessource()},
            {"staffid",opportunity.getStaffid()},
            {"opportunityremarks",opportunity.getOpportunityremarks()},
            {"customername",opportunity.getCustomername()},
            {"profile",opportunity.getProfile()},
            {"customertype",opportunity.getCustomertype()},
            {"customerstatus",opportunity.getCustomerstatus()},
            {"regionid",opportunity.getRegionid()},
            {"parentcustomerid",opportunity.getParentcustomerid()},
            {"customersource",opportunity.getCustomersource()},
            {"size",opportunity.getSize()},
            {"telephone",opportunity.getTelephone()},
            {"email",opportunity.getEmail()},
            {"website",opportunity.getWebsite()},
            {"address",opportunity.getAddress()},
            {"zipcode",opportunity.getZipcode()},
            {"createdate",opportunity.getCreatedate()},
            {"customerremarks",opportunity.getCustomerremarks()}
        };
        new Thread(new Runnable() {
            @Override
            public void run() {
                String js_string=connect("opportunity_create_json",param_opportunity);
                Message message=new Message();
                message.what=MessageType.RESULT_ADD_OBJECT;
                try {
                    JSONObject jsonObject=new JSONObject(js_string);
                    String resultcode=jsonObject.getString("resultcode");
                    if (resultcode.equals("0")){
                        message.obj="商机添加成功";
                    }else {
                        message.obj="商机添加失败";
                    }
                    handler.sendMessage(message);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }).start();

    }
    /*修改商机*/
    public static void modifyObject(final Handler handler,final Opportunity opportunity){
        final String[][] param_opportunity={
                {"opportunityid",opportunity.getOpportunityid()},
                {"opportunitytitle",opportunity.getOpportunitytitle()},
                {"customerid",opportunity.getCustomerid()},
                {"estimatedamount",opportunity.getEstimatedamount()},
                {"successrate",opportunity.getSuccessrate()},
                {"expecteddate",opportunity.getExpecteddate()},
                {"opportunitystatus",opportunity.getOpportunitystatus()},
                {"channel",opportunity.getChannel()},
                {"businesstype",opportunity.getBusinesstype()},
                {"acquisitiondate",opportunity.getAcquisitiondate()},
                {"opportunitiessource",opportunity.getOpportunitiessource()},
                {"staffid",opportunity.getStaffid()},
                {"opportunityremarks",opportunity.getOpportunityremarks()},
                {"customername",opportunity.getCustomername()},
                {"profile",opportunity.getProfile()},
                {"customertype",opportunity.getCustomertype()},
                {"customerstatus",opportunity.getCustomerstatus()},
                {"regionid",opportunity.getRegionid()},
                {"parentcustomerid",opportunity.getParentcustomerid()},
                {"customersource",opportunity.getCustomersource()},
                {"size",opportunity.getSize()},
                {"telephone",opportunity.getTelephone()},
                {"email",opportunity.getEmail()},
                {"website",opportunity.getWebsite()},
                {"address",opportunity.getAddress()},
                {"zipcode",opportunity.getZipcode()},
                {"createdate",opportunity.getCreatedate()},
                {"customerremarks",opportunity.getCustomerremarks()}
        };
        new Thread(new Runnable() {
            @Override
            public void run() {
                String js_string=connect("opportunity_modify_json",param_opportunity);
                Message message=new Message();
                message.what=MessageType.RESULT_MODIFY_OBJECT;
                try {
                    JSONObject jsonObject=new JSONObject(js_string);
                    String resultcode=jsonObject.getString("resultcode");
                    if (resultcode.equals("0")){
                        message.obj="商机信息修改成功";
                    }else {
                        message.obj="商机信息修改失败";
                    }
                    handler.sendMessage(message);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }).start();

    }
    /*新增合同*/
    public static void addObject(final Handler handler,final Contract contract){
        final String[][] param_contract={
           {"contracttitle",contract.getContracttitle()},
           {"opportunityid",contract.getOpportunityid()},
           {"customerid",contract.getCustomerid()},
           {"totalamount",contract.getTotalamount()},
           {"startdate",contract.getStartdate()},
           {"enddate",contract.getEnddate()},
           {"contractstatus",contract.getContractstatus()},
           {"contractnumber",contract.getContractnumber()},
           {"contracttype",contract.getContracttype()},
           {"paymethod",contract.getPaymethod()},
           {"clientcontractor",contract.getClientcontractor()},
           {"ourcontractor",contract.getOurcontractor()},
           {"staffid",contract.getStaffid()},
           {"signingdate",contract.getSigningdate()},
           {"attachment",contract.getAttachment()},
           {"contractremarks",contract.getContractremarks()},
           {"opportunitytitle",contract.getOpportunitytitle()},
           {"estimatedamount",contract.getEstimatedamount()},
           {"successrate",contract.getSuccessrate()},
           {"expecteddate",contract.getExpecteddate()},
           {"opportunitystatus",contract.getOpportunitystatus()},
           {"channel",contract.getChannel()},
           {"businesstype",contract.getBusinesstype()},
           {"acquisitiondate",contract.getAcquisitiondate()},
           {"opportunitiessource",contract.getOpportunitiessource()},
           {"opportunityremarks",contract.getOpportunityremarks()},
           {"customername",contract.getCustomername()},
           {"profile",contract.getProfile()},
           {"customertype",contract.getCustomertype()},
           {"customerstatus",contract.getCustomerstatus()}
        };
        new Thread(new Runnable() {
            @Override
            public void run() {
                String js_string=connect("contract_create_json",param_contract);
                Message message=new Message();
                message.what=MessageType.RESULT_ADD_OBJECT;
                try {
                    JSONObject jsonObject=new JSONObject(js_string);
                    String resultcode=jsonObject.getString("resultcode");
                    if (resultcode.equals("0")){
                        message.obj="合同添加成功";
                    }else {
                        message.obj="合同添加失败";
                    }
                    handler.sendMessage(message);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }).start();

    }
    /*修改合同*/
    public static void modifyObject(final Handler handler,final Contract contract){
        final String[][] param_contract={
                {"contractid",contract.getContractid()},
                {"contracttitle",contract.getContracttitle()},
                {"opportunityid",contract.getOpportunityid()},
                {"customerid",contract.getCustomerid()},
                {"totalamount",contract.getTotalamount()},
                {"startdate",contract.getStartdate()},
                {"enddate",contract.getEnddate()},
                {"contractstatus",contract.getContractstatus()},
                {"contractnumber",contract.getContractnumber()},
                {"contracttype",contract.getContracttype()},
                {"paymethod",contract.getPaymethod()},
                {"clientcontractor",contract.getClientcontractor()},
                {"ourcontractor",contract.getOurcontractor()},
                {"staffid",contract.getStaffid()},
                {"signingdate",contract.getSigningdate()},
                {"attachment",contract.getAttachment()},
                {"contractremarks",contract.getContractremarks()},
                {"opportunitytitle",contract.getOpportunitytitle()},
                {"estimatedamount",contract.getEstimatedamount()},
                {"successrate",contract.getSuccessrate()},
                {"expecteddate",contract.getExpecteddate()},
                {"opportunitystatus",contract.getOpportunitystatus()},
                {"channel",contract.getChannel()},
                {"businesstype",contract.getBusinesstype()},
                {"acquisitiondate",contract.getAcquisitiondate()},
                {"opportunitiessource",contract.getOpportunitiessource()},
                {"opportunityremarks",contract.getOpportunityremarks()},
                {"customername",contract.getCustomername()},
                {"profile",contract.getProfile()},
                {"customertype",contract.getCustomertype()},
                {"customerstatus",contract.getCustomerstatus()}
        };
        new Thread(new Runnable() {
            @Override
            public void run() {
                String js_string=connect("contract_modify_json",param_contract);
                Message message=new Message();
                message.what=MessageType.RESULT_MODIFY_OBJECT;
                try {
                    JSONObject jsonObject=new JSONObject(js_string);
                    String resultcode=jsonObject.getString("resultcode");
                    if (resultcode.equals("0")){
                        message.obj="合同信息修改成功";
                    }else {
                        message.obj="合同信息修改失败";
                    }
                    handler.sendMessage(message);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }).start();

    }
    /*新增联系人*/
    public static void addObject(final Handler handler,final Contact contact) {
        final String[][] param_contract = {
            {"customerid",contact.getCustomerid()},
            {"contactsname",contact.getContactsname()},
            {"contactsage",contact.getContactsage()},
            {"contactsgender",contact.getContactsgender()},
            {"contactsmobile",contact.getContactsmobile()},
            {"contactstelephone",contact.getContactstelephone()},
            {"contactsemail",contact.getContactsemail()},
            {"contactsaddress",contact.getContactsaddress()},
            {"contactszipcode",contact.getContactszipcode()},
            {"contactsqq",contact.getContactsqq()},
            {"contactswechat",contact.getContactswechat()},
            {"contactswangwang",contact.getContactswangwang()},
            {"contactsdeptname",contact.getContactsname()},
            {"contactsposition",contact.getContactsposition()},
            {"contactsremarks",contact.getContactsremarks()},
            {"customername",contact.getCustomername()},
            {"profile",contact.getProfile()},
            {"customertype",contact.getCustomertype()},
            {"customerstatus",contact.getCustomerstatus()},
            {"regionid",contact.getRegionid()},
            {"parentcustomerid",contact.getParentcustomerid()},
            {"customersource",contact.getCustomersource()},
            {"size",contact.getSize()},
            {"telephone",contact.getTelephone()},
            {"email",contact.getEmail()},
            {"website",contact.getWebsite()},
            {"address",contact.getAddress()},
            {"zipcode",contact.getZipcode()},
            {"staffid",contact.getStaffid()},
            {"createdate",contact.getCreatedate()},
            {"customerremarks",contact.getCustomerremarks()}
        };
        new Thread(new Runnable() {
            @Override
            public void run() {
                String js_string = connect("contact_create_json", param_contract);
                Message message = new Message();
                message.what = MessageType.RESULT_ADD_OBJECT;
                try {
                    JSONObject jsonObject = new JSONObject(js_string);
                    String resultcode = jsonObject.getString("resultcode");
                    if (resultcode.equals("0")) {
                        message.obj = "联系人添加成功";
                    } else {
                        message.obj = "联系人添加失败";
                    }
                    handler.sendMessage(message);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }
    /*修改联系人*/
    public static void modifyObject(final Handler handler,final Contact contact) {
        final String[][] param_contract = {
                {"contactsid",contact.getContactsid()},
                {"customerid",contact.getCustomerid()},
                {"contactsname",contact.getContactsname()},
                {"contactsage",contact.getContactsage()},
                {"contactsgender",contact.getContactsgender()},
                {"contactsmobile",contact.getContactsmobile()},
                {"contactstelephone",contact.getContactstelephone()},
                {"contactsemail",contact.getContactsemail()},
                {"contactsaddress",contact.getContactsaddress()},
                {"contactszipcode",contact.getContactszipcode()},
                {"contactsqq",contact.getContactsqq()},
                {"contactswechat",contact.getContactswechat()},
                {"contactswangwang",contact.getContactswangwang()},
                {"contactsdeptname",contact.getContactsname()},
                {"contactsposition",contact.getContactsposition()},
                {"contactsremarks",contact.getContactsremarks()},
                {"customername",contact.getCustomername()},
                {"profile",contact.getProfile()},
                {"customertype",contact.getCustomertype()},
                {"customerstatus",contact.getCustomerstatus()},
                {"regionid",contact.getRegionid()},
                {"parentcustomerid",contact.getParentcustomerid()},
                {"customersource",contact.getCustomersource()},
                {"size",contact.getSize()},
                {"telephone",contact.getTelephone()},
                {"email",contact.getEmail()},
                {"website",contact.getWebsite()},
                {"address",contact.getAddress()},
                {"zipcode",contact.getZipcode()},
                {"staffid",contact.getStaffid()},
                {"createdate",contact.getCreatedate()},
                {"customerremarks",contact.getCustomerremarks()}
        };
        new Thread(new Runnable() {
            @Override
            public void run() {
                String js_string = connect("contact_modify_json", param_contract);
                Message message = new Message();
                message.what = MessageType.RESULT_MODIFY_OBJECT;
                try {
                    JSONObject jsonObject = new JSONObject(js_string);
                    String resultcode = jsonObject.getString("resultcode");
                    if (resultcode.equals("0")) {
                        message.obj = "联系人信息修改成功";
                    } else {
                        message.obj = "联系人信息修改失败";
                    }
                    handler.sendMessage(message);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }

    public static String[][] removeNullParams(String[][] old_param){
        ArrayList<String[]> tempArray=new ArrayList<String[]>();

        for(int i=0;i<old_param.length;i++){
            if(!old_param[i][1].equals("")&&!old_param[i][1].equals("null")){
                tempArray.add(old_param[i]);
            }
        }

        String[][] new_params=new String[tempArray.size()][2];
        for(int i=0;i<tempArray.size();i++){
            new_params[i]=tempArray.get(i);
        }
        return new_params;
    };
}
