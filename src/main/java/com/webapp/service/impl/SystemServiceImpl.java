package com.webapp.service.impl;

import com.alibaba.fastjson.JSON;
import com.webapp.common.MySecureProtocolSocketFactory;
import com.webapp.service.SystemService;
import com.webapp.utils.Constant;
import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.methods.PostMethod;
import org.apache.commons.httpclient.methods.RequestEntity;
import org.apache.commons.httpclient.methods.StringRequestEntity;
import org.apache.commons.httpclient.protocol.Protocol;
import org.apache.commons.httpclient.protocol.ProtocolSocketFactory;
import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;
import org.springframework.util.DigestUtils;

import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by zjmvic on 2016/1/10.
 */
@Service("SystemService")
public class SystemServiceImpl implements SystemService{

    @Autowired
    JdbcTemplate jdbcTemplate;

    /**
     * @param LoginName
     * @return map包含一个result是否登录成功和设备ID EquipmentID
     */

    public Map<String,Object> login(String LoginName) {
        Map<String,Object> map = new HashMap<String, Object>();
        String result = "success";
        String sql = "select UserID from user where LoginName = ?";
        List<Map<String,Object>> list = jdbcTemplate.queryForList(sql, new Object[]{LoginName});
        if(list.size() == 0) {
            result = "failed";
        }
        else {
            map.put("UserID",list.get(0).get("UserID"));
        }
        map.put("result",result);
        return map;
    }



    /**
     * 目前是用手机号获取accessToken，method为 token/getAccessToken
     * @param method
     * @param paramsMap
     * @return
     */
    public Map<String, Object> paramsInit(String method, Map<String, Object> paramsMap) {
        Map<String,Object> map = new HashMap<String,Object>();
        Long time = System.currentTimeMillis()/1000;
        String key= Constant.YS_KEY;
        String secret= Constant.YS_SECRET;
        StringBuilder paramString = new StringBuilder();
        List<String> paramList = new ArrayList<String>();
        for (Iterator<String> it = paramsMap.keySet().iterator(); it.hasNext();) {
            String key1 = it.next();
            String param = key1 + ":" + paramsMap.get(key1);
            paramList.add(param);
        }
        String[] params = paramList.toArray(new String[paramList.size()]);
        Arrays.sort(params);
        for (String param : params) {
            paramString.append(param).append(",");
        }
        paramString.append("method").append(":").append(method).append(",");
        paramString.append("time").append(":").append(time).append(",");
        paramString.append("secret").append(":").append(secret);
       /* System.out.println(paramString.toString().trim());*/

        String sign = null;

        try {
            sign = DigestUtils.md5DigestAsHex(paramString.toString().trim().getBytes("UTF-8"));
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        Map<String, Object> systemMap = new HashMap<String, Object>();
        systemMap.put("ver", "1.0");
        systemMap.put("sign", sign);
        systemMap.put("key", key);
        systemMap.put("time", time);
        map.put("system", systemMap);
        map.put("method", method);
        map.put("params", paramsMap);

        /**随机生成一个ID**/
        String id = String.valueOf(Math.random()+1000000);
        map.put("id",id);
        return map;
    }

    public Map<String, Object> getAccessTokenByTel(Map<String,Object> params) {
        String data = "success";
        Map<String,Object> info = new HashMap<String,Object>();
        String result = null;
        //将map转化为json
        String json = JSON.toJSONString(params);
        ProtocolSocketFactory fcty = new MySecureProtocolSocketFactory();
        Protocol.registerProtocol("https", new Protocol("https", fcty, 443));
        HttpClient client = new HttpClient();
        // 使用POST方法
        PostMethod method = new PostMethod("https://open.ys7.com:443/api/method");

        try {
            RequestEntity entity = new StringRequestEntity(json, "application/json", "UTF-8");
            method.setRequestEntity(entity);
            client.executeMethod(method);

            InputStream inputStream = method.getResponseBodyAsStream();
            result = IOUtils.toString(inputStream);
            //将新用户的数据存到数据库中
            Pattern pAccessToken=Pattern.compile("accessToken\":\"([^\"]*)");
            Pattern pId=Pattern.compile("userId\":\"([^\"]*)");
            Matcher mId=pId.matcher(result);
            Matcher mAccessToken=pAccessToken.matcher(result);
            String id = "";
            String accessToken = "";
            if( mId.find()&&mAccessToken.find()) {
                id = mId.group(1).toString();
                accessToken = mAccessToken.group(1).toString();
                info.put("id",id);
                info.put("accessToken",accessToken);
                info.put("data",data);

            }
            else {
                info.put("data","failed");
            }

        } catch (Exception e) {
            e.printStackTrace();
        }finally {
            // 释放连接
            method.releaseConnection();
        }
        return info;
    }


    /**
     * 通过UserID查询摄像头的序列号
     *
     * @param UserID
     * @return 一个json字符串
     */
    public String getCameraData(String UserID) {
        Map<String,Object> map = new HashMap<String, Object>();
        String result ="success";
        StringBuffer sb1 = new StringBuffer("");
        sb1.append("select ser.SRNo from stand_equipment_record ser " +
                "left join stand_equipment_info sei on ser.EquipmentID = sei.EquipmentID " +
                "where sei.ClassifiedID = 4 and ser.UserID = ?");
        List<Map<String,Object>> list1 = jdbcTemplate.queryForList(sb1.toString(),new Object[]{UserID});
        if(list1.size() == 0) {
            result = "failed";
        }
        else {
            map.put("data",list1);
        }
        map.put("result",result);
        return JSON.toJSONString(map);
    }

    public  String getMonitorData(String UserID) {
        Map<String,Object> map = new HashMap<String, Object>();
        String result ="success";
        StringBuffer sb1 = new StringBuffer("");
        sb1.append("select ser.SRNo from stand_equipment_record ser " +
                "left join stand_equipment_info sei on ser.EquipmentID = sei.EquipmentID " +
                "where sei.ClassifiedID = 1 and ser.UserID = ?");
        List<Map<String,Object>> list1 = jdbcTemplate.queryForList(sb1.toString(),new Object[]{UserID});
        if(list1.size() == 0) {
            result = "failed";
        }
        else {
            map.put("data",list1);
        }
        map.put("result",result);
        return JSON.toJSONString(map);
    }

}
