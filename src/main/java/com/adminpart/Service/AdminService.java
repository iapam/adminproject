package com.adminpart.Service;

import com.adminpart.Model.Admin;
import com.adminpart.Model.Agent;
import com.adminpart.Security.PassEncoder;
import com.adminpart.Repository.AdminRepository;
import com.adminpart.Request.AdminRequest;
import com.adminpart.dto.Editdto;
import lombok.AllArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import com.adminpart.Repository.agentRepository;

import java.util.List;

@Service
@AllArgsConstructor
public class AdminService implements UserDetailsService {
    private final AdminRepository adminRepository;
    private final PassEncoder encodes;
    private final agentRepository agentrepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return adminRepository.findByAdminUserid(username).orElseThrow(()->
                new UsernameNotFoundException("Incorrect details"));
    }
    public String registersadmin(Admin admin) {
        BCryptPasswordEncoder passwordEncoder=encodes.encoder();
        String password= passwordEncoder.encode(admin.getPassword());
        admin.setPassword(password);
        adminRepository.save(admin);
        return "registered";
    }
    public String editVotes(Editdto editdto){
        int totalndcvote=0;
        int totalnppvote=0;
        Agent agent=agentrepository.findAgentByUserId(editdto.getUserId())
                .orElseThrow(()->new IllegalStateException("not agent"));
        if(agent.status.equals("record")){
            return "error_page";
        }else{
            List<Agent>agents=agentrepository.findAll();
            for(Agent agent1 : agents){
               totalndcvote=totalndcvote+agent1.getNdcvotes();
               totalnppvote=totalnppvote+agent1.getNppvotes();

            }
            int totalndc=totalndcvote+editdto.getNdc_votes();
            int totalnpp=totalndcvote+editdto.getNppvotes();
            System.out.println(totalndc+" "+totalnpp);
            agentrepository.updateAgent(editdto.getUserId(),
                    editdto.getNppvotes(),editdto.getNdc_votes(),totalndc,totalnpp,"record");
        }
        return "edited successfully";
    }
}
