package com.example.a15850.supcrm.Data;

import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.EditText;

import com.example.a15850.supcrm.Contact;
import com.example.a15850.supcrm.Enum.MessageType;
import com.example.a15850.supcrm.FollowUpRecord;
import com.example.a15850.supcrm.Kit;
import com.example.a15850.supcrm.R;
import com.example.a15850.supcrm.Staff;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class Activity_TestApi extends AppCompatActivity {

    private EditText api_result;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_activity__test_api);
        api_result=(EditText)findViewById(R.id.api_result_show);
        String[][] param={
                {"currentpage","1"},
                {"search",""}
        };
//        DB_API_Service.findAllObjects(handler,"common_followup_json");
        Kit.sendRequest("common_contract_json",handler,-1,param);
    }

    private Handler handler=new Handler(){
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what){
                case -1://解析并自动输出属性声明和初始化代码
                    String json_string=(String)msg.obj;
                    String out="";
                    try {
                        JSONObject jsonObject=new JSONObject(json_string);
                        String jsonObject_object=jsonObject.getString("0");
                        String[] attributes=jsonObject_object.split(",");

                        for (int i=0;i<attributes.length;i++){
                            String[] temp=attributes[i].split(":");
                            String attribute=temp[0].replaceAll("\"","");
//                            out+="private String "+attribute+";\n";//输出属性定义语句
//                            首字母转换大写
//                            byte[] items = attribute.getBytes();
//                            items[0] = (byte)((char)items[0] - ( 'a' - 'A'));
//                            String attribute_u = new String(items);
//                            out+="this.set"+attribute_u+"(jsonObject.getString(\""+attribute+"\"));\n";//输出初始化语句

                            out+="\""+attribute+":\"+"+attribute+"+\"\\n\""+"\n+";
                        }
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                    Log.i("json",out);
                    api_result.setText(json_string);
                    break;
                case MessageType.API_COMMON_FOLLOWUP_JSON:
                    String obj_string=(String)msg.obj;
                    ArrayList<FollowUpRecord> objects=(ArrayList<FollowUpRecord>)Kit.stringToObject(obj_string);
                    String result="";
                    for(int i=0;i<objects.size();i++){
                        result+="object_"+String.valueOf(i+1)+":\n"+objects.get(i).toString();
                    }
                    api_result.setText(result);
                    break;
//                case MessageType.API_COMMON_CONTACTS_PAGESIZE_JSON:
//                    String jsonString_pagesize=(String) msg.obj;
//                    try {
//                        JSONObject jobject_pagesize=new JSONObject(jsonString_pagesize);
//                        int pagecount=Integer.parseInt(jobject_pagesize.getString("pagecount"));//页数
//                        for(int i=0;i<pagecount;i++){
//                            String[][] param={
//                                    {"currentpage",String.valueOf(i+1)},
//                                    {"search",""}
//                            };
//                            Kit.sendRequest("http://nqiwx.mooctest.net:8090/wexin.php/Api/Index/common_contacts_json",handler,MessageType.API_COMMON_CONTACTS_ALL_JSON,param);
//                        }
//                    } catch (JSONException e) {
//                        e.printStackTrace();
//                    }
//
//
//                    break;
//                case MessageType.API_COMMON_CONTACTS_ALL_JSON:
//                    StringBuilder response=new StringBuilder();
//                    String jsonString=(String) msg.obj;
//
//                    try {
//                        JSONObject jobject=new JSONObject(jsonString);
//                        int currentcount=Integer.parseInt(jobject.getString("currentcount"));//当前页联系人数量
//                        //输出联系人信息
//                        for(int i=0;i<currentcount;i++){
//                            JSONObject contact=jobject.getJSONObject(String.valueOf(i));
//                            response.append(contact.getString("contactsname"));
//                            response.append("-");
//                            response.append(contact.getString("contactsage"));
//                            response.append("\n");
//                        }
//                    } catch (JSONException e) {
//                        e.printStackTrace();
//                    }
//                    api_result.setText(response);
//                    break;

            }
        }
    };

}
