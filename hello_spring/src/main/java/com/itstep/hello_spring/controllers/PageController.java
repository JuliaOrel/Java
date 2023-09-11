package com.itstep.hello_spring.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.ArrayList;
import java.util.List;

@Controller
public class PageController {
    @GetMapping("/")
    public String index(){
        return ("pages/index");
    }

    @GetMapping("/thymeleaf")
    public String buildServer(Model model){
        List<String> lstData=new ArrayList<>();
        lstData.add("First element");
        lstData.add("Second element");
        lstData.add("Third element");

        model.addAttribute("lstData", lstData);

        model.addAttribute("title", "Hello Models");
        return ("example/thymeleaf");
    }
}
