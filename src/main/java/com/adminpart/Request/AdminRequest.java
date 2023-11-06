package com.adminpart.Request;

import lombok.*;

@EqualsAndHashCode
@Getter
@ToString
@AllArgsConstructor
@Setter
public class AdminRequest {
    private String adminName;
    private String adminNumber;
    private String password;
    private String adminUserid;
}
