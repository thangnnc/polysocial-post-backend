package com.polysocial.rest.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.polysocial.consts.StatisticalAPI;
import com.polysocial.dto.StatisticalDTO;
import com.polysocial.service.StatisticalService;

@RestController
public class StatisticalController {
    
    @Autowired
    private StatisticalService statisticalService;

    @GetMapping(value = StatisticalAPI.API_STATISTICALAPI)
    public ResponseEntity getStatistical(@RequestParam Long groupId) {
       try{
        List<StatisticalDTO> statisticalDTOs = statisticalService.getStatisticalByGroupId(groupId);
        return ResponseEntity.ok(statisticalDTOs);
       }catch(Exception e){
        e.printStackTrace();
        return null;
       }
    }
}
