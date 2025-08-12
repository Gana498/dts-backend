package com.dts.dtsbackend.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import com.dts.dtsbackend.entity.Team;
import com.dts.dtsbackend.service.TeamService;

@Component
public class DataInitializer implements CommandLineRunner {
    
    @Autowired
    private TeamService teamService;
    
    @Override
    public void run(String... args) throws Exception {
        // Check if data already exists
        if (teamService.getAllTeamMembers().isEmpty()) {
            initializeTeamData();
        }
    }
    
    private void initializeTeamData() {
        // Create sample team members
        Team member1 = new Team(
            "DHARMA TEJA NERALLA",
            "CEO & Founder",
            "Experienced full-stack developer with expertise in Python, Django, and React. Passionate about clean code and modern development practices.",
            "https://via.placeholder.com/150/0066CC/FFFFFF?text=DT",
            "dharmateja@dts.com",
            "https://www.linkedin.com/in/dharma-teja-neralla-490072255",
            null // CEO level - no manager
        );
        
        // Save the CEO first
        Team savedCEO = teamService.saveTeamMember(member1);
         
        System.out.println("Sample team data initialized successfully!");
    }
}
