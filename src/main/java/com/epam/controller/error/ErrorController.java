package com.epam.controller.error;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by Sahak_Babayan on 3/30/2016.
 */
@Controller
public class ErrorController {

    @RequestMapping("/404")
    public String resourceNotFound(HttpServletRequest request, Model model) {
        String resourceName = getResourceName(request);
        model.addAttribute("requestedResource", resourceName);
        return "/error/404";
    }

    @RequestMapping("/403")
    public String accessDenied(HttpServletRequest request, Model model) {
        String resourceName = getResourceName(request);
        model.addAttribute("requestedResource", resourceName);
        return "/error/403";
    }

    @RequestMapping("/500")
    public String internalServerError(HttpServletRequest request, Model model) {
        return "/error/500";
    }

    private String getResourceName(HttpServletRequest request) {
        return (String) request.getAttribute("javax.servlet.error.request_uri");
    }
}
