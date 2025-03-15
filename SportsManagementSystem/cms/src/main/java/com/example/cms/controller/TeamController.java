package com.example.cms.controller;


import com.example.cms.model.entity.Team;
import com.example.cms.model.entity.Player;
import com.example.cms.model.entity.Game;
import com.example.cms.model.repository.TeamRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


import java.util.HashMap;
import java.util.List;
import java.util.Map;


@CrossOrigin
@RestController
@RequestMapping("/teams")
public class TeamController {

    private final TeamRepository teamRepository;

    @Autowired
    public TeamController(TeamRepository teamRepository) {
        this.teamRepository = teamRepository;
    }

    // Get all teams
    @GetMapping("")
    public List<Team> getAllTeams() {
        return teamRepository.findAll();
    }

//    @GetMapping("/{teamID}")
//    public Team getTeamById(@PathVariable String teamID) {
//        return teamRepository.findById(teamID)
//                .orElseThrow(() -> new RuntimeException("Team not found with ID: " + teamID));
//    }
@GetMapping("/{teamID}")
public Map<String, Object> getTeamById(@PathVariable String teamID) {
    Team team = teamRepository.findById(teamID)
            .orElseThrow(() -> new RuntimeException("Team not found with ID: " + teamID));

    // Create a custom response including captainID
    Map<String, Object> response = new HashMap<>();
    response.put("teamID", team.getTeamID());
    response.put("teamName", team.getTeamName());
    response.put("captainID", team.getCaptain() != null ? team.getCaptain().getUserID() : null);
    response.put("homeGames", team.getHomeGames());
    response.put("awayGames", team.getAwayGames());

    return response;
}

//
//    // Get a team by ID
//    @GetMapping("/{teamID}")
//    public Team getTeamById(@PathVariable Long teamID) {
//        return teamRepository.findTeamById(teamID);
//    }
//
//    // Get all players in a team
//    @GetMapping("/{teamID}/players")
//    public List<Player> getPlayersByTeamId(@PathVariable Long teamID) {
//        return teamRepository.findPlayersByTeamId(teamID);
//    }
//
//    // Get the captain of a team
//    @GetMapping("/{teamID}/captain")
//    public Player getCaptain(@PathVariable Long teamID) {
//        return teamRepository.findCaptainByTeamId(teamID);
//    }
//
//    // Get the schedule of a team (all games in which the team is involved)
//    @GetMapping("/{teamID}/schedule")
//    public List<Game> getSchedule(@PathVariable Long teamID) {
//        return teamRepository.findScheduleByTeamId(teamID);
//    }
//
//    // Get the standings of a league (optional, can be based on league-related team rankings)
//    @GetMapping("/{teamID}/standings")
//    public List<Team> getTeamStandings(@PathVariable Long teamID) {
//        // Optionally, you can use the team repository to get standings if required
//        // For simplicity, assuming this is related to a league
//        String leagueID = teamRepository.findTeamById(teamID).getLeague().getLeagueID();
//        return teamRepository.getStandingsByLeagueId(leagueID);
//    }
}


