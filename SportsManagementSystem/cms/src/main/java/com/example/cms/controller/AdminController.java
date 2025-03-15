package com.example.cms.controller;

import com.example.cms.controller.dto.LeagueDTO;
import com.example.cms.model.entity.Admin;
import com.example.cms.model.repository.AdminRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("/admin")
class AdminController {

    private final AdminRepository adminRepository;

    @Autowired
    AdminController(AdminRepository adminRepository) {
        this.adminRepository = adminRepository;
    }

    // Get all admins
    @GetMapping()
    List<Admin> retrieveAllAdmin() {
        return adminRepository.findAll();
    }

    // Create a new league
    @PostMapping("/league")
    String createLeague(@RequestParam String leagueID,
                        @RequestParam String leagueName,
                        @RequestParam String leagueGender,
                        @RequestParam String divisionNum,
                        @RequestParam String leagueSport) {
        try {
            adminRepository.createLeague(
                    leagueID,
                    leagueName,
                    leagueGender,
                    divisionNum,
                    leagueSport
            );
            return "League created successfully.";
        } catch (Exception e) {
            return "Error creating league: " + e.getMessage();
        }
    }

    // Delete a league
    @DeleteMapping("/league/{leagueId}")
    String deleteLeague(@PathVariable String leagueId) {
        try {
            adminRepository.deleteLeague(leagueId);
            return "League deleted successfully.";
        } catch (Exception e) {
            return "Error deleting league: " + e.getMessage();
        }
    }

    // Create a new team
    @PostMapping("/team")
    String createTeam(@RequestParam String teamID,
                      @RequestParam String teamName,
                      @RequestParam String captainID,
                      @RequestParam String leagueID) {
        try {
            adminRepository.createTeam(teamID, teamName, captainID, leagueID);
            return "Team created successfully.";
        } catch (Exception e) {
            return "Error creating team: " + e.getMessage();
        }
    }

    // Delete a team
    @DeleteMapping("/team/{teamId}")
    String deleteTeam(@PathVariable String teamId) {
        try {
            adminRepository.deleteTeam(teamId);
            return "Team deleted successfully.";
        } catch (Exception e) {
            return "Error deleting team: " + e.getMessage();
        }
    }

    // Create a new game
    @PostMapping("/game")
    String createGame(@RequestParam Long gameID,
                      @RequestParam String datetime,
                      @RequestParam String location,
                      @RequestParam String team1ID,
                      @RequestParam String team2ID,
                      @RequestParam int teamScore1,
                      @RequestParam int teamScore2,
                      @RequestParam String gameStatus,
                      @RequestParam Long refereeID,
                      @RequestParam String leagueID) {
        try {
            adminRepository.createGame(
                    gameID, datetime, location, team1ID, team2ID,
                    teamScore1, teamScore2, gameStatus, refereeID, leagueID
            );
            return "Game created successfully.";
        } catch (Exception e) {
            return "Error creating game: " + e.getMessage();
        }
    }

    // Delete a game
    @DeleteMapping("/game/{gameId}")
    String deleteGame(@PathVariable Long gameId) {
        try {
            adminRepository.deleteGame(gameId);
            return "Game deleted successfully.";
        } catch (Exception e) {
            return "Error deleting game: " + e.getMessage();
        }
    }

    // Assign captain to a team
    @PutMapping("/team/{teamId}/captain/{captainId}")
    public String assignCaptain(
            @PathVariable String teamId,
            @PathVariable String captainId,  // Changed to String
            @RequestParam String adminId) {  // Changed to String
        try {
            adminRepository.assignCaptainByAdmin(teamId, captainId, adminId);
            return "Captain assigned successfully.";
        } catch (Exception e) {
            return "Error assigning captain: " + e.getMessage();
        }
    }

    // Assign a referee to a game
    @PostMapping("/game/{gameId}/referee")
    String assignReferee(@PathVariable Long gameId, @RequestParam Long refereeID) {
        try {
            adminRepository.assignReferee(gameId, refereeID);
            return "Referee assigned successfully.";
        } catch (Exception e) {
            return "Error assigning referee: " + e.getMessage();
        }
    }

    // Update game score
    @PutMapping("/game/{gameId}/score")
    String updateGameScore(@PathVariable Long gameId,
                           @RequestParam int teamScore1,
                           @RequestParam int teamScore2) {
        try {
            adminRepository.updateGameScore(gameId, teamScore1, teamScore2);
            return "Game score updated successfully.";
        } catch (Exception e) {
            return "Error updating game score: " + e.getMessage();
        }
    }

    // Update game status
    @PutMapping("/game/{gameId}/status")
    String updateGameStatus(@PathVariable Long gameId,
                            @RequestParam String gameStatus) {
        try {
            adminRepository.updateGameStatus(gameId, gameStatus);
            return "Game status updated successfully.";
        } catch (Exception e) {
            return "Error updating game status: " + e.getMessage();
        }
    }
    // Remove captain to a team
    @DeleteMapping("/team/{teamId}/captain")
    public String removeCaptain(
            @PathVariable String teamId,
            @RequestParam String adminId) {
        try {
            adminRepository.removeCaptainByAdmin(teamId, adminId);
            return "Captain removed successfully.";
        } catch (Exception e) {
            return "Error removing captain: " + e.getMessage();
        }
    }
}