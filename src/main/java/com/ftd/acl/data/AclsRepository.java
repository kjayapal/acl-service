package com.ftd.acl.data;

import java.util.List;
import org.springframework.data.mongodb.repository.MongoRepository;


public interface AclsRepository extends MongoRepository<Acl, String> {

    Acl findById(String id);
    Acl findBySiteId(String siteId);
    Acl findBySiteIdAndFormNameAndAttrNameAndRoleAllIgnoreCase(
                  String siteId, String formName, String attrName, String role);
    List<Acl> findBySiteIdAndFormNameAndRoleAllIgnoreCase(
            String siteId, String formName, String role);

    List<AclData> findAttrNameAndActionTypeBySiteIdAndFormNameAndRoleAllIgnoreCase(
            String siteId, String formName, String role);
    // public List<Product> findByPrice(float price);

}
