package com.example.cms.model.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import java.util.ArrayList;

@Entity
@NoArgsConstructor
@Getter
@Setter
//@Table(name = "players")
//public class Player extends User{
//
//
//    @ManyToOne // allow multiple players per team
//    @JoinColumn(name = "teamID") // FK
//    @JsonBackReference("team-players")
//    private Team team;
//}
//

@Table(name = "players")
public class Player extends User {

    @ManyToOne
    @JoinColumn(name = "teamID") // Foreign Key
    @JsonBackReference("team-players") // Prevents infinite recursion
    private Team team;

    // Expose teamID in JSON responses, but do not store it separately
    @Transient
    public String getTeamID() {
        return (team != null) ? team.getTeamID() : null;
    }
}
