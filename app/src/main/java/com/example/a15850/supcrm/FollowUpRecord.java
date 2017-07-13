package com.example.a15850.supcrm;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.Serializable;

/**
 * Created by 15850 on 2016/6/6.
 */
public class FollowUpRecord implements Serializable{
    private String followupid="";
    private String sourceid="";
    private String sourcetype="";
    private String followuptype="";
    private String createtime="";
    private String creatorid="";
    private String content="";
    private String followupremarks="";
    private String customerid="";
    private String customername="";
    private String profile="";
    private String customertype="";
    private String customerstatus="";
    private String regionid="";
    private String parentcustomerid="";
    private String customersource="";
    private String size="";
    private String telephone="";
    private String email="";
    private String website="";
    private String address="";
    private String zipcode="";
    private String staffid="";
    private String createdate="";
    private String customerremarks="";
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
    private String weixinid="";
    private String avatar="";
    private String extattr="";
    private String staffstatus="";
    private String enable="";
    private String staffremarks="";

    public void Initial(JSONObject jsonObject){
        try {
            this.setFollowupid(jsonObject.getString("followupid"));
            this.setSourceid(jsonObject.getString("sourceid"));
            this.setSourcetype(jsonObject.getString("sourcetype"));
            this.setFollowuptype(jsonObject.getString("followuptype"));
            this.setCreatetime(jsonObject.getString("createtime"));
            this.setCreatorid(jsonObject.getString("creatorid"));
            this.setContent(jsonObject.getString("content"));
            this.setFollowupremarks(jsonObject.getString("followupremarks"));
            this.setCustomerid(jsonObject.getString("customerid"));
            this.setCustomername(jsonObject.getString("customername"));
            this.setProfile(jsonObject.getString("profile"));
            this.setCustomertype(jsonObject.getString("customertype"));
            this.setCustomerstatus(jsonObject.getString("customerstatus"));
            this.setRegionid(jsonObject.getString("regionid"));
            this.setParentcustomerid(jsonObject.getString("parentcustomerid"));
            this.setCustomersource(jsonObject.getString("customersource"));
            this.setSize(jsonObject.getString("size"));
            this.setTelephone(jsonObject.getString("telephone"));
            this.setEmail(jsonObject.getString("email"));
            this.setWebsite(jsonObject.getString("website"));
            this.setAddress(jsonObject.getString("address"));
            this.setZipcode(jsonObject.getString("zipcode"));
            this.setStaffid(jsonObject.getString("staffid"));
            this.setCreatedate(jsonObject.getString("createdate"));
            this.setCustomerremarks(jsonObject.getString("customerremarks"));
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
            this.setWeixinid(jsonObject.getString("weixinid"));
            this.setAvatar(jsonObject.getString("avatar"));
            this.setExtattr(jsonObject.getString("extattr"));
            this.setStaffstatus(jsonObject.getString("staffstatus"));
            this.setEnable(jsonObject.getString("enable"));
            this.setStaffremarks(jsonObject.getString("staffremarks"));
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    @Override
    public String toString() {
        return "followupid:"+followupid+"\n"
                +"sourceid:"+sourceid+"\n"
                +"sourcetype:"+sourcetype+"\n"
                +"followuptype:"+followuptype+"\n"
                +"createtime:"+createtime+"\n"
                +"creatorid:"+creatorid+"\n"
                +"content:"+content+"\n"
                +"followupremarks:"+followupremarks+"\n"
                +"customerid:"+customerid+"\n"
                +"customername:"+customername+"\n"
                +"profile:"+profile+"\n"
                +"customertype:"+customertype+"\n"
                +"customerstatus:"+customerstatus+"\n"
                +"regionid:"+regionid+"\n"
                +"parentcustomerid:"+parentcustomerid+"\n"
                +"customersource:"+customersource+"\n"
                +"size:"+size+"\n"
                +"telephone:"+telephone+"\n"
                +"email:"+email+"\n"
                +"website:"+website+"\n"
                +"address:"+address+"\n"
                +"zipcode:"+zipcode+"\n"
                +"staffid:"+staffid+"\n"
                +"createdate:"+createdate+"\n"
                +"customerremarks:"+customerremarks+"\n"
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
                +"weixinid:"+weixinid+"\n"
                +"avatar:"+avatar+"\n"
                +"extattr:"+extattr+"\n"
                +"staffstatus:"+staffstatus+"\n"
                +"enable:"+enable+"\n"
                +"staffremarks:"+staffremarks+"\n";
    }

    public String getFollowupid() {
        return followupid;
    }

    public void setFollowupid(String followupid) {
        this.followupid = followupid;
    }

    public String getSourceid() {
        return sourceid;
    }

    public void setSourceid(String sourceid) {
        this.sourceid = sourceid;
    }

    public String getSourcetype() {
        return sourcetype;
    }

    public void setSourcetype(String sourcetype) {
        this.sourcetype = sourcetype;
    }

    public String getFollowuptype() {
        return followuptype;
    }

    public void setFollowuptype(String followuptype) {
        this.followuptype = followuptype;
    }

    public String getCreatetime() {
        return createtime;
    }

    public void setCreatetime(String createtime) {
        this.createtime = createtime;
    }

    public String getCreatorid() {
        return creatorid;
    }

    public void setCreatorid(String creatorid) {
        this.creatorid = creatorid;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getFollowupremarks() {
        return followupremarks;
    }

    public void setFollowupremarks(String followupremarks) {
        this.followupremarks = followupremarks;
    }

    public String getCustomerid() {
        return customerid;
    }

    public void setCustomerid(String customerid) {
        this.customerid = customerid;
    }

    public String getCustomername() {
        return customername;
    }

    public void setCustomername(String customername) {
        this.customername = customername;
    }

    public String getProfile() {
        return profile;
    }

    public void setProfile(String profile) {
        this.profile = profile;
    }

    public String getCustomertype() {
        return customertype;
    }

    public void setCustomertype(String customertype) {
        this.customertype = customertype;
    }

    public String getCustomerstatus() {
        return customerstatus;
    }

    public void setCustomerstatus(String customerstatus) {
        this.customerstatus = customerstatus;
    }

    public String getRegionid() {
        return regionid;
    }

    public void setRegionid(String regionid) {
        this.regionid = regionid;
    }

    public String getParentcustomerid() {
        return parentcustomerid;
    }

    public void setParentcustomerid(String parentcustomerid) {
        this.parentcustomerid = parentcustomerid;
    }

    public String getCustomersource() {
        return customersource;
    }

    public void setCustomersource(String customersource) {
        this.customersource = customersource;
    }

    public String getSize() {
        return size;
    }

    public void setSize(String size) {
        this.size = size;
    }

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getWebsite() {
        return website;
    }

    public void setWebsite(String website) {
        this.website = website;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getZipcode() {
        return zipcode;
    }

    public void setZipcode(String zipcode) {
        this.zipcode = zipcode;
    }

    public String getStaffid() {
        return staffid;
    }

    public void setStaffid(String staffid) {
        this.staffid = staffid;
    }

    public String getCreatedate() {
        return createdate;
    }

    public void setCreatedate(String createdate) {
        this.createdate = createdate;
    }

    public String getCustomerremarks() {
        return customerremarks;
    }

    public void setCustomerremarks(String customerremarks) {
        this.customerremarks = customerremarks;
    }

    public String getUserid() {
        return userid;
    }

    public void setUserid(String userid) {
        this.userid = userid;
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



    }
