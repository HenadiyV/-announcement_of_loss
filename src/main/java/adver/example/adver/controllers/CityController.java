package adver.example.adver.controllers;


import adver.example.adver.models.City;
import adver.example.adver.repos.CityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/admin_city/city")
public class CityController {
//    final CityRepository cityRepo;
//
//    @Autowired
//    public CityController(CityRepository cityRepo) {
//        this.cityRepo = cityRepo;
//    }
//
//    @GetMapping
//    public List<City> list(){
//        return (List<City>) cityRepo.findAll();
//    }
//
//    @GetMapping("{id}")
//    public City getOne(@PathVariable("id") City city) {
//        return city;
//    }
//
//    @PostMapping
//    public City create(@RequestBody City city) { ;
//        return cityRepo.save(city);
//
//    }
//
//
//    @PutMapping("{id}")
//    public City update(@RequestBody City newCity, @PathVariable int id) {
//
//        return cityRepo.findById(id)
//                .map(city -> {
//                    city.setName(newCity.getName());
//                    return cityRepo.save(city);
//                }).get();
//
//    }
//
//    @DeleteMapping("{id}")
//    public void delete(@PathVariable("id") City city) {
//        cityRepo.delete(city);
//    }
//    //добавил коммит
}