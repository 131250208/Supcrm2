package com.example.a15850.supcrm;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.Serializable;

/**
 * Created by 15850 on 2016/5/9.
 */
public class Contract implements Serializable {

    private String contractid="";
    private String contracttitle="";
    private String opportunityid="";
    private String customerid="";
    private String totalamount="";
    private String startdate="";
    private String enddate="";
    private String contractstatus="";
    private String contractnumber="";
    private String contracttype="";
    private String paymethod="";
    private String clientcontractor="";
    private String ourcontractor="";
    private String staffid="";
    private String signingdate="";
    private String attachment="";
    private String contractremarks="";
    private String opportunitytitle="";
    private String estimatedamount="";
    private String successrate="";
    private String expecteddate="";
    private String opportunitystatus="";
    private String channel="";
    private String businesstype="";
    private String acquisitiondate="";
    private String opportunitiessource="";
    private String opportunityremarks="";
    private String customername="";
    private String profile="";
    private String customertype="";
    private String customerstatus="";

    @Override
    public String toString() {
        return "contractid:"+contractid+"\n"
                +"contracttitle:"+contracttitle+"\n"
                +"opportunityid:"+opportunityid+"\n"
                +"customerid:"+customerid+"\n"
                +"totalamount:"+totalamount+"\n"
                +"startdate:"+startdate+"\n"
                +"enddate:"+enddate+"\n"
                +"contractstatus:"+contractstatus+"\n"
                +"contractnumber:"+contractnumber+"\n"
                +"contracttype:"+contracttype+"\n"
                +"paymethod:"+paymethod+"\n"
                +"clientcontractor:"+clientcontractor+"\n"
                +"ourcontractor:"+ourcontractor+"\n"
                +"staffid:"+staffid+"\n"
                +"signingdate:"+signingdate+"\n"
                +"attachment:"+attachment+"\n"
                +"contractremarks:"+contractremarks+"\n"
                +"opportunitytitle:"+opportunitytitle+"\n"
                +"estimatedamount:"+estimatedamount+"\n"
                +"successrate:"+successrate+"\n"
                +"expecteddate:"+expecteddate+"\n"
                +"opportunitystatus:"+opportunitystatus+"\n"
                +"channel:"+channel+"\n"
                +"businesstype:"+businesstype+"\n"
                +"acquisitiondate:"+acquisitiondate+"\n"
                +"opportunitiessource:"+opportunitiessource+"\n"
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
                +"customerremarks:"+customerremarks+"\n"
                ;
    }

    public void Initial(JSONObject jsonObject){
        try {
            this.setContractid(jsonObject.getString("contractid"));
            this.setContracttitle(jsonObject.getString("contracttitle"));
            this.setOpportunityid(jsonObject.getString("opportunityid"));
            this.setCustomerid(jsonObject.getString("customerid"));
            this.setTotalamount(jsonObject.getString("totalamount"));
            this.setStartdate(jsonObject.getString("startdate"));
            this.setEnddate(jsonObject.getString("enddate"));
            this.setContractstatus(jsonObject.getString("contractstatus"));
            this.setContractnumber(jsonObject.getString("contractnumber"));
            this.setContracttype(jsonObject.getString("contracttype"));
            this.setPaymethod(jsonObject.getString("paymethod"));
            this.setClientcontractor(jsonObject.getString("clientcontractor"));
            this.setOurcontractor(jsonObject.getString("ourcontractor"));
            this.setStaffid(jsonObject.getString("staffid"));
            this.setSigningdate(jsonObject.getString("signingdate"));
            this.setAttachment(jsonObject.getString("attachment"));
            this.setContractremarks(jsonObject.getString("contractremarks"));
            this.setOpportunitytitle(jsonObject.getString("opportunitytitle"));
            this.setEstimatedamount(jsonObject.getString("estimatedamount"));
            this.setSuccessrate(jsonObject.getString("successrate"));
            this.setExpecteddate(jsonObject.getString("expecteddate"));
            this.setOpportunitystatus(jsonObject.getString("opportunitystatus"));
            this.setChannel(jsonObject.getString("channel"));
            this.setBusinesstype(jsonObject.getString("businesstype"));
            this.setAcquisitiondate(jsonObject.getString("acquisitiondate"));
            this.setOpportunitiessource(jsonObject.getString("opportunitiessource"));
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
    public String getContractid() {
        return contractid;
    }

    public void setContractid(String contractid) {
        this.contractid = contractid;
    }

    public String getContracttitle() {
        return contracttitle;
    }

    public void setContracttitle(String contracttitle) {
        this.contracttitle = contracttitle;
    }

    public String getOpportunityid() {
        return opportunityid;
    }

    public void setOpportunityid(String opportunityid) {
        this.opportunityid = opportunityid;
    }

    public String getCustomerid() {
        return customerid;
    }

    public void setCustomerid(String customerid) {
        this.customerid = customerid;
    }

    public String getTotalamount() {
        return totalamount;
    }

    public void setTotalamount(String totalamount) {
        this.totalamount = totalamount;
    }

    public String getStartdate() {
        return startdate;
    }

    public void setStartdate(String startdate) {
        this.startdate = startdate;
    }

    public String getEnddate() {
        return enddate;
    }

    public void setEnddate(String enddate) {
        this.enddate = enddate;
    }

    public String getContractstatus_chinese() {
        switch (contractstatus){
            case "1":return "未开始";
            case "2":return "执行中";
            case "3":return "成功结束";
            case "4":return "意外终止";
        }
        return contractstatus;
    }
    public String getContractstatus() {
         return contractstatus;
    }

    public void setContractstatus(String contractstatus) {
        switch (contractstatus){
            case "未开始":this.contractstatus = "1";
                break;
            case "执行中":this.contractstatus = "2";
                break;
            case "成功结束":this.contractstatus = "3";
                break;
            case "意外终止":this.contractstatus = "4";
                break;
            default:this.contractstatus = contractstatus;
                break;
        }
    }

    public String getContractnumber() {
        return contractnumber;
    }

    public void setContractnumber(String contractnumber) {
        this.contractnumber = contractnumber;
    }

    public String getContracttype_chinese() {
        switch (contracttype){
            case "1":return "重要合同";
            case "2":return "一般合同";
            case "3":return "低价值合同";
        }
        return contracttype;
    }
    public String getContracttype() {
        return contracttype;
    }

    public void setContracttype(String contracttype) {
        switch (contracttype){
            case "重要合同":this.contracttype = "1";
                break;
            case "一般合同":this.contracttype = "2";
                break;
            case "低价值合同":this.contracttype = "3";
                break;
            default:this.contracttype = contracttype;
                break;
        }
    }

    public String getPaymethod() {
        return paymethod;
    }

    public void setPaymethod(String paymethod) {
        this.paymethod = paymethod;
    }

    public String getClientcontractor() {
        return clientcontractor;
    }

    public void setClientcontractor(String clientcontractor) {
        this.clientcontractor = clientcontractor;
    }

    public String getOurcontractor() {
        return ourcontractor;
    }

    public void setOurcontractor(String ourcontractor) {
        this.ourcontractor = ourcontractor;
    }

    public String getStaffid() {
        return staffid;
    }

    public void setStaffid(String staffid) {
        this.staffid = staffid;
    }

    public String getSigningdate() {
        return signingdate;
    }

    public void setSigningdate(String signingdate) {
        this.signingdate = signingdate;
    }

    public String getAttachment() {
        return attachment;
    }

    public void setAttachment(String attachment) {
        this.attachment = attachment;
    }

    public String getContractremarks() {
        return contractremarks;
    }

    public void setContractremarks(String contractremarks) {
        this.contractremarks = contractremarks;
    }

    public String getOpportunitytitle() {
        return opportunitytitle;
    }

    public void setOpportunitytitle(String opportunitytitle) {
        this.opportunitytitle = opportunitytitle;
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

    public String getOpportunitystatus() {
        return opportunitystatus;
    }

    public void setOpportunitystatus(String opportunitystatus) {
        this.opportunitystatus = opportunitystatus;
    }

    public String getChannel() {
        return channel;
    }

    public void setChannel(String channel) {
        this.channel = channel;
    }

    public String getBusinesstype() {
        return businesstype;
    }

    public void setBusinesstype(String businesstype) {
        this.businesstype = businesstype;
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

    private String regionid;
    private String parentcustomerid;
    private String customersource;
    private String size;
    private String telephone;
    private String email;
    private String website;
    private String address;
    private String zipcode;
    private String createdate;
    private String customerremarks;

        public Contract(){


    }

}
