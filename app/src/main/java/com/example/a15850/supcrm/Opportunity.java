package com.example.a15850.supcrm;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.Serializable;

/**
 * Created by 15850 on 2016/5/10.
 */
public class Opportunity implements Serializable {
    private String opportunityid="";
    private String opportunitytitle="";
    private String customerid="";
    private String estimatedamount="";
    private String successrate="";
    private String expecteddate="";
    private String opportunitystatus="";
    private String channel="";
    private String businesstype="";
    private String acquisitiondate="";
    private String opportunitiessource="";
    private String staffid="";
    private String opportunityremarks="";
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
    private String createdate="";
    private String customerremarks="";

    public void Initial(JSONObject jsonObject){
        try {
            this.setOpportunityid(jsonObject.getString("opportunityid"));
            this.setOpportunitytitle(jsonObject.getString("opportunitytitle"));
            this.setCustomerid(jsonObject.getString("customerid"));
            this.setEstimatedamount(jsonObject.getString("estimatedamount"));
            this.setSuccessrate(jsonObject.getString("successrate"));
            this.setExpecteddate(jsonObject.getString("expecteddate"));
            this.setOpportunitystatus(jsonObject.getString("opportunitystatus"));
            this.setChannel(jsonObject.getString("channel"));
            this.setBusinesstype(jsonObject.getString("businesstype"));
            this.setAcquisitiondate(jsonObject.getString("acquisitiondate"));
            this.setOpportunitiessource(jsonObject.getString("opportunitiessource"));
            this.setStaffid(jsonObject.getString("staffid"));
            this.setOpportunityremarks(jsonObject.getString("opportunityremarks"));
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
            this.setCreatedate(jsonObject.getString("createdate"));
            this.setCustomerremarks(jsonObject.getString("customerremarks"));
        } catch (JSONException e) {
            e.printStackTrace();
        }

    }

    @Override
    public String toString() {
        return "opportunityid:"+opportunityid+"\n"
                +"opportunitytitle:"+opportunitytitle+"\n"
                +"customerid:"+customerid+"\n"
                +"estimatedamount:"+estimatedamount+"\n"
                +"successrate:"+successrate+"\n"
                +"expecteddate:"+expecteddate+"\n"
                +"opportunitystatus:"+opportunitystatus+"\n"
                +"channel:"+channel+"\n"
                +"businesstype:"+businesstype+"\n"
                +"acquisitiondate:"+acquisitiondate+"\n"
                +"opportunitiessource:"+opportunitiessource+"\n"
                +"staffid:"+staffid+"\n"
                +"opportunityremarks:"+opportunityremarks+"\n"
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
                +"createdate:"+createdate+"\n"
                +"customerremarks:"+customerremarks+"\n";
    }

    public String getOpportunityid() {
        return opportunityid;
    }

    public void setOpportunityid(String opportunityid) {
        this.opportunityid = opportunityid;
    }

    public String getOpportunitytitle() {
        return opportunitytitle;
    }

    public void setOpportunitytitle(String opportunitytitle) {
        this.opportunitytitle = opportunitytitle;
    }

    public String getCustomerid() {
        return customerid;
    }

    public void setCustomerid(String customerid) {
        this.customerid = customerid;
    }

    public String getEstimatedamount() {
        return estimatedamount;
    }

    public void setEstimatedamount(String estimatedamount) {
        this.estimatedamount = estimatedamount;
    }

    public String getSuccessrate() {
        return successrate;
    }

    public void setSuccessrate(String successrate) {
        this.successrate = successrate;
    }

    public String getExpecteddate() {
        return expecteddate;
    }

    public void setExpecteddate(String expecteddate) {
        this.expecteddate = expecteddate;
    }

    public String getOpportunitystatus_chinese() {
        switch (opportunitystatus){
            case "1":
                return "初步洽谈";
            case "2":
                return "需求确定";
            case "3":
                return "方案报价";
            case "4":
                return "谈判合同";
            case "5":
                return "赢单";
            case "6":
                return "输单";
        }
        return opportunitystatus;
    }
    public String getOpportunitystatus() {
        return opportunitystatus;
    }

    public void setOpportunitystatus(String opportunitystatus) {
        switch (opportunitystatus){
            case "初步洽谈":        this.opportunitystatus = "1";
                break;
            case "需求确定":this.opportunitystatus = "2";
                break;
            case "方案报价":this.opportunitystatus = "3";
                break;
            case "谈判合同":this.opportunitystatus = "4";
                break;
            case "赢单":this.opportunitystatus = "5";
                break;
            case "输单":this.opportunitystatus = "6";
                break;
            default:        this.opportunitystatus = opportunitystatus;
                break;
        }
    }

    public String getChannel() {
        return channel;
    }

    public void setChannel(String channel) {
        this.channel = channel;
    }

    public String getBusinesstype_chinese() {
        switch (businesstype){
            case "1":
                return "重要商机";
            case "2":
                return "一般商机";
            case "3":
                return "低价值商机";

        }
        return businesstype;
    }
    public String getBusinesstype() {
        return businesstype;
    }
    public void setBusinesstype(String businesstype) {
        switch (businesstype){
            case "重要商机":        this.businesstype = "1";
                break;
            case "一般商机":this.businesstype = "2";
                break;
            case "低价值商机":this.businesstype = "3";
                break;
            default:this.businesstype = businesstype;
                break;
        }
    }

    public String getAcquisitiondate() {
        return acquisitiondate;
    }

    public void setAcquisitiondate(String acquisitiondate) {
        this.acquisitiondate = acquisitiondate;
    }

    public String getOpportunitiessource() {
        return opportunitiessource;
    }

    public void setOpportunitiessource(String opportunitiessource) {
        this.opportunitiessource = opportunitiessource;
    }

    public String getStaffid() {
        return staffid;
    }

    public void setStaffid(String staffid) {
        this.staffid = staffid;
    }

    public String getOpportunityremarks() {
        return opportunityremarks;
    }

    public void setOpportunityremarks(String opportunityremarks) {
        this.opportunityremarks = opportunityremarks;
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




        public Opportunity(){

    }

}
