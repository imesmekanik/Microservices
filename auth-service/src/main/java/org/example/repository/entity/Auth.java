package org.example.repository.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.example.repository.enums.Activated;
import org.example.repository.enums.Roles;

import javax.persistence.*;

@Table(name = "tblauth")
@Entity
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Auth {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;
    @Column(length = 16,nullable = false,unique = true)
    String username;
    String password;
    String email;
    String phone;
    @Enumerated(EnumType.STRING)
    Activated activated;
    @Enumerated(EnumType.STRING)
    Roles roles;
}
