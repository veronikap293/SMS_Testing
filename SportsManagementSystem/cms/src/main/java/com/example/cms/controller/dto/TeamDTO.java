package com.example.cms.controller.dto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class TeamDTO {
    private String teamID;
    private String teamName;
    private int teamCapacity;
    private String sportName;
    private String divisionName;
}

