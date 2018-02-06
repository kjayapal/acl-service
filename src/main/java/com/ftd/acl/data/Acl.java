package com.ftd.acl.data;

import java.io.Serializable;
import java.util.Arrays;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import io.swagger.annotations.ApiParam;

@Document(collection = "ACLS")
public class Acl implements Serializable {

    private static final long serialVersionUID = -5037544901973298544L;

    @Id
    private String id;
    @ApiParam ("Site ID")
    private String siteId;
    private String formName;
    private String attrName;
    private String role;
    private String[] actionType;

    public Acl() {

    }

    public Acl(String id, String siteId, String formName, String role) {
        this.id = id;
        this.siteId = siteId;
        this.formName = formName;
        this.role = role;
        this.attrName = null;
        this.actionType = null;
    }

    public Acl(String id, String siteId, String formName, String attrName, String role, String[] actionType) {
        this.id = id;
        this.siteId = siteId;
        this.formName = formName;
        this.attrName = attrName;
        this.role = role;
        this.actionType = actionType;
    }


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

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

    public String getAttrName() {
        return attrName;
    }

    public void setAttrName(String attrName) {
        this.attrName = attrName;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String[] getActionType() {
        return actionType;
    }

    public void setActionType(String[] actionType) {
        this.actionType = actionType;
    }

    @Override
    public String toString() {
        return "Acl [id=" + id + ", siteId=" + siteId + ", formName=" + formName + ", attrName=" + attrName + ", role="
                + role + ", actionType=" + Arrays.toString(actionType) + "]";
    }

    public String toJSON() {
        return "{ \"siteId\":\"" + siteId + "\", \"formName\": \"" + formName + "\", "
                + "\"attrName\": \"" + attrName
                + "\", \"role=\": \"" + role + "\", \"actionType\": \"" + actionType + "\"}";
    }

}
