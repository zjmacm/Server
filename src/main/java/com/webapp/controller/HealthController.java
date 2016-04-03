package com.webapp.controller;

import com.webapp.service.HealthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;
import java.util.Map;

/**
 * Created by zjmvic on 2016/1/17.
 */

@Controller
@RequestMapping(value = "/beitai/health")
public class HealthController {

    @Autowired
    private HealthService healthService;

    @RequestMapping(value = "/savePressure",method = RequestMethod.POST)
    @ResponseBody
    public String savePressure(
            @RequestParam(value="HighPressure",defaultValue = "")String HighPressure,
            @RequestParam(value="LowPressure",defaultValue = "")String LowPressure,
            @RequestParam(value="Pulse",defaultValue = "")String Pulse,
            @RequestParam(value = "HealthUserID",defaultValue = "")String HealthUserID) {
        String result = healthService.savePressure(HighPressure, LowPressure, Pulse, HealthUserID);
        return result;
    }

    @RequestMapping(value = "/saveBlood",method = RequestMethod.POST)
    @ResponseBody
    public String saveBlood(
            @RequestParam(value="BloodGlucose",defaultValue = "")String BloodGlucose,
            @RequestParam(value = "HealthUserID",defaultValue = "")String HealthUserID) {
        String result = healthService.saveBlood(BloodGlucose, HealthUserID);
        return result;
    }

    @RequestMapping(value = "/saveTemperature",method = RequestMethod.POST)
    @ResponseBody
    public String saveTemperature(
            @RequestParam(value="Temperature",defaultValue = "")String Temperature,
            @RequestParam(value = "HealthUserID",defaultValue = "")String HealthUserID) {
        String result = healthService.saveTemperature(Temperature, HealthUserID);
        return result;
    }

    @RequestMapping(value = "/saveWeight",method = RequestMethod.POST)
    @ResponseBody
    public void saveWeight(
            @RequestParam(value="Weight",defaultValue = "")String Weight,
            @RequestParam(value = "HealthUserID",defaultValue = "")String HealthUserID) {

        healthService.getStableWeight(Weight,HealthUserID);

    }

    @RequestMapping(value = "/saveFat",method = RequestMethod.POST)
    @ResponseBody
    public String saveFat(
            @RequestParam(value="FatRate",defaultValue = "")String FatRate,
            @RequestParam(value="FatClass",defaultValue = "")String FatClass,
            @RequestParam(value="Muscle",defaultValue = "")String Muscle,
            @RequestParam(value="Water",defaultValue = "")String Water,
            @RequestParam(value = "HealthUserID",defaultValue = "")String HealthUserID) {
        String result = healthService.saveFat(FatRate, FatClass, Muscle, Water, HealthUserID);
        return result;
    }


    @RequestMapping(value = "/getHistoryPressure",method = RequestMethod.POST)
    @ResponseBody
    public List<Map<String,Object>> getHistoryPre(@RequestParam(value = "HealthUserID",defaultValue = "")String HealthUserID) {
        List<Map<String,Object>> list = healthService.getHistoryPre(HealthUserID);
        return list;
    }

    @RequestMapping(value = "/getHistoryBlood",method = RequestMethod.POST)
    @ResponseBody
    public List<Map<String,Object>> getHistoryBlo(@RequestParam(value = "HealthUserID",defaultValue = "")String HealthUserID) {
        List<Map<String,Object>> list = healthService.getHistoryBlo(HealthUserID);
        return list;
    }

    @RequestMapping(value = "/getHistoryTemperature",method = RequestMethod.POST)
    @ResponseBody
    public List<Map<String,Object>> getHistoryTem(@RequestParam(value = "HealthUserID",defaultValue = "")String HealthUserID) {
        List<Map<String,Object>> list = healthService.getHistoryTem(HealthUserID);
        return list;
    }

    @RequestMapping(value = "/getHistoryWeight",method = RequestMethod.POST)
    @ResponseBody
    public List<Map<String,Object>> getHistoryWei(@RequestParam(value = "HealthUserID",defaultValue = "")String HealthUserID) {
        List<Map<String,Object>> list = healthService.getHistoryWei(HealthUserID);
        return list;
    }

    @RequestMapping(value = "/getHistoryFat",method = RequestMethod.POST)
    @ResponseBody
    public List<Map<String,Object>> getHistoryFat(@RequestParam(value = "HealthUserID",defaultValue = "")String HealthUserID) {
        List<Map<String,Object>> list = healthService.getHistoryFat(HealthUserID);
        return list;
    }

}
