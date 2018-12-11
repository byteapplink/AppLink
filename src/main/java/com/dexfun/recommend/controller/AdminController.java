package com.dexfun.recommend.controller;

import java.math.BigDecimal;

import com.dexfun.recommend.entity.AppEntity;
import com.dexfun.recommend.entity.UserEntity;
import com.dexfun.recommend.service.AppService;
import com.dexfun.recommend.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@Controller
public class AdminController {

    @Autowired
    UserService userService;

    @Autowired
    AppService appService;

    @RequestMapping("/admin")
    public Object adminHtml(Model model, HttpServletRequest request) {
        HttpSession session = request.getSession(false);
        if (session == null) {
            return new ModelAndView("redirect:/admin/login.html");
        }
        Object userId = session.getAttribute("userId");

        if (StringUtils.isEmpty(userId)) {
            return new ModelAndView("redirect:/admin/login.html");
        }
        model.addAttribute("user", userService.findByUserId(Long.valueOf(userId.toString())));
        model.addAttribute("apps", appService.findAllByUserId(Long.valueOf(userId.toString())));
        return "admin/index.html";
    }

    @RequestMapping("/admin/login.html")
    public Object loginHtml(Model model, HttpServletRequest request, String username, String password) {
        if (!StringUtils.isEmpty(username) && !StringUtils.isEmpty(password)) {
            HttpSession session = request.getSession(true);
            System.out.println(username + password);
            UserEntity user = userService.findByUsernameEqualsAndPasswordEquals(username, password);
            if (user != null) {
                session.setAttribute("userId", user.getUserId());
                return new ModelAndView("redirect:/admin");
            } else {
                model.addAttribute("message", "账号或密码错误");
            }

        }
        return model;
    }

    @RequestMapping("/admin/register")
    public Object registerHtml(Model model, HttpServletRequest request, String username, String nickname, String password, String password2) {
        if (!StringUtils.isEmpty(username) && !StringUtils.isEmpty(nickname) && !StringUtils.isEmpty(password) && !StringUtils.isEmpty(password2)) {
            if (password.equals(password2)) {
                Boolean aBoolean = userService.register(username, nickname, password);
                if (aBoolean) {
                    return loginHtml(model, request, username, password);
                } else {
                    model.addAttribute("message", "用户已存在");
                }
            } else {
                model.addAttribute("message", "两次输入密码不一致");
            }
        }

        return model;
    }

    @RequestMapping("/admin/editor/{appId}")
    public Object editorHtml(Model model, HttpServletRequest request, @PathVariable("appId") Long appId,
                             String title,
                             String description,
                             String size,
                             String version,
                             String icon,
                             String download) {
        HttpSession session = request.getSession(false);
        if (session == null) {
            return new ModelAndView("redirect:/admin/login.html");
        }
        Object userId = session.getAttribute("userId");
        if (StringUtils.isEmpty(userId)) {
            return new ModelAndView("redirect:/admin/login.html");
        }
        if (StringUtils.isEmpty(title)) {
            model.addAttribute("app", appService.findAllByUserIdAndAppId(Long.valueOf(userId.toString()), appId));
        } else {
            AppEntity allByUserIdAndAppId = appService.findAllByUserIdAndAppId(Long.valueOf(userId.toString()), appId);
            if (allByUserIdAndAppId == null) {
                return "黑人问号";
            }
            allByUserIdAndAppId.setTitle(title);
            allByUserIdAndAppId.setDescription(description);
            allByUserIdAndAppId.setSize(new BigDecimal(size));
            allByUserIdAndAppId.setVersion(version);
            allByUserIdAndAppId.setIcon(icon);
            allByUserIdAndAppId.setDownload(download);
            allByUserIdAndAppId.setStatus(0);
            appService.save(allByUserIdAndAppId);
            model.addAttribute("app", allByUserIdAndAppId);
        }
        return "admin/editor.html";
    }

    @RequestMapping(value = "/admin/add.html")
    public Object addHtml(HttpServletRequest request) {
        HttpSession session = request.getSession(false);
        if (session == null) {
            return new ModelAndView("redirect:/admin/login.html");
        }
        Object userId = session.getAttribute("userId");
        if (StringUtils.isEmpty(userId)) {
            return new ModelAndView("redirect:/admin/login.html");
        }
        return "admin/add.html";
    }

    @RequestMapping(value = "/admin/add")
    public Object add(Model model,
                      HttpServletRequest request,
                      String title,
                      String description,
                      String size,
                      String version,
                      String icon,
                      String download) {
        HttpSession session = request.getSession(false);
        if (session == null) {
            return new ModelAndView("redirect:/admin/login.html");
        }
        Object userId = session.getAttribute("userId");
        if (StringUtils.isEmpty(userId)) {
            return new ModelAndView("redirect:/admin/login.html");
        }
        AppEntity appEntity = new AppEntity();
        appEntity.setUserId(Long.valueOf(userId.toString()));
        appEntity.setTitle(title);
        appEntity.setDescription(description);
        appEntity.setSize(new BigDecimal(size));
        appEntity.setVersion(version);
        appEntity.setIcon(icon);
        appEntity.setDownload(download);
        appEntity.setStatus(0);
        appService.save(appEntity);
        model.addAttribute("app", appEntity);

        return new ModelAndView("redirect:/admin");
    }

    @RequestMapping("/admin/delete/{appId}")
    public Object delete(Model model, HttpServletRequest request, @PathVariable("appId") Long appId) {
        HttpSession session = request.getSession(false);
        if (session == null) {
            return new ModelAndView("redirect:/admin/login.html");
        }
        Object userId = session.getAttribute("userId");
        if (StringUtils.isEmpty(userId)) {
            return new ModelAndView("redirect:/admin/login.html");
        }
        appService.delete(Long.valueOf(userId.toString()), appId);
        return new ModelAndView("redirect:/admin");
    }
}
