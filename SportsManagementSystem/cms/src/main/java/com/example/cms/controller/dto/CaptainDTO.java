package com.example.cms.controller.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CaptainDTO {
    private long captainID;
    private long userID;
    private String firstName;
    private String lastName;
    private String email;
}

