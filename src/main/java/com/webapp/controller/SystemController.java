package com.webapp.controller;

import com.webapp.service.SystemService;
import com.webapp.utils.Constant;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.Map;


/**
 * Created by zjmvic on 2016/1/10.
 */
@Controller
public class SystemController {

    @Autowired
    SystemService systemService;

    //根据电话号码获取accessToken和萤石云服务器的FamilyID
    @RequestMapping(value = "/getInfo",method = RequestMethod.POST)
    @ResponseBody
    public Map<String,Object> getInfo() {
        System.out.println("hello world");
        Map<String,Object> paramsMap = new HashMap<String,Object>();
        paramsMap.put("phone",Constant.phoneNum);
        Map<String,Object> map = systemService.paramsInit("token/getAccessToken",paramsMap);
        Map<String,Object> conds = systemService.getAccessTokenByTel(map);
        return conds;
    }

    //登录之后返回是否登录成功
    @RequestMapping(value = "/login",method = RequestMethod.POST,produces = "application/json; charset=UTF-8")
    @ResponseBody
    public Map<String,Object> login(@RequestParam(value = "LoginName",defaultValue = "")String LoginName) {
        return systemService.login(LoginName);
    }

    @RequestMapping(value = "/getCameraSNRo" , method =  RequestMethod.POST, produces = "application/json; charset=UTF-8")
    @ResponseBody
    public String getCameraSNRo(@RequestParam(value = "UserID",defaultValue = "") String UserID) {
        String result = systemService.getCameraData(UserID);
        return result;
    }

    @RequestMapping(value = "/getMonitorSNRo" , method =  RequestMethod.POST, produces = "application/json; charset=UTF-8")
    @ResponseBody
    public String getMonitorSNRo(@RequestParam(value = "UserID",defaultValue = "") String UserID) {
        String result = systemService.getMonitorData(UserID);
        return result;
    }


}
