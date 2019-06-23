package adver.example.adver.controllers;

import adver.example.adver.models.Adver;
import adver.example.adver.models.User;
import adver.example.adver.repos.AdverRepository;
import adver.example.adver.repos.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Map;

/*
*@autor Hennadiy Voroboiv 
01.06.2019
*/
@Controller

public class IndexController {


    @GetMapping("/")
    public String MyIndex()
    {
        return "index";
    }

}
