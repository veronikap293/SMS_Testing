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

@Repository
public interface CaptainRepository extends JpaRepository<Captain, Long> {

    // ADD PLAYER TO TEAM
    // does this by changing the player's teamID to the captains teamID
    @Modifying
    @Transactional
    @Query(value = "UPDATE Player p SET p.teamID = (SELECT c.teamID FROM Captain c WHERE c.userID = :captainId) WHERE p.userID = :playerId", nativeQuery = true)
    int addPlayerToTeam(@Param("captainId") Long captainId, @Param("playerId") Long playerId);

    // Captain removes player
    @Modifying
    @Transactional
    @Query(value = "UPDATE players p SET p.TEAMID = NULL WHERE p.USERID = :playerId AND EXISTS (SELECT 1 FROM teams t WHERE t.CAPTAINID = :captainId AND t.TEAMID = p.TEAMID)", nativeQuery = true)
    int removePlayerFromTeam(@Param("captainId") Long captainId, @Param("playerId") Long playerId);

}
