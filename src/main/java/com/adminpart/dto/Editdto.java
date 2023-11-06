package com.adminpart.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class Editdto {
    private String userId;
    private int ndc_votes;
    private int nppvotes;
}
