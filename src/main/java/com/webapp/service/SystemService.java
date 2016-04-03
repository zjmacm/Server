package com.webapp.service;

import java.util.Map;

/**
 * Created by zjmvic on 2016/1/10.
 */
public interface SystemService {

    //七星管家app登录，登录成功返回改用户的设备序列号+设备名字
    Map<String,Object> login(String LoginName);


    Map<String,Object> paramsInit(String method, Map<String, Object> params);


    Map<String,Object> getAccessTokenByTel(Map<String, Object> params);

    String getCameraData(String UserID);


    String getMonitorData(String UserID);



}
