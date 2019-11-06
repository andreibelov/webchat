package net.bootlab.webchat.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import static net.bootlab.webchat.configs.Endpoints.API_STUFF;

@Controller
public class StuffController {

    @RequestMapping(API_STUFF)
//    @ResponseBody
    public String greetings() {
        return "favicon.txt";
    }
}
