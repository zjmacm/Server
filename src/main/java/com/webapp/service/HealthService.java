package com.webapp.service;

import java.util.List;
import java.util.Map;

/**
 * Created by zjmvic on 2016/1/17.
 */
public interface HealthService {

    String savePressure(String HighPressure, String LowPressure, String Pulse, String HealthUserID);

    //保存血糖
    String saveBlood(String BloodGlucose, String HealthUserID);

    String saveTemperature(String Temperature, String HealthUserID);

    String getStableWeight(String weight, String HealthUserID);

    String saveWeight(String Weight, String HealthUserID);

    //保存脂肪率，脂肪等级，肌肉量，水分
    String saveFat(String FatRate, String FatClass, String Muscle, String Water, String HealthUserID);

    //获得历史测量记录
    List<Map<String,Object>> getHistoryPre(String HealthUserID);

    List<Map<String,Object>> getHistoryBlo(String HealthUserID);

    List<Map<String,Object>> getHistoryTem(String HealthUserID);

    List<Map<String,Object>> getHistoryWei(String HealthUserID);

    List<Map<String,Object>> getHistoryFat(String HealthUserID);


    Map<String,Object> getWeekPressure(String HealthUserID);

    Map<String,Object> getWeekBlood(String HealthUserID);

    Map<String,Object> getWeekTemperature(String HealthUserID);

    Map<String,Object> getWeekWeight(String HealthUserID);

    Map<String,Object> getWeekFat(String HealthUserID);


    Map<String,Object> getMonthPressure(String HealthUserID);

    Map<String,Object> getMonthBlood(String HealthUserID);

    Map<String,Object> getMonthTemperature(String HealthUserID);

    Map<String,Object> getMonthWeight(String HealthUserID);

    Map<String,Object> getMonthFat(String HealthUserID);


    Map<String,Object> getYearPressure(String HealthUserID);

    Map<String,Object> getYearBlood(String HealthUserID);

    Map<String,Object> getYearTemperature(String HealthUserID);

    Map<String,Object> getYearWeight(String HealthUserID);

    Map<String,Object> getYearFat(String HealthUserID);

}
