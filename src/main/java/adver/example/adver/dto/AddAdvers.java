package adver.example.adver.dto;

import adver.example.adver.models.Category;
import adver.example.adver.models.City;
import adver.example.adver.models.Status;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

/*
 *@autor Hennadiy Voroboiv
 *@email henadiyv@gmail.com
 *09.06.2019
 */
public class AddAdvers {
    @NotNull(message="Виберіть місто")
    private City city ;
    @NotNull(message="Виберіть категорію")
    private Category category ;
    @NotNull( message="Виберіть статус")
    private Status status ;
    @NotEmpty(message="Введіть текст")
    private String textAdver;

    public AddAdvers() {
    }

    public City getCity() {
        return city;
    }

    public void setCity(City city) {
        this.city = city;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public String getTextAdver() {
        return textAdver;
    }

    public void setTextAdver(String textAdver) {
        this.textAdver = textAdver;
    }
}
