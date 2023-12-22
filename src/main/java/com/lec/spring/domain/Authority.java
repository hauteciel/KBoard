package com.lec.spring.domain;

import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Authority {
    private Long id;  // PK
    private String name;   // 권한명 ex) "ROLE_MEMBER", "ROLE_ADMIN"
}
