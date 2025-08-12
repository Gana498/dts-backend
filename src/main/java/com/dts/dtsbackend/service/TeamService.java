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
}
