package com.adminpart.Model;

import com.adminpart.Model.agentRole;
import jakarta.persistence.*;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.Collections;

@Entity
@Setter
@EqualsAndHashCode
@NoArgsConstructor
public class Admin implements UserDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE,generator = "agents_Sequence1")
    @SequenceGenerator(name="agents_Sequence1",sequenceName = "agents_Sequence1",allocationSize = 1)
    private Long agentId;
    private String adminName;
    private String adminNumber;
    private String password;
    private String adminUserid;
    @Enumerated(EnumType.STRING)
    private agentRole role;

    public Admin(String adminName, String adminNumber, String adminuserid, String password, agentRole role) {
        this.adminName = adminName;
        this.adminNumber = adminNumber;
        this.adminUserid = adminuserid;
        this.password = password;
        this.role = role;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        SimpleGrantedAuthority authority=new SimpleGrantedAuthority(role.name());
        return Collections.singleton(authority);
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return adminUserid;
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
