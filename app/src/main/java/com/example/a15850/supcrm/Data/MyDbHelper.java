package com.example.a15850.supcrm.Data;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;

/**
 * Created by 15850 on 2016/5/20.
 */
public class MyDbHelper extends SQLiteOpenHelper {
    /*建表语句*/
    public static final String create_crm_admingroup="CREATE TABLE crm_admingroup ( " +
            "admingroupid integer PRIMARY KEY AUTOINCREMENT, "+
            "name TEXT ,"+
           "chinesename TEXT,"+
            "flag TEXT ,"+
            "createtime NUMERIC ,"+
            "updatetime NUMERIC ,"+
            "remarks TEXT)";
    public static final String createTable_crm_adminuser="CREATE TABLE `crm_adminuser` " +
            "( `adminuserid` integer PRIMARY KEY AUTOINCREMENT,"+ 
            "`groupid` integer ,"+
            " `username` TEXT ,"+
            " `password` TEXT ,"+
            "`userimage` TEXT,"+
            "`status` integer,"+
            "`email` TEXT ,"+
            "`realname` TEXT,"+
            " `sex` TEXT ,"+
            "`tel` TEXT,"+
            "`mobile` TEXT ,"+
            " `fax` TEXT ,"+
            "`web_url` TEXT ,"+
            " `address` TEXT ,"+
            " `login_count`integer,"+
            "`createtime` NUMERIC ,"+
            "`updatetime` NUMERIC ,"+
            " `last_logintime` NUMERIC ,"+
            " `last_ip` TEXT )";
           
    public static final String createTable_crm_contacts="CREATE TABLE `crm_contacts`" +
            " (  `contactsid` integer PRIMARY KEY AUTOINCREMENT , " +
            " `customerid` integer ,  " +
            "`contactsname` TEXT,  " +
            "`contactsage` integer ," +
            " `contactsgender` text , " +
            " `contactsmobile` TEXT , " +
            "`contactstelephone` TEXT, " +
            "`contactsemail` TEXT ," +
            " `contactsaddress` TEXT , " +
            "`contactszipcode` TEXT," +
            " `contactsqq` TEXT ," +
            "`contactswechat` TEXT , " +
            "`contactswangwang` TEXT , " +
            "`contactsdeptname` TEXT ," +
            " `contactsremarks` TEXT )";

    public static final String createTable_crm_contract="CREATE TABLE `crm_contract` ( `contractid` integer  PRIMARY KEY AUTOINCREMENT,  `contracttitle` TEXT  ,  `opportunityid` integer  , `customerid` integer  , `totalamount` REAL  ,  `startdate` NUMERIC , `enddate` NUMERIC , `contractstatus` integer ,`contractnumber` TEXT , `contracttype` integer  , `paymethod` TEXT , `clientcontractor` TEXT ,  `ourcontractor` TEXT ,  `staffid` integer  ,  `signingdate` NUMERIC ,`attachment` TEXT , `contractremarks` TEXT )";
    public static final String createTable_crm_customer="CREATE TABLE `crm_customer` (  `customerid` integer PRIMARY KEY AUTOINCREMENT,  `customername` TEXT  ,  `profile` text ,`customertype` integer , `customerstatus` integer ,  `regionid` integer  , `customersource` TEXT , `size` integer  , `telephone` TEXT , `email` TEXT , `website` TEXT , `address` TEXT ,  `zipcode` TEXT ,  `staffid` integer   ,  `createdate` NUMERIC ,  `customerremarks` TEXT )";
    public static final String creatTable_crm_department="CREATE TABLE `crm_department` ( `departmentid` integer PRIMARY KEY AUTOINCREMENT, `departmentname` TEXT  , `parentid` integer  ,`departmentorder` integer  ,`departmentstatus` integer , `departmentremarks` TEXT )";
    public static final String createTable_crm_followup="CREATE TABLE `crm_followup` ( `followupid` integer PRIMARY KEY AUTOINCREMENT, `sourceid` integer  , `sourcetype` integer  , `followuptype` integer  , `createtime` NUMERIC NULL ,`creatorid` integer  , `content` text , `followupremarks` TEXT )";
    public static final String createTable_crm_log="CREATE TABLE `crm_log` (`id` integer PRIMARY KEY AUTOINCREMENT,`error` integer   ,`userid` integer   ,`username` TEXT  ,`note` TEXT  ,`ip` TEXT  ,`time` NUMERIC )";
    public static final String createTable_crm_opportunity="CREATE TABLE `crm_opportunity` ( `opportunityid` integer  PRIMARY KEY AUTOINCREMENT, `opportunitytitle` TEXT  ,  `customerid` integer  , `estimatedamount` REAL  ,  `successrate` integer  ,  `expecteddate` NUMERIC ,  `opportunitystatus` integer ,  `channel` TEXT , `businesstype` integer  , `acquisitiondate` NUMERIC ,  `opportunitiessource` TEXT ,`staffid` integer  ,`opportunityremarks` TEXT )";
    public static final String createTable_crm_oprelatedproduct="CREATE TABLE `crm_oprelatedproduct` (  `id` integer  PRIMARY KEY AUTOINCREMENT,  `opportunityid` integer  ,`productid` integer  ,  `productnumber` integer  ,  `suggestprice` REAL   ,  `relatedremarks` TEXT )";
    public static final String createTable_crm_product="CREATE TABLE `crm_product` (  `productid` integer  PRIMARY KEY AUTOINCREMENT,  `productname` TEXT  , `productsn` TEXT , `standardprice` REAL   , `salesunit` TEXT , `unitcost` REAL   , `classification` TEXT  , `picture` TEXT ,  `introduction` text ,  `productremarks` TEXT )";
    public static final String createTable_crm_region="CREATE TABLE `crm_region` (`regionid` integer  PRIMARY KEY AUTOINCREMENT,`country` TEXT  ,`province` TEXT  ,`city` TEXT  ,`district` TEXT  ,`remarks` TEXT )";
    public static final String createTable_crm_remind="CREATE TABLE `crm_remind` (  `remindid` integer  PRIMARY KEY AUTOINCREMENT,`creatorid` integer  ,`createtime` NUMERIC ,`departmentid` integer  ,  `content` text ,`remindtime` integer , `remindcycle` integer ,`sourcetype` integer  ,`sourceid` integer  ,`remarks` TEXT )";
    public static final String createTable_crm_saletarget="CREATE TABLE `crm_saletarget` (  `saletargetid` integer  PRIMARY KEY AUTOINCREMENT,  `departmentid` integer  ,  `staffid` integer  ,  `targetyear` integer  , `m1target` REAL   , `m2target` REAL   ,  `m3target` REAL   , `m4target` REAL   ,  `m5target` REAL   ,`m6target` REAL   , `m7target` REAL   , `m8target` REAL   ,`m9target` REAL   ,`m10target` REAL   ,`m11target` REAL   ,`m12target` REAL   ,`saleremark` TEXT )";
    public static final String createTable_crm_sign="CREATE TABLE `crm_sign` (  `signid` integer  PRIMARY KEY AUTOINCREMENT, `signstaffid` integer   , `visitcustomerid` integer  ,  `signtime` NUMERIC ,  `signplace` TEXT  , `longitude` TEXT  , `latitude` TEXT  ,  `remarks` TEXT )";
    public static final String createTabel_crm_staff="CREATE TABLE `crm_staff` (`staffid` integer  PRIMARY KEY AUTOINCREMENT,`userid` TEXT  ,`openid` TEXT  ,`name` TEXT  ,`departmentid` integer  ,`leaderflag` integer  ,`position` TEXT ,`order` integer  ,`mobile` TEXT ,`tel` TEXT ,`gender` TEXT  ,`email` TEXT ,`weixinid` TEXT ,`avatar` TEXT ,`extattr` text ,`staffstatus` integer ,`enable` integer ,`staffremarks` TEXT )";
    public static final String createTable_crm_workreport="CREATE TABLE `crm_workreport` (  `workreportid` integer  PRIMARY KEY AUTOINCREMENT,`reportstaffid` integer   ,`reportdate` NUMERIC ,`summarytoday` text ,`tomorrowplan` text ,`markingstaffid` integer  ,`cc` text ,`remarks` TEXT )";

    private Context mContext;
    public MyDbHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
        mContext=context;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(create_crm_admingroup);
        db.execSQL(createTable_crm_adminuser);
        db.execSQL(createTable_crm_contacts);
        db.execSQL(createTable_crm_contract);
        db.execSQL(createTable_crm_customer);
        db.execSQL(creatTable_crm_department);
        db.execSQL(createTable_crm_followup);
        db.execSQL(createTable_crm_log);
        db.execSQL(createTable_crm_opportunity);
        db.execSQL(createTable_crm_oprelatedproduct);
        db.execSQL(createTable_crm_product);
        db.execSQL(createTable_crm_region);
        db.execSQL(createTable_crm_remind);
        db.execSQL(createTable_crm_saletarget);
        db.execSQL(createTable_crm_sign);
        db.execSQL(createTabel_crm_staff);
        db.execSQL(createTable_crm_workreport);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("drop table if exists crm_admingroup");
        db.execSQL("drop table if exists crm_adminuser");
        onCreate(db);
        Toast.makeText(mContext, "Update Succeeded", Toast.LENGTH_SHORT).show();
    }
}
