package com.adminpart.Controllers;
import lombok.*;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

@EqualsAndHashCode
@Getter
@ToString
@AllArgsConstructor
@Setter
@NoArgsConstructor
@Component
public class agentDetail {
    private String agentname;
    private String number;
    private String agenttown;
    private String agentpost;
    private int nppvotes;
    private int ndcvotes;
    private String userId;
    private int total_ndc_vote;
    private int total_npp_votes;
}
