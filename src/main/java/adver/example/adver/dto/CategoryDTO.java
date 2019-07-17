package adver.example.adver.dto;

import adver.example.adver.models.Category;


import java.util.ArrayList;
import java.util.List;

/*
 *@autor Hennadiy Voroboiv
 *@email henadiyv@gmail.com
 *06.07.2019
 */

public class CategoryDTO {
    private Category Sd;
    List<Category> categoryList=new ArrayList<Category>();

    public CategoryDTO() {
    }
    public CategoryDTO(List<Category> ls) {
        if (ls.size()<1)return;
        for (Category stat: ls
                ) {
            Category temp=new Category();
            temp.setId(stat.getId());
            temp.setName(stat.getName());
            categoryList.add(temp);
        }
    }

    public List<Category> getListCategorys() {
        return categoryList;
    }
}
