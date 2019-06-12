package adver.example.adver.controllers;

import adver.example.adver.models.Rl;
import adver.example.adver.models.User;
import adver.example.adver.repos.UserRepository;
import adver.example.adver.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import javax.validation.Valid;
import java.util.Collections;
import java.util.Map;

/*
 *@autor Hennadiy Voroboiv
 *@email henadiyv@gmail.com
 *04.06.2019
 */
@Controller
public class RegistrationController {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private UserService userService;
    @GetMapping("/login")
    public String login(){
        return "login";
    }
    @GetMapping("/registration")
    public String registration(User user)
    {

        return "registration";
    }
    @PostMapping("/registration")
    public String addNewUser(@Valid User user , BindingResult bindingResult , Model model){
        if(bindingResult.hasErrors()){
Map<String,String> errorMap=UtilsController.getErrors(bindingResult);
model.mergeAttributes(errorMap);
//model.addAttribute("user",user);
            return "registration";
        }

   //  User userDB=userRepository.findByEmail(user.getEmail());
if(!userService.addUser(user)) {
    model.addAttribute("nameError", "User exists");
    return "registration";
}
//user.setRls(Collections.singleton(Rl.USER));
//userRepository.save(user);
return "redirect:/login";//
        }



}
