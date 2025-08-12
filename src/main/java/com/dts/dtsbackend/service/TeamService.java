package com.dts.dtsbackend.service;

import com.dts.dtsbackend.entity.Team;
import com.dts.dtsbackend.repository.TeamRepository;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TeamService {
    
    @Autowired
    private TeamRepository teamRepository;
    
    // Get all team members
    public List<Team> getAllTeamMembers() {
        return teamRepository.findAllByOrderByIdAsc();
    }
    
    // Get team member by ID
    public Optional<Team> getTeamMemberById(Long id) {
        return teamRepository.findById(id);
    }
    
    // Save or update team member
    public Team saveTeamMember(Team team) {
        return teamRepository.save(team);
    }
    
    // Delete team member by ID
    public void deleteTeamMember(Long id) {
        teamRepository.deleteById(id);
    }
    
    // Find team members by position
    public List<Team> getTeamMembersByPosition(String position) {
        return teamRepository.findByPositionContainingIgnoreCase(position);
    }
    
    // Find team members by name
    public List<Team> getTeamMembersByName(String name) {
        return teamRepository.findByNameContainingIgnoreCase(name);
    }
    
    // Check if team member exists
    public boolean existsById(Long id) {
        return teamRepository.existsById(id);
    }
    
    // Get team members who report to a specific person
    public List<Team> getTeamMembersByReportsTo(Long reportsTo) {
        return teamRepository.findByReportsTo(reportsTo);
    }
    
    // Get all top-level team members (CEOs/founders)
    public List<Team> getTopLevelTeamMembers() {
        return teamRepository.findByReportsToIsNull();
    }
    
    // Validate if a manager exists (for hierarchy validation)
    public boolean isValidManager(Long managerId) {
        return managerId == null || teamRepository.existsById(managerId);
    }
    
    // Generate default avatar if not provided
    public String generateDefaultAvatar(String name) {
        if (name == null || name.trim().isEmpty()) {
            return "https://via.placeholder.com/150/0066CC/FFFFFF?text=?";
        }
        
        String[] nameParts = name.trim().split("\\s+");
        String initials = "";
        
        if (nameParts.length >= 2) {
            initials = nameParts[0].substring(0, 1).toUpperCase() + 
                      nameParts[nameParts.length - 1].substring(0, 1).toUpperCase();
        } else if (nameParts.length == 1) {
            initials = nameParts[0].substring(0, Math.min(2, nameParts[0].length())).toUpperCase();
        }
        
        return "https://via.placeholder.com/150/0066CC/FFFFFF?text=" + initials;
    }
    
    // Add team member with validation and avatar generation
    public Team addTeamMember(Team team) {
        // Validate manager exists if reportsTo is provided
        if (!isValidManager(team.getReportsTo())) {
            throw new IllegalArgumentException("Invalid manager ID: " + team.getReportsTo());
        }
        
        // Generate avatar if not provided
        if (team.getAvatar() == null || team.getAvatar().trim().isEmpty()) {
            team.setAvatar(generateDefaultAvatar(team.getName()));
        }
        
        return teamRepository.save(team);
    }
}
