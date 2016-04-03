package com.webapp.controller;

import com.webapp.service.SecurityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Created by zjmvic on 2016/1/17.
 */
@Controller
@RequestMapping(value = "/beitai/security")
public class SecurityController {

    @Autowired
    private SecurityService securityService;

    @RequestMapping(value = "/getAlarmInfo",method = RequestMethod.POST)
    @ResponseBody
    public String getAlarmInfo(
            @RequestParam(value="ControllerID",defaultValue = "")String ControllerID,
            @RequestParam(value="TypeID",defaultValue = "")String TypeID) {
        String result = securityService.recordAlarmInfo(ControllerID,TypeID);
        return result;
    }

}
