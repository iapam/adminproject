package com.adminpart.Request;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Component;

@Getter
@Setter
@EqualsAndHashCode
@Component
public class RegistrationRequest {
    private String agentName;
    private String agentNumber;
    private String agentTown;
    private String agentConsituency;
    private String userId;
    private String password;
}

