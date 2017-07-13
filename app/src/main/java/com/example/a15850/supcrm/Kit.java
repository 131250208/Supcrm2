package com.example.a15850.supcrm;

import android.os.Handler;
import android.os.Message;
import android.util.Base64;

import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

/**
 * Created by 15850 on 2016/5/3.
 */
public class Kit {
    /*通用工具方法*/
    //将可序列化的对象转换成字符串
    public static String objectToString(Object object){
        ByteArrayOutputStream byteArrayOutputStream=new ByteArrayOutputStream();
        String base_string="";
        try {
            ObjectOutputStream objectOutputStream=new ObjectOutputStream(byteArrayOutputStream);
            objectOutputStream.writeObject(object);
            base_string= Base64.encodeToString(byteArrayOutputStream.toByteArray(),Base64.DEFAULT);
            objectOutputStream.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return base_string;
    }
    //将可序列化的对象字符串转换成相应对象
    public static Object stringToObject(String base_string){
        byte[] base64= Base64.decode(base_string, Base64.DEFAULT);
        ByteArrayInputStream byteArrayInputStream=new ByteArrayInputStream(base64);
        Object ob=null;
        try {
            ObjectInputStream objectInputStream=new ObjectInputStream(byteArrayInputStream);
            ob=objectInputStream.readObject();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return ob;
    }

    private static final String urlAddress="http://nqiwx.mooctest.net:8090/wexin.php/Api/Index/";
    //get方法发送请求
    public static void sendRequest(final String query, final Handler handler, final int messageType){
        //开启新线程来访问网络
        new Thread(new Runnable() {
            @Override
            public void run() {
                HttpURLConnection connection=null;
                try {
                    URL url=new URL(urlAddress+query);
                    connection=(HttpURLConnection)url.openConnection();
                    connection.setRequestMethod("GET");
                    connection.setConnectTimeout(8000);
                    connection.setReadTimeout(8000);
                    InputStream inputStream=connection.getInputStream();
                    //读取输入流
                    BufferedReader reader=new BufferedReader(new InputStreamReader(inputStream));
                    StringBuilder response=new StringBuilder();
                    String line;
                    while ((line=reader.readLine())!=null){
                        response.append(line);
                    }
                    Message message=new Message();
                    message.what=messageType;
                    message.obj=response.toString();
                    handler.sendMessage(message);
                } catch (MalformedURLException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                }finally {
                    if (connection!=null){
                        connection.disconnect();
                    }
                }

            }
        }).start();
    }
    //post方法发送请求
    public static void sendRequest(final String query,final Handler handler,final int messageType,final String[][] param){
        //开启新线程来访问网络
        new Thread(new Runnable() {
            @Override
            public void run() {
                HttpURLConnection connection=null;
                try {
                    URL url=new URL(urlAddress+query);
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
                    Message message=new Message();
                    message.what=messageType;
                    message.obj=response.toString();
                    handler.sendMessage(message);
                } catch (MalformedURLException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                }finally {
                    if (connection!=null){
                        connection.disconnect();
                    }
                }

            }
        }).start();
    }

    /*针对该应用的工具方法*/

}
