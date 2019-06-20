package adver.example.adver.controllers;


import adver.example.adver.DTO.CityDto;
import adver.example.adver.models.City;
import adver.example.adver.repos.CityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/admin_city/city")
public class CityController {
    final CityRepository cityRepo;

    @Autowired
    public CityController(CityRepository cityRepo) {
        this.cityRepo = cityRepo;
    }

    @GetMapping
    public List<City> getListCity(){
        CityDto ST=new CityDto((List<City>)cityRepo.findAll());
        return ST.getListCitys();
    }

    @GetMapping("{id}")
    public City getOne(@PathVariable("id") City city) {
        return city;
    }

    @PostMapping
    public City createCity(@RequestBody City city) { ;
        return cityRepo.save(city);

    }


    @PutMapping("{id}")
    public City updateCity(@RequestBody City newCity, @PathVariable int id) {

        return cityRepo.findById(id)
                .map(city -> {
                    city.setName(newCity.getName());
                    return cityRepo.save(city);
                }).get();

    }

    @DeleteMapping("{id}")
    public void deleteCity(@PathVariable("id") City city) {
        cityRepo.delete(city);
    }
}