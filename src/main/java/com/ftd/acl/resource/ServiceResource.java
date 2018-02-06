package com.ftd.acl.resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.ftd.acl.api.request.ServiceRequest;
import com.ftd.acl.api.response.ServiceResponse;
import com.ftd.acl.api.response.ServiceResponse2;
import com.ftd.acl.api.response.ServiceResponse3;
import com.ftd.acl.bl.MyService;
import com.ftd.acl.data.Acl;
import com.ftd.acl.data.AclsRepository;

import io.swagger.annotations.Api;

@RestController
@RefreshScope
@RequestMapping("/apis")
@Api(value = "ACLs Service API", description = "To Get ACLs for given role")
public class ServiceResource {

    private static final Logger LOGGER = LoggerFactory.getLogger(ServiceResource.class);

    @Autowired
    private MyService myService;

    @Autowired
    private AclsRepository aclsRepository;

    @PostMapping(value = "/v2/getacls", consumes = { MediaType.APPLICATION_JSON_VALUE }, produces = {
            MediaType.APPLICATION_JSON_VALUE })
    @Cacheable(cacheNames = "acls")
    public ResponseEntity<ServiceResponse2> getAclsV2(@RequestBody ServiceRequest request) {
        ServiceResponse2 serviceResponse2 = null;
        LOGGER.info("Called get acls method with all values.... ");
        if (request.getSiteId() != null && request.getFormName() != null && request.getRole() != null) {
            serviceResponse2 = myService.getResponse2(request.getSiteId(), request.getFormName(), request.getRole());
        } else {
            serviceResponse2 = new ServiceResponse2();
            serviceResponse2.setStatus("Invlid input data");
        }

        return new ResponseEntity<>(serviceResponse2, HttpStatus.OK);
    }

    @PostMapping(value = "/v3/getacls", consumes = { MediaType.APPLICATION_JSON_VALUE }, produces = {
            MediaType.APPLICATION_JSON_VALUE })
    @Cacheable(cacheNames = "acls")
    public ResponseEntity<ServiceResponse3> getAclsV3(@RequestBody ServiceRequest request) {
        ServiceResponse3 serviceResponse3 = new ServiceResponse3();
        LOGGER.info("Called get acls method with all values.... ");
        if (request.getSiteId() != null && request.getFormName() != null && request.getRole() != null) {
            serviceResponse3 = myService.getResponse3(request.getSiteId(), request.getFormName(), request.getRole());
        } else {
            serviceResponse3.setStatus("Invlid input data");
        }

        return new ResponseEntity<>(serviceResponse3, HttpStatus.OK);
    }

    @PostMapping(value = "/v1/getacls", consumes = { MediaType.APPLICATION_JSON_VALUE }, produces = {
            MediaType.APPLICATION_JSON_VALUE })
    @Cacheable(cacheNames = "aclsFullData")
    public ResponseEntity<ServiceResponse> getAclsV1(@RequestBody ServiceRequest request) {
        ServiceResponse serviceResponse = null;
        LOGGER.info("Called get acls method with all values.... ");
        System.out.println(request.toString());
        if (request.getSiteId() != null && request.getFormName() != null && request.getRole() != null) {
            serviceResponse = myService.getResponse(request.getSiteId(), request.getFormName(), request.getRole());
        } else {
            serviceResponse = new ServiceResponse();
            serviceResponse.setStatus("Invlid input data");
        }

        return new ResponseEntity<>(serviceResponse, HttpStatus.OK);
    }

    @PostMapping(value = "/addacls", consumes = { MediaType.APPLICATION_JSON_VALUE }, produces = {
            MediaType.APPLICATION_JSON_VALUE })
    @Cacheable(cacheNames = "addacls")
    public ResponseEntity<String> addAcls(@RequestBody Acl request) {
        String jsonResponse;
        LOGGER.info("Called add acls method with all values.... ");
        try {
            if (request.getSiteId() != null
                && request.getFormName() != null
                && request.getAttrName() != null
                && request.getRole() != null
                && request.getActionType() != null) {
                Acl acl = new Acl(request.getId(), request.getSiteId(),
                                     request.getFormName(),
                                     request.getAttrName(),
                                     request.getRole(),
                                     request.getActionType());
                aclsRepository.save(acl);
                jsonResponse = "{status: success}";
            } else {
                jsonResponse = "{status: error, error: Invlid input data " + request.getSiteId() + "-"
                        + request.getFormName() + "-" + request.getAttrName() + "-" + request.getRole() + "-"
                        + request.getActionType() + "}";
            }
        } catch (Exception e) {
            jsonResponse = "{status: error}";
            LOGGER.error("ERROR: " + e.getMessage());
        }

        return new ResponseEntity<>(jsonResponse, HttpStatus.OK);
    }


    public ResponseEntity<String> sendResponse(Object obj) {
        ObjectMapper mapper = new ObjectMapper();
        String jsonResponse;
        try {
            jsonResponse = mapper.writeValueAsString(obj);
            return new ResponseEntity<>(jsonResponse, HttpStatus.OK);
        } catch (JsonProcessingException e) {
            jsonResponse = "{status : \"Unexpected error\"}";
            return new ResponseEntity<>(jsonResponse, HttpStatus.BAD_REQUEST);
        }
    }

}
