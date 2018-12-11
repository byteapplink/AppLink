package com.dexfun.recommend.controller;

import com.dexfun.recommend.service.AppService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;


@Controller
public class IndexController {

    @Autowired
    AppService appService;

    @RequestMapping("/{userId}")
    public Object apps(Model model, @PathVariable(required = false) Long userId) {
        model.addAttribute("apps", appService.findAllByUserId(userId));
        return "index.html";
    }
    @RequestMapping("/")
    public Object appsIndex(Model model) {
        model.addAttribute("apps", appService.findAll());
        return "index.html";
    }
}
