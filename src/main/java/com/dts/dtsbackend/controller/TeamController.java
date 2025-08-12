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
    
    /**
     * Add a new team member with validation
     * @param team Team member data
     * @return Created team member
     */
    @PostMapping("/add-team-member")
    public ResponseEntity<?> addTeamMember(@RequestBody Team team) {
        try {
            // Validate required fields
            if (team.getName() == null || team.getName().trim().isEmpty()) {
                return new ResponseEntity<>("Name is required", HttpStatus.BAD_REQUEST);
            }
            if (team.getPosition() == null || team.getPosition().trim().isEmpty()) {
                return new ResponseEntity<>("Position is required", HttpStatus.BAD_REQUEST);
            }
            if (team.getDescription() == null || team.getDescription().trim().isEmpty()) {
                return new ResponseEntity<>("Description is required", HttpStatus.BAD_REQUEST);
            }
            
            // Validate email format if provided
            if (team.getEmail() != null && !team.getEmail().trim().isEmpty()) {
                String emailRegex = "^[^\\s@]+@[^\\s@]+\\.[^\\s@]+$";
                if (!team.getEmail().matches(emailRegex)) {
                    return new ResponseEntity<>("Invalid email format", HttpStatus.BAD_REQUEST);
                }
            }
            
            // Validate LinkedIn URL if provided
            if (team.getLinkedin() != null && !team.getLinkedin().trim().isEmpty()) {
                if (!team.getLinkedin().contains("linkedin.com")) {
                    return new ResponseEntity<>("Invalid LinkedIn URL", HttpStatus.BAD_REQUEST);
                }
            }
            
            Team savedTeam = teamService.addTeamMember(team);
            return new ResponseEntity<>(savedTeam, HttpStatus.CREATED);
        } catch (IllegalArgumentException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        } catch (Exception e) {
            return new ResponseEntity<>("Failed to add team member: " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    
    /**
     * Get team members who report to a specific person
     * @param managerId Manager's ID
     * @return List of team members reporting to the manager
     */
    @GetMapping("/reports-to/{managerId}")
    public ResponseEntity<List<Team>> getTeamMembersByReportsTo(@PathVariable("managerId") Long managerId) {
        try {
            List<Team> teamMembers = teamService.getTeamMembersByReportsTo(managerId);
            if (teamMembers.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
            return new ResponseEntity<>(teamMembers, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    
    /**
     * Get all top-level team members (CEOs/founders)
     * @return List of top-level team members
     */
    @GetMapping("/top-level")
    public ResponseEntity<List<Team>> getTopLevelTeamMembers() {
        try {
            List<Team> teamMembers = teamService.getTopLevelTeamMembers();
            if (teamMembers.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
            return new ResponseEntity<>(teamMembers, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    
    /**
     * Validate if a team member exists (for manager validation)
     * @param id Team member ID to validate
     * @return Validation response
     */
    @GetMapping("/validate/{id}")
    public ResponseEntity<Boolean> validateTeamMember(@PathVariable("id") Long id) {
        try {
            boolean exists = teamService.existsById(id);
            return new ResponseEntity<>(exists, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(false, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
