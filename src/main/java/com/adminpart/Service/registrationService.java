package com.adminpart.Service;

import com.adminpart.Model.Admin;
import com.adminpart.Model.Agent;
import com.adminpart.Model.agentRole;
import com.adminpart.Request.AdminRequest;
import com.adminpart.Request.RegistrationRequest;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class registrationService {
    private final AgentService agentService;
    private final AdminService adminService;
    public String regService(RegistrationRequest registrationRequest){
        return agentService.registers(new Agent(
                registrationRequest.getAgentName(),
                registrationRequest.getAgentNumber(),
                registrationRequest.getAgentTown(),
                registrationRequest.getAgentConsituency(),
                registrationRequest.getUserId(),
                registrationRequest.getPassword(), agentRole.USER,
                "unrecord"
        ));
    }
    public String registerAdmin(AdminRequest adminRequest){
        return adminService.registersadmin(new Admin(adminRequest.getAdminName()
                ,adminRequest.getAdminNumber()
                ,adminRequest.getAdminUserid()
                ,adminRequest.getPassword(),agentRole.ADMIN)) ;
    }
}
