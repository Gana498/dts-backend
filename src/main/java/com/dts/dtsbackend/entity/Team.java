package com.dts.dtsbackend.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "teams")
public class Team {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @Column(nullable = false, length = 100)
    private String name;
    
    @Column(nullable = false, length = 100)
    private String position;
    
    @Column(columnDefinition = "TEXT")
    private String description;
    
    @Column(length = 500)
    private String avatar;
    
    @Column(length = 100)
    private String email;
    
    @Column(length = 200)
    private String linkedin;
    
    // Default constructor
    public Team() {}
    
    // Constructor with required fields
    public Team(String name, String position, String description) {
        this.name = name;
        this.position = position;
        this.description = description;
    }
    
    // Full constructor
    public Team(String name, String position, String description, String avatar, String email, String linkedin) {
        this.name = name;
        this.position = position;
        this.description = description;
        this.avatar = avatar;
        this.email = email;
        this.linkedin = linkedin;
    }
    
    // Getters and Setters
    public Long getId() {
        return id;
    }
    
    public void setId(Long id) {
        this.id = id;
    }
    
    public String getName() {
        return name;
    }
    
    public void setName(String name) {
        this.name = name;
    }
    
    public String getPosition() {
        return position;
    }
    
    public void setPosition(String position) {
        this.position = position;
    }
    
    public String getDescription() {
        return description;
    }
    
    public void setDescription(String description) {
        this.description = description;
    }
    
    public String getAvatar() {
        return avatar;
    }
    
    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }
    
    public String getEmail() {
        return email;
    }
    
    public void setEmail(String email) {
        this.email = email;
    }
    
    public String getLinkedin() {
        return linkedin;
    }
    
    public void setLinkedin(String linkedin) {
        this.linkedin = linkedin;
    }
    
    @Override
    public String toString() {
        return "Team{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", position='" + position + '\'' +
                ", description='" + description + '\'' +
                ", avatar='" + avatar + '\'' +
                ", email='" + email + '\'' +
                ", linkedin='" + linkedin + '\'' +
                '}';
    }
}
