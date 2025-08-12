package com.dts.dtsbackend.repository;

import com.dts.dtsbackend.entity.Team;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TeamRepository extends JpaRepository<Team, Long> {
    
    // Find team members by position
    List<Team> findByPositionContainingIgnoreCase(String position);
    
    // Find team members by name
    List<Team> findByNameContainingIgnoreCase(String name);
    
    // Find all team members ordered by id
    List<Team> findAllByOrderByIdAsc();
    
    // Find team members who report to a specific person
    List<Team> findByReportsTo(Long reportsTo);
    
    // Find all top-level team members (CEOs/founders)
    List<Team> findByReportsToIsNull();
}
