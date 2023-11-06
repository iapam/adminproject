package com.adminpart.Model;
import jakarta.persistence.*;
import lombok.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.Collections;
@ToString
@Getter
@Setter
@EqualsAndHashCode
@NoArgsConstructor
@Entity()
@Table(name = "agent")
public class Agent implements UserDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE,generator = "agents_Sequence")
    @SequenceGenerator(name="agents_Sequence",sequenceName = "agents_Sequence",allocationSize = 1)
    private Long agentId;
    private String agentName;
    private String agentNumber;
    private String agentTown;
    private String agentConsituency;
    @Column(name ="nppvotes" )
    private int nppvotes;
    @Column(name ="ndcvotes" )
    private int ndcvotes;
    private String userId;
    private String password;

    @Enumerated(EnumType.STRING)
    private agentRole role;
    @Column(name ="total_ndc_vote" )
    private int total_ndc_vote;
    @Column(name ="total_npp_vote" )
    private int total_npp_votes;
    public String status;

    public Agent(String agentName, String agentNumber, String agentTown, String agentConsituency, String userId, String password,agentRole role,String status) {
        this.agentName = agentName;
        this.agentNumber = agentNumber;
        this.agentTown = agentTown;
        this.agentConsituency = agentConsituency;
        this.userId = userId;
        this.password = password;
        this.role=role;
        this.status=status;

    }

    public Agent(String userId,int nppvotes, int ndcvotes, int total_ndc_vote, int total_npp_votes,agentRole role) {
        this.nppvotes = nppvotes;
        this.ndcvotes = ndcvotes;
        this.total_ndc_vote = total_ndc_vote;
        this.total_npp_votes = total_npp_votes;
        this.role=role;
        this.userId=userId;
    }

    public Agent(String agentName, String agentNumber, String agentTown, String agentConsituency, String userId,agentRole role) {
        this.agentName = agentName;
        this.agentNumber = agentNumber;
        this.agentTown = agentTown;
        this.agentConsituency = agentConsituency;
        this.userId = userId;
        this.role=role;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        SimpleGrantedAuthority authority=new SimpleGrantedAuthority(role.name());
        return Collections.singletonList(authority);
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return userId;
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
