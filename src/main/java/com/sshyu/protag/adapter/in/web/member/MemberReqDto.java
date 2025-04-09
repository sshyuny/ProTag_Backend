package com.sshyu.protag.adapter.in.web.member;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class MemberReqDto {
    
    private String memberName;
    private String loginId;
    private String password;

}
