package com.example.a15850.supcrm;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.Serializable;

/**
 * Created by 15850 on 2016/6/7.
 */
public class Department implements Serializable{
    private String departmentid="";
    private String departmentname="";
    private String parentid="";
    private String departmentorder="";
    private String departmentstatus="";
    private String departmentremarks="";

    @Override
    public String toString() {
        return "departmentid:"+departmentid+"\n"
                +"departmentname:"+departmentname+"\n"
                +"parentid:"+parentid+"\n"
                +"departmentorder:"+departmentorder+"\n"
                +"departmentstatus:"+departmentstatus+"\n"
                +"departmentremarks:"+departmentremarks+"\n"
                ;
    }

    public void Initial(JSONObject jsonObject){
        try {
            this.setDepartmentid(jsonObject.getString("departmentid"));
            this.setDepartmentname(jsonObject.getString("departmentname"));
            this.setParentid(jsonObject.getString("parentid"));
            this.setDepartmentorder(jsonObject.getString("departmentorder"));
            this.setDepartmentstatus(jsonObject.getString("departmentstatus"));
            this.setDepartmentremarks(jsonObject.getString("departmentremarks"));
        } catch (JSONException e) {
            e.printStackTrace();
        }

    }
    public String getDepartmentid() {
        return departmentid;
    }

    public void setDepartmentid(String departmentid) {
        this.departmentid = departmentid;
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


}
