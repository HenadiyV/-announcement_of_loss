package adver.example.adver.controllers;

import adver.example.adver.models.Adver;
import adver.example.adver.models.Category;
import adver.example.adver.models.City;
import adver.example.adver.models.Status;
import adver.example.adver.repos.AdverRepository;
import adver.example.adver.repos.CategoryRepository;
import adver.example.adver.repos.CityRepository;
import adver.example.adver.repos.StatusRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

/*
*@autor Hennadiy Voroboiv 
01.06.2019
9:10
*/
@Controller
@RequestMapping("/advers")
public class AdverController {
    @Autowired
    private AdverRepository adverRepository;
    @Autowired
    private CategoryRepository categoryRepository;
    @Autowired
    private CityRepository cityRepository;
    @Autowired
    private StatusRepository statusRepository;

    @GetMapping
    public String MyAdver() {
        return "advers";
    }

    @GetMapping("/add")
    public String MyAddAdver(Map<String, Object> model) {
        Iterable<Category> categoryList = categoryRepository.findAll();
        model.put("categoryList", categoryList);
        Category category = new Category();
        model.put("category1", category);

//      Iterable<City>cityList=cityRepository.findAll();
//      model.put("cityList",cityList);
//      City city= new City();
//        model.put("city",city);


//Iterable<Status>statusList=statusRepository.findAll();
//model.addAttribute("statusList",statusList);
        return "add_advers";
    }

    @PostMapping("/add_advers")
    public @ResponseBody
    String AddNewAdver(@RequestParam String textAdver, @RequestParam Category category,
                       @RequestParam String photo) {
        return "Saved adver" + textAdver + "-" + category.getName() + photo;
    }

    @GetMapping("/hound")
    public String MyHound(Map<String, Object> model) {

        Iterable<Adver> adverList = adverRepository.findByStatusName("Нашел");

        model.put("listAdver", adverList);
        return "hound";
    }

    @GetMapping("/lost")
    public String MyLost(Map<String, Object> model) {
        Iterable<Adver> adverList = adverRepository.findByStatusName("Потерял");
        model.put("listAdver", adverList);
        return "lost";
    }

}
