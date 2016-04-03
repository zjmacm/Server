package com.webapp.service;

import java.util.Map;

/**
 * 添加用户，删除用户，查找用户(显示列表)
 * Created by zjmvic on 2016/1/17.
 */
public interface UserService {

    //查找 返回json 包含id，头像，姓名，关系
    String getUsers(Map<String, Object> map);

    String getRelations();

    //增加 给我FamilyID，头像，姓名，性别，年龄，关系
    Map<String,Object> addUser(Map<String, Object> map);

    //删除 给我FamilyID,UserID，返回一个字符串
    String delUser(Map<String, Object> map);




}
