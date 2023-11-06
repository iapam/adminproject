package com.adminpart.Service;

import com.adminpart.Controllers.agentDetail;
import com.adminpart.Model.Agent;
import com.adminpart.Security.PassEncoder;
import lombok.AllArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import com.adminpart.Repository.agentRepository;

import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
@Service
public class AgentService implements UserDetailsService {
    private final PassEncoder encodes;
    private final agentRepository repository;
    private final agentDetail agentDetail;
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return repository.findByUserId(username).orElseThrow(
                ()->new IllegalStateException("User with id "+username+" is not registed")
        );
    }
    public String registers(Agent agent) {
        BCryptPasswordEncoder passwordEncoder=encodes.encoder();
        String password= passwordEncoder.encode(agent.getPassword());
        agent.setPassword(password);
        repository.save(agent);
        return "registered";
    }
    public String updateagent(Agent agent){
        repository.updateAgent(agent.getUserId(), agent.getNppvotes(),
                agent.getNdcvotes(),agent.getTotal_ndc_vote(), agent.getTotal_npp_votes(),"record");
        System.out.println(agent.getNdcvotes()+"the votes");
        return "Thanks for your Contributiom";
    }
    public List<agentDetail> agentdetail(List<Agent> agents){
        agentDetail detail=null;
        List<agentDetail> agentdetails=new ArrayList<>();
        for(Agent agent: agents){
            detail=new agentDetail();
            detail.setAgentname(agent.getAgentName());
            detail.setAgenttown(agent.getAgentTown());
            detail.setAgentpost(agent.getAgentConsituency());
            detail.setNumber(agent.getAgentNumber());
            detail.setNppvotes(agent.getNppvotes());
            detail.setNdcvotes(agent.getNdcvotes());
            detail.setUserId(agent.getUserId());
            detail.setTotal_ndc_vote(agent.getTotal_ndc_vote());
            detail.setTotal_npp_votes(agent.getTotal_npp_votes());
            agentdetails.add(detail);
        }

        return agentdetails;
    }

}
