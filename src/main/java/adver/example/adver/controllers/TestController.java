package adver.example.adver.controllers;

import adver.example.adver.dto.CategoryDTO;
import adver.example.adver.models.Category;
import adver.example.adver.repos.CategoryRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

/*
 *@autor Hennadiy Voroboiv
 *@email henadiyv@gmail.com
 *04.07.2019
 */
@Controller
@RequestMapping("/test")
public class TestController {
//    @Value("${upload.path}")
//    private String uploadPath;

//    final CategoryRepository categoryRepository;
//
//    @Autowired
//    public TestController(CategoryRepository categoryRepository) {
//        this.categoryRepository = categoryRepository;
//    }

//    @Autowired
//    private AdverRepository adverRepository;
  //  @Autowired
  //  private CategoryRepository categoryRepository;
//    @Autowired
//    private CityRepository cityRepository;
//    @Autowired
//    private StatusRepository statusRepository;
//    @GetMapping
//    public String myTest() {
//
//        return "test";
//    }
    @RequestMapping("/category")
    public String myTest() {

        return "category";
    }
}
//public List<Category> getListCategory(Map<String ,Object> model) {
////        List<Category> сatList =categoryRepository.findAll();
////model.put("list",сatList);return сatList ;
//    CategoryDTO ST=new CategoryDTO((List<Category>)categoryRepository.findAll());
//    return ST.getListCategorys();
//
//}
//
//
//    @GetMapping("{id}")
//    public String getOne(@PathVariable("id") Category category,Map<String ,Object> model) {
//        model.put("list",category);
//        return "test" ;
//    }
//
//    @PostMapping
//    public Category createCategory(@RequestBody Category category) { ;
//        return categoryRepository.save(category);
//
//    }
//
//    @PutMapping("{id}")
//    public Category updateCategory(@PathVariable ("id") Category categoryFromDB,
//                                   @RequestBody Category category ) {
//        BeanUtils.copyProperties(category,categoryFromDB,"id");
//        return categoryRepository.save(category);
//    }
//
//    @DeleteMapping("{id}")
//    public void deleteAdver(@PathVariable("id") Category category) {
//        categoryRepository.delete(category);
//    }
//


//@GetMapping
//
//public String test(){
//    return "test";
//}

//    @GetMapping
//    public List<Adver> getListAdvers() {
//        AdverDTO adverList=new AdverDTO((List<Adver>) adverRepository.findAll());
//        return  adverList.getListAdver();
//    }
//    public String viewAll(Map<String, Object> model, @PageableDefault(sort = {"id"}, direction = Sort.Direction.DESC) Pageable pageable){
//        model.put("found",false);
//        model.put("lost",true);
//        model.put("index",false);
//        Page<Adver> adverList = adverRepository.findByStatus_Id(2, pageable);
//        model.put("listAdver", adverList);
//        return "test";
//    }

//@GetMapping("/foundView1")
//@ResponseBody
//public List<Adver> viewAllTest() {
//    List<Adver> adverList= adverRepository.findAll();
//    return  adverList;
//}

//@PostMapping("/view")
//public String viewList(@RequestParam boolean lost,@RequestParam boolean found){
//
//    return "view";
//}
//    @GetMapping("/foundView")
//    @ResponseBody
//    public Page<Adver> foundView(Map<String, Object> model,@PageableDefault(sort = {"id"}, direction = Sort.Direction.DESC) Pageable pageable){
//        Page<Adver> adverList = adverRepository.findByStatus_Id(2, pageable);
//        model.put("listAdver", adverList);
//        return adverList;
//    }
//    @GetMapping("/lostView")
//    @ResponseBody
//    public Page<Adver> lostView(Map<String, Object> model,@PageableDefault(sort = {"id"}, direction = Sort.Direction.DESC) Pageable pageable){
//        Page<Adver> adverList = adverRepository.findByStatus_Id(1, pageable);
//        model.put("listAdver", adverList);
//        return adverList;
//    }

//    @PutMapping("{id}")
////    public Adver updateAdver(@RequestBody Adver newAdver, @PathVariable int id) {
////
//////        return adverRepo.findById(id)
//////                .map(adver -> {
//////                    adver.setName(newAdver.getName());
//////                    return adverRepo.save(adver);
//////                }).get();
////
////    }
