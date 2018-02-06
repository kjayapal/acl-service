package com.ftd.acl.bl.impl;

import java.util.HashMap;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import com.codahale.metrics.annotation.ExceptionMetered;
import com.codahale.metrics.annotation.Timed;
import com.ftd.acl.api.response.ServiceResponse;
import com.ftd.acl.api.response.ServiceResponse2;
import com.ftd.acl.api.response.ServiceResponse3;
import com.ftd.acl.bl.MyService;
import com.ftd.acl.config.MyConfigurationProperties;
import com.ftd.acl.data.Acl;
import com.ftd.acl.data.AclData;
import com.ftd.acl.data.AclsRepository;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.services.micro.commons.logging.annotation.LogExecutionTime;

@Service(value = "MyService")
@EnableConfigurationProperties(MyConfigurationProperties.class)
public class MyServiceImpl implements MyService {

    private static final Logger LOGGER = LoggerFactory.getLogger(MyServiceImpl.class);

    @Autowired
    private AclsRepository aclsRepo;

    @Override
    @Timed
    @ExceptionMetered
    @HystrixCommand(groupKey = "hystrixGroup", commandKey = "helloCommandKey",
                                               threadPoolKey = "helloThreadPoolKey",
                                               fallbackMethod = "fallbackHello")
    @Cacheable(cacheNames = "default")
    @LogExecutionTime
    public ServiceResponse getResponse() {
        LOGGER.info("getResponse called ");
        List<Acl> aclList = aclsRepo.findAll();
        return new ServiceResponse(aclList);
    }

    @Override
    @Timed
    @ExceptionMetered
    @HystrixCommand(groupKey = "hystrixGroup", commandKey = "helloCommandKey",
                                               threadPoolKey = "helloThreadPoolKey",
                                               fallbackMethod = "fallbackHello")
    @Cacheable(cacheNames = "default")
    @LogExecutionTime
    public ServiceResponse getResponse(String id) {
        LOGGER.info("getResponse called ");
        Acl acl = aclsRepo.findById(id);
        return new ServiceResponse(acl);
    }

    @Override
    @Timed
    @ExceptionMetered
    @HystrixCommand(groupKey = "hystrixGroup", commandKey = "helloCommandKey",
                                               threadPoolKey = "helloThreadPoolKey",
                                               fallbackMethod = "fallbackHello")
    @Cacheable(cacheNames = "default")
    @LogExecutionTime
    public ServiceResponse getResponse(String siteId, String formName, String role) {
        LOGGER.info("Getting results for all given input...");
        System.out.println("Getting results for all given input...");
        ServiceResponse serviceResponse;

        List<Acl> aclList = aclsRepo.findBySiteIdAndFormNameAndRoleAllIgnoreCase(siteId, formName, role);

        if (aclList == null) {
            serviceResponse = new ServiceResponse();
        } else {
            serviceResponse = new ServiceResponse(aclList);
        }
        return serviceResponse;
    }

    @Override
    @Timed
    @ExceptionMetered
    @HystrixCommand(groupKey = "hystrixGroup", commandKey = "helloCommandKey",
                                               threadPoolKey = "helloThreadPoolKey",
                                               fallbackMethod = "fallbackHello")
    @Cacheable(cacheNames = "default")
    @LogExecutionTime
    public ServiceResponse getResponse(String siteId, String formName, String attrName, String role) {
        LOGGER.info("Getting results for all given input...");
        System.out.println("Getting results for all given input...");
        ServiceResponse serviceResponse;

        Acl acl = aclsRepo.findBySiteIdAndFormNameAndAttrNameAndRoleAllIgnoreCase(siteId, formName, attrName, role);

        if (acl == null) {
            serviceResponse = new ServiceResponse();
        } else {
            serviceResponse = new ServiceResponse(acl);
        }
        return serviceResponse;
    }

    public ServiceResponse fallbackHello() {
        ServiceResponse serviceResponse = new ServiceResponse();
        serviceResponse.setStatus("error (This is Hello from fallback)");
        return serviceResponse;
    }

    public ServiceResponse fallbackHello(String siteId, String formName, String role) {
        ServiceResponse serviceResponse = new ServiceResponse();
        serviceResponse.setStatus("error (This is Hello from fallback)");
        return serviceResponse;
    }

    public ServiceResponse fallbackHello(String siteId, String formName, String attrName, String role) {
        ServiceResponse serviceResponse = new ServiceResponse();
        serviceResponse.setStatus("error (This is Hello from fallback)");
        return serviceResponse;
    }

    public ServiceResponse fallbackHello(String id) {
        ServiceResponse serviceResponse = new ServiceResponse();
        serviceResponse.setStatus("error (This is Hello from fallback)");
        return serviceResponse;
    }

    @Override
    @Timed
    @ExceptionMetered
    @HystrixCommand(groupKey = "hystrixGroup", commandKey = "helloCommandKey", threadPoolKey = "helloThreadPoolKey",
                                                fallbackMethod = "fallbackHello2")
    @Cacheable(cacheNames = "default")
    @LogExecutionTime
    public ServiceResponse2 getResponse2(String siteId, String formName, String role) {
        LOGGER.info("Getting results for all given input...");
        System.out.println("Getting results for all given input...");
        ServiceResponse2 serviceResponse2;

        List<AclData> aclDataList = aclsRepo.findAttrNameAndActionTypeBySiteIdAndFormNameAndRoleAllIgnoreCase(siteId,
                formName, role);

        if (aclDataList == null) {
            serviceResponse2 = new ServiceResponse2();
        } else {
            serviceResponse2 = new ServiceResponse2(aclDataList);
        }
        return serviceResponse2;
    }

    public ServiceResponse2 fallbackHello2(String siteId, String formName, String role) {
        ServiceResponse2 serviceResponse2 = new ServiceResponse2();
        serviceResponse2.setStatus("error (This is Hello from fallback)");
        return serviceResponse2;
    }

    @Override
    @Timed
    @ExceptionMetered
    @HystrixCommand(groupKey = "hystrixGroup", commandKey = "helloCommandKey",
//                                               fallbackMethod = "fallbackHello3",
                                               threadPoolKey = "helloThreadPoolKey"
                                                )
    @Cacheable(cacheNames = "default")
    @LogExecutionTime
    public ServiceResponse3 getResponse3(String siteId, String formName, String role) {
        LOGGER.info("Getting results for all given input...");
        System.out.println("Getting results for all given input...");
        ServiceResponse3 serviceResponse3;

        List<AclData> aclDataList = aclsRepo.findAttrNameAndActionTypeBySiteIdAndFormNameAndRoleAllIgnoreCase(siteId,
                formName, role);

        HashMap<String, String[]> hashmap = new HashMap<String, String[]>();

        for (AclData aclData : aclDataList) {
            hashmap.put(aclData.getAttrName(), aclData.getActionType());
        }
        if (aclDataList == null) {
            serviceResponse3 = new ServiceResponse3();
        } else {
            serviceResponse3 = new ServiceResponse3(hashmap);
        }
        return serviceResponse3;
    }

    public ServiceResponse3 fallbackHello3(String siteId, String formName, String role) {
        ServiceResponse3 serviceResponse3 = new ServiceResponse3();
        serviceResponse3.setStatus("error (This is Hello from fallback)");
        return serviceResponse3;
    }
}
