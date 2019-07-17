package adver.example.adver.dto;

import adver.example.adver.models.City;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

/*
 *@autor Hennadiy Voroboiv
 *@email henadiyv@gmail.com
 *13.07.2019
 */
public class CityDTO {

    private int id;
    @NotEmpty(message="Виберіть місто")
    private String name;

    public CityDTO(City city) {
        this.city = city;
    }

    public City getCity() {
        return city;
    }

    public void setCity(City city) {
        this.city = city;
    }

    private City city;

    public CityDTO(@NotEmpty(message = "Виберіть місто") String name) {
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
