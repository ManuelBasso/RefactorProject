package com.develhope.spring.user;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.develhope.spring.role.Role;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Inheritance(strategy = InheritanceType.JOINED)
@Data
@Table(name = "Users")
@AllArgsConstructor
@NoArgsConstructor
public class User implements UserDetails {

    @Id()
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String firstName;

    @Column(nullable = false)
    private String lastName;

    @Column(nullable = false, unique = true)
    private String email;

    @Column(nullable = false)
    private String password;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "users_role", joinColumns = @JoinColumn(name = "users_id"), inverseJoinColumns = @JoinColumn(name = "role_id"))
    private Set<Role> role = new HashSet<>();

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        Set<SimpleGrantedAuthority> authorities = new HashSet<>();
        for (Role role : this.role) {
            authorities.add(new SimpleGrantedAuthority(role.getName().name()));
        }
        return authorities;
    }

    @Override
    public String getUsername() {
        return email;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

}
