package com.webapp.service.impl;

import com.alibaba.fastjson.JSON;
import com.webapp.service.UserService;
import com.webapp.utils.Constant;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import java.io.File;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by zjmvic on 2016/1/17.
 */
@Service("UserService")
public class UserServiceImpl implements UserService {

    @Autowired
    JdbcTemplate jdbcTemplate;

    /**
     *
     * @param map FamilyID
     * @return json格式的用户信息列表
     */
    public String getUsers(Map<String, Object> map) {
        //家庭的id
        String UserID = map.get("UserID").toString();
        String sql = "select h.HealthUserID,h.FileFolder,h.UserName,r.RelationsName from health_user h left join relations r on " +
                "h.RelationID = r.RelationsID where h.UserID = ?";
        List<Map<String,Object>> list = jdbcTemplate.queryForList(sql, new Object[]{UserID});
        return JSON.toJSONString(list);
    }


    public String getRelations() {
        String sql = "select *from relations";
        List<Map<String,Object>> list = jdbcTemplate.queryForList(sql);
        return JSON.toJSONString(list);
    }


    /**
     *
     * @param map ID，头像，姓名，性别，年龄，关系
     * @return success or failed
     */

    public Map<String,Object> addUser(Map<String, Object> map) {
        Map<String,Object> conds = new HashMap<String, Object>();
        String result = "success";
        String UserName = map.get("UserName").toString();
        String Sex = map.get("Sex").toString();
        int Old = Integer.valueOf(map.get("Old").toString());
        String RelationID  = map.get("RelationID").toString();
        //头像存的是本地服务器的url
        String FileFolder = map.get("FileFolder").toString();
        String UserID = map.get("UserID").toString();
        String FamilyID = "1";
        try{
            String sql = "insert into health_user (UserName,Sex,Old,RelationID,FileFolder,UserID,FamilyID) values(?,?,?,?,?,?,?)";
            jdbcTemplate.update(sql,new Object[]{UserName,Sex,Old,RelationID,FileFolder,UserID,FamilyID});
            conds.put("result",result);
            String HealthUserID = jdbcTemplate.queryForList("select *from health_user where FileFolder = ?",new Object[]{FileFolder}).
                    get(0).
                    get("HealthUserID").
                    toString();
            conds.put("HealthUserID",HealthUserID);
        }catch (Exception e) {
            e.printStackTrace();
            result = "failed";
            conds.put("result",result);
        }
        return conds;
    }

    public String delUser(Map<String, Object> map) {
        String result = "success";
        String UserID = map.get("UserID").toString();
        String HealthUserID = map.get("HealthUserID").toString();
        try{
            String delPic = "select FileFolder from health_user where UserID = ? and HealthUserID = ?";
            String picName = jdbcTemplate.queryForList(delPic,new Object[]{UserID,HealthUserID})
                    .get(0)
                    .get("FileFolder")
                    .toString();
            //执行删除照片的操作
            String path = Constant.uploadPath+picName;
            File file = new File(path);
            if(file.isFile() && file.exists()) {
                file.delete();
            }
            else {
                System.out.println("file don't exist,can't delete!");
            }
            String sql = "delete from health_user where UserID = ? and HealthUserID = ?";
            jdbcTemplate.update(sql,new Object[]{UserID,HealthUserID});
        }catch (Exception e) {
            e.printStackTrace();
            result = "failed";
        }
        return result;
    }

}
