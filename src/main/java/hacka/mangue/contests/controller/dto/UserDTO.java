package hacka.mangue.contests.controller.dto;

import hacka.mangue.contests.domain.models.user.User;

import java.util.Set;
import java.util.stream.Collectors;

public class UserDTO {

    private Long id;
    private String username;
    private String password;
    private Set<ContestDTO> contests; // Use ContestDTO for contests

    // Default constructor
    public UserDTO() {
    }

    // Constructor to convert entity to DTO
    public UserDTO(User user) {
        this.id = user.getId();
        this.username = user.getUsername();
        this.password = user.getPassword();
        this.contests = user.getContests().stream()
                .map(ContestDTO::new) // Map contests to ContestDTO
                .collect(Collectors.toSet());
    }

    // Method to convert DTO to entity
    public User toModel() {
        User user = new User();
        user.setId(this.id);
        user.setUsername(this.username);
        user.setPassword(this.password);
        return user;
    }

    // Getters and setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public Set<ContestDTO> getContests() {
        return contests;
    }

    public void setContests(Set<ContestDTO> contests) {
        this.contests = contests;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}