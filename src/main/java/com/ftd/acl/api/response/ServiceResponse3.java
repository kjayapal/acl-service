package com.ftd.acl.api.response;

import java.io.Serializable;
import java.util.HashMap;

import io.swagger.annotations.ApiModelProperty;

public class ServiceResponse3 implements Serializable {
    private static final long serialVersionUID = -3228807230740133965L;
    @ApiModelProperty(notes = "API response status")
    private String status;
    @ApiModelProperty(notes = "ACLs list (parameter and associated actiontype)")
    private HashMap<String, String[]> hashmap;

    public ServiceResponse3() {
        this.hashmap = null;
        this.status = "error";
    }

    public ServiceResponse3(HashMap<String, String[]> hashmap) {
        this.hashmap = hashmap;
        this.status = "success";
    }

    public HashMap<String, String[]> getHashmap() {
        return hashmap;
    }

    public void setHashmap(HashMap<String, String[]> hashmap) {
        this.hashmap = hashmap;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

}
