package com.dts.dtsbackend.controller;

import com.dts.dtsbackend.entity.Team;
import com.dts.dtsbackend.service.TeamService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/team")
@CrossOrigin(origins = "*")
public class TeamController {
    
    @Autowired
    private TeamService teamService;
    
    /**
     * Get all team members
     * @return List of all team members
     */
    @GetMapping
    public ResponseEntity<List<Team>> getAllTeamMembers() {
        try {
            List<Team> teamMembers = teamService.getAllTeamMembers();
            if (teamMembers.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
            return new ResponseEntity<>(teamMembers, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    
    /**
     * Get team member by ID
     * @param id Team member ID
     * @return Team member details
     */
    @GetMapping("/{id}")
    public ResponseEntity<Team> getTeamMemberById(@PathVariable("id") Long id) {
        try {
            Optional<Team> teamMember = teamService.getTeamMemberById(id);
            if (teamMember.isPresent()) {
                return new ResponseEntity<>(teamMember.get(), HttpStatus.OK);
            } else {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    
    /**
     * Create a new team member
     * @param team Team member data
     * @return Created team member
     */
    @PostMapping
    public ResponseEntity<Team> createTeamMember(@RequestBody Team team) {
        try {
            Team savedTeam = teamService.saveTeamMember(team);
            return new ResponseEntity<>(savedTeam, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    
    /**
     * Update an existing team member
     * @param id Team member ID
     * @param team Updated team member data
     * @return Updated team member
     */
    @PutMapping("/{id}")
    public ResponseEntity<Team> updateTeamMember(@PathVariable("id") Long id, @RequestBody Team team) {
        try {
            Optional<Team> existingTeam = teamService.getTeamMemberById(id);
            if (existingTeam.isPresent()) {
                team.setId(id);
                Team updatedTeam = teamService.saveTeamMember(team);
                return new ResponseEntity<>(updatedTeam, HttpStatus.OK);
            } else {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    
    /**
     * Delete a team member
     * @param id Team member ID
     * @return Response status
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<HttpStatus> deleteTeamMember(@PathVariable("id") Long id) {
        try {
            if (teamService.existsById(id)) {
                teamService.deleteTeamMember(id);
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            } else {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    
    /**
     * Search team members by position
     * @param position Position to search for
     * @return List of team members with matching position
     */
    @GetMapping("/search/position")
    public ResponseEntity<List<Team>> getTeamMembersByPosition(@RequestParam String position) {
        try {
            List<Team> teamMembers = teamService.getTeamMembersByPosition(position);
            if (teamMembers.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
            return new ResponseEntity<>(teamMembers, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    
    /**
     * Search team members by name
     * @param name Name to search for
     * @return List of team members with matching name
     */
    @GetMapping("/search/name")
    public ResponseEntity<List<Team>> getTeamMembersByName(@RequestParam String name) {
        try {
            List<Team> teamMembers = teamService.getTeamMembersByName(name);
            if (teamMembers.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
            return new ResponseEntity<>(teamMembers, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
