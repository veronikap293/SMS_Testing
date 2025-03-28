package com.example.cms.controller;

import com.example.cms.controller.exceptions.CaptainNotFoundException;
import com.example.cms.model.entity.Captain;
import com.example.cms.model.entity.Player;
import com.example.cms.model.repository.CaptainRepository;
import com.example.cms.model.repository.PlayerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.transaction.Transactional;
import java.util.List;

@RestController
@RequestMapping("/captains") // Add a base path for all captain-related endpoints
public class CaptainController {

    @Autowired
    private final CaptainRepository captainRepository;
    @Autowired
    private PlayerRepository playerRepository;

    public CaptainController(CaptainRepository captainRepository, PlayerRepository playerRepository) {

        this.captainRepository = captainRepository;
        this.playerRepository = playerRepository;
    }

    // GET ALL CAPTAINS
    @GetMapping
    List<Captain> retrieveAllCaptains() {
        return captainRepository.findAll();
    }

    // RETRIEVE CAPTAIN
    @GetMapping("/{captainId}")
    Captain retreiveCaptain(@PathVariable("captainId") String userID) {
        return captainRepository.findById(userID)
                .orElseThrow(() -> new CaptainNotFoundException(userID));
    }

    // ADD PLAYER TO TEAM
    @PostMapping("/{captainId}/addPlayer/{playerId}")
    @Transactional
    public void addPlayerToTeam(@PathVariable String captainId, @PathVariable String playerId) {
        captainRepository.addPlayerToTeam(captainId, playerId);
    }

    // REMOVE PLAYER TO TEAM
    @DeleteMapping("/{captainId}/removePlayer/{playerId}")
    @Transactional
    public void removePlayerFromTeam(@PathVariable String captainId, @PathVariable String playerId) {
        captainRepository.removePlayerFromTeam(captainId, playerId);
    }

    // CREATE A PLAYER
    @PostMapping("/createPlayer")
    @Transactional
    public Player createPlayerForCaptain(@RequestBody Player player) {
        // Extract captain ID from security context or session (depending on how auth is set up)
        // For now, if it's hardcoded or test-based:
        String captainId = "1"; // replace with real logic later

        Captain captain = captainRepository.findById((captainId))
                .orElseThrow(() -> new CaptainNotFoundException(captainId));

        Player savedPlayer = playerRepository.save(player);

        captainRepository.addPlayerToTeam(String.valueOf(captainId), savedPlayer.getUserID());

        return savedPlayer;
    }
}