package com.webapp.service.impl;

import com.webapp.service.HealthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import java.util.*;

/**
 * Created by zjmvic on 2016/1/17.
 */
@Service
public class HealthServiceImpl implements HealthService{

    int[] theDaysOfMonth = {31,29,31,30,31,30,31,31,30,31,30,31};

    @Autowired
    private JdbcTemplate jdbcTemplate;

    public List<Map<String, Object>> getHistoryPre(String HealthUserID) {
        String sql = "select Date,Time,HighPressure,LowPressure,Pulse from health_record " +
                "where HealthUserID = ? " +
                "order by Date desc ,Time desc";
        List<Map<String,Object>> list = jdbcTemplate.queryForList(sql,new Object[]{HealthUserID});
        return list;
    }

    public List<Map<String, Object>> getHistoryBlo(String HealthUserID) {
        StringBuffer sb = new StringBuffer("");
        sb.append("select Date,Time,BloodGlucose from health_record ")
                .append("where HealthUserID = ?")
                .append("order by Date desc,time desc");
        List<Map<String,Object>> list = jdbcTemplate.queryForList(sb.toString(),new Object[]{HealthUserID});
        return list;
    }

    public List<Map<String, Object>> getHistoryTem(String HealthUserID) {
        StringBuffer sb = new StringBuffer("");
        sb.append("select Date,Time,Temperature from health_record ")
                .append("where HealthUserID = ?")
                .append("order by Date desc,time desc");
        List<Map<String,Object>> list = jdbcTemplate.queryForList(sb.toString(),new Object[]{HealthUserID});
        return list;
    }

    public List<Map<String, Object>> getHistoryWei(String HealthUserID) {
        StringBuffer sb = new StringBuffer("");
        sb.append("select Date,Time,Weight from health_record ")
                .append("where HealthUserID = ?")
                .append("order by Date desc,time desc");
        List<Map<String,Object>> list = jdbcTemplate.queryForList(sb.toString(),new Object[]{HealthUserID});
        return list;
    }

    public List<Map<String, Object>> getHistoryFat(String HealthUserID) {
        StringBuffer sb = new StringBuffer("");
        sb.append("select Date,Time,FatRate,FatClass,Muscle,Water from health_record ")
                .append("where HealthUserID = ?")
                .append("order by Date desc,time desc");
        List<Map<String,Object>> list = jdbcTemplate.queryForList(sb.toString(),new Object[]{HealthUserID});
        return list;
    }

    public String savePressure(String HighPressure, String LowPressure, String Pulse, String HealthUserID) {
        String result = "success";
        String sql = "insert into health_record (HealthUserID,Date,Time,HighPressure,LowPressure,Pulse)" +
                "values (?,?,?,?,?,?)";
        Date now = new Date();
        int id = Integer.valueOf(HealthUserID);
        double high = Double.valueOf(HighPressure);
        double low = Double.valueOf(LowPressure);
        double pulse = Double.valueOf(Pulse);
        Object[] objects = {id,now,now,high,low,pulse};
        try {
            jdbcTemplate.update(sql, objects);
        }catch (Exception e) {
            result = "failed";
            e.printStackTrace();
        }
        return result;
    }

    public String saveBlood(String BloodGlucose, String HealthUserID) {
        String result = "success";
        String sql = "insert into health_record (HealthUserID,Date,Time,BloodGlucose)" +
                "VALUES (?,?,?,?)";
        Date now = new Date();
        int id = Integer.valueOf(HealthUserID);
        double glucose = Double.valueOf(BloodGlucose);
        Object[] objects = {id,now,now,glucose};
        try {
            jdbcTemplate.update(sql,objects);
        }catch (Exception e) {
            result = "failed";
            e.printStackTrace();
        }
        return result;
    }

    public String saveTemperature(String Temperature, String HealthUserID) {
        String result = "success";
        String sql = "insert into health_record (HealthUserID,Date,Time,Temperature)" +
                "values(?,?,?,?)";
        Date now = new Date();
        int id = Integer.valueOf(HealthUserID);
        double temperature = Double.valueOf(Temperature);
        Object[] objects = {id,now,now,temperature};
        try {
            jdbcTemplate.update(sql,objects);
        }catch (Exception e) {
            result = "failed";
            e.printStackTrace();
        }
        return result;
    }

    public String getStableWeight(String weight,String HealthUserID) {
        Stack<String> s = new Stack<String>();
        s.push(weight);
        
        return null;
    }





    public String saveWeight(String Weight, String HealthUserID) {
        System.out.println(Weight);
        String result = "success";
        String sql = "insert into health_record (HealthUserID,Date,Time,Weight)" +
                "values(?,?,?,?)";
        Date now = new Date();
        int id = Integer.valueOf(HealthUserID);
        double weight = Double.parseDouble(Weight);
        Object[] objects = {id,now,now,weight};
        try {
            jdbcTemplate.update(sql,objects);
        }catch (Exception e) {
            result = "failed";
            e.printStackTrace();
        }
        return result;
    }

    public String saveFat(String FatRate, String FatClass, String Muscle, String Water, String HealthUserID) {
        String result = "success";
        String sql = "insert into health_record (HealthUserID,Date,Time,FatRate,FatClass,Muscle,Water)" +
                "values(?,?,?,?,?,?,?)";
        Date now = new Date();
        int id = Integer.valueOf(HealthUserID);
        double fatRate = Double.valueOf(FatRate);
        double fatClass = Double.valueOf(FatClass);
        double muscle = Double.valueOf(Muscle);
        double water = Double.valueOf(Water);
        Object[] objects = {id,now,now,fatRate,fatClass,muscle,water};
        try {
            jdbcTemplate.update(sql,objects);
        }catch (Exception e) {
            result = "failed";
            e.printStackTrace();
        }
        return result;
    }

    public String getSql() {
        StringBuffer sql = null;
        sql.append("select ");

        return sql.toString();
    }

    public Map<String,Object> getWeekPressure(String HealthUserID) {
        String sql = "select HighPressure, LowPressure, Pulse where HealthUserID =　？and ";
        return null;
    }

    public Map<String,Object> getWeekBlood(String HealthUserID) {
        return null;
    }

    public Map<String,Object> getWeekTemperature(String HealthUserID) {
        return null;
    }

    public Map<String,Object> getWeekWeight(String HealthUserID) {
        return null;
    }

    public Map<String,Object> getWeekFat(String HealthUserID) {
        return null;
    }

    public Map<String,Object> getMonthPressure(String HealthUserID) {
        String result = "success";
        List<Map<String,Object>> list = new ArrayList<Map<String,Object>>();
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(new Date());
        int year = calendar.get(Calendar.YEAR);
        int month = calendar.get(Calendar.MONTH)+1;
        int day = theDaysOfMonth[month-1];
        String sql = "select HighPressure,LowPressure,Pulse from health_record where HealthUserID = ? and Date > ? and Date < ?";
        String date1 = String.valueOf(year)+"-"+String.valueOf(month)+"-"+String.valueOf(1);
        String date2 = String.valueOf(year)+"-"+String.valueOf(month)+"-"+String.valueOf(day);
        try{
             list = jdbcTemplate.queryForList(sql, new Object[]{HealthUserID,date1,date2});
        }catch (Exception e) {
            result = "failed";
            e.printStackTrace();
        }
        int size = list.size();
        int sum1=size,sum2=size,sum3=size;
        double HighPressureSum = 0,LowPressureSum = 0,PulseSum = 0;
        for(int i = 0;i<list.size();i++) {
            double temp1 = Double.valueOf(list.get(i).get("HighPressure").toString());
            double temp2 = Double.valueOf(list.get(i).get("LowPressure").toString());
            double temp3 = Double.valueOf(list.get(i).get("Pulse").toString());
            if(temp1==0){
                sum1--;
            }
            if(temp2==0){
                sum2--;
            }
            if(temp3==0){
                sum3--;
            }
            HighPressureSum += temp1;
            LowPressureSum += temp2;
            PulseSum += temp3;
        }
        double AveHigh = HighPressureSum/sum1;
        double AveLow = LowPressureSum/sum2;
        double AvePulse = PulseSum/sum3;
        Map<String,Object> map = new HashMap<String, Object>();
       /* map.put("高血压月平均值",AveHigh);
        map.put("低血压月平均值",AveLow);
        map.put("脉搏月平均值",AvePulse);*/

        map.put("result",result);
        return map;
    }

    public Map<String,Object> getMonthBlood(String HealthUserID) {
        String result = "success";
        List<Map<String,Object>> list = new ArrayList<Map<String,Object>>();
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(new Date());
        int year = calendar.get(Calendar.YEAR);
        int month = calendar.get(Calendar.MONTH)+1;
        int day = theDaysOfMonth[month-1];
        String sql = "select BloodGlucose from health_record where HealthUserID = ? and Date > ? and Date < ?";
        String date1 = String.valueOf(year)+"-"+String.valueOf(month)+"-"+String.valueOf(1);
        String date2 = String.valueOf(year)+"-"+String.valueOf(month)+"-"+String.valueOf(day);
        try{
            list = jdbcTemplate.queryForList(sql, new Object[]{HealthUserID,date1,date2});
        }catch (Exception e) {
            result = "failed";
            e.printStackTrace();
        }
        int size = list.size();
        int sum = size;
        double BloodSum = 0;
        for(int i = 0;i<list.size();i++) {
             double temp = Double.valueOf(list.get(i).get("BloodGlucose").toString());
             if(temp==0) {
                 sum--;
             }
            BloodSum+=temp;
        }
        double AveBlood = BloodSum/sum;

        Map<String,Object> map = new HashMap<String, Object>();
        /* map.put("血糖月平均值",AveBlood);*/
        map.put("result",result);
        return map;
    }

    public Map<String,Object> getMonthTemperature(String HealthUserID) {
        String result = "success";
        List<Map<String,Object>> list = new ArrayList<Map<String,Object>>();
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(new Date());
        int year = calendar.get(Calendar.YEAR);
        int month = calendar.get(Calendar.MONTH)+1;
        int day = theDaysOfMonth[month-1];
        String sql = "select Temperature from health_record where HealthUserID = ? and Date > ? and Date < ?";
        String date1 = String.valueOf(year)+"-"+String.valueOf(month)+"-"+String.valueOf(1);
        String date2 = String.valueOf(year)+"-"+String.valueOf(month)+"-"+String.valueOf(day);
        try{
            list = jdbcTemplate.queryForList(sql, new Object[]{HealthUserID,date1,date2});
        }catch (Exception e) {
            result = "failed";
            e.printStackTrace();
        }
        double TemSum = 0;
        int size = list.size();
        int sum = size;
        for(int i = 0;i<list.size();i++) {
            double temp = Double.valueOf(list.get(i).get("Temperature").toString());
            if(temp==0) {
                sum--;
            }
            TemSum+=temp;
        }
        double AveTem = TemSum/sum;
        /*System.out.println(AveTem);*/
        Map<String,Object> map = new HashMap<String, Object>();
       /* map.put("体温月平均值",AveTem);

        map.put("result",result);*/
        return map;
    }

    public Map<String,Object> getMonthWeight(String HealthUserID) {
        String result = "success";
        List<Map<String,Object>> list = new ArrayList<Map<String,Object>>();
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(new Date());
        int year = calendar.get(Calendar.YEAR);
        int month = calendar.get(Calendar.MONTH)+1;
        int day = theDaysOfMonth[month-1];
        String sql = "select Weight from health_record where HealthUserID = ? and Date > ? and Date < ?";
        String date1 = String.valueOf(year)+"-"+String.valueOf(month)+"-"+String.valueOf(1);
        String date2 = String.valueOf(year)+"-"+String.valueOf(month)+"-"+String.valueOf(day);
        try{
            list = jdbcTemplate.queryForList(sql, new Object[]{HealthUserID,date1,date2});
        }catch (Exception e) {
            result = "failed";
            e.printStackTrace();
        }
        double WeiSum = 0;
        int size = list.size();
        int sum = size;
        for(int i = 0;i<list.size();i++) {
            double temp = Double.valueOf(list.get(i).get("Weight").toString());
            if(temp==0) {
                sum--;
            }
            WeiSum+=temp;
        }
        double AveWei = WeiSum/sum;
        System.out.println(AveWei);
        Map<String,Object> map = new HashMap<String, Object>();
        /*map.put("体重月平均值",AveWei);
        map.put("result",result);*/
        return map;
    }

    public Map<String,Object> getMonthFat(String HealthUserID) {
        String result = "success";
        List<Map<String,Object>> list = new ArrayList<Map<String,Object>>();
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(new Date());
        int year = calendar.get(Calendar.YEAR);
        int month = calendar.get(Calendar.MONTH)+1;
        int day = theDaysOfMonth[month-1];
        String sql = "select FatRate,FatClass,Muscle,Water from health_record where HealthUserID = ? and Date > ? and Date < ?";
        String date1 = String.valueOf(year)+"-"+String.valueOf(month)+"-"+String.valueOf(1);
        String date2 = String.valueOf(year)+"-"+String.valueOf(month)+"-"+String.valueOf(day);
        try{
            list = jdbcTemplate.queryForList(sql, new Object[]{HealthUserID,date1,date2});
        }catch (Exception e) {
            result = "failed";
            e.printStackTrace();
        }
        int size = list.size();
        int sum1=size,sum2=size,sum3=size,sum4=size;
        double RateSum = 0,ClassSum = 0,MuscleSum = 0,WaterSum = 0;
        for(int i = 0;i<list.size();i++) {
            double temp1 = Double.valueOf(list.get(i).get("FatRate").toString());
            double temp2 = Double.valueOf(list.get(i).get("FatClass").toString());
            double temp3 = Double.valueOf(list.get(i).get("Muscle").toString());
            double temp4 = Double.valueOf(list.get(i).get("Water").toString());
            if(temp1==0){
                sum1--;
            }
            if(temp2==0){
                sum2--;
            }
            if(temp3==0){
                sum3--;
            }
            if(temp4==0){
                sum4--;
            }
            RateSum += temp1;
            ClassSum += temp2;
            MuscleSum += temp3;
            WaterSum +=temp4;
        }
        double AveRate = RateSum/sum1;
        double AveClass = ClassSum/sum2;
        double AveMuscle = MuscleSum/sum3;
        double AveWater = WaterSum/sum4;
        /*System.out.println(AveRate+" "+AveClass+" "+AveMuscle+" "+AveWater);*/
        Map<String,Object> map = new HashMap<String, Object>();
        /*map.put("脂肪率月平均值",AveRate);
        map.put("脂肪等级月平均值",AveClass);
        map.put("肌肉量月平均值",AveMuscle);
        map.put("水分月平均值",AveWater);
        map.put("result", result);*/
        return map;
    }

    public Map<String,Object> getYearPressure(String HealthUserID) {
        String result = "success";
        List<Map<String,Object>> list = new ArrayList<Map<String,Object>>();
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(new Date());
        int year = calendar.get(Calendar.YEAR);
        String sql = "select HighPressure,LowPressure,Pulse from health_record where HealthUserID = ? and Date > ? and Date < ?";
        String date1 = String.valueOf(year)+"-"+String.valueOf(1)+"-"+String.valueOf(1);
        String date2 = String.valueOf(year)+"-"+String.valueOf(12)+"-"+String.valueOf(31);
        try{
            list = jdbcTemplate.queryForList(sql, new Object[]{HealthUserID,date1,date2});
        }catch (Exception e) {
            result = "failed";
            e.printStackTrace();
        }
        int size = list.size();
        int sum1=size,sum2=size,sum3=size;
        double HighPressureSum = 0,LowPressureSum = 0,PulseSum = 0;
        for(int i = 0;i<list.size();i++) {
            double temp1 = Double.valueOf(list.get(i).get("HighPressure").toString());
            double temp2 = Double.valueOf(list.get(i).get("LowPressure").toString());
            double temp3 = Double.valueOf(list.get(i).get("Pulse").toString());
            if(temp1==0){
                sum1--;
            }
            if(temp2==0){
                sum2--;
            }
            if(temp3==0){
                sum3--;
            }
            HighPressureSum += temp1;
            LowPressureSum += temp2;
            PulseSum += temp3;
        }
        double AveHigh = HighPressureSum/sum1;
        double AveLow = LowPressureSum/sum2;
        double AvePulse = PulseSum/sum3;
        /*System.out.println(AveHigh+" "+AveLow+" "+AvePulse);*/
        Map<String,Object> map = new HashMap<String, Object>();
        /*map.put("高血压年平均值",AveHigh);
        map.put("低血压年平均值",AveLow);
        map.put("脉搏年平均值",AvePulse);
        map.put("result", result);*/
        return map;
    }

    public Map<String,Object> getYearBlood(String HealthUserID) {
        String result = "success";
        List<Map<String,Object>> list = new ArrayList<Map<String,Object>>();
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(new Date());
        int year = calendar.get(Calendar.YEAR);
        String sql = "select BloodGlucose from health_record where HealthUserID = ? and Date > ? and Date < ?";
        String date1 = String.valueOf(year)+"-"+String.valueOf(1)+"-"+String.valueOf(1);
        String date2 = String.valueOf(year)+"-"+String.valueOf(12)+"-"+String.valueOf(31);
        try{
            list = jdbcTemplate.queryForList(sql, new Object[]{HealthUserID,date1,date2});
        }catch (Exception e) {
            result = "failed";
            e.printStackTrace();
        }
        int size = list.size();
        int sum = size;
        double BloodSum = 0;
        for(int i = 0;i<list.size();i++) {
            double temp = Double.valueOf(list.get(i).get("BloodGlucose").toString());
            if(temp==0) {
                sum--;
            }
            BloodSum+=temp;
        }
        double AveBlood = BloodSum/sum;
       /* System.out.println(AveBlood);*/
        Map<String,Object> map = new HashMap<String, Object>();
       /* map.put("血糖年平均值",AveBlood);
        map.put("result",result);*/
        return map;
    }

    public Map<String,Object> getYearTemperature(String HealthUserID) {
        String result = "success";
        List<Map<String,Object>> list = new ArrayList<Map<String,Object>>();
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(new Date());
        int year = calendar.get(Calendar.YEAR);
        String sql = "select Temperature from health_record where HealthUserID = ? and Date > ? and Date < ?";
        String date1 = String.valueOf(year)+"-"+String.valueOf(1)+"-"+String.valueOf(1);
        String date2 = String.valueOf(year)+"-"+String.valueOf(12)+"-"+String.valueOf(31);
        try{
            list = jdbcTemplate.queryForList(sql, new Object[]{HealthUserID,date1,date2});
        }catch (Exception e) {
            result = "failed";
            e.printStackTrace();
        }
        double TemSum = 0;
        int size = list.size();
        int sum = size;
        for(int i = 0;i<list.size();i++) {
            double temp = Double.valueOf(list.get(i).get("Temperature").toString());
            if(temp==0) {
                sum--;
            }
            TemSum+=temp;
        }
        double AveTem = TemSum/sum;
        /*System.out.println(AveTem);*/
        Map<String,Object> map = new HashMap<String, Object>();
        /*map.put("体温年平均值",AveTem);
        map.put("result",result);*/
        return map;
    }

    public Map<String,Object> getYearWeight(String HealthUserID) {
        String result = "success";
        List<Map<String,Object>> list = new ArrayList<Map<String,Object>>();
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(new Date());
        int year = calendar.get(Calendar.YEAR);
        String sql = "select Weight from health_record where HealthUserID = ? and Date > ? and Date < ?";
        String date1 = String.valueOf(year)+"-"+String.valueOf(1)+"-"+String.valueOf(1);
        String date2 = String.valueOf(year)+"-"+String.valueOf(12)+"-"+String.valueOf(31);
        try{
            list = jdbcTemplate.queryForList(sql, new Object[]{HealthUserID,date1,date2});
        }catch (Exception e) {
            result = "failed";
            e.printStackTrace();
        }
        double WeiSum = 0;
        int size = list.size();
        int sum = size;
        for(int i = 0;i<list.size();i++) {
            double temp = Double.valueOf(list.get(i).get("Weight").toString());
            if(temp==0) {
                sum--;
            }
            WeiSum+=temp;
        }
        double AveWei = WeiSum/sum;
        System.out.println(AveWei);
        Map<String,Object> map = new HashMap<String, Object>();
       /* map.put("体重年平均值",AveWei);
        map.put("result",result);*/
        return map;
    }

    public Map<String,Object> getYearFat(String HealthUserID) {
        String result = "success";
        List<Map<String,Object>> list = new ArrayList<Map<String,Object>>();
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(new Date());
        int year = calendar.get(Calendar.YEAR);
        String sql = "select FatRate,FatClass,Muscle,Water from health_record where HealthUserID = ? and Date > ? and Date < ?";
        String date1 = String.valueOf(year)+"-"+String.valueOf(1)+"-"+String.valueOf(1);
        String date2 = String.valueOf(year)+"-"+String.valueOf(12)+"-"+String.valueOf(31);
        try{
            list = jdbcTemplate.queryForList(sql, new Object[]{HealthUserID,date1,date2});
        }catch (Exception e) {
            result = "failed";
            e.printStackTrace();
        }
        int size = list.size();
        int sum1=size,sum2=size,sum3=size,sum4=size;
        double RateSum = 0,ClassSum = 0,MuscleSum = 0,WaterSum = 0;
        for(int i = 0;i<list.size();i++) {
            double temp1 = Double.valueOf(list.get(i).get("FatRate").toString());
            double temp2 = Double.valueOf(list.get(i).get("FatClass").toString());
            double temp3 = Double.valueOf(list.get(i).get("Muscle").toString());
            double temp4 = Double.valueOf(list.get(i).get("Water").toString());
            if(temp1==0){
                sum1--;
            }
            if(temp2==0){
                sum2--;
            }
            if(temp3==0){
                sum3--;
            }
            if(temp4==0){
                sum4--;
            }
            RateSum += temp1;
            ClassSum += temp2;
            MuscleSum += temp3;
            WaterSum +=temp4;
        }
        double AveRate = RateSum/sum1;
        double AveClass = ClassSum/sum2;
        double AveMuscle = MuscleSum/sum3;
        double AveWater = WaterSum/sum4;
        /*System.out.println(AveRate+" "+AveClass+" "+AveMuscle+" "+AveWater);*/
        Map<String,Object> map = new HashMap<String, Object>();
        map.put("脂肪率年平均值",AveRate);
        map.put("脂肪等级年平均值",AveClass);
        map.put("肌肉量年平均值",AveMuscle);
        map.put("水分年平均值",AveWater);
        map.put("result", result);
        return map;
    }

}
