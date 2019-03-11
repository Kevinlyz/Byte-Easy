package com.mbyte.easy.entity;

import java.util.List;

public class SysRole {
    private Long id;

    private Boolean available;

    private String description;

    private String name;
    private List<SysResource> resources;
    public List<SysResource> getResources() {
        return resources;
    }
    public void setResources(List<SysResource> resources) {
        this.resources = resources;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Boolean getAvailable() {
        return available;
    }

    public void setAvailable(Boolean available) {
        this.available = available;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description == null ? null : description.trim();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }
}