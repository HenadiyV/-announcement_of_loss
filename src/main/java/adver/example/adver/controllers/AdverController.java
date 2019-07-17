package adver.example.adver.controllers;

import adver.example.adver.dto.AddAdversDTO;
import adver.example.adver.dto.CityCategoryDTO;
import adver.example.adver.dto.MessageUserDTO;
import adver.example.adver.models.*;
import adver.example.adver.repos.*;
import adver.example.adver.service.UserService;
import com.liferay.portal.kernel.util.FileUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
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

import javax.imageio.ImageIO;
import javax.servlet.ServletException;
import javax.validation.Valid;
import java.awt.geom.AffineTransform;
import java.awt.image.AffineTransformOp;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.security.Principal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;
import java.util.UUID;


/*
*@autor Hennadiy Voroboiv
01.06.2019
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
//@Autowired
//private UserRepository userRepository;
    @GetMapping
    public String myAdver() {

        return "advers";
    }
@GetMapping("/add_advers_lost")
public boolean addAdversLost(boolean lost){
   return lost;
}
//@RequestParam(required = false, value = "stat") Integer Id @PathVariable("id") String id
    @GetMapping("/add_advers")
    public String myAddAdver(AddAdversDTO adver,@RequestParam("status") String stat , Map<String, Object> model) throws ParseException {

        Iterable<Category> categoryList = categoryRepository.findAll();
        model.put("categoryList", categoryList);

        Iterable<City> cityList = cityRepository.findAll();
        model.put("cityList", cityList);

 int statusId=Integer.parseInt(stat);
        Status status = statusRepository.findById(statusId);
        model.put("status", status);
//        SimpleDateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy");
//        Date startDate = dateFormat.parse(dateFormat.format(new Date()));
//        model.put("dat",startDate);
        return "add_advers";
    }

    @PostMapping("/add_advers")
    public String addNewAdver(@AuthenticationPrincipal User user,
                              @Valid AddAdversDTO adver, BindingResult bindingResult, Model model,
                              @RequestParam(required = false, value = "dataStop") @DateTimeFormat(pattern = "yyyy-MM-dd") Date dataStop,
                              @RequestParam("status") int stat ,
                              @RequestParam(required = false, value = "file")
                               MultipartFile file) throws ParseException, IOException {
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
                model.addAttribute("dataSotpEr", "Не вказано дату знахідки.");

            }
            if (adver.getTextAdver().isEmpty()) {
                model.addAttribute("textAdverEr", "Не вказано текст оголошеня.");

            }
            Iterable<Category> categoryList = categoryRepository.findAll();
            model.addAttribute("categoryList", categoryList);

            Iterable<City> cityList = cityRepository.findAll();
            model.addAttribute("cityList", cityList);

//            int statusId=Integer.parseInt(stat);
            Status status = statusRepository.findById(stat);

            model.addAttribute("status", status);
            result = false;
            return "add_advers";
        }
        SimpleDateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy");
        Date startDate = dateFormat.parse(dateFormat.format(new Date()));
//        if (dataStop == null || startDate.after(dataStop)) {
//            model.addAttribute("dataSotpEr", "Вкажіть коректну дату");
//            Iterable<Category> categoryList = categoryRepository.findAll();
//            model.addAttribute("categoryList", categoryList);
//
//            Iterable<City> cityList = cityRepository.findAll();
//            model.addAttribute("cityList", cityList);
//            Iterable<Status> statusList = statusRepository.findAll();
//            model.addAttribute("statusList", statusList);
//            result = false;
//            return "add_advers";
//        }
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
        }else photo="noimage.png";

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
// установка размера картинки
    private void processImage(int maxWidth, int maxHeight, File src) throws ServletException {
        BufferedImage bi=null;
        try {
            bi=ImageIO.read(src);
        } catch (IOException e) {
            throw new ServletException(e);
        }
        double max=0;
        int size=0;
        int ww=maxWidth-bi.getWidth();
        int hh=maxHeight-bi.getHeight();

        if (ww<0 || hh<0) {
            if(ww<hh) {
                max=maxWidth;
                size=bi.getWidth();
            } else {
                max=maxHeight;
                size=bi.getHeight();
            }
            if(size>0 && size>max) {
                double trans=1.0/(size/max);

                AffineTransform tr=new AffineTransform ();
                tr.scale(trans, trans);
                AffineTransformOp op=new AffineTransformOp(tr, AffineTransformOp.TYPE_BILINEAR);
                Double w=new Double(bi.getWidth()*trans);
                Double h=new Double(bi.getHeight()*trans);
                BufferedImage bi2=new BufferedImage(w.intValue(), h.intValue(), bi.getType());
                op.filter(bi, bi2);
                try {
                    ImageIO.write(bi2, FileUtil.getExtension(src.getName()), src);
                } catch (IOException e) {
                    throw new ServletException(e.getMessage(), e);
                }
            }
        }
    }

    @GetMapping("/found")
    public String myFound(@AuthenticationPrincipal User user,
                          Map<String, Object> model,
                          @PageableDefault(sort = {"id"}, direction = Sort.Direction.DESC) Pageable pageable) {

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
        model.put("url_category", "/advers/found_category");
        model.put("url_city", "/advers/found_city");
        menuLostFound(model);
        pageHed(model);
        model.put("found",false);
        model.put("lost",true);
        model.put("office",true);
        model.put("listAdver", adverList);
            for(Adver a:adverList){
                a.getTextAdver().length();
            }
        model.put("num", adverList.getTotalElements());
        // adverList.getTotalPages()
        return "found";
    }

    @GetMapping("/found_city")
    public String filtrFoundByCity(@AuthenticationPrincipal User user,
                                   @RequestParam(required = false, value = "testCity") City city,
                                   Map<String, Object> model,
                                   @PageableDefault(sort = {"id"}, direction = Sort.Direction.DESC) Pageable pageable) {

        menuLostFound(model);
        pageHed(model);
        model.put("found",false);
        model.put("lost",true);
        model.put("office",true);
        if (city!=null){
            Page<Adver> adverList = adverRepository.findByCategory_IdAndStatus_Id(city.getId(), 2, pageable);
            model.put("listAdver", adverList);
        }
        else {
            Page<Adver> adverList = adverRepository.findByStatus_Id(2, pageable);
            model.put("listAdver", adverList);
            model.put("errCity",true);
        }

        model.put("url", "/advers/found");
        model.put("url_category", "/advers/found_category");
        model.put("url_city", "/advers/found_city");
        if (user != null) {
            model.put("us", 1);
        } else {
            model.put("us", 0);
        }
        return "found";
    }

    @GetMapping("/found_category")
    public String filterFoundByCategory(@AuthenticationPrincipal User user,
                                        @RequestParam(required = false, value = "testCategory") Category category,
                                        Map<String, Object> model,
                                        @PageableDefault(sort = {"id"}, direction = Sort.Direction.DESC) Pageable pageable) {
        if (category!=null){
            Page<Adver> adverList = adverRepository.findByCategory_IdAndStatus_Id(category.getId(), 2, pageable);
            model.put("listAdver", adverList);
        }
        else {
            Page<Adver> adverList = adverRepository.findByStatus_Id(2, pageable);
            model.put("listAdver", adverList);
            model.put("errCategory",true);
        }
        menuLostFound(model);
        pageHed(model);
        model.put("found",false);
        model.put("lost",true);
        model.put("office",true);

        model.put("url", "/advers/found");
        model.put("url_category", "/advers/found_category");
        model.put("url_city", "/advers/found_city");

        if (user != null) {
            model.put("us", 1);
        } else {
            model.put("us", 0);
        }
        return "found";
    }

    @GetMapping("/lost")
    public String myLost(@AuthenticationPrincipal User user,
                         Map<String, Object> model,
                         @PageableDefault(sort = {"id"}, direction = Sort.Direction.DESC) Pageable pageable) {

        Page<Adver> adverList = adverRepository.findByStatus_Id(1, pageable);
        model.put("url", "/advers/lost");
        model.put("url_city", "/advers/lost_city");
        model.put("url_category", "/advers/lost_category");
        model.put("listAdver", adverList);
        menuLostFound(model);
        pageHed(model);
        model.put("found",true);
        model.put("lost",false);
        model.put("office",true);
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

        menuLostFound(model);
        pageHed(model);
        model.put("found",true);
        model.put("lost",false);
        model.put("office",true);
        if (city!=null){
            Page<Adver> adverList = adverRepository.findByCategory_IdAndStatus_Id(city.getId(), 1, pageable);
            model.put("listAdver", adverList);
        }
        else {
            Page<Adver> adverList = adverRepository.findByStatus_Id(1, pageable);
            model.put("listAdver", adverList);
            model.put("errCity",true);
        }

        model.put("url", "/advers/lost");
        model.put("url_city", "/advers/lost_city");
        model.put("url_category", "/advers/lost_category");
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

        menuLostFound(model);
        pageHed(model);
        model.put("found",true);
        model.put("lost",false);
        model.put("office",true);
        if (category!=null){
        Page<Adver> adverList = adverRepository.findByCategory_IdAndStatus_Id(category.getId(), 1, pageable);
            model.put("listAdver", adverList);
        }
        else {
            Page<Adver> adverList = adverRepository.findByStatus_Id(1, pageable);
            model.put("listAdver", adverList);
            model.put("errCategory",true);
        }
        model.put("url", "/advers/lost");
        model.put("url_city", "/advers/lost_city");
        model.put("url_category", "/advers/lost_category");

        if (user != null) {
            model.put("us", 1);
        } else {
            model.put("us", 0);
        }

        return "lost";
    }
    @GetMapping("/menu_lost_found")
    public String menuLostFound( Map<String, Object> model){
        Iterable<Category> categoryList = categoryRepository.findAll();
        model.put("categoryList", categoryList);

        Iterable<City> cityList = cityRepository.findAll();
        model.put("cityList", cityList);
        return "menu_lost_found";
    }
@GetMapping("/hed")
public String pageHed(Map<String, Object> model){
    model.put("index",false);
        return "hed";
}
    @GetMapping("/message")
    public String UserMessage(MessageUserDTO mess) {

        return "message";
    }

    @PostMapping("/messageUser")
    public String ReturnUserId(@RequestParam("user") int id, Model model) {

        model.addAttribute("userId", id);

        return "message";
    }

    @PostMapping("/message")
    public String UserMessagePost(@AuthenticationPrincipal User user,
                                  @Valid MessageUserDTO mess, BindingResult bindingResult,

                                  Model model) {
        if (bindingResult.hasErrors()) {
            Map<String, String> errorMap = UtilsController.getErrors(bindingResult);
            model.mergeAttributes(errorMap);
            model.addAttribute("userId", mess.getToUser());
            model.addAttribute("textMessEr", "Поле повідомленя не може бути пустим.");
            if(mess.getTextMessage().length()>100){
                model.addAttribute("textMessEr", "Поле повідомленя не може бути більше ніж сто символів.");
            }
            return "message";
        } else {
            User userTo = userRepository.findById(mess.getToUser());
            Message message = new Message(mess.getTextMessage(), true, user, userTo);
            messageRepository.save(message);
            model.addAttribute("userTo", userTo.getName());
        }
        model.addAttribute("userMessage", mess.getTextMessage());
        return "send_message";
    }

    @PostMapping("adversDelete")
    public String AdversDelete(@RequestParam("url") String url, @RequestParam("messDel") Integer id) {
adverRepository.delete(adverRepository.findById((int)id));
        return "redirect:"+url;
    }
   // @PostMapping("")
//    @RequestMapping(value = "/my_login", method = RequestMethod.GET)
//    @ResponseBody
//    public String currentUserName(Principal principal) {
//        return principal.getName();
//    }
//    public String myLogin(@RequestParam("username")String user,@RequestParam("password")String password,@RequestParam("url")String url){
//
//        User us=userRepository.findByName(user);
//        if(us!=null&&password.equals(us.getPassword())) {
//        return url;
//        }
//        else{
//            return "registration";
//        }
//    }
}
