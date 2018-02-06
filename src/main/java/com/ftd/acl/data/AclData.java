package com.ftd.acl.data;

import java.io.Serializable;
import java.util.Arrays;

public class AclData  implements Serializable {
    private static final long serialVersionUID = 5412107647335180549L;
    private String attrName;
    private String[] actionType;
    public String getAttrName() {
        return attrName;
    }
    public void setAttrName(String attrName) {
        this.attrName = attrName;
    }
    public String[] getActionType() {
        return actionType;
    }
    public void setActionType(String[] actionType) {
        this.actionType = actionType;
    }
    @Override
    public String toString() {
        return "AclData [attrName=" + attrName + ", actionType=" + Arrays.toString(actionType) + "]";
    }

}

