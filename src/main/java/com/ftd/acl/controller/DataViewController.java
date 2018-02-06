package com.ftd.acl.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.ftd.acl.data.Acl;
import com.ftd.acl.data.AclsRepository;


@Controller
public class DataViewController {

//    @Autowired
//    private Acls acls;

    @Autowired
    private AclsRepository aclsRepo;

    @GetMapping("/data")
    public String getData(Model model) {
        List<Acl> aclsList = aclsRepo.findAll();
        model.addAttribute("aclsList", aclsList);
        return "data";
    }

//    @RequestMapping(value = "/search", method = RequestMethod.GET)
    public String search(Model model) {
        List<Acl> aclsList = aclsRepo.findAll();
        model.addAttribute("aclsList", aclsList);
        return "data";
    }

}