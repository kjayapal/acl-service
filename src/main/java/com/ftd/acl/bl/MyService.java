package com.ftd.acl.bl;

import com.ftd.acl.api.response.ServiceResponse;
import com.ftd.acl.api.response.ServiceResponse2;
import com.ftd.acl.api.response.ServiceResponse3;

public interface MyService {
    ServiceResponse getResponse();
    ServiceResponse getResponse(String siteId);
    ServiceResponse getResponse(String siteId, String formName, String role);
    ServiceResponse2 getResponse2(String siteId, String formName, String role);
    ServiceResponse3 getResponse3(String siteId, String formName, String role);
    ServiceResponse getResponse(String siteId, String formName, String attrName, String role);
}
