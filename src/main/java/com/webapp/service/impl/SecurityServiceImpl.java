package com.webapp.service.impl;

import com.webapp.service.SecurityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import java.util.Date;

/**
 * Created by zjmvic on 2016/1/17.
 */
@Service
public class SecurityServiceImpl implements SecurityService{

    @Autowired
    JdbcTemplate jdbcTemplate;
    public String recordAlarmInfo(String ControllerID, String TypeID) {
        String result = "success";
        Date now = new Date();
        String sql = "insert into controller_alarm_record (ControllerID,Date,Time,TypeID) values(?,?,?,?)";
        Object[] objects = {ControllerID,now,now,TypeID};
        try {
            jdbcTemplate.update(sql,objects);
        }catch (Exception e) {
            result = "failed";
            e.printStackTrace();
        }
        return result;
    }

}
