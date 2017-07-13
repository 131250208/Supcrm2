package com.example.a15850.supcrm;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.Serializable;

/**
 * Created by 15850 on 2016/5/10.
 */
public class Product implements Serializable {
    private String productid="";
    private String productname="";
    private String productsn="";
    private String suggestedprice="";
    private String standardprice="";
    private String productnumber="";
    private String salesunit="";
    private String unitcost="";
    private String classification="";
    private String picture="";
    private String introduction="";
    private String productremarks="";

    public void Initial(JSONObject jsonObject){
        try {
            this.setProductid(jsonObject.getString("productid"));
            this.setProductname(jsonObject.getString("productname"));
            this.setProductsn(jsonObject.getString("productsn"));
            this.setStandardprice(jsonObject.getString("standardprice"));
            this.setSalesunit(jsonObject.getString("salesunit"));
            this.setUnitcost(jsonObject.getString("unitcost"));
            this.setClassification(jsonObject.getString("classification"));
            this.setPicture(jsonObject.getString("picture"));
            this.setIntroduction(jsonObject.getString("introduction"));
            this.setProductremarks(jsonObject.getString("productremarks"));
        } catch (JSONException e) {
            e.printStackTrace();
        }

    }

    @Override
    public String toString() {
        return "productid:"+productid+"\n"
                +"productname:"+productname+"\n"
                +"productsn:"+productsn+"\n"
                +"standardprice:"+standardprice+"\n"
                +"suggestedprice"+suggestedprice+"\n"
                +"productnumber"+productnumber+"\n"
                +"salesunit:"+salesunit+"\n"
                +"unitcost:"+unitcost+"\n"
                +"classification:"+classification+"\n"
                +"picture:"+picture+"\n"
                +"introduction:"+introduction+"\n"
                +"productremarks:"+productremarks+"\n";
    }

    public String getProductid() {
        return productid;
    }

    public void setProductid(String productid) {
        this.productid = productid;
    }

    public String getProductname() {
        return productname;
    }

    public void setProductname(String productname) {
        this.productname = productname;
    }

    public String getProductsn() {
        return productsn;
    }

    public void setProductsn(String productsn) {
        this.productsn = productsn;
    }

    public String getStandardprice() {
        return standardprice;
    }

    public void setStandardprice(String standardprice) {
        this.standardprice = standardprice;
    }

    public String getSalesunit() {
        return salesunit;
    }

    public void setSalesunit(String salesunit) {
        this.salesunit = salesunit;
    }

    public String getUnitcost() {
        return unitcost;
    }

    public void setUnitcost(String unitcost) {
        this.unitcost = unitcost;
    }

    public String getClassification() {
        return classification;
    }

    public void setClassification(String classification) {
        this.classification = classification;
    }

    public String getPicture() {
        return picture;
    }

    public void setPicture(String picture) {
        this.picture = picture;
    }

    public String getIntroduction() {
        return introduction;
    }

    public void setIntroduction(String introduction) {
        this.introduction = introduction;
    }

    public String getProductremarks() {
        return productremarks;
    }

    public void setProductremarks(String productremarks) {
        this.productremarks = productremarks;
    }


    public Product(){

    }

    public String getSuggestedprice() {
        return suggestedprice;
    }

    public void setSuggestedprice(String suggestedprice) {
        this.suggestedprice = suggestedprice;
    }

    public String getProductnumber() {
        return productnumber;
    }

    public void setProductnumber(String productnumber) {
        this.productnumber = productnumber;
    }
}
