package com.polysocial.dto;

import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;

import lombok.Data;

@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
@Data
public class GroupDTO {
    private Long groupId;

    private String name;

    private Long totalMember;

    private String description;

    private Boolean status;

    private LocalDateTime createdDate;
}
