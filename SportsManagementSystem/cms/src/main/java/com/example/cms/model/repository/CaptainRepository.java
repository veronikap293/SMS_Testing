package com.example.cms.model.repository;
import com.example.cms.controller.exceptions.PlayerNotFoundException;
import com.example.cms.model.entity.Player;
import com.example.cms.model.entity.Team;

import com.example.cms.model.entity.Captain;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Repository
public interface CaptainRepository extends JpaRepository<Captain, String> {

    // ADD PLAYER TO TEAM
    // does this by changing the player's teamID to the captains teamID
    @Modifying
    @Transactional
    @Query(value = "UPDATE players SET teamID = (SELECT t.teamID FROM teams t WHERE t.captainID = :captainId) WHERE userId = :playerId", nativeQuery = true)
    int addPlayerToTeam(@Param("captainId") String captainId, @Param("playerId") String playerId);

    // Captain removes player
    @Modifying
    @Transactional
    @Query(value = "UPDATE players p SET p.teamID = NULL WHERE p.userID = :playerId AND EXISTS (SELECT 1 FROM captains c WHERE c.userID = :captainId AND c.role = 'captain' AND c.teamID = p.teamID)", nativeQuery = true)
    int removePlayerFromTeam(@Param("captainId") String captainId, @Param("playerId") String playerId);


    Captain findByEmailAndPassword(String email, String password);



}
