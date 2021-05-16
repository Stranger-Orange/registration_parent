package com.orange.registration.vo.acl;

import lombok.Data;

@Data
public class AssignVo {

    private Long roleId;

    private Long[] permissionId;
}
