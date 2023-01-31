package com.example.decagon_Life_Project.entities;

import lombok.*;
import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.io.Serializable;


@Entity
@Data
@Builder
@Setter @Getter
@AllArgsConstructor
@NoArgsConstructor
public class UserEntity implements Serializable {
    private static final long serialVersionUID = 2L;
    @Id
    @Column(name = "id", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long userId;
    @ManyToOne
    @JoinColumn(name = "roles_role_id")
    private Role roles;
    @Size(min = 2, max=64, message = "firstname must be at least two letter long")
    private String firstName;
    @Size(min = 2, max=64, message = "lastname must be at least two letter long")
    private String lastName;
    @Email(regexp = "[a-z0-9._%+-]+@[a-z0-9.-]+\\.[a-z]{2,3}",
            flags = Pattern.Flag.CASE_INSENSITIVE)
    private String email;
    @Size(min = 8, max = 16, message = "password must be at least 8 letters long")
    @Column(nullable = false)
    private String password;
    @Column(nullable = false)
    private Boolean subscribeToNewsletter;
    private String token;
    private Boolean isActive;

}
