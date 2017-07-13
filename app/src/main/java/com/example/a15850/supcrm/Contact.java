package com.example.a15850.supcrm;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.Serializable;

/**
 * Created by 15850 on 2016/5/8.
 */
public class Contact implements Serializable {
    private String contactsid="";
    private String customerid="";
    private String contactsname="";
    private String contactsage="";
    private String contactsgender="";
    private String contactsmobile="";
    private String contactstelephone="";
    private String contactsemail="";
    private String contactsaddress="";
    private String contactszipcode="";
    private String contactsqq="";
    private String contactswechat="";
    private String contactswangwang="";
    private String contactsdeptname="";
    private String contactsposition="";
    private String contactsremarks="";
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

    public void Initial(JSONObject jsonObject){
        try {
            this.setContactsid(jsonObject.getString("contactsid"));
            this.setCustomerid(jsonObject.getString("customerid"));
            this.setContactsname(jsonObject.getString("contactsname"));
            this.setContactsage(jsonObject.getString("contactsage"));
            this.setContactsgender(jsonObject.getString("contactsgender"));
            this.setContactsmobile(jsonObject.getString("contactsmobile"));
            this.setContactstelephone(jsonObject.getString("contactstelephone"));
            this.setContactsemail(jsonObject.getString("contactsemail"));
            this.setContactsaddress(jsonObject.getString("contactsaddress"));
            this.setContactszipcode(jsonObject.getString("contactszipcode"));
            this.setContactsqq(jsonObject.getString("contactsqq"));
            this.setContactswechat(jsonObject.getString("contactswechat"));
            this.setContactswangwang(jsonObject.getString("contactswangwang"));
            this.setContactsdeptname(jsonObject.getString("contactsdeptname"));
            this.setContactsposition(jsonObject.getString("contactsposition"));
            this.setContactsremarks(jsonObject.getString("contactsremarks"));
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
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    @Override
    public String toString() {
        return  "contactsid:"+contactsid+"\n"
                +"customerid:"+customerid+"\n"
                +"contactsname:"+contactsname+"\n"
                +"contactsage:"+contactsage+"\n"
                +"contactsgender:"+contactsgender+"\n"
                +"contactsmobile:"+contactsmobile+"\n"
                +"contactstelephone:"+contactstelephone+"\n"
                +"contactsemail:"+contactsemail+"\n"
                +"contactsaddress:"+contactsaddress+"\n"
                +"contactszipcode:"+contactszipcode+"\n"
                +"contactsqq:"+contactsqq+"\n"
                +"contactswechat:"+contactswechat+"\n"
                +"contactswangwang:"+contactswangwang+"\n"
                +"contactsdeptname:"+contactsdeptname+"\n"
                +"contactsposition:"+contactsposition+"\n"
                +"contactsremarks:"+contactsremarks+"\n"
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
                +"customerremarks:"+customerremarks+"\n";

    }

    public String getContactsid() {
        return contactsid;
    }

    public void setContactsid(String contactsid) {
        this.contactsid = contactsid;
    }

    public String getCustomerid() {
        return customerid;
    }

    public void setCustomerid(String customerid) {
        this.customerid = customerid;
    }

    public String getContactsname() {
        return contactsname;
    }

    public void setContactsname(String contactsname) {
        this.contactsname = contactsname;
    }

    public String getContactsage() {
        return contactsage;
    }

    public void setContactsage(String contactsage) {
        this.contactsage = contactsage;
    }

    public String getContactsgender() {
        return contactsgender;
    }

    public void setContactsgender(String contactsgender) {
        this.contactsgender = contactsgender;
    }

    public String getContactsmobile() {
        return contactsmobile;
    }

    public void setContactsmobile(String contactsmobile) {
        this.contactsmobile = contactsmobile;
    }

    public String getContactstelephone() {
        return contactstelephone;
    }

    public void setContactstelephone(String contactstelephone) {
        this.contactstelephone = contactstelephone;
    }

    public String getContactsemail() {
        return contactsemail;
    }

    public void setContactsemail(String contactsemail) {
        this.contactsemail = contactsemail;
    }

    public String getContactsaddress() {
        return contactsaddress;
    }

    public void setContactsaddress(String contactsaddress) {
        this.contactsaddress = contactsaddress;
    }

    public String getContactszipcode() {
        return contactszipcode;
    }

    public void setContactszipcode(String contactszipcode) {
        this.contactszipcode = contactszipcode;
    }

    public String getContactsqq() {
        return contactsqq;
    }

    public void setContactsqq(String contactsqq) {
        this.contactsqq = contactsqq;
    }

    public String getContactswechat() {
        return contactswechat;
    }

    public void setContactswechat(String contactswechat) {
        this.contactswechat = contactswechat;
    }

    public String getContactswangwang() {
        return contactswangwang;
    }

    public void setContactswangwang(String contactswangwang) {
        this.contactswangwang = contactswangwang;
    }

    public String getContactsdeptname() {
        return contactsdeptname;
    }

    public void setContactsdeptname(String contactsdeptname) {
        this.contactsdeptname = contactsdeptname;
    }

    public String getContactsposition() {
        return contactsposition;
    }

    public void setContactsposition(String contactsposition) {
        this.contactsposition = contactsposition;
    }

    public String getContactsremarks() {
        return contactsremarks;
    }

    public void setContactsremarks(String contactsremarks) {
        this.contactsremarks = contactsremarks;
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



    public Contact(){

    }

}
