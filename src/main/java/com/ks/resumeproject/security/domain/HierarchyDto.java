package com.ks.resumeproject.security.domain;

import lombok.Data;

@Data
public class HierarchyDto {
    private Long id;
    private String roleType;
    private Long parentId;
    private String parentType;
}
