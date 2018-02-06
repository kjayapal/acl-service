package com.ftd.acl.api.request;

import javax.validation.constraints.NotNull;

import io.swagger.annotations.ApiModelProperty;

public class ServiceRequest {

    @NotNull(message = "{error.siteid.notnull}")
    @ApiModelProperty(notes = "Site ID", required = true, allowableValues = "FTD, PRO")
    private String siteId;

    @NotNull(message = "{error.formname.notnull}")
    @ApiModelProperty(notes = "Form name", required = true)
    private String formName;

    @NotNull(message = "{error.role.notnull}")
    @ApiModelProperty(notes = "User role", required = true)
    private String role;

    public String getSiteId() {
        return siteId;
    }
    public void setSiteId(String siteId) {
        this.siteId = siteId;
    }
    public String getFormName() {
        return formName;
    }
    public void setFormName(String formName) {
        this.formName = formName;
    }

    public String getRole() {
        return role;
    }
    public void setRole(String role) {
        this.role = role;
    }

    @Override
    public String toString() {
        return "ServiceRequest [siteId=" + siteId + ", formName=" + formName
//              + ", attrName=" + attrName
                + ", role=" + role + "]";
    }

}
