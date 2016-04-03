package com.webapp.controller;

import com.webapp.service.UserService;
import com.webapp.service.impl.UploadFileService;
import com.webapp.utils.Constant;
import net.sf.ehcache.transaction.xa.EhcacheXAException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.commons.CommonsMultipartFile;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.lang.Object;import java.lang.String;import java.util.*;import java.util.Map;

/**
 *
 * 添加用户，删除用户，查找用户(显示列表)
 * Created by zjmvic on 2016/1/17.
 */
@Controller
@RequestMapping("/beitai")
public class UserController {

    @Autowired
    UserService userService;

    @Autowired
    UploadFileService uploadFileService;

    private final static String IMG_DESC_PATH = Constant.uploadPath;


    @RequestMapping(value = "/getHealthUsers",method = RequestMethod.POST,produces = "application/json; charset=UTF-8")
    @ResponseBody
    public String getHealthUsers(@RequestParam Map<String,Object> reqs){
        String str = userService.getUsers(reqs);
        return str;
    }

    @RequestMapping(value = "/getRelations",method = RequestMethod.POST,produces = "application/json; charset=UTF-8")
    @ResponseBody
    public String getRelations() {
        return userService.getRelations();
    }

    @RequestMapping(value ="/addHealthUser",method = RequestMethod.POST)
    @ResponseBody
    public Map<String,Object> addUser(@RequestParam Map<String,Object> reqs,
                          @RequestParam("FileFolder") CommonsMultipartFile multipartFile) {
        //webapp下的路径暂时获取不到，暂时用绝对路径代替
        String path = IMG_DESC_PATH;
        try {
            reqs.put("FileFolder", uploadFileService.uploadFile(multipartFile, path));
        }catch (IOException e) {
            e.printStackTrace();
        }
        Map<String,Object> map = userService.addUser(reqs);
        return map;
    }

    /**
     *
     * @param reqs FamilyID, HealthUserID
     * @return success or failed
     */
    @RequestMapping(value = "/delHealthUser",method = RequestMethod.POST)
    @ResponseBody
    public String delUser(@RequestParam Map<String,Object> reqs) {
        String result = userService.delUser(reqs);
        return result;
    }

}
