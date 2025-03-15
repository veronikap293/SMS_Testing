package com.example.cms.controller.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class RefereeDTO {
    private long refID;
    private long userID;
    private String firstName;
    private String lastName;
    private String email;
    private List<Long> gameIDs;
}

