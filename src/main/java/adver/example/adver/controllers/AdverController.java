package adver.example.adver.controllers;

import adver.example.adver.models.*;
import adver.example.adver.repos.*;
import adver.example.adver.dto.CityCategoryDTO;
import adver.example.adver.dto.MessageUserDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.data.domain.Pageable;

import javax.validation.Valid;

import java.io.File;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

/*
*@autor Hennadiy Voroboiv
01.06.2019
9:10
*/
@Controller
@RequestMapping("/advers")
public class AdverController {

    @Value("${upload.path}")
    private String uploadPath;
    @Autowired
    private AdverRepository adverRepository;
    @Autowired
    private CategoryRepository categoryRepository;
    @Autowired
    private CityRepository cityRepository;
    @Autowired
    private StatusRepository statusRepository;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private MessageRepository messageRepository;

    @GetMapping
    public String myAdver() {

        return "advers";
    }

    @GetMapping("/add_advers")
    public String myAddAdver(AddAdvers adver, @RequestParam(required = false, value = "stat") Integer Id, Map<String, Object> model) {

        Iterable<Category> categoryList = categoryRepository.findAll();
        model.put("categoryList", categoryList);

        Iterable<City> cityList = cityRepository.findAll();
        model.put("cityList", cityList);


        Iterable<Status> statusList = statusRepository.findAll();
        model.put("statusList", statusList);
        return "add_advers";
    }

    @PostMapping("/add_advers")
    public String addNewAdver(@AuthenticationPrincipal User user,
                              @Valid AddAdvers adver, BindingResult bindingResult, Model model,
                              @RequestParam(required = false, value = "dataStop") @DateTimeFormat(pattern = "yyyy-MM-dd") Date dataStop,
                              @RequestParam(required = false, value = "file") MultipartFile file) throws ParseException, IOException {
        boolean result = true;
        if (bindingResult.hasErrors()) {
            Map<String, String> errorMap = UtilsController.getErrors(bindingResult);
            model.mergeAttributes(errorMap);


            if (adver.getCity() == null) {
                model.addAttribute("cityEr", "Не вибранно місто");

            }
            if (adver.getCategory() == null) {
                model.addAttribute("categoryEr", "Не вибранно категорию.");

            }
            if (adver.getStatus() == null) {
                model.addAttribute("statusEr", "Не вказано статус.");

            }
            if (dataStop == null) {
                model.addAttribute("dataSotpEr", "Не вказано дату завершенн дії оголошеня .");

            }
            if (adver.getTextAdver().isEmpty()) {
                model.addAttribute("textAdverEr", "Не вказано текст оголошеня .");

            }
            Iterable<Category> categoryList = categoryRepository.findAll();
            model.addAttribute("categoryList", categoryList);

            Iterable<City> cityList = cityRepository.findAll();
            model.addAttribute("cityList", cityList);


            Iterable<Status> statusList = statusRepository.findAll();
            model.addAttribute("statusList", statusList);
            result = false;
            return "add_advers";
        }
        SimpleDateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy");
        Date startDate = dateFormat.parse(dateFormat.format(new Date()));
        if (dataStop == null || startDate.after(dataStop)) {
            model.addAttribute("dataSotpEr", "Вкажіть коректну дату");
            Iterable<Category> categoryList = categoryRepository.findAll();
            model.addAttribute("categoryList", categoryList);

            Iterable<City> cityList = cityRepository.findAll();
            model.addAttribute("cityList", cityList);
            Iterable<Status> statusList = statusRepository.findAll();
            model.addAttribute("statusList", statusList);
            result = false;
            return "add_advers";
        }


        String photo = "";
        if (file != null && !file.getOriginalFilename().isEmpty()) {
            File uploadDir = new File(uploadPath);
            if (!uploadDir.exists()) {
                uploadDir.mkdir();
            }

            String uuid = UUID.randomUUID().toString();
            String fileName = file.getOriginalFilename();
            int startIndex = fileName.replaceAll("\\\\", "/").lastIndexOf("/");
            fileName = fileName.substring(startIndex + 1);
            photo = uuid + fileName;


            file.transferTo(new File(uploadPath + "/" + photo));
        }else photo="no-photo.png";

        if (result) {
            Adver adv = new Adver(adver.getTextAdver(), photo, startDate, dataStop, adver.getStatus(), adver.getCategory(), user, adver.getCity());
            adverRepository.save(adv);

        }
        SimpleDateFormat dateFormat1 = null;
        dateFormat1 = new SimpleDateFormat("dd MMMM YYYY");
        String dts = dateFormat1.format(startDate);
        String stp = dateFormat1.format(dataStop);
        model.addAttribute("dataSart", dts);
        model.addAttribute("user", user);
        model.addAttribute("adver", adver);
        model.addAttribute("dataStop", stp);
        model.addAttribute("photo", photo);
        model.addAttribute("photoUp", uploadPath + photo);

        return "advers";
    }


    @GetMapping("/found")
    public String myFound(@AuthenticationPrincipal User user, Map<String, Object> model, @PageableDefault(sort = {"id"}, direction = Sort.Direction.DESC) Pageable pageable) {
        Iterable<Category> categoryList = categoryRepository.findAll();
        model.put("categoryList", categoryList);

        Iterable<City> cityList = cityRepository.findAll();
        model.put("cityList", cityList);

        Page<Adver> adverList = adverRepository.findByStatus_Id(2, pageable);
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if (user != null) {
            if (principal instanceof User) {
                Role role = ((User) principal).getRole();
                if (role.getName().equals("admin")) {
                    model.put("us", 2);
                }

            } else
                model.put("us", 1);
        } else {
            model.put("us", 0);
        }
        model.put("url", "/advers/found");
        model.put("listAdver", adverList);

        model.put("num", adverList.getTotalElements());
        // adverList.getTotalPages()
        return "found";
    }

    @GetMapping("/found_city")
    public String filtrFoundByCity(@AuthenticationPrincipal User user, @RequestParam(required = false, value = "testCity") City city,
                                   Map<String, Object> model,
                                   @PageableDefault(sort = {"id"}, direction = Sort.Direction.DESC) Pageable pageable
    ) {
        Iterable<Category> categoryList = categoryRepository.findAll();
        model.put("categoryList", categoryList);

        Iterable<City> cityList = cityRepository.findAll();
        model.put("cityList", cityList);

//        Page<Adver> page=adverRepository.findByAll(pageable);
        Page<Adver> adverList = adverRepository.findByCity_IdAndStatus_Id(city.getId(), 2, pageable);
        model.put("listAdver", adverList);
        model.put("url", "found_city");
        if (user != null) {
            model.put("us", 1);
        } else {
            model.put("us", 0);
        }
        return "found";
    }

    @GetMapping("/found_category")
    public String filterFoundByCategory(@AuthenticationPrincipal User user, @RequestParam(required = false, value = "testCategory") Category category,
                                        Map<String, Object> model,
                                        @PageableDefault(sort = {"id"}, direction = Sort.Direction.DESC) Pageable pageable
    ) {
        Iterable<Category> categoryList = categoryRepository.findAll();
        model.put("categoryList", categoryList);

        Iterable<City> cityList = cityRepository.findAll();
        model.put("cityList", cityList);

        Page<Adver> adverList = adverRepository.findByCategory_IdAndStatus_Id(category.getId(), 2, pageable);
        // adverList.
        model.put("listAdver", adverList);
        model.put("url", "found_category");
        if (user != null) {
            model.put("us", 1);
        } else {
            model.put("us", 0);
        }
        return "found";
    }

    @GetMapping("/lost")
    public String myLost(@AuthenticationPrincipal User user, CityCategoryDTO cityCategoryDTO, Map<String, Object> model,
                         @PageableDefault(sort = {"id"}, direction = Sort.Direction.DESC) Pageable pageable) {

        Iterable<Category> categoryList = categoryRepository.findAll();
        model.put("categoryList", categoryList);

        Iterable<City> cityList = cityRepository.findAll();
        model.put("cityList", cityList);

        Page<Adver> adverList = adverRepository.findByStatus_Id(1, pageable);
        model.put("url", "/advers/lost");
        model.put("listAdver", adverList);
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if (user != null) {
            if (principal instanceof User) {
                Role role = ((User) principal).getRole();
                if (role.getName().equals("admin")) {
                    model.put("us", 2);
                }

            } else
                model.put("us", 1);
        } else {
            model.put("us", 0);
        }
        return "lost";
    }

    @GetMapping("/lost_city")
    public String filtrLostByCity(@AuthenticationPrincipal User user, @RequestParam(required = false, value = "testCity") City city,
                                  Map<String, Object> model,
                                  @PageableDefault(sort = {"id"}, direction = Sort.Direction.DESC) Pageable pageable) {
        Iterable<Category> categoryList = categoryRepository.findAll();
        model.put("categoryList", categoryList);

        Iterable<City> cityList = cityRepository.findAll();
        model.put("cityList", cityList);
//        model.put("res", city.getName());
        Page<Adver> adverList = adverRepository.findByCity_IdAndStatus_Id(city.getId(), 1, pageable);

        model.put("listAdver", adverList);
        if (user != null) {
            model.put("us", 1);
        } else {
            model.put("us", 0);
        }
        return "lost";
    }

    @GetMapping("/lost_category")
    public String filtrLostByCategory(@AuthenticationPrincipal User user, @RequestParam(required = false, value = "testCategory") Category category,
                                      Map<String, Object> model,
                                      @PageableDefault(sort = {"id"}, direction = Sort.Direction.DESC) Pageable pageable) {

        Iterable<Category> categoryList = categoryRepository.findAll();
        model.put("categoryList", categoryList);

        Iterable<City> cityList = cityRepository.findAll();
        model.put("cityList", cityList);
        Page<Adver> adverList = adverRepository.findByCategory_IdAndStatus_Id(category.getId(), 1, pageable);

        model.put("listAdver", adverList);
        if (user != null) {
            model.put("us", 1);
        } else {
            model.put("us", 0);
        }
        return "lost";
    }

    @GetMapping("/message")
    public String UserMessage(MessageUserDTO mess) {
        //  @AuthenticationPrincipal User user,Model modelmodel.addAttribute("us",user.getId());
        return "message";
    }

    @PostMapping("/messageUser")
    public String ReturnUserId(@RequestParam("user") int id, Model model) {
//mess.setIdUser(id);,MessageUserDTO mess
        model.addAttribute("userId", id);

        return "message";
    }

    //,@Valid MessageUserDTO mess,@ResponseBody@RequestParam("user") int userId,Model model,@RequestParam("usId") User usId ,@RequestParam("textMessage") String str,
    @PostMapping("/message")
    public String UserMessagePost(@AuthenticationPrincipal User user,
                                  @Valid MessageUserDTO mess, BindingResult bindingResult,

                                  Model model) {
        if (bindingResult.hasErrors()) {
            Map<String, String> errorMap = UtilsController.getErrors(bindingResult);
            model.mergeAttributes(errorMap);
            model.addAttribute("userId", mess.getIdUser());
            model.addAttribute("textMessEr", "Поле повідомленя не може бути пустим.");
            return "message";
        } else {
            User userTo = userRepository.findById(mess.getIdUser());
            Message message = new Message(mess.getTextMessage(), true, user, userTo);
            messageRepository.save(message);
            model.addAttribute("userName", userTo.getName());
        }


        model.addAttribute("userStr", mess.getTextMessage());
        return "temp";
    }

    @PostMapping("messageDelete")
    public String DeleteMessage(@RequestParam("url") String url, @RequestParam("messDel") Integer id,Model model) {

String str=url.substring(url.indexOf("/")+1);
        model.addAttribute("id",id);
        model.addAttribute("url",url);
        model.addAttribute("str",str);
        return "redirect:"+url;
    }
}
