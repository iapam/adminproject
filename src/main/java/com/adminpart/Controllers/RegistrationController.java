package com.adminpart.Controllers;
import com.adminpart.dto.Editdto;
import com.adminpart.Model.Agent;
import com.adminpart.Repository.agentRepository;
import com.adminpart.Request.AdminRequest;
import com.adminpart.Request.RegistrationRequest;
import com.adminpart.Service.AdminService;
import com.adminpart.Service.registrationService;
import com.adminpart.Service.AgentService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;


@Controller
@AllArgsConstructor
public class RegistrationController {
    private final agentRepository agentRepository;
    private final registrationService registrationservice;
    private final AgentService agentService;
    private final AdminService adminService;
    private final RegistrationRequest registrationRequest;

    @PostMapping("/save")
    public String req(@RequestParam("userid") String userid,@RequestParam("agentName") String agentName
    ,@RequestParam("agentNumber") String agentNumber,@RequestParam("agentTown") String agentTown
    ,@RequestParam("agentPost") String agentPost,@RequestParam("password") String password){
       registrationRequest.setUserId(userid);
       registrationRequest.setAgentName(agentName);
       registrationRequest.setAgentTown(agentTown);
       registrationRequest.setAgentConsituency(agentPost);
       registrationRequest.setPassword("00000");
       registrationRequest.setAgentNumber(agentNumber);
       String save=registrationservice.regService(registrationRequest);
        return  "redirect:/recordAgents";
        //agentdetails.cnycdphn7qhf.eu-north-1.rds.amazonaws.com
        //13.48.104.154
    }

    @PostMapping("/registerAdmin")
    public String saveAdmin(@RequestBody AdminRequest  adminRequest){

        return registrationservice.registerAdmin(adminRequest);
    }

    @GetMapping("/recordAgents")
    public String getRecordAgent(Model model){
        List<Agent>storeRecordAgent=new ArrayList<>();
        List<Agent> allAgents=agentRepository.findAll();
        for(Agent agent:allAgents){
            if(agent.getStatus().equals("record")){
                storeRecordAgent.add(agent);
            }
        }
        int NURA=getnumberofUrecord();
        List<agentDetail> returnDetails=agentService.agentdetail(storeRecordAgent);
        model.addAttribute("recordAgent",returnDetails);
        model.addAttribute("nura",NURA);

        return "/Admindashboard";
    }
    @GetMapping("/recordAgents/getUnrecodedAgent")
    public String getUnRecordedAgent(Model model) {

        List<Agent> storeunRecordAgent = new ArrayList<>();
        List<Agent> allAgents = agentRepository.findAll();
        for (Agent agent : allAgents) {
            if (agent.getStatus().equals("unrecord")) {
                storeunRecordAgent.add(agent);
            }
        }
       List<agentDetail>listNURA=agentService.agentdetail(storeunRecordAgent);
        model.addAttribute("unrecord",listNURA);
        return "/viewUnrecordAgent";
    }
    @GetMapping("/numUnrecord")
    public int getnumberofUrecord(){
         int counter=0;
        List<Agent> allAgents = agentRepository.findAll();
        for (Agent agent : allAgents) {
            if (agent.getStatus().equals("unrecord")) {
                counter=counter+1;
            }
        }
        return counter;
    }
    @GetMapping("/recordAgents/registrationPage")
    public String registrationPage(Model model){
        Random ras=new Random();
        int userid=ras.nextInt(20000000)+1;
       // int password=ras.nextInt(1000000)+1;

        model.addAttribute("user",userid);
        model.addAttribute("password","00000");
        return "/registeration";
    }
    @GetMapping("/recordAgents/deleteuser/{userid}")
    public String deleteUser(@PathVariable("userid") String userid){
       agentRepository.deleteUser(userid);
        return  "redirect:/recordAgents";
    }
    @PostMapping("/editVote")
    public String editVote(@RequestBody Editdto editdto){
      adminService.editVotes(editdto);
        return  "redirect:/recordAgents";
    }


}
