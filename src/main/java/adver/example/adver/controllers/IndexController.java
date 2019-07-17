package adver.example.adver.controllers;

import adver.example.adver.models.Adver;
import adver.example.adver.models.User;
import adver.example.adver.repos.AdverRepository;
import adver.example.adver.repos.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Map;

/*
*@autor Hennadiy Voroboiv 
01.06.2019
*/
@Controller

public class IndexController {


    @GetMapping("/")
    public String MyIndex(Model model)
    {
        boolean index=true;
        model.addAttribute("index",index);
        return "index";
    }

//    @GetMapping("/Gologin")
//    public String Gologin()
//    {
//
//        return "Gologin";
//    }

}
