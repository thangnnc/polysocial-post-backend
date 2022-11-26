package com.polysocial.service;

import java.util.List;

import com.polysocial.dto.StatisticalDTO;

public interface StatisticalService {

    List<StatisticalDTO> getStatisticalByGroupId(Long groupId);

    
}
