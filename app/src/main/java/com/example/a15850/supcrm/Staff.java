package com.example.a15850.supcrm;


import org.json.JSONException;
import org.json.JSONObject;

import java.io.Serializable;

/**
 * Created by 15850 on 2016/6/4.
 */
public class Staff implements Serializable {
    private String staffid="";
    private String userid="";
    private String openid="";
    private String name="";
    private String departmentid="";
    private String leaderflag="";
    private String position="";
    private String order="";
    private String mobile="";
    private String tel="";
    private String gender="";
    private String email="";
    private String weixinid="";
    private String avatar="";
    private String extattr="";
    private String staffstatus="";
    private String enable="";
    private String staffremarks="";
    private String departmentname="";
    private String parentid="";
    private String departmentorder="";
    private String departmentstatus="";
    private String departmentremarks="";

    public void Initial(JSONObject jsonObject){
        //传入staff的属性

        try {
            this.setStaffid(jsonObject.getString("staffid"));
            this.setUserid(jsonObject.getString("userid"));
            this.setOpenid(jsonObject.getString("openid"));
            this.setName(jsonObject.getString("name"));
            this.setDepartmentid(jsonObject.getString("departmentid"));
            this.setLeaderflag(jsonObject.getString("leaderflag"));
            this.setPosition(jsonObject.getString("position"));
            this.setOrder(jsonObject.getString("order"));
            this.setMobile(jsonObject.getString("mobile"));
            this.setTel(jsonObject.getString("tel"));
            this.setGender(jsonObject.getString("gender"));
            this.setEmail(jsonObject.getString("email"));
            this.setWeixinid(jsonObject.getString("weixinid"));
            this.setAvatar(jsonObject.getString("avatar"));
            this.setExtattr(jsonObject.getString("extattr"));
            this.setStaffstatus(jsonObject.getString("staffstatus"));
            this.setEnable(jsonObject.getString("enable"));
            this.setStaffremarks(jsonObject.getString("staffremarks"));
            this.setDepartmentname(jsonObject.getString("departmentname"));
            this.setParentid(jsonObject.getString("parentid"));
            this.setDepartmentorder(jsonObject.getString("departmentorder"));
            this.setDepartmentstatus(jsonObject.getString("departmentstatus"));
            this.setDepartmentremarks(jsonObject.getString("departmentremarks"));
        } catch (JSONException e) {
            e.printStackTrace();
        }

    }
    @Override
    public String toString() {
        return"staffid:"+staffid+"\n"
                +"userid:"+userid+"\n"
                +"openid:"+openid+"\n"
                +"name:"+name+"\n"
                +"departmentid:"+departmentid+"\n"
                +"leaderflag:"+leaderflag+"\n"
                +"position:"+position+"\n"
                +"order:"+order+"\n"
                +"mobile:"+mobile+"\n"
                +"tel:"+tel+"\n"
                +"gender:"+gender+"\n"
                +"email:"+email+"\n"
                +"weixinid:"+weixinid+"\n"
                +"avatar:"+avatar+"\n"
                +"extattr:"+extattr+"\n"
                +"staffstatus:"+staffstatus+"\n"
                +"enable:"+enable+"\n"
                +"staffremarks:"+staffremarks+"\n"
                +"departmentname:"+departmentname+"\n"
                +"parentid:"+parentid+"\n"
                +"departmentorder:"+departmentorder+"\n"
                +"departmentstatus:"+departmentstatus+"\n"
                +"departmentremarks:"+departmentremarks+"\n";


    }

    public String getOpenid() {
        return openid;
    }

    public void setOpenid(String openid) {
        this.openid = openid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDepartmentid() {
        return departmentid;
    }

    public void setDepartmentid(String departmentid) {
        this.departmentid = departmentid;
    }

    public String getLeaderflag() {
        return leaderflag;
    }

    public void setLeaderflag(String leaderflag) {
        this.leaderflag = leaderflag;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public String getOrder() {
        return order;
    }

    public void setOrder(String order) {
        this.order = order;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getTel() {
        return tel;
    }

    public void setTel(String tel) {
        this.tel = tel;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getWeixinid() {
        return weixinid;
    }

    public void setWeixinid(String weixinid) {
        this.weixinid = weixinid;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public String getExtattr() {
        return extattr;
    }

    public void setExtattr(String extattr) {
        this.extattr = extattr;
    }

    public String getStaffstatus() {
        return staffstatus;
    }

    public void setStaffstatus(String staffstatus) {
        this.staffstatus = staffstatus;
    }

    public String getEnable() {
        return enable;
    }

    public void setEnable(String enable) {
        this.enable = enable;
    }

    public String getStaffremarks() {
        return staffremarks;
    }

    public void setStaffremarks(String staffremarks) {
        this.staffremarks = staffremarks;
    }

    public String getDepartmentname() {
        return departmentname;
    }

    public void setDepartmentname(String departmentname) {
        this.departmentname = departmentname;
    }

    public String getParentid() {
        return parentid;
    }

    public void setParentid(String parentid) {
        this.parentid = parentid;
    }

    public String getDepartmentorder() {
        return departmentorder;
    }

    public void setDepartmentorder(String departmentorder) {
        this.departmentorder = departmentorder;
    }

    public String getDepartmentstatus() {
        return departmentstatus;
    }

    public void setDepartmentstatus(String departmentstatus) {
        this.departmentstatus = departmentstatus;
    }

    public String getDepartmentremarks() {
        return departmentremarks;
    }

    public void setDepartmentremarks(String departmentremarks) {
        this.departmentremarks = departmentremarks;
    }



    public String getStaffid() {
        return staffid;
    }

    public void setStaffid(String staffid) {
        this.staffid = staffid;
    }

    public String getUserid() {
        return userid;
    }

    public void setUserid(String userid) {
        this.userid = userid;
    }
}
