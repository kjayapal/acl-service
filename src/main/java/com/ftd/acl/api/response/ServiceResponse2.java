package com.ftd.acl.api.response;

import java.io.Serializable;
import java.util.List;

import com.ftd.acl.data.AclData;

public class ServiceResponse2 implements Serializable {
    private static final long serialVersionUID = -3228807230740133965L;
    private List<AclData> aclData;
    private String status;

    public ServiceResponse2() {
        this.aclData = null;
        this.status = "error";
    }

    public ServiceResponse2(List<AclData> aclData) {
        this.aclData = aclData;
        this.status = "success";
    }

    public List<AclData> getAclData() {
        return aclData;
    }

    public void setAclData(List<AclData> aclData) {
        this.aclData = aclData;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

}
