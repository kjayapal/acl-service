package com.ftd.acl.api.response;

import java.io.Serializable;
import java.util.List;
import com.ftd.acl.data.Acl;

public class ServiceResponse implements Serializable {
    private static final long serialVersionUID = -3228807230740133965L;
    private List<Acl> aclList;
    private String status;

    public ServiceResponse() {
        this.aclList = null;
        this.status = "error";
    }

    public ServiceResponse(Acl acl) {
        this.aclList.set(0, acl);
        this.status = "success";
    }

    public ServiceResponse(List<Acl> aclList) {
        this.aclList = aclList;
        this.status = "success";
    }

    public List<Acl> getAclList() {
        return aclList;
    }

    public void setAclList(List<Acl> aclList) {
        this.aclList = aclList;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "ServiceResponse [aclList=" + aclList + ", status=" + status + "]";
    }


}
