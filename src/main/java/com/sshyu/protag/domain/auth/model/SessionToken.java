package com.sshyu.protag.domain.auth.model;

import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class SessionToken {
    
    private final String token;

}
