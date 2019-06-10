package adver.example.adver.view;

import javax.validation.constraints.NotNull;

/*
 *@autor Hennadiy Voroboiv
 *@email henadiyv@gmail.com
 *06.06.2019
 */
public class CityCategory {
    private int city_id;
    private int category_id;
    @NotNull
    private String city_name;
    @NotNull
    private String category_name;

    public int getCity_id() {
        return city_id;
    }

    public void setCity_id(int city_id) {
        this.city_id = city_id;
    }

    public int getCategory_id() {
        return category_id;
    }

    public void setCategory_id(int category_id) {
        this.category_id = category_id;
    }

    public String getCity_name() {
        return city_name;
    }

    public void setCity_name(String city_name) {
        this.city_name = city_name;
    }

    public String getCategory_name() {
        return category_name;
    }

    public void setCategory_name(String category_name) {
        this.category_name = category_name;
    }
}
